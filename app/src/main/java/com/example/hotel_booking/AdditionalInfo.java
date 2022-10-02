package com.example.hotel_booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AdditionalInfo extends AppCompatActivity {

    EditText firstname, lastname, number;
    Button submit;
    FirebaseFirestore fStore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        number = findViewById(R.id.number);
        submit = findViewById(R.id.submitinfo);
        fStore =FirebaseFirestore.getInstance();
        mAuth =FirebaseAuth.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String unumber = number.getText().toString();
                Toast.makeText(AdditionalInfo.this, fname+lname+unumber, Toast.LENGTH_SHORT).show();

                Map<String, Object> user = new HashMap<>();
                user.put("firstname", fname);
                user.put("lastname", lname);
                user.put("number", unumber);

                fStore.collection("users").document(uid).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        finish();
                    }
                });
            }
        });
    }
}
