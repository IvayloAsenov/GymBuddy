package com.example.ivo.gymbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ivo on 11/6/2017.
 */

public class WorkoutCounter extends AppCompatActivity
{
    static private int workout_counter = 0;
    private Context context;

    public WorkoutCounter(Context context)
    {
        this.context = context;
    }
    protected int readWCounter()
    {
        int wc = 0;
        SharedPreferences sharedPref = context.getSharedPreferences("saved_workout_counter",Context.MODE_PRIVATE);
        int defaultValue = 0;
        wc = sharedPref.getInt("saved_workout_counter", defaultValue);
        return wc;
    }

    protected void writeWCounter(int wc)
    {
        SharedPreferences sharedPref = context.getSharedPreferences("saved_workout_counter", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("saved_workout_counter", wc);
        editor.commit();
    }
}
