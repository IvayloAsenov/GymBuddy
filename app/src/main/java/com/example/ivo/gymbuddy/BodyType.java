package com.example.ivo.gymbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Saves the current body type to a file and reads from it
 *
 * @author IvayloA
 */

public class BodyType extends AppCompatActivity implements BodyTypes{

    static private int body_type = 0;
    private Context context;
    ImageView iv_body_type;

    /**
     * Constructor that takes the context of Home
     * @param context
     */
    public BodyType(Context context)
    {
        this.context = context;
    }

    /**
     * Reads the body type from the file
     * @return index of the body type
     */
    protected int readBType()
    {
        int bt = 0;
        SharedPreferences sharedPref = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int defaultValue = 0;
        bt = sharedPref.getInt("saved_body_type", defaultValue);
        return bt;
    }
}
