package com.example.mycalendar;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

public class CalendarData {
    private calendarPresenter CalendarPresenter;

    String eventName;
    String startTime;
    String endTime;
    String date;
    String notes;
    String dailyGoals;

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
