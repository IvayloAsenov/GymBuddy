package com.k9.ivo.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ivo on 1/15/2018.
 */

public class Score {

    Activity activity;
    SharedPreferences sharedPref;

    public Score(Activity a){
        activity = a;
        sharedPref = activity.getApplicationContext().getSharedPreferences("saved_score", Context.MODE_PRIVATE);
    }

    public int getCurrentScore(){
        int currentScore = sharedPref.getInt("score", 0);
        return currentScore;
    }

    public void setCurrentScore(int score){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("score", score).commit(); // Save as daily challenge completed
    }
}
