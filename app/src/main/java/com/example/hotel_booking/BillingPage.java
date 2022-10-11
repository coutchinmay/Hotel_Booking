package com.example.hotel_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class BillingPage extends AppCompatActivity {

    TextView billHotel, billDate, billUserName, billCost;
    Button btnPay;
    Bundle extras;
    String dateRangeText;
    String selectedHotel;
    FirebaseFirestore fStore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_page);

        billHotel = findViewById(R.id.billHotel);
        billDate = findViewById(R.id.billDate);
        billUserName = findViewById(R.id.billUserName);
        billCost = findViewById(R.id.billCost);
        btnPay = findViewById(R.id.btnPay);
        extras = getIntent().getExtras();
        selectedHotel = extras.getString("selectedHotel");
        dateRangeText = extras.getString("date");
        String test = extras.getString("test");
        fStore = FirebaseFirestore.getInstance();
        String bid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Toast.makeText(this, test+selectedHotel, Toast.LENGTH_SHORT).show();
        billDate.setText(dateRangeText);


        fStore.collection("hotels")
                .whereEqualTo("hotelName", selectedHotel)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                billHotel.setText(document.getData().get("hotelName").toString());
                                billCost.setText(document.getData().get("cost").toString());
                            }
                        }
                    }
                });

        DocumentReference docRef = fStore.collection("users").document(bid);
        docRef.get().addOnSuccessListener(documentSnapshot -> {

                    if (documentSnapshot.exists()) {
                        String firstname = documentSnapshot.getString("firstname");
                        String lastname = documentSnapshot.getString("lastname");
                        billUserName.setText(firstname+lastname);
                        Toast.makeText(this, firstname+lastname, Toast.LENGTH_SHORT).show();
                    }

        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hname = billHotel.getText().toString();
                String bDate = billDate.getText().toString();

                Map<String, Object> booking = new HashMap<>();
                booking.put("hotelName", hname);
                booking.put("date", bDate);

                fStore.collection("bookings").document(bid).set(booking).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        finish();
                    }
                });
            }
        });

    }
}