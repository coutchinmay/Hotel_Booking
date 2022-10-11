package com.example.hotel_booking;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class hotelInfo extends AppCompatActivity {

    TextView dateRangeText, address, hotelInfo, checkIn, checkOut, cost;
    Button calender, book;
    Bundle extras;
    String selectedHotel;
    String nameHotel;
    String datePicker;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        dateRangeText = findViewById(R.id.tvDate);
        calender = findViewById(R.id.opencalendar);
        address = findViewById(R.id.tvAddress);
        hotelInfo = findViewById(R.id.tvHotelInfo);
        checkIn = findViewById(R.id.tvCheckin);
        checkOut = findViewById(R.id.tvCheckout);
        cost = findViewById(R.id.tvCost);
        book = findViewById(R.id.booking);
        extras = getIntent().getExtras();
        selectedHotel = extras.getString("hotelName");


        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().
                setSelection(Pair.create(MaterialDatePicker.thisMonthInUtcMilliseconds(), MaterialDatePicker.todayInUtcMilliseconds())).build();

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(), "Tag_picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        dateRangeText.setText(materialDatePicker.getHeaderText());
                        Toast.makeText(hotelInfo.this, materialDatePicker.getHeaderText(), Toast.LENGTH_SHORT).show();
                        datePicker = materialDatePicker.getHeaderText();
                    }
                });
            }

        });
        fStore = FirebaseFirestore.getInstance();

        fStore.collection("hotels")
                .whereEqualTo("hotelName", selectedHotel)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                address.setText(document.getData().get("address").toString());
                                hotelInfo.setText(document.getData().get("hotelInfo").toString());
                                checkIn.setText(document.getData().get("check-in").toString());
                                checkOut.setText(document.getData().get("check-out").toString());
                                cost.setText(document.getData().get("cost").toString());
                                nameHotel = document.getData().get("hotelName").toString();
                            }
                        }
                    }
                });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BillingPage.class);
                i.putExtra("selectedHotel", nameHotel);
                i.putExtra("date",datePicker);
                i.putExtra("test", "banerlayeuncallnhikela");
                startActivity(i);
            }
        });


    }
}