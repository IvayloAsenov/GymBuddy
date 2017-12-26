package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.icu.text.IDNA;

/**
 * Created by Ivo on 12/26/2017.
 */


/*
    Class used to save information in order to see if daily
    challenge was completed or not!

    To do:

    dc1: timeWorkout > 30
    dc2: todayWorkout is different than yesterdayWorkout
    dc3: todayWorkout is legs
    dc4: todayWorkout is chest
    dc5: todayWorkout is triceps
    dc6: todayWorkout is back
 */

public class InformationDailyChallenges {

    Activity activity;

    boolean completed;


    InformationDailyChallenges(Activity a){
        activity = a;
    }

    protected boolean checkCompletion(int challenge) {
        switch(challenge)
        {
            case 1:
                completed = challenge1();
                break;
            case 2:
                completed = challenge2();
                break;

            case 3:
                completed = challenge3();
                break;

            case 4:
                completed = challenge4();
                break;

            case 5:
                completed = challenge5();
                break;

            case 6:
                completed = challenge6();
                break;
        }

        return completed;
    }

    private boolean challenge1(){
        return false;
    }

    private boolean challenge2(){
        return false;
    }

    private boolean challenge3(){
        return false;
    }

    private boolean challenge4(){
        return false;
    }

    private boolean challenge5(){
        return false;
    }

    private boolean challenge6(){
        return false;
    }
}
