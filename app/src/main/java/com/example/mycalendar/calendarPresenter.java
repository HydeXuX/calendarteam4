package com.example.mycalendar;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
    EditText editTextStart, editTextEnd, editTextTitle, editTextDescription, editTextDate;
    DatabaseReference ref;
    List<CalendarData> eventList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventList = new ArrayList<>();
        setContentView(R.layout.event_change);
        editTextStart = (EditText) findViewById(R.id.eventStartTime);
        editTextEnd = (EditText) findViewById(R.id.eventEndTime);
        editTextTitle = (EditText) findViewById(R.id.eventName);
        editTextDescription = (EditText) findViewById(R.id.eventNotes);
        ref = FirebaseDatabase.getInstance().getReference("Calendar Data");
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
        editTextStart = findViewById(R.id.eventStartTime);
        editTextEnd = findViewById(R.id.eventEndTime);
        editTextDescription = findViewById(R.id.eventNotes);
        editTextDate = findViewById(R.id.eventDate);
        String eventName = editTextTitle.getText().toString();
        String startTime = editTextStart.getText().toString();
        String endTme = editTextEnd.getText().toString();
        String date = editTextDate.getText().toString();
        String notes = editTextDescription.getText().toString();
        String id = ref.push().getKey();
        CalendarData event = new CalendarData(eventName, startTime, endTme, date, notes);
        ref.child(id).setValue(event);
        toastDisplay("Event sent");
    }
}