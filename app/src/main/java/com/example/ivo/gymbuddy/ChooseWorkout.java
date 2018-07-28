package com.example.ivo.gymbuddy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
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

public class ChooseWorkout {

    Activity activity;
    ImageButton ib_chest;
    ImageButton ib_back;
    ImageButton ib_shoulders;
    ImageButton ib_arms;
    ImageButton ib_legs;
    ImageButton ib_abs;
    ImageButton ib_run;
    ImageButton ib_other;

    Dialog dialog;

    String workout;

    ChooseWorkout(Activity a){
        activity = a;
    }

    TimerSwitch t;

    /**
     * method to show dialog and to take care of button clicks
     * @param _t TimerSwitch object
     */
    protected void showDialog(TimerSwitch _t){

        workout = "";

        t = _t;

        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.choose_workout);

        View v = LayoutInflater.from(activity).inflate(R.layout.choose_workout, null); // Inflate choose_workout layout

        ib_chest = (ImageButton) dialog.findViewById(R.id.ib_chest);
        ib_back = (ImageButton) dialog.findViewById(R.id.ib_back);
        ib_shoulders = (ImageButton) dialog.findViewById(R.id.ib_shoulders);
        ib_arms = (ImageButton) dialog.findViewById(R.id.ib_arms);
        ib_legs = (ImageButton) dialog.findViewById(R.id.ib_legs);
        ib_abs = (ImageButton) dialog.findViewById(R.id.ib_abs);
        ib_run = (ImageButton) dialog.findViewById(R.id.ib_run);
        ib_other = (ImageButton) dialog.findViewById(R.id.ib_other);

        ib_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Chest");
            }
        });

        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Back");
            }
        });

        ib_shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Shoulders");
            }
        });

        ib_arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Arms");
            }
        });

        ib_legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Legs");
            }
        });

        ib_abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Abs");
            }
        });

        ib_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Run");
            }
        });

        ib_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick("Other");
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
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
