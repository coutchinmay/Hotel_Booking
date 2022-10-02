package com.example.hotel_booking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragmnet extends Fragment {
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    EditText firstName,lastName, email, number;
    Button profileEditButton, profileSaveButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState) {

        View v = inflater.inflate(R.layout.fragment_profile, null);
         firstName = (EditText) v.findViewById(R.id.firstname);
         lastName = (EditText) v.findViewById(R.id.lastname);
         email = (EditText) v.findViewById(R.id.email);
         number = (EditText) v.findViewById(R.id.number);
        profileEditButton = (Button) v.findViewById(R.id.profileEditButton);
        profileSaveButton = (Button) v.findViewById(R.id.profileSaveButton);
        Toast.makeText(getActivity(), getData(), Toast.LENGTH_SHORT).show();

        profileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName.setEnabled(true);
                lastName.setEnabled(true);
                email.setEnabled(true);
                number.setEnabled(true);
                profileSaveButton.setVisibility(View.VISIBLE);
                profileEditButton.setVisibility(View.GONE);
            }
        });

        profileSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference docRef = fStore.collection("users").document(mAuth.getCurrentUser().getUid());
                String ufName, ulName, uEmail, uNumber;
                ufName = firstName.getText().toString();
                ulName = lastName.getText().toString();
                uEmail = email.getText().toString();
                uNumber = number.getText().toString();
                docRef.update("firstname", ufName,"lastname", ulName,"email", uEmail, "number",uNumber).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        firstName.setEnabled(false);
                        lastName.setEnabled(false);
                        email.setEnabled(false);
                        number.setEnabled(false);
                        profileSaveButton.setVisibility(View.GONE);
                        profileEditButton.setVisibility(View.VISIBLE);
                    }
                });
            }
        });


        return v;


    }

    public String getData(){
        final String[] myVar = new String[1];
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        DocumentReference docRef = fStore.collection("users").document(mAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(documentSnapshot -> {

            if (documentSnapshot.exists()) {
                String firstname = documentSnapshot.getString("firstname");
                String lastname = documentSnapshot.getString("lastname");
                String pnumber = documentSnapshot.getString(    "number");
                String pemail = mAuth.getCurrentUser().getEmail();

                firstName.setText(firstname);
                lastName.setText(lastname);
                email.setText(pemail);
                number.setText(pnumber);

            }else {
            }
        });
        return "Hello!!";
    }

}