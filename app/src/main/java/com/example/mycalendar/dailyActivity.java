package com.example.mycalendar;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

public class dailyActivity extends Activity {

    final static private String SHARED_PREF_FILE = "com.example.mycalendar.User.SHARED_PREF_FILE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveSharedPref(){
        SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(SHARED_PREF_FILE, R.id.userLogIn);
        editor.commit();
    }

    public void loadSharedPref(){
        SharedPreferences sharedPref = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPref.contains(SHARED_PREF_FILE)){
            String username = sharedPref.getString;
        }
    }
}
