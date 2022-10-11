package com.example.hotel_booking;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookingFragmnet extends Fragment {
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;
    TextView hotel, cost, datePicker;
    Button deleteHotel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancesState) {
        View v = inflater.inflate(R.layout.fragment_booking, null);
        hotel = (TextView) v.findViewById(R.id.hotel);
        cost = (TextView) v.findViewById(R.id.cost);
        datePicker = (TextView) v.findViewById(R.id.date);
        deleteHotel = (Button) v.findViewById(R.id.deletehotel);
        String bid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getData();


//        deleteHotel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseFirestore.getInstance().collection("bookings").document(bid).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//
//                        }
//                    }
//                });
//            }
//        });
        return v;
    }

    public String getData() {
        final String[] myVar = new String[1];
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        DocumentReference docRef = fStore.collection("bookings").document(mAuth.getCurrentUser().getUid());
        docRef.get().addOnSuccessListener(documentSnapshot -> {

            if (documentSnapshot.exists()) {
                String hotelName = documentSnapshot.getString("hotelName");
                String date = documentSnapshot.getString("date");

                hotel.setText(hotelName);
                datePicker.setText(date);
            }


        });
        return "hello!";
    }





}

