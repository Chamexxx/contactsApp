package com.upn.contactsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.contactsapp.entities.Contact;

import java.util.List;
import java.util.UUID;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);


        Button btn = findViewById(R.id.btnCreateOnFirebase);
        EditText etName = findViewById(R.id.editTe);

        btn.setOnClickListener(v -> {
            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference contactsRef = database.getReference("N00300141");
            DatabaseReference contacts = contactsRef.child("contacts");

            contactsRef.addValueEventListener(new ValueEventListener(){

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child : snapshot.getChildren()){
                        Contact c = child.getValue(Contact.class);
                        contacts.add(c);
                        Log.i("MAIN_APP", c.uuid);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


//            Contact c1 = new Contact("Luis", "12345678");
//            c1.uuid = UUID.randomUUID().toString();
//            Contact c3 = new Contact("Miguel", "123456");
//            c2.uuid = UUID.randomUUID().toString();

//            String name = etName.getText().toString();
//            c3.uuid =UUID.randomUUID().toString();
//            table.child(c3.uuid).setValue(c3);

//            etName.setText("");

        });

    }
}