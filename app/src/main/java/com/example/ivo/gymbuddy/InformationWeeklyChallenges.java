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

    dc1: minutes exercised > 240
    dc2: # workouts > 4

 */

public class InformationWeeklyChallenges {

    Activity activity;

    boolean completed;

    int saved_completed = 0; // 0: not completed 1: completed

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    InformationWeeklyChallenges(Activity a){
        activity = a;
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_weekly_challenges", Context.MODE_PRIVATE);
        saved_completed = sharedPref.getInt("saved_completed", 0);
    }

    protected boolean checkCompletion(int challenge, int minutes, String workout) {

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
            editor.putInt("timeWorkout", 0).commit();
            return true;
        }
        else{
            editor.putInt("timeWorkout", timeWorkout + minutes).commit();
            return false;
        }
    }

    private boolean challenge2(){

        int numWorkouts = sharedPref.getInt("numWorkouts", 0);
        numWorkouts ++;

        if(numWorkouts >= 4){
            editor.putInt("numWorkouts", 0).commit();
            return true;
        }else{
            editor.putInt("numWorkouts", numWorkouts).commit();
            return false;
        }
    }
}
