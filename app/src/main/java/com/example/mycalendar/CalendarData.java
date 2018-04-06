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
    String dailyGoals;
    List<CalendarData> list;    //Is this what we need? I'm confused because we'll be getting a bunch of eventNames, startTimes etc back
                                //so how can we store all of the data, should each of them be made "List<String> eventName"?
    //firebasehelper class @weatherstation
    public CalendarData(){
    }

    public CalendarData(String eventName, String startTime, String endTime, String date, String notes, String dailyGoals){
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.notes = notes;
        this.dailyGoals = dailyGoals;
    }

    public List getList(){
        return list;
    }

    public void setList(List newList){
        list = newList;
    }

    public String getEventName(){
        return eventName;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getDate(){
        return date;
    }

    public String getNotes(){
        return notes;
    }

    public String getDailyGoals(){
        return dailyGoals;
    }
}
