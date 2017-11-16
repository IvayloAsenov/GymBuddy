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

        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        String stored_daily_day = sharedPref.getString("saved_daily_date", today_day);
        String stored_weekly_day = sharedPref.getString("saved_weekly_date", today_day);

        Toast.makeText(activity.getApplicationContext(), "" + today_day + "" + stored_weekly_day, Toast.LENGTH_SHORT).show();
        verifyDailyDate(today_day, stored_daily_day);
        verifyWeeklyDate(today_day, stored_weekly_day);
    }

    protected void verifyDailyDate(String today_day, String stored_daily_day){
        if(stored_daily_day.equals(today_day)) {
            // Keep challenge
           // saveDate(today_day);
        }else{
            //Change daily challenge
            nv = (NavigationView) activity.findViewById(R.id.navigation);
            nv.getMenu().findItem(R.id.account).setTitle("CHANGE CHALLENGE!");
            saveDate(today_day, 0);
        }
    }

    protected void verifyWeeklyDate(String today_day, String stored_weekly_date){

        int td = dayToNum(today_day);
        int wd = dayToNum(stored_weekly_date);

        int dif = wd - td;

        Log.d("ALLALALA", " " + td + " " + wd);
        if(dif == 1 || (dif == 6 && wd == 7)){
            // Change daily challenge
            nv = (NavigationView) activity.findViewById(R.id.navigation);
            nv.getMenu().findItem(R.id.logout).setTitle("CHANGE WEEKLY CHALLENGE!");
            saveDate(today_day, 1);
        }else{
            // Do nothing
        }
    }

    private int dayToNum(String day){
        if(day.equals("Monday"))
            return 1;
        else if(day.equals("Tuesday"))
            return 2;
        else if(day.equals("Wednesday"))
            return 3;
        else if(day.equals("Thursday"))
            return 4;
        else if(day.equals("Friday"))
            return 5;
        else if(day.equals("Saturday"))
            return 6;
        else if(day.equals("Sunday"))
            return 7;

        return 1;
    }

    protected void saveDate(String today_day, int w_d){
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(w_d == 0){
            editor.putString("saved_daily_date", today_day);
        }else{
            editor.putString("saved_weekly_date", today_day);
        }
        editor.commit();
    }


}
