package com.example.ivo.gymbuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Ivo on 11/8/2017.
 */

public class BodyType extends AppCompatActivity implements BodyTypes{

    static private int body_type = 0;
    private Context context;

    public BodyType(Context context)
    {
        this.context = context;
    }

    protected int readBType()
    {
        int bt = 0;
        SharedPreferences sharedPref = context.getSharedPreferences("saved_body_type",Context.MODE_PRIVATE);
        int defaultValue = 0;
        bt = sharedPref.getInt("saved_workout_counter", defaultValue);
        return bt;
    }

    protected void writeWCounter()
    {
        SharedPreferences sharedPref = context.getSharedPreferences("saved_body_type", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("saved_body_type", body_type+1);
        editor.commit();
    }

    protected int getWCounter()
    {
        return body_type;
    }

    protected void setWCounter(int bt)
    {
        body_type = bt;
    }

    protected void changeBType()
    {
       // ImageView iv_body_type = (ImageView) context.get
      //  iv_body_type.setImageResource(imageList[readBType()+1]);
    }
}
