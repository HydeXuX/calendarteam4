package com.example.mycalendar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String log = "MainActivity";
    private TextView currentDate;
    private LinearLayout dailyLayout;
    private AccountManagement accountManagement;
    private ArrayAdapter adapter;
    private FirebaseAuth mAuth;
    private TextView mStatusView;
    private TextView mDetailView;

    final static private String SHARED_PREF_FILE = "ander.Desktop.CS246.Repositories.calendarteam4.calendarteam4.SHARED_PREF_FILE";
    final static private String IS_SAVED =         "ander.Desktop.CS246.Repositories/calendarteam4.calendarteam4.IS_SAVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDate = findViewById(R.id.dailyGoals);
        dailyLayout = findViewById(R.id.innerScrollable);
        dailyLayout = findViewById(R.id.dailyEvents);

        /****** Must change below to match our project *******
        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1);
        //dailyLayout.setAdapter(adapter);

<<<<<<< HEAD
        /****** Must change below to match our project *******/
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1);
        //dailyLayout.setAdapter(adapter);

        //dailyPresenter = new dailyPresenter();
        //dailyPresenter.register(this);


        //dailyPresenter.loadSharedPref();

        mAuth = FirebaseAuth.getInstance();
        //mStatusView = findViewById(R.id.status);  // where are these and
        //mDetailView = findViewById(R.id.detail);  // what do they do??

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.createAccount).setOnClickListener(this);
    }

    /******************
     * Check if user is currently signed in
     *****************/
    public void onStart() {
        super.onStart();
        //Check if user is signed in (non-null) and update depending on that
        FirebaseUser currentUser = mAuth.getCurrentUser();
        accountManagement.updateUI(currentUser);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                startSignIn();
                break;
            case R.id.createAccount:
                startActivity(new Intent(this, AccountManagement.class));
                break;
        }
    }

    /******************
     *  When our app resumes do the following:
     ****************/
    @Override
    protected void onResume(){
        super.onResume();
        dailyPresenter.register(this);
        Log.i(MainActivity.log, "Changed state to: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        dailyPresenter.unRegister(this);
        saveSharedPref();
        Log.i(MainActivity.log, "Changed state to: onPause");
    }

    private void loadSharedPref() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(IS_SAVED)) {
            /********
             * Here we need to set all of the current textViews and other boxes
             * to the firebaseStorage that we had saved. For example:
             * presenter.setXYZ(XYZ);
             */
            Log.i(MainActivity.log, "Load Preferences is Successful");
        }
        else{
            Log.e(MainActivity.log, "Could not load preferences");
        }
    }

    private void saveSharedPref() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<String>takenData = data.getCalendarData();
        /** I want to have the editor save each string of firebaseStorage in our firebaseStorage.list **/
        //editor.putString(IS_SAVED, takenData(/*first string.. then second..*/)); /** have this as a for-loop to do that**/
        editor.commit();
        if(takenData.equals(null)){
            Log.w(MainActivity.log, "TakenData is null");
        }
    }

    public void refreshData(View view) {
        /** add a request firebaseStorage class to dailyPresenter..........? **/
        // on second thoughts maybe we just need to send the list in here and not refer to dailyPresenter except to save the new list if we changed it or not
        List<String>takenData = data.getCalendarData();
        /** we will call on this function whenever an event is created/deleted **/
        Log.i(MainActivity.log, "Refresh data");
    }

    public void setData(String data) {
        /** loops through like saveSharedPref, will set firebaseStorage to the view **/
        //for (int i = 0; i < numberOfEvents; i++) {
        //    dailyLayout.someHowAccessEachBoxAppropriately(data);
            /** Bro MacBeth Does tv_weatherData.setText(firebaseStorage);, where tv_weatherData is TextView type **/
        }
    }
//}