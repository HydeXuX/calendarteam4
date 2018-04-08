package com.example.mycalendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Text;

public class dailyPresenter extends Activity {
    DatabaseReference databaseReference;
    Button FIVEam, SIXam, SEVENam, EIGHTam, NINEam, TENam, ELEVENam, TWELVEam, ONEpm, TWOpm, THREEpm,
            FOURpm, FIVEpm, SIXpm, SEVENpm, EIGHTpm, NINEpm, TENpm, ELEVENpm, TWELVEpm, ONEam, TWOam,
            THREEam, FOURam;
    List<CalendarData> eventList;
    List<Button> myFkingButtons;
    final static private String SHARED_PREF_FILE = "com.example.mycalendar.userData.SHARED_PREF_FILE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_layout);
        eventList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Calendar Data");
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //TextView textView = findViewById(R.id.currentDate);
        //textView.setText(currentDateTimeString);
        myFkingButtons = new ArrayList<>();
        // Get UI elements
        FIVEam = findViewById(R.id.daily5am);
        SIXam = findViewById(R.id.daily6am);
        SEVENam = findViewById(R.id.daily7am);
        EIGHTam = findViewById(R.id.daily8am);
        NINEam = findViewById(R.id.daily9am);
        TENam = findViewById(R.id.daily10am);
        ELEVENam = findViewById(R.id.daily11am);
        TWELVEam = findViewById(R.id.daily12am);
        ONEpm = findViewById(R.id.daily1pm);
        TWOpm = findViewById(R.id.daily2pm);
        THREEpm = findViewById(R.id.daily3pm);
        FOURpm = findViewById(R.id.daily4pm);
        FIVEpm = findViewById(R.id.daily5pm);
        SIXpm = findViewById(R.id.daily6pm);
        SEVENpm = findViewById(R.id.daily7pm);
        EIGHTpm = findViewById(R.id.daily8pm);
        NINEpm = findViewById(R.id.daily9pm);
        TENpm = findViewById(R.id.daily10pm);
        ELEVENpm = findViewById(R.id.daily11pm);
        TWELVEpm = findViewById(R.id.daily12pm);
        ONEam = findViewById(R.id.daily1am);
        TWOam = findViewById(R.id.daily2am);
        THREEam = findViewById(R.id.daily3am);
        FOURam = findViewById(R.id.daily4am);
        myFkingButtons.add(TWELVEam);
        myFkingButtons.add(ONEam);
        myFkingButtons.add(TWOam);
        myFkingButtons.add(THREEam);
        myFkingButtons.add(FOURam);
        myFkingButtons.add(FIVEam);
        myFkingButtons.add(SIXam);
        myFkingButtons.add(SEVENam);
        myFkingButtons.add(EIGHTam);
        myFkingButtons.add(NINEam);
        myFkingButtons.add(TENam);
        myFkingButtons.add(ELEVENam);
        myFkingButtons.add(TWELVEpm);
        myFkingButtons.add(ONEpm);
        myFkingButtons.add(TWOpm);
        myFkingButtons.add(THREEpm);
        myFkingButtons.add(FOURpm);
        myFkingButtons.add(FIVEpm);
        myFkingButtons.add(SIXpm);
        myFkingButtons.add(SEVENpm);
        myFkingButtons.add(EIGHTpm);
        myFkingButtons.add(NINEpm);
        myFkingButtons.add(TENpm);
        myFkingButtons.add(ELEVENpm);


        //Our data is in a list of CalendarData objects
        // Im going to take the data from our calendarData and use it
        //    to populate the daily view by making the buttons change
        //    types to be visible and then to change the text as well

    }

    @Override
    public void onStart() {

        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot eventSnap : dataSnapshot.getChildren()) {
                    CalendarData tempData = eventSnap.getValue(CalendarData.class);
                    eventList.add(tempData);
                    populateDaily(eventList);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }
    public void populateDaily(List<CalendarData> eventList) {
        for (int i = 0; i < eventList.size(); i++) {
            CalendarData thatEvent = eventList.get(i);
            for(int j = thatEvent.getStartInt();j <=thatEvent.getEndInt();j++){
                myFkingButtons.get(j).setText(thatEvent.getEventName());

            }
        }
    }

    public void goToEventChange(View view){
        Intent intent = new Intent(dailyPresenter.this, calendarPresenter.class);
        startActivity(intent);
    }

    public void goToMonthly(View view){
        Intent intent = new Intent(dailyPresenter.this, monthlyPresenter.class);
        startActivity(intent);
    }

    public void logOut(View view) {
        Intent intent = new Intent(dailyPresenter.this, Settings.class);
        startActivity(intent);
    }

}