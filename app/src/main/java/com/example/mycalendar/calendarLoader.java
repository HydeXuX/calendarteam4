package com.example.mycalendar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class calendarLoader {
    /***
     * The purpose of this class is to be data retrieval, hence "loader"
     */
    private calendarData calendarData;

    List retrieveCalendarData(){
        List data = calendarData.getCalendarData();
        return data;
    }

    void sendDataToFirebase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    }

}
