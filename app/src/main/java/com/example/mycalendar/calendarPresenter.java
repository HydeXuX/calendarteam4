package com.example.mycalendar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

// CONTAINS ALL LOGIC
public class calendarPresenter extends AppCompatActivity {
    EditText editTextTitle, editTextDescription;
    Button b_pick, a_pick, c_pick;
    int day, month, year, minute, hour;
    int dayFinal, monthFinal, yearFinal, minuteFinal, hourFinal;
    Calendar calendar;
    int currentHour, currentMinute;
    String amPm;
    DatabaseReference ref;
    List<CalendarData> eventList;
    int start, end;
    public static final String EXTRA_MESSAGE = "com.example.mycalendar.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventList = new ArrayList<>();
        setContentView(R.layout.event_change);
        a_pick = (Button) findViewById(R.id.startTimePicker);
        b_pick = (Button) findViewById(R.id.datePicker);
        c_pick = (Button) findViewById(R.id.endTimePicker);
        editTextTitle = (EditText) findViewById(R.id.eventName);
        editTextDescription = (EditText) findViewById(R.id.eventNotes);
        ref = FirebaseDatabase.getInstance().getReference("Calendar Data");


        a_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog((calendarPresenter.this), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        start = hourOfDay;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                            a_pick.setText(String.format("%02d:%02d", hourOfDay - 12, minute) + amPm);
                        } else {
                            amPm = "AM";
                            a_pick.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);
                        }

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();

            }
        });

        c_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog((calendarPresenter.this), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        end = hourOfDay;
                        if (hourOfDay > 12) {
                            amPm = "PM";
                            c_pick.setText(String.format("%02d:%02d", hourOfDay - 12, minute) + amPm);
                        }
                        else if(hourOfDay == 12){
                            amPm = "PM";
                            c_pick.setText(String.format("%02d:%02d", hourOfDay , minute) + amPm);
                        }
                        else {
                            amPm = "AM";
                            c_pick.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);
                        }

                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();

            }
        });

        b_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(calendarPresenter.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        b_pick.setText(month + "/" + dayOfMonth + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for (DataSnapshot eventSnap : dataSnapshot.getChildren()) {
                    CalendarData tempData = eventSnap.getValue(CalendarData.class);
                    eventList.add(tempData);
                    //editTextDescription.setText(eventList.get(0).toString()+ eventList.size());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void toastDisplay(CharSequence text) {
        //Displaying toast message saying successful sent
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        View viw = toast.getView();
        viw.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        TextView te = viw.findViewById(android.R.id.message);
        te.setTextColor(Color.WHITE);
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 150);
        toast.show();

    }

    public void send(View view) {
        editTextTitle = findViewById(R.id.eventName);
        editTextDescription = findViewById(R.id.eventNotes);
        String eventName = editTextTitle.getText().toString();
        String startTime = a_pick.getText().toString();
        String endTme = c_pick.getText().toString();
        String date = b_pick.getText().toString();
        String notes = editTextDescription.getText().toString();
        String id = ref.push().getKey();
        CalendarData event = new CalendarData(eventName, startTime, endTme, date, notes,start, end);
        ref.child(id).setValue(event);
        toastDisplay("Event sent");
        Intent intent = new Intent(calendarPresenter.this, dailyPresenter.class);
        startActivity(intent);
    }
    public List<CalendarData> getEventList(){
        return eventList;
    }
    public void logIn(View view) {
        String message = "something";
        Intent intent = new Intent(this, dailyPresenter.class);
        startActivity(intent);
    }
}