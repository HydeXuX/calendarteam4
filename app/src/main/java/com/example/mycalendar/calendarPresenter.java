package com.example.mycalendar;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.firebase.database.Exclude;
import android.provider.ContactsContract.Contacts.Data;
import android.widget.TextView;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

// CONTAINS ALL LOGIC
public class calendarPresenter {
    private userData userData;
    private calendarData calendarData;
    private calendarView calendarView;

    public String getUsername() {
        return userData.getUsername();
    }

    public void setUsername(String username) {
        userData.setUsername(username);
    }

    public String getPassword() {
        return userData.getPassword();
    }

    public void setPassword(String password) {
        userData.setPassword(password);
    }

    public void onCreate(String type){
        //is this necessary? or do we use 3 "separate" onCreates to show the different views?
        //something like this?
        switch (type){
            case("daily"):
                //do something
                break;
            case("weekly"):
                //do something
                break;
            case("monthly"):
                //do something
                break;
        }
    }

    public void displayCalendar(){
        // how would this work? do we call onCreate instead of this function? or have onCreate load the data after
        // we switch the view with this function?
    }

    public void addEvent() {

    }

    // WE NEED SAVE EVENT (AND FOR OUR OTHER VARIABLES)
    // TO ACTUALLY HAVE A FUNCTION WHICH SAVES THEM
    public void saveEvent() {
    }

    public void deleteEvent(){
    }

    public void changeGoals(){
    }

    public void saveGoals(){
    }

    public void changeDay(){
    }

    public void changeNotes(){
    }

    public void saveNotes(){
    }
}
