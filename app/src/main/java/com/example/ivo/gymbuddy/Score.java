package com.example.ivo.gymbuddy;

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

    public String getCurrentScore(){

        return "test";
    }

    public void setCurrentScore(String score){

    }
}
