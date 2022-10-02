package com.example.hotel_booking;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class nav_header extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    TextView name, email, heading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        heading = (TextView) findViewById(R.id.heading);

        heading.setText("GoodMorning");

//        name = (TextView) findViewById(R.id.toolbarName);
//        email = (TextView) findViewById(R.id.toolbarEmail);
//        String ABC = "hellloo";
//        name.setText(ABC);
//
//
//        mAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//
//        DocumentReference docRef = fStore.collection("users").document(mAuth.getCurrentUser().getUid());
//        docRef.get().addOnSuccessListener(documentSnapshot -> {
//           if (documentSnapshot.exists()){
//               String firstName = documentSnapshot.getString("firstname");
//               String useremail = documentSnapshot.getString("email");
//               name.setText("firstName");
//               email.setText("useremail");
//
//           }
//        });
//
//    }
    }
}
