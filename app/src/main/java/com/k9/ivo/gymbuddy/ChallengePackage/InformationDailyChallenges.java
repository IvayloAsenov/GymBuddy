// File Name: Home.java
// Developers: Ivaylo Asenov
// Purpose: Used to save information in order to see if daily
//          challenge was completed or not
//            dc1: timeWorkout > 30
//            dc2: workout twice today
//            dc3: todayWorkout is legs
//            dc4: todayWorkout is chest
//            dc5: todayWorkout is triceps
//            dc6: todayWorkout is back

package com.k9.ivo.gymbuddy.ChallengePackage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class InformationDailyChallenges {

    Activity activity;

    boolean completed;

    int saved_completed = 0; // 0: not completed 1: completed


    public InformationDailyChallenges(Activity a){
        activity = a;
    }

    public InformationDailyChallenges() {

    }

    public boolean isCompleted(int challenge) {
        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_daily_challenges", Context.MODE_PRIVATE);
        saved_completed = sharedPref.getInt("saved_completed", 0);

        // If completed == 1 just return false, cannot complete twice
        if(saved_completed == 1)
            return true;

        return false;
    }

    public boolean checkCompletion(int challenge, int minutes, String workout) {

        SharedPreferences sharedPref = activity.getApplicationContext().getSharedPreferences("saved_info_daily_challenges", Context.MODE_PRIVATE);
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

            case 2:
                completed = challenge3(workout);
                break;

            case 3:
                completed = challenge4(workout);
                break;

            case 4:
                completed = challenge5(workout);
                break;

            case 5:
                completed = challenge6(workout);
                break;
        }

        if(completed) {
            Toast.makeText(activity, "CHALLENGE COMPLETED!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("saved_completed", 1).commit(); // Save as daily challenge completed
        }
        return completed;
    }

    /* Verify if time workout > 30 */
    private boolean challenge1(int minutes){
        if(minutes >= 30)
            return true;
        return false;
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

    /* Verify if workout is Legs */
    private boolean challenge3(String workout){
        if(workout.equals("Legs"))
            return true;
        return false;
    }

    /* Verify if workout is Chest */
    private boolean challenge4(String workout){
        if(workout.equals("Chest"))
            return true;
        return false;
    }

    /* Verify if workout is Triceps */
    private boolean challenge5(String workout){
        if(workout.equals("Triceps"))
            return true;
        return false;
    }

    /* Verify if workout is Back */
    private boolean challenge6(String workout){
        if(workout.equals("Back"))
            return true;
        return false;
    }
}
