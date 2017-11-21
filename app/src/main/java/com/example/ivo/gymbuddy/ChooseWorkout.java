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
 * Created by Ivo on 11/19/2017.
 */

public class ChooseWorkout{


    // Declare all views

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

    // Constructor to get Home's activity
    ChooseWorkout(Activity a){
        activity = a;
    }

    TimerSwitch t; // TimerSwitch object


    // Method to show dialog and to take care of button clicks
    protected void showDialog(TimerSwitch _t){

        t = _t;


        // Create Dialog
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.choose_workout);

        View v = LayoutInflater.from(activity).inflate(R.layout.choose_workout, null); // Inflate choose_workout layout

        // Create ImageButtons
        ib_shoulders = (ImageButton) dialog.findViewById(R.id.ib_shoulders);
        ib_running = (ImageButton) dialog.findViewById(R.id.ib_run);
        ib_cycling = (ImageButton) dialog.findViewById(R.id.ib_cycling);
        ib_chest = (ImageButton) dialog.findViewById(R.id.ib_chest);
        ib_biceps = (ImageButton) dialog.findViewById(R.id.ib_biceps);
        ib_triceps = (ImageButton) dialog.findViewById(R.id.ib_triceps);
        ib_back = (ImageButton) dialog.findViewById(R.id.ib_back);


        // ImageButton click events
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

    // Method called when method is clicked
    // Cancels the dialog and start timer
    protected void onButtonClick(String _workout){

        dialog.cancel();
        workout = _workout;

        t.startTimer();
    }

    // Return the workout
    protected String getWorkout(){
        return workout;
    }
}
