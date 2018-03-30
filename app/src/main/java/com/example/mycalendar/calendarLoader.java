package com.example.mycalendar;

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
        FirebaseDatabase database = FIrebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    }

}
