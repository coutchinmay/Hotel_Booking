package com.example.hotel_booking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//class Test {
//
//    private String hotelName, city;
//
//    public Test(String hotelName, String city) {
//        this.hotelName = hotelName;
//        this.city = city;
//    }
//
//    public String getHotelName() {
//        return hotelName;
//    }
//
//    public String getCity() {
//        return city;
//    }
//}

public class hotelCards extends AppCompatActivity implements MyAdapter.OnHotelItemListener{

    RecyclerView recyclerView;
    ArrayList<Hotel> hotelArrayList;
    MyAdapter myAdapter;

    Bundle extras;
    String selectedCity;
    FirebaseFirestore fStore;
//    TextView hotel_textView;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_cards);



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fStore = FirebaseFirestore.getInstance();
        hotelArrayList = new ArrayList<Hotel>();
        myAdapter = new  MyAdapter(hotelCards.this,hotelArrayList,hotelCards.this);
        recyclerView.setAdapter(myAdapter);

        EventChangeListner();

//        fStore = FirebaseFirestore.getInstance();
//        hotel_textView = (TextView) findViewById(R.id.hotel_textView);

//        fStore.collection("hotels").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    List<String> list = new ArrayList<>();
//                    HashMap<String, Test> hotelDetails = new HashMap<String, Test>();
//
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        list.add(document.getId());
//                        hotelDetails.put(document.getId().toString(), new Test(document.getData().get("hotelName").toString(), document.getData().get("city").toString()));
//                    }
//                    for (Test obj: hotelDetails.values()) {
////                        Toast.makeText(hotelCards.this, obj.getCity(), Toast.LENGTH_SHORT).show();
//                        if (selectedCity.equals(obj.getCity())){
//                            Toast.makeText(hotelCards.this, "you are inside IF", Toast.LENGTH_SHORT).show();
//                            hotel_textView.setText(obj.getHotelName());
//                        }
//                    }
//                }
//            }
//
//
//        });


        extras = getIntent().getExtras();
        selectedCity = extras.getString("citySelected");
        Toast.makeText(this, selectedCity, Toast.LENGTH_SHORT).show();
    }

    private void EventChangeListner() {

        fStore.collection("hotels").orderBy("hotelName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        Toast.makeText(hotelCards.this, value.toString(), Toast.LENGTH_SHORT).show();
                        if (error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                if ( dc.getDocument().getData().get("city").toString().equals(selectedCity)){
                                    hotelArrayList.add(dc.getDocument().toObject(Hotel.class));
                                }

                            }
                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });
    }

    @Override
    public void onHotelClick(int position) {
            Intent i = new Intent(getApplicationContext(), DatePicker.class);
            startActivity(i);
    }
}