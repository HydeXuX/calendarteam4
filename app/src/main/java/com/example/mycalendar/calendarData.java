package com.example.mycalendar;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

public class calendarData {
    private calendarPresenter CalendarPresenter;
    private long TS;  //what is TS?
    private List<String> calendarData;
    private Stack dailyGoals;


    public List<String> getCalendarData(){
        return calendarData;
    }

    public void setCalendarData(List<String>calendarData){
        this.calendarData = calendarData;
    }

    public Stack changeGoals(){
        return dailyGoals;
    }
}
