package com.example.mycalendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class calendarLoader extends AppCompatActivity{

    DatabaseReference databaseEvents;

    /***
     * The purpose of this class is to be data retrieval, hence "loader"
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_change);
        databaseEvents = FirebaseDatabase.getInstance().getReference("event");
    }

}
