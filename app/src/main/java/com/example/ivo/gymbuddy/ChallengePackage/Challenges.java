// File Name: Challenges.java
// Developers: Ivaylo Asenov
// Purpose: Cycles between challenges daily and weekly

package com.example.ivo.gymbuddy.ChallengePackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivo.gymbuddy.R;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Ivo on 11/15/2017.
 * Added comments + weekly challenge 2 (problem with pushes!).
 * Final test push to merge accounts
 */

public class Challenges implements DailyChallenges, WeeklyChallenges {

    Activity activity;
    String today_day = ""; // Variable used to store today's date

    NavigationView nv;
    MenuItem mi;

    Random rand;

    SharedPreferences sharedPref;

    Dialog dialog;

    // Get Home's context
    public Challenges(Activity a){
        activity = a;
        setDailyChallenge();
        setWeeklyChallenge();
        getDays();
    }

    // Get current day
    protected void getDays(){

        Calendar sCalendar = Calendar.getInstance();
        String today_day = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        String stored_daily_day = sharedPref.getString("saved_daily_date", today_day);
        int weekly_date = sharedPref.getInt("saved_weekly_counter", 6);

        verifyDailyDate(today_day, stored_daily_day, weekly_date);
    }

    // Check if it has been a day and changes the challenges if so
    protected void verifyDailyDate(String today_day, String stored_daily_day, int weekly_date){
        if(stored_daily_day.equals(today_day)) {
            // Keep challenge
        }else{
            //Change daily challenge
            saveDate(today_day, 0);
            changeDailyChallenge();
            verifyWeeklyDate(weekly_date);
        }
    }

    // Check if it has been a week and changes the challenges if so
    protected void verifyWeeklyDate(int wd){
        wd = wd - 1;

        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Toast.makeText(activity.getApplicationContext(), " " + wd, Toast.LENGTH_LONG).show();

        if(wd > 0) {
            editor.putInt("saved_weekly_counter", wd).commit();
        }else{
            editor.putInt("saved_weekly_counter", 6).commit();
            changeWeeklyChallenge();
        }

    }

    // Save the date to shared preference
    protected void saveDate(String today_day, int w_d){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges_dates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(w_d == 0){
            editor.putString("saved_daily_date", today_day).apply();
        }
    }

    // Change daily challenge to the next daily challenge in DailyChallenge Interface
    protected void changeDailyChallenge(){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        int stored_daily_challenge = sharedPref.getInt("saved_daily_challenge", 0);

        int next_daily_challenge = stored_daily_challenge + 1;

        if(next_daily_challenge >= len)
            next_daily_challenge = 0;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("saved_daily_challenge", next_daily_challenge).apply();

//        nv = (NavigationView) activity.findViewById(R.id.navigation);
//        nv.getMenu().findItem(R.id.account).setTitle(daily_challenges[next_daily_challenge]);

        // Reset the daily completed status
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_daily_challenges", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.putInt("saved_completed", 0).commit();
    }

    // Change weekly challenge to the next weekly challenge in WeeklyChallenge Interface
    protected void changeWeeklyChallenge(){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        int stored_weekly_challenge = sharedPref.getInt("saved_weekly_challenge", 0);

        int next_weekly_challenge = stored_weekly_challenge + 1;

        if(next_weekly_challenge >= wlen)
            next_weekly_challenge = 0;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("saved_weekly_challenge", next_weekly_challenge).apply();

        /* Reset the weekly challenge counter */
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_weekly_challenges", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.putInt("timeWorkout", 0).commit();
        editor.putInt("numWorkouts", 0).commit();

//        nv = (NavigationView) activity.findViewById(R.id.navigation);
//        nv.getMenu().findItem(R.id.user).setTitle(weekly_challenges[next_weekly_challenge]);
    }

    // Sets the daily challenge when app is ran
    protected void setDailyChallenge(){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        int stored_daily_challenge = sharedPref.getInt("saved_daily_challenge", 0);

//        nv = (NavigationView) activity.findViewById(R.id.navigation);
//        nv.getMenu().findItem(R.id.account).setTitle(daily_challenges[stored_daily_challenge]);
    }

    // Sets the weekly challenge when app is ran
    protected void setWeeklyChallenge(){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        int stored_weekly_challenge = sharedPref.getInt("saved_weekly_challenge", 0);

        //SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putInt("saved_weekly_challenge", 0).commit();

//        nv = (NavigationView) activity.findViewById(R.id.navigation);
//        nv.getMenu().findItem(R.id.user).setTitle(weekly_challenges[stored_weekly_challenge]);
    }

    // Gets the daily challenge when app is ran
    public int getDailyChallenge(){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        return sharedPref.getInt("saved_daily_challenge", 0);
    }

    // Gets the weekly challenge when app is ran
    public int getWeeklyChallenge(){
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_challenges", Context.MODE_PRIVATE);
        return sharedPref.getInt("saved_weekly_challenge", 0);
    }

    public void showDialog() {

        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.challenge_view);

        View v = LayoutInflater.from(activity).inflate(R.layout.challenge_view, null);

        int daily_challenge = getDailyChallenge();
        String daily_challenge_text = v.getResources().getString(daily_challenges[daily_challenge]);

        int weekly_challenge = getWeeklyChallenge();
        String weekly_challenge_text = v.getResources().getString(weekly_challenges[weekly_challenge]);

        TextView tv_daily_text = (TextView) dialog.findViewById(R.id.dailyChallenge);
        tv_daily_text.setText(daily_challenge_text);

        TextView tv_weekly_text = (TextView) dialog.findViewById(R.id.weeklyChallenge);
        tv_weekly_text.setText(weekly_challenge_text);

        ImageView iv_daily = (ImageView) dialog.findViewById(R.id.daily_checkmark);
        ImageView iv_weekly = (ImageView) dialog.findViewById(R.id.weekly_checkmark);

        InformationDailyChallenges idc = new InformationDailyChallenges(activity);
        InformationWeeklyChallenges iwc = new InformationWeeklyChallenges(activity);

        if (iwc.isCompleted())
            iv_weekly.setImageResource(android.R.drawable.btn_star_big_on);

        if (idc.isCompleted(daily_challenge))
            iv_daily.setImageResource(android.R.drawable.btn_star_big_on);

        dialog.show();
    }
}
