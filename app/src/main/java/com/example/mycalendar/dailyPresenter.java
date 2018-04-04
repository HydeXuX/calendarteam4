package com.example.mycalendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;

import org.w3c.dom.Text;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

public class dailyPresenter extends Activity {

    private userData user;

    final static private String SHARED_PREF_FILE = "com.example.mycalendar.userData.SHARED_PREF_FILE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_layout);

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        TextView textView = (TextView)findViewById(R.id.currentDate);
        textView.setText(currentDateTimeString);
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

    // called when user taps the "weekly" button
    //fun changeToWeekly(view: View) {
        //Do something in response to button
    //}

    public void register(MainActivity mainActivity) {
        //REGISTER HERE
    }

    public void unRegister(MainActivity mainActivity) {
        //UNREGISTER HERE
    }
}
