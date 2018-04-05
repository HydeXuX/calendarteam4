package com.example.mycalendar;

import java.util.Calendar;
//import com.google.firebase.database.Exclude;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.provider.CalendarContract.Events;
import android.support.v4.content.ContextCompat;
import android.database.Cursor;
import android.util.Log;
import android.content.Intent;
import android.provider.CalendarContract.Calendars;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

// CONTAINS ALL LOGIC
public class calendarPresenter extends AppCompatActivity implements View.OnClickListener {
    private userData userData;
    private CalendarData calendarData;
    private calendarView calendarView;
    private static final String DEBUG_TAG = "calendarPresenter";

    EditText editTextStart, editTextEnd, editTextTitle, editTextDescription;
    public Button button;
    Cursor cursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //is this necessary? or do we use 3 "separate" onCreates to show the different views?
        //something like this?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_change);
        button = (Button) findViewById(R.id.SaveEvent);
        button.setOnClickListener(this);
        editTextStart = (EditText) findViewById(R.id.eventStartTime);
        editTextEnd = (EditText) findViewById(R.id.eventEndTime);
        editTextTitle = (EditText) findViewById(R.id.eventName);
        editTextDescription = (EditText) findViewById(R.id.eventNotes);

        }



    /**
     * Method for saving events on the click of a button
     * <p>
     * Method will take in the Title, Description, Start time, end time, and the time zone and save them to a specific
     * time slot.
     *
     * @author Britthl
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * create event function.
             *
             * creates an event taking in the Title, Description, time zone, end time and start time and
             * adding it to the calendar data. Also gives the event and eventID
             *
             * @author Britthl
             */
            case  R.id.button:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    return;
                }

                String stime = editTextStart.getText().toString().trim();
                String etime = editTextStart.getText().toString().trim();
                String title = editTextStart.getText().toString().trim();
                String info = editTextStart.getText().toString().trim();
                int calID = 3;

                ContentResolver cr = getContentResolver();
                ContentValues values = new ContentValues();
                values.put(Events.DTSTART, stime);
                values.put(Events.DTEND, etime);
                values.put(Events.TITLE, title);
                values.put(Events.DESCRIPTION, info);
                values.put(Events.CALENDAR_ID, calID);
                Uri uri = cr.insert(Events.CONTENT_URI, values);
                //Send the Uri to firebase!!

// get the event ID that is the last element in the Uri
                long eventID = Long.parseLong(uri.getLastPathSegment());
//
// ... do something with event ID
//
//


                break;

            /**
            * delete Event
             *
             * deletes an event that has already been created. Linked to the button on event change UI.
             *
             * *@author Britthl
            */
            case R.id.deleteEvent:

               // ContentResolver cr2 = getContentResolver();
               // ContentValues cv2 = new ContentValues();
                // long eventID = 208; // I don't really know how to set the eventID so I just gave it a random value.
                Uri deleteUri = null;

                //deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
                int rows = getContentResolver().delete(deleteUri, null, null);
                Log.i(DEBUG_TAG, "Rows deleted: " +rows);

                }
        }

    /**
     * updates events with new title or whatever the change might be.
     *
     * Allows user to make changes to an already existing event.
     *
     * @authot Britthl
     */
    public void updateEvents() {
        long eventID = 208;
        Uri uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
        Intent intent = new Intent(Intent.ACTION_EDIT)
                .setData(uri)
                .putExtra(Events.TITLE, "My New Title");
        startActivity(intent);

    }

    /**
     * View a specific date on the Calendar.
     *
     * Allows the user to look up a specific date and recieve information from events planned for that day.
     *
     * @author Britthl
     */
    public void viewCalendarDate() {

        long startMillis = 100;

        Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        ContentUris.appendId(builder, startMillis);
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(builder.build());
        startActivity(intent);
    }

    /**
     * View a specific event.
     *
     * Allows the user to bring up information on a certain event.
     *
     * @author Britthl
     */

    public void viewCalendarEvent() {
        long eventID = 208;

        Uri uri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(uri);
        startActivity(intent);

    }



    static Uri asSyncAdapter(Uri uri, String account, String accountType) {
        return uri.buildUpon()
                .appendQueryParameter(android.provider.CalendarContract.CALLER_IS_SYNCADAPTER,"true")
                .appendQueryParameter(Calendars.ACCOUNT_NAME, account)
                .appendQueryParameter(Calendars.ACCOUNT_TYPE, accountType).build();
    }

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

/**
 if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CALENDAR)) {
 Toast.makeText(This, "Calendar permission is needed to save event", Toast.LENGTH_SHORT).show();
 }

 ActivityCompat.requestPermissions(new String[]{Manifest.permission.READ_CALENDAR}, REQUEST_READ_CALENDAR);
 }
 */