package com.example.mycalendar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
//import com.google.firebase.database.Exclude;

import android.*;
import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract.Contacts.Data;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.widget.Toast;
import android.provider.CalendarContract.Events;
import android.support.v4.content.ContextCompat;
import android.database.Cursor;
import android.util.Log;
import android.content.Intent;
import android.provider.CalendarContract.Calendars;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

// CONTAINS ALL LOGIC
public class calendarPresenter extends AppCompatActivity implements View.OnClickListener {
    private userData userData;
    private calendarData calendarData;
    private calendarView calendarView;
    private static final String DEBUG_TAG = "calendarPresenter";

    public Button button;
    public Button button2;
    Cursor cursor;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        //is this necessary? or do we use 3 "separate" onCreates to show the different views?
        //something like this?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button = (Button) findViewById(R.id.);
        //button.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.SaveEvent);
        button2.setOnClickListener(this);

        /**switch (type) {
            case ("daily"):
                //do something
                break;
            case ("weekly"):
                //do something
                break;
            case ("monthly"):
                //do something
                break; */
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

            /**case R.id.button:

                //check for permission to read calendar
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                cursor = getContentResolver().query(Events.CONTENT_URI, null, null, null, null);
                while (cursor.moveToNext()) {
                    if (cursor != null) {
                        int id_1 = cursor.getColumnIndex(Events._ID);
                        int id_2 = cursor.getColumnIndex(Events.TITLE);
                        int id_3 = cursor.getColumnIndex(Events.DESCRIPTION);

                        String idValue = cursor.getString(id_1);
                        String titleValue = cursor.getString(id_2);
                        String descriptionValue = cursor.getString(id_3);

                        Toast.makeText(this, idValue + " , " + titleValue + " , " + descriptionValue, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Event is not present.", Toast.LENGTH_SHORT).show();
                    }

                }
                break; */
            /**
             * create event function.
             *
             * creates an event taking in the Title, Description, time zone, end time and start time and
             * adding it to the calendar data. Also gives the event and eventID
             *
             * @author Britthl
             */
            case  R.id.button2:
                Calendar beginTime = Calendar.getInstance();
                beginTime.set(2018, 3, 19, 7, 30);
                Calendar endTime = Calendar.getInstance();
                endTime.set(2018, 3, 19, 8, 30);
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(Events.CONTENT_URI)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(Events.TITLE, "Yoga")
                        .putExtra(Events.DESCRIPTION, "Group class")
                        .putExtra(Events.EVENT_LOCATION, "The gym")
                        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
                        .putExtra(Intent.EXTRA_EMAIL, "rowan@example.com,trevor@example.com");
                startActivity(intent);


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
                long eventID = 200; // I don't really know how to set the eventID so I just gave it a random value.
                Uri deleteUri = null;

                deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
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