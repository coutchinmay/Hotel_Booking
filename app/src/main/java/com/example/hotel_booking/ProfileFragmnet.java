package com.example.hotel_booking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragmnet extends Fragment {
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    EditText some;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState) {

        View v = inflater.inflate(R.layout.fragment_profile, null);
         some = (EditText) v.findViewById(R.id.email);
        Toast.makeText(getActivity(), getData(), Toast.LENGTH_SHORT).show();

        return v;
    }

    public String getData(){
        final String[] myVar = new String[1];
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        DocumentReference docRef = fStore.collection("users").document(mAuth.getCurrentUser().getUid());
        Toast.makeText(getActivity(), "callllll"+docRef, Toast.LENGTH_SHORT).show();
        docRef.get().addOnSuccessListener(documentSnapshot -> {
//            Toast.makeText(getActivity(),"inside doc ref", Toast.LENGTH_SHORT).show();

            if (documentSnapshot.exists()) {
                String email = documentSnapshot.getString("email");
                Toast.makeText(getActivity(), email, Toast.LENGTH_LONG).show();
                myVar[0] = email;
                some.setText(email);
            }else {
                String email = documentSnapshot.getString("email");
                Toast.makeText(getActivity(), "no spanshot"+email, Toast.LENGTH_SHORT).show();
            }
        });
        return "something";
    }
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        Toast.makeText(getActivity(), some.getText().toString(), Toast.LENGTH_SHORT).show();
//    }
}