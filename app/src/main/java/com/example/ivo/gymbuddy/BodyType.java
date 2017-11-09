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

    protected void writeWCounter()
    {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
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
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home, null, false);
        iv_body_type = (ImageView) v.findViewById(R.id.iv_body_type);
        Log.d("test", ""+readBType());
        iv_body_type.setImageResource(imageList[readBType()+1]);
    }
}
