package com.example.mycalendar;

import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;


/**
 * Created by britthunterlefevre on 3/7/18.
 */

public class User {
    private String username;
    private String password;

    final static private String SHARED_PREF_FILE = "com.example.mycalendar.User.SHARED_PREF_FILE";


    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }



    public void loadSharedPref(){

    }


}
