package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ivo on 11/14/2017.
 */

class TimerSwitch {

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;

    Handler handler;

    int seconds, minutes, milliSeconds;

    Activity activity;

    TextView tv_timer;

    boolean paused = false;

    TimerSwitch(Activity a){
        activity = a;
        tv_timer = (TextView) activity.findViewById(R.id.tv_timer);
        handler = new Handler(Looper.getMainLooper());
    }

    protected void startTimer(){
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

    protected String stopTimer(){
        MillisecondTime = 0L;
        StartTime = 0L;
        TimeBuff = 0L;
        UpdateTime = 0L;
        seconds = 0;
        minutes = 0;
        milliSeconds = 0;

        handler.removeMessages(0);

        String time = tv_timer.getText().toString();
        tv_timer.setText("00:00:00");

        return time;
    }

    protected int getMinutes(){
        return minutes;
    }

    protected void pauseTimer(){
        if(!paused){
            TimeBuff += MillisecondTime;
            //handler.removeCallbacks(runnable);
            handler.removeMessages(0);
            paused = true;
        }else{
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable,0);
            paused = false;
        }
    }

    protected Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            seconds = (int) (UpdateTime/1000);
            minutes = seconds/60;
            seconds = seconds % 60;
            milliSeconds = (int) (UpdateTime % 1000);
            milliSeconds /= 10;
            tv_timer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
            + ":" + String.format("%02d", milliSeconds));
            handler.postDelayed(this,0);
        }
    };

    protected void clear(){
        paused = false;
    }
}
