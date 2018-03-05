package com.example.mycalendar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ander on 2/28/2018.
 */

public class data {

    // How to get an instance of our database using
    // get instance() and reference location to write to.
    // Can save objects this way as well
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("message");

    public void saveData() {
        myRef.setValue("Hello, World!");
    }

}
