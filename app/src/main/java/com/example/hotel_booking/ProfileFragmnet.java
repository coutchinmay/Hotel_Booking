package com.example.hotel_booking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileFragmnet extends Fragment {
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState){
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//
//        DocumentReference docRef = fStore.collection("users").document(fmAuth.getCurrentUser().getUid());
//        docRef.get().addOnSuccessListener(documentSnapshot -> {
//
//
//            Snackbar.make(profileMainContainer, "Loading...", Snackbar.LENGTH_INDEFINITE)
//                    .setBackgroundTint(getResources().getColor(R.color.gray))
//                    .setActionTextColor(getResources().getColor(R.color.white))
//                    .setDuration(500)
//                    .show();
//            if (documentSnapshot.exists()) {
//                String firstName = documentSnapshot.getString("firstName");
//                String lastName = documentSnapshot.getString("lastName");
//                String fullName = firstName + "  " + lastName;
//                String age = documentSnapshot.getString("age");
//                String height = documentSnapshot.getString("height");
//                String weight = documentSnapshot.getString("weight");
//                userName.setText(fullName);
//                userAge.setText(age);
//                userWeight.setText(weight);
//                userHeight.setText(height);
//
//            }
//        });
    }
}
