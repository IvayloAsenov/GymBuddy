package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ivo on 12/26/2017.
 */


/*
    Class used to save information in order to see if weekly
    challenge was completed or not!

    To do:

    dc1: # of workouts > 4
    dc2: minutes exercised > 240

    VICE VERSA
 */

public class InformationWeeklyChallenges {

    Activity activity;

    boolean completed;

    int saved_completed = 0; // 0: not completed 1: completed

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    InformationWeeklyChallenges(Activity a){
        activity = a;
    }

    protected boolean checkCompletion(int challenge, int minutes, String workout) {

        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_weekly_challenges", Context.MODE_PRIVATE);
        saved_completed = sharedPref.getInt("saved_completed", 0);

        // If completed == 1 just return false, cannot complete twice
        if(saved_completed == 1)
            return false;

        switch(challenge)
        {
            case 0:
                completed = challenge1(minutes);
                break;

            case 1:
                completed = challenge2();
                break;
        }

        if(completed) {
            Toast.makeText(activity, "CHALLENGE COMPLETED!", Toast.LENGTH_SHORT).show();
            editor = sharedPref.edit();
            editor.putInt("saved_completed", 1).commit(); // Save as daily challenge completed
        }
        return completed;
    }

    /* Verify if time workout > 240 */
    private boolean challenge1(int minutes){

        int timeWorkout = sharedPref.getInt("timeWorkout", 0);

        if(timeWorkout + minutes >= 240){
            editor.putInt("timeWorkout", 0);
            return true;
        }
        else{
            editor.putInt("timeWorkout", timeWorkout + minutes);
            return false;
        }
    }


    private boolean challenge2(){

        // Get today's day
        Calendar sCalendar = Calendar.getInstance();
        String today_day = sCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());

        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_daily_challenges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String stored_daily_day = sharedPref.getString("saved_daily_date", today_day);
        int stored_workout_counter = sharedPref.getInt("saved_daily_workout_counter", -1);

        // First workout of the day
        if(stored_workout_counter == -1 && today_day.equals(stored_daily_day)) {
            editor.putInt("saved_daily_workout_counter", 1).commit();
        }

        // Second workout of the day
        else if(stored_workout_counter == 1 && today_day.equals(stored_daily_day))
        {
            editor.putInt("saved_daily_workout_counter", -1).commit();
            return true;
        }

        // Different day
        else
        {
            editor.putString("saved_daily_date", today_day).commit();
            editor.putInt("saved_daily_workout_counter", -1).commit();
        }

        return false;
    }
}
