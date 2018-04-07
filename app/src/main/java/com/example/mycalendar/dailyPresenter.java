package com.example.mycalendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Text;

public class dailyPresenter extends Activity {
    private userData user;
    private calendarPresenter calendarPresenter;
    DatabaseReference databaseReference;
    Button FIVEam, SIXam, SEVENam, EIGHTam, NINEam, TENam, ELEVENam, TWELVEam, ONEpm, TWOpm, THREEpm,
            FOURpm, FIVEpm, SIXpm, SEVENpm, EIGHTpm, NINEpm, TENpm, ELEVENpm, TWELVEpm, ONEam, TWOam,
            THREEam, FOURam;
    final static private String SHARED_PREF_FILE = "com.example.mycalendar.userData.SHARED_PREF_FILE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_layout);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> data = (Map<String, String>) dataSnapshot.getValue();
                Toast.makeText(getApplicationContext(), "Event Title: "+data.get("EventName"),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
        // we aren't showing the current date anymore. This was giving errors since it was null
        /*String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        TextView textView = findViewById(R.id.currentDate);
        textView.setText(currentDateTimeString);*/

        // Get UI elements
        FIVEam    = findViewById(R.id.daily5am);
        SIXam     = findViewById(R.id.daily6am);
        SEVENam   = findViewById(R.id.daily7am);
        EIGHTam   = findViewById(R.id.daily8am);
        NINEam    = findViewById(R.id.daily9am);
        TENam     = findViewById(R.id.daily10am);
        ELEVENam  = findViewById(R.id.daily11am);
        TWELVEam  = findViewById(R.id.daily12am);
        ONEpm     = findViewById(R.id.daily1pm);
        TWOpm     = findViewById(R.id.daily2pm);
        THREEpm   = findViewById(R.id.daily3pm);
        FOURpm    = findViewById(R.id.daily4pm);
        FIVEpm    = findViewById(R.id.daily5pm);
        SIXpm     = findViewById(R.id.daily6pm);
        SEVENpm   = findViewById(R.id.daily7pm);
        EIGHTpm   = findViewById(R.id.daily8pm);
        NINEpm    = findViewById(R.id.daily9pm);
        TENpm     = findViewById(R.id.daily10pm);
        ELEVENpm  = findViewById(R.id.daily11pm);
        TWELVEpm  = findViewById(R.id.daily12pm);
        ONEam     = findViewById(R.id.daily1am);
        TWOam     = findViewById(R.id.daily2am);
        THREEam   = findViewById(R.id.daily3am);
        FOURam    = findViewById(R.id.daily4am);

        //Our data is in a list of CalendarData objects
        // Im going to take the data from our calendarData and use it
        //    to populate the daily view by making the buttons change
        //    types to be visible and then to change the text as well
        populateDaily();
    }

    public void populateDaily(){
        /*List list = calendarPresenter.getList();
        for(int i = 0; i < list.size(); i++){
            String date      = list.get(i).getDate;


            String notes     = list.get(i).getNotes;
            String eventName = list.get(i).getEventName;
            String startTime = list.get(i).getStartTime;
            String endTime   = list.get(i).getEndTime;



        }*/
    }

    public void saveSharedPref(){
        SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putInt(SHARED_PREF_FILE, R.id.userLogIn);
        editor.commit();
    }

    public void loadSharedPref(){
        SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPref.contains(SHARED_PREF_FILE)){
            String username = sharedPref.getString(SHARED_PREF_FILE, "Username here");
            user.setUsername(username);
        }
    }
}