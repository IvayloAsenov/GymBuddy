package com.example.ivo.gymbuddy;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity implements BodyTypes{

    // Declare radio buttons, buttons and image views
    RadioButton rb_legs;
    RadioButton rb_shoulders;
    RadioButton rb_cycling;
    RadioButton rb_triceps;
    RadioButton rb_biceps;
    RadioButton rb_run;
    RadioButton rb_back;
    RadioButton rb_chest;

    RadioGroup rg;
    Button but;
    Button b_add_workout;
    Button b_viewWorkouts;

    ImageView body_type;
    TextView tv_timer;

    ImageButton ib_startWorkout;

    // Minutes and seconds for the timer
    int minutes = 0;
    int seconds = 0;

    boolean b_workout = false; // Variable to start/end workout

    AlertDialog dialog; // Dialog that will pop up when starting workout

    String workout=""; // String that will hold the current workout

    int current_body = 0; // Variable used to cycle through body types

    public int workout_counter; // Public variable used to count workouts

    SaveFile sf;
    BodyType bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sf = new SaveFile(this);
        bt = new BodyType(this);

        but = (Button) findViewById(R.id.button);
        b_add_workout = (Button) findViewById(R.id.LogAWorkout);
        b_viewWorkouts = (Button) findViewById(R.id.b_viewWorkouts);

        tv_timer = (TextView) findViewById(R.id.tv_timer);
        //body_type = (ImageView) findViewById(R.id.iv_body_type);
        ib_startWorkout = (ImageButton) findViewById(R.id.ib_startWorkout);

        // Change activity -> add Workout
        b_add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddWorkout.class);
                startActivity(intent);
            }
        });

        // Change activity -> view Workouts
        b_viewWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SeeWorkouts.class);
                startActivity(intent);
            }
        });

        // Start/Stop Workout
        ib_startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b_workout) // Stop workout, stop timer and save to file
                {
                    b_workout=false;
                    createTimer();
                }
                else{
                    createDialog(); // If no workout started, then start a new one

                }
            }
        });
    }

    /*
      Timer that runs the method every second and increments the timer
      on click of StartWorkout
       */
    private void createTimer()
    {
        final Timer timer = new Timer();
        tv_timer.setVisibility(View.VISIBLE); //Make timer visible

        /*
        Toggle between start/end workout using a boolean variable b_workout
         */

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Home.this.runOnUiThread(new Runnable() {
                    public void run() {
                        String s_time="";
                        //Increase i by 1 every second simulating a timer
                        if(b_workout == true) {
                            s_time = getTime(minutes, seconds);
                            tv_timer.setText(s_time);
                            seconds++;
                        }

                        //Stop timer and reset it to 0
                        if(b_workout == false) {
                            timer.cancel();
                            b_workout=true;
                            tv_timer.setText("");
                            seconds=0;
                            tv_timer.setVisibility(View.INVISIBLE);
                            b_workout=false;
                            formatMessage(workout,s_time);
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    /*
        Formats the time to (minutes):(seconds) time format
     */
    private String getTime(int minutes, int seconds)
    {
        String s_time;
        String s_seconds = "";
        String s_minutes = "";

        if(seconds < 10 && seconds >= 0)
            s_seconds = "0" + seconds;
        else if(seconds < 60 && seconds >= 10)
            s_seconds = Integer.toString(seconds);
        else {
            seconds = 0;
            minutes++;
            s_seconds = "0" + seconds;
        }

        if(minutes < 10)
            s_minutes = "0" + minutes;
        else {
            s_minutes = Integer.toString(minutes);
        }

        s_time = s_minutes + ":" + s_seconds;
        return s_time;
    }


    /*
        Formats the string that is going to be saved to the file
     */
    public void formatMessage(String s_workout, String s_time)
    {
        String message;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        message = "[" + formattedDate + " " + s_workout + " " + s_time + " ";
        sf.saveToFile(message);
    }

    /*
        Writes to save file
     */

    /*
        Creates a pop up dialog that lets the user choose a workout
     */
    private void createDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = Home.this.getLayoutInflater();
        ViewGroup parent = null;

        View view = inflater.inflate(R.layout.choose_workout, parent, false);
        builder.setView(view);

        builder.setTitle("Choose your workout!");

        dialog = builder.create();

        rb_legs = (RadioButton) view.findViewById(R.id.rb_legs);
        rb_shoulders = (RadioButton) view.findViewById(R.id.rb_shoulders);
        rb_cycling = (RadioButton) view.findViewById(R.id.rb_cycling);
        rb_run = (RadioButton) view.findViewById(R.id.rb_run);
        rb_chest = (RadioButton) view.findViewById(R.id.rb_chest);
        rb_back = (RadioButton) view.findViewById(R.id.rb_back);
        rb_biceps = (RadioButton) view.findViewById(R.id.rb_biceps);
        rb_triceps = (RadioButton) view.findViewById(R.id.rb_triceps);

        rg = (RadioGroup) view.findViewById(R.id.radioGroup);

        Button b_confirm = (Button) view.findViewById(R.id.b_confirm);

        b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();
                if(id == rb_legs.getId())
                    workout = "Run";
                else if(id == rb_cycling.getId())
                    workout = "Cycling";
                else if(id == rb_shoulders.getId())
                    workout = "Chest";
                else if(id == rb_shoulders.getId())
                    workout = "Back";
                else if(id == rb_shoulders.getId())
                    workout = "Biceps";
                else if(id == rb_shoulders.getId())
                    workout = "Triceps";
                else if(id == rb_shoulders.getId())
                    workout = "Shoulders";
                else if(id == rb_shoulders.getId())
                    workout = "Legs";

                b_workout=true;
                dialog.cancel();
                createTimer();
            }

        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
