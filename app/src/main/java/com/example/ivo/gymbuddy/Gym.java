package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Used to retrieve data about owned gyms
 *
 * @author Ivaylo Asenov
 */

public class Gym {

    Activity activity;
    SharedPreferences sharedPref;
    ArrayList<Integer> ownedGyms;

    /**
     * Constructor
     * @param a Activity
     */
    public Gym(Activity a){
        activity = a;
        sharedPref = activity.getApplicationContext().getSharedPreferences("GYMS", Context.MODE_PRIVATE);
        ownedGyms = new ArrayList<>();

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("gym0", 0).commit();
        editor.putInt("gym1", 0).commit();
        editor.putInt("gym2", 0).commit();

    }

    /**
     * Returns a list of all the gyms owned
     *
     * @return ArrayList of gym owned where 1=gym owned 0=not owned
     */
    public ArrayList<Integer> getOwnedGyms(){

        ownedGyms.clear();

        ownedGyms.add(0, sharedPref.getInt("gym0", 0));
        ownedGyms.add(1, sharedPref.getInt("gym1", 0));
        ownedGyms.add(2, sharedPref.getInt("gym2", 0));

        return ownedGyms;
    }

    /**
     * Register a gym to being bought/ owned
     *
     * @param position The position of the gym that is being bought
     */
    public void saveGym(int position){
        SharedPreferences.Editor editor = sharedPref.edit();

        switch(position){
            case 0:
                editor.putInt("gym0", 1).commit();
                break;
            case 1:
                editor.putInt("gym1", 1).commit();
                break;
            case 2:
                editor.putInt("gym2", 1).commit();
                break;
        }
    }
}
