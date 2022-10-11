package com.example.hotel_booking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;



public class hotelCards extends AppCompatActivity implements MyAdapter.OnHotelItemListener{
    RecyclerView recyclerView;
    ArrayList<Hotel> hotelArrayList;
    MyAdapter myAdapter;
    Bundle extras;
    String selectedCity;
    FirebaseFirestore fStore;
    String hotelName;


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


        extras = getIntent().getExtras();
        selectedCity = extras.getString("citySelected");
    }

    private void EventChangeListner() {

        fStore.collection("hotels").orderBy("hotelName", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
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
            Intent i = new Intent(getApplicationContext(), hotelInfo.class);
        Hotel vd = hotelArrayList.get(position);
        hotelName = vd.getHotelName();
        i.putExtra("hotelName", hotelName);
            startActivity(i);

    }
}