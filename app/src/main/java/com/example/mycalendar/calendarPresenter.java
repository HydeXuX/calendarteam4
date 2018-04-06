package com.example.mycalendar;

import java.util.Calendar;
//import com.google.firebase.database.Exclude;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.provider.CalendarContract.Events;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.content.Intent;
import android.provider.CalendarContract.Calendars;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONException;
import org.json.JSONObject;

// CONTAINS ALL LOGIC
public class calendarPresenter extends AppCompatActivity implements View.OnClickListener {
    private userData userData;
    private CalendarData calendarData;
    private static final String DEBUG_TAG = "calendarPresenter";

    View view;
    EditText editTextStart, editTextEnd, editTextDate, editTextTitle, editTextDescription;
    public Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_change);
        button = (Button) findViewById(R.id.SaveEvent);
        button.setOnClickListener(this);
        editTextStart = (EditText) findViewById(R.id.eventStartTime);
        editTextEnd = (EditText) findViewById(R.id.eventEndTime);
        editTextTitle = (EditText) findViewById(R.id.eventName);
        editTextDescription = (EditText) findViewById(R.id.eventNotes);
        };

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
            case  R.id.SaveEvent:
                send(view);
                break;
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

    public void toastDisplay(CharSequence text){
        //Displaying toast message saying successful sent
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        View view = toast.getView();
        view.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        TextView te = view.findViewById(android.R.id.message);
        te.setTextColor(Color.WHITE);
        toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 150);
        toast.show();

    }

    public void send(View view){
        editTextTitle = findViewById(R.id.eventName);
        editTextStart = findViewById(R.id.eventStartTime);
        editTextEnd = findViewById(R.id.eventEndTime);
        editTextDescription = findViewById(R.id.eventNotes);
        editTextDate = findViewById(R.id.eventDate);
        JSONObject json = new JSONObject();

        try {
            json.put("EventName",editTextTitle.getText().toString());
            json.put("StartTime",editTextStart.getText().toString());
            json.put("StartEnd",editTextEnd.getText().toString());
            json.put("Date",editTextDate.getText().toString());
            json.put("Notes",editTextDescription.getText().toString());
            //toastDisplay("Information gathered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref =database.getReference("Json");
        ref.setValue(json.toString());
        toastDisplay("Event sent");
        //Send the Uri to firebase!!
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