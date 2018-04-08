package com.example.mycalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class monthlyPresenter extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monthly_layout);

        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView datePicker = findViewById(R.id.monthlyView);
        datePicker.init(today, nextYear.getTime()).withSelectedDate(today);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);

                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);
                String selectedDate = "" + calSelected.get(Calendar.DAY_OF_MONTH)
                        + " " + (calSelected.get(Calendar.MONTH) + 1)
                        + " " + calSelected.get(Calendar.YEAR);
                        //selectedDate is what we show in the toast.
                //how to show what happened to selected date.. for this example is a toast.
                Toast.makeText(monthlyPresenter.this, selectedDate, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(monthlyPresenter.this, dailyPresenter.class);
                startActivity(intent);
            }

            @Override
            public void onDateUnselected(Date date) {
            }
        });
    }
}
