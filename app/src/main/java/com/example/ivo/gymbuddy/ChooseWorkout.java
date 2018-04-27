package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Chooses workout once the start button has been clicked
 *
 * @author Ivaylo Asenov
 */

public class ChooseWorkout{

    Activity activity;
    ImageButton ib_shoulders;
    ImageButton ib_running;
    ImageButton ib_cycling;
    ImageButton ib_chest;
    ImageButton ib_biceps;
    ImageButton ib_triceps;
    ImageButton ib_back;

    Dialog dialog;

    String workout = "";

    ChooseWorkout(Activity a){
        activity = a;
    }

    TimerSwitch t;

    /**
     * method to show dialog and to take care of button clicks
     * @param _t TimerSwitch object
     */
    protected void showDialog(TimerSwitch _t){

        t = _t;

        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.choose_workout);

        View v = LayoutInflater.from(activity).inflate(R.layout.choose_workout, null); // Inflate choose_workout layout

        ib_shoulders = (ImageButton) dialog.findViewById(R.id.ib_shoulders);
        ib_running = (ImageButton) dialog.findViewById(R.id.ib_run);
        ib_cycling = (ImageButton) dialog.findViewById(R.id.ib_cycling);
        ib_chest = (ImageButton) dialog.findViewById(R.id.ib_chest);
        ib_biceps = (ImageButton) dialog.findViewById(R.id.ib_biceps);
        ib_triceps = (ImageButton) dialog.findViewById(R.id.ib_triceps);
        ib_back = (ImageButton) dialog.findViewById(R.id.ib_back);

        ib_shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Shoulders");
            }
        });

        ib_running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Running");
            }
        });

        ib_cycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Cycling");
            }
        });

        ib_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Chest");
            }
        });

        ib_biceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Biceps");
            }
        });

        ib_triceps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Triceps");
            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Back");
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * Cancels the dialog and starts timer
     * @param _workout
     */
    protected void onButtonClick(String _workout){

        dialog.cancel();
        workout = _workout;

        t.startTimer();
    }

    /**
     * @return String workout
     */
    protected String getWorkout(){
        return workout;
    }
}
