package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ivo on 11/15/2017.
 */

public class Challenges {

    Activity activity;
    String today_day = ""; // Variable used to store today's date

    NavigationView nv;
    MenuItem mi;

    // Get Home's context
    Challenges(Activity a){
        activity = a;
        getDays();
    }

    protected void getDays(){

        Calendar sCalendar = Calendar.getInstance();
        String today_day = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_daily_date", Context.MODE_PRIVATE);
        String stored_daily_day = sharedPref.getString("saved_daily_date", today_day);

        Toast.makeText(activity.getApplicationContext(), "" + today_day + "" + stored_daily_day, Toast.LENGTH_SHORT).show();
        verifyDailyDate(today_day, stored_daily_day);
    }

    protected void verifyDailyDate(String today_day, String stored_daily_day){
        if(stored_daily_day.equals(today_day)) {
            // Keep challenge
            saveDate(today_day);
        }else{
            //Change daily challenge
            nv = (NavigationView) activity.findViewById(R.id.navigation);
            nv.getMenu().findItem(R.id.account).setTitle("CHANGE CHALLENGE!");
        }
    }

    protected void saveDate(String today_day){
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_daily_date", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("saved_daily_date", today_day);
        editor.commit();
    }
}
