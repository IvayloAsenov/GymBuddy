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
import java.util.Random;

/**
 * Created by Ivo on 11/15/2017.
 */

public class Challenges implements DailyChallenges{

    Activity activity;
    String today_day = ""; // Variable used to store today's date

    NavigationView nv;
    MenuItem mi;

    Random rand;

    // Get Home's context
    Challenges(Activity a){
        activity = a;
        setDailyChallenge();
        getDays();
    }

    // Get current day
    protected void getDays(){

        Calendar sCalendar = Calendar.getInstance();
        String today_day = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        String stored_daily_day = sharedPref.getString("saved_daily_date", today_day);
        int weekly_date = sharedPref.getInt("saved_weekly_counter", 6);

        verifyDailyDate(today_day, stored_daily_day);
    }

    // Check if it has been a day and changes the challenges if so
    protected void verifyDailyDate(String today_day, String stored_daily_day){
        if(stored_daily_day.equals(today_day)) {
            // Keep challenge
           // saveDate(today_day);
        }else{
            //Change daily challenge
            saveDate(today_day, 0);
            changeDailyChallenge();
        }
    }

    // Check if it has been a week and changes the challenges if so
    protected void verifyWeeklyDate(String today_day, String stored_weekly_date){


    }

    // Save the date to shared preference
    protected void saveDate(String today_day, int w_d){
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(w_d == 0){
            editor.putString("saved_daily_date", today_day).apply();
        }else{
            editor.putString("saved_weekly_date", today_day).apply();
        }
    }


    // Change daily challenge to the next daily challenge in DailyChallenge Interface
    protected void changeDailyChallenge(){
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        int stored_daily_challenge = sharedPref.getInt("saved_daily_challenge", 0);

        int next_daily_challenge = stored_daily_challenge + 1;

        if(next_daily_challenge >= len)
            next_daily_challenge = 0;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("saved_daily_challenge", next_daily_challenge).apply();

        nv = (NavigationView) activity.findViewById(R.id.navigation);
        nv.getMenu().findItem(R.id.account).setTitle(daily_challenges[next_daily_challenge]);
    }

    // Sets the daily challenge when app is ran
    protected void setDailyChallenge(){

        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        int stored_daily_challenge = sharedPref.getInt("saved_daily_challenge", 0);

        nv = (NavigationView) activity.findViewById(R.id.navigation);
        nv.getMenu().findItem(R.id.account).setTitle(daily_challenges[stored_daily_challenge]);
    }

}
