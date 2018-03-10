package com.example.mycalendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.TextView;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private calendarData data;
    private dailyActivity dailyPresenter;
    private TextView currentDate;
    private GridLayout dailyLayout;
    private ArrayAdapter adapter;

    final static private String SHARED_PREF_FILE = "ander.Desktop.CS246.Repositories.calendarteam4.calendarteam4.SHARED_PREF_FILE";
    final static private String IS_SAVED = "ander.Desktop.CS246.Repositories/calendarteam4.calendarteam4.IS_SAVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*** this assumes we have an id textView and gridLayout in our xml ***/
        currentDate = (TextView) findViewById(R.id.textView);
        dailyLayout = (GridLayout) findViewById(R.id.gridLayout);

        /****** Must change below to match our project *******/
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1)
        dailyLayout.setAdapter(adapter);

        dailyPresenter = new dailyActivity();
        dailyPresenter.register(this);

        dailyPresenter.loadSharedPref();
    }

    /******************
     *  When our app resumes do the following:
     ****************/
    @Override
    protected void onResume(){
        super.onResume();
        dailyPresenter.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dailyPresenter.unregister();
        saveSharedPref();
    }

    private void loadSharedPref() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(IS_SAVED)) {
            /********
             * Here we need to set all of the current textViews and other boxes
             * to the data that we had saved. For example:
             * presenter.setXYZ(XYZ);
             */

        }
    }

    private void saveSharedPref() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /** I want to have the editor save each string of data in our data.list **/
        editor.putString(IS_SAVED, data.getList()); /** have this as a for-loop to do that**/
        editor.commit();
    }

    public void refreshData(View view) {
        /** add a request data class to dailyPresenter **/
        dailyPresenter.requestData();
        /** we will call on this function whenever an event is created/deleted **/
    }

    public void setData(String data) {
        /** loops through like saveSharedPref, will set data to the view **/
        for (int i = 0; i < numberOfEvents; i++) {
            dailyLayout.someHowAccessEachBoxAppropriately(data);
            /** Bro MacBeth Does tv_weatherData.setText(data);, where tv_weatherData is TextView type **/
        }
    }
}