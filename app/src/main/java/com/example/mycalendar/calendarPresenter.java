package com.example.mycalendar;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.google.firebase.database.Exclude;
import android.provider.ContactsContract.Contacts.Data;
import android.widget.TextView;

/**
 * Created by britthunterlefevre on 3/7/18.
 */

public class calendarPresenter {
    private userData ud;

    public calendarPresenter() {

    }
    public String getUsername() {
        return ud.getUsername();
    }

    public void setUsername(String username) {
        ud.setUsername(username);
    }

    public String getPassword() {
        return ud.getPassword();
    }

    public void setPassword(String password) {
        ud.setPassword(password);
    }


}
