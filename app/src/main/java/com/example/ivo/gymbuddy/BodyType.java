// File Name: Home.java
// Developers: Ivaylo Asenov
// Purpose: Cycles between body types

package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Ivo on 11/8/2017.
 */

public class BodyType extends AppCompatActivity implements BodyTypes{

    static private int body_type = 0;
    private Context context;
    ImageView iv_body_type;

    public BodyType(Context context)
    {
        this.context = context;
    }

    protected int readBType()
    {
        int bt = 0;
        SharedPreferences sharedPref = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        int defaultValue = 0;
        bt = sharedPref.getInt("saved_body_type", defaultValue);
        return bt;
    }

    protected void writeBCounter(int bt)
    {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("saved_body_type", bt);
        editor.commit();
    }
}
