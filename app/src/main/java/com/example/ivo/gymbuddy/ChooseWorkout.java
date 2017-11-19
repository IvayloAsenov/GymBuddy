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

public class ChooseWorkout {

    Activity activity;
    ImageButton ib_shoulders;
    ImageButton ib_running;

    Dialog dialog;
    String workout;

    ChooseWorkout(Activity a){
        activity = a;
    }

    protected String showDialog(){
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.choose_workout);

        View v = LayoutInflater.from(activity).inflate(R.layout.choose_workout, null);

        ib_shoulders = (ImageButton) dialog.findViewById(R.id.ib_shoulders);

        ib_shoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //b_workout=true;
                //dialog.cancel();
                //t.startTimer();
                dialog.cancel();
                workout = "Soulerer";
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        while(workout.equals("null")) ;

        return workout;
    }
}
