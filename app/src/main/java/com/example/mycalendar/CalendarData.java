package com.example.mycalendar;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class CalendarData {
    private calendarPresenter CalendarPresenter;
    private DatabaseReference databaseReference;

    String eventName;
    String startTime;
    String endTime;
    String date;
    String notes;
    int start;
    int end;
    //firebasehelper class @weatherstation
    public CalendarData(){
    }

    public CalendarData(String eventName, String startTime, String endTime, String date, String notes, int start, int end){
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.notes = notes;
        this.start = start;
        this.end = end;
    }

    public String getEventName(){
        return eventName;
    }
    public void setEventName(String newEvent) { eventName = newEvent; }

    public String getStartTime(){
        return startTime;
    }
    public void setStartTime(String newStartTime) { startTime = newStartTime;}

    public String getEndTime(){return endTime;}
    public void setEndTime(String newEndTime){ endTime = newEndTime;}

    public String getDate(){return date;}
    public void setDate(String newDate){date = newDate;}

    public String getNotes(){return notes;}
    public void setNotes(String newNotes){notes = newNotes;}

    public void setStart(int start) {this.start = start;}
    public int getStartInt(){return start;}

    public void setEnd(int end) {this.end = end;}
    public int getEndInt() {return end;}
}