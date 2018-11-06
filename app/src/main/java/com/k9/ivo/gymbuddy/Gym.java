package com.k9.ivo.gymbuddy;

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
    SharedPreferences.Editor editor;

    /**
     * Constructor
     * @param a Activity
     */
    public Gym(Activity a){
        activity = a;
        sharedPref = activity.getApplicationContext().getSharedPreferences("GYMS", Context.MODE_PRIVATE);
        ownedGyms = new ArrayList<>();

        editor = sharedPref.edit();
    }

    /**
     * Returns a list of all the gyms owned
     *
     * @return ArrayList of gym owned where 1=gym owned 0=not owned
     */
    public ArrayList<Integer> getOwnedGyms(){

        ownedGyms.clear();

        ownedGyms.add(0, sharedPref.getInt("gym0", 1));
        ownedGyms.add(1, sharedPref.getInt("gym1", 0));
        ownedGyms.add(2, sharedPref.getInt("gym2", 0));
        ownedGyms.add(3, sharedPref.getInt("gym3", 0));

        return ownedGyms;
    }

    /**
     * Register a gym to being bought/ owned
     *
     * @param position The position of the gym that is being bought
     */
    public void saveGym(int position){
        switch(position){
            case 1:
                editor.putInt("gym1", 1).commit();
                break;
            case 2:
                editor.putInt("gym2", 1).commit();
                break;
            case 3:
                editor.putInt("gym3", 1).commit();
                break;
        }
    }

    /**
     * Saves current gym
     *
     * @param position The position of the gym 0/old 1/intermediate 2/expensive
     */
    public void setCurrentGym(int position){
        editor.putInt("currentGym", position).commit();
    }

    /**
     * Returns the position of the current gym
     */
    public int getCurrentGym(){
        return sharedPref.getInt("currentGym", 0); // If not yet saved then current gym is the starter gym
    }
}
