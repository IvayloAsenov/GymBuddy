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
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

public class Home extends AppCompatActivity implements BodyTypes{

    // Declare radio buttons, buttons and image views
    RadioGroup rg;

    ImageButton b_add_workout;
    ImageButton b_viewWorkouts;

    ImageView iv_body_type;
    TextView tv_timer;

    ImageButton ib_startWorkout;
    ImageButton ib_pauseWorkout;
    ImageButton ib_stopWorkout;


    // Minutes and seconds for the timer
    int minutes = 0;
    int seconds = 0;

    boolean b_workout = false; // Variable to start/end workout

    //AlertDialog dialog; // Dialog that will pop up when starting workout

    String workout=""; // String that will hold the current workout

    int current_body = 0; // Variable used to cycle through body types

    private int workout_counter; // Public variable used to count workouts

    // Declare objects
    SaveFile sf;
    BodyType bt;
    WorkoutCounter wc;
    TimerSwitch t;
    Challenges c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create objects
        sf = new SaveFile(this);
        bt = new BodyType(this);
        wc = new WorkoutCounter(this);
        t = new TimerSwitch(this);
        c = new Challenges(this);

        // Create views
        b_add_workout = (ImageButton) findViewById(R.id.LogAWorkout);
        b_viewWorkouts = (ImageButton) findViewById(R.id.b_viewWorkouts);

        tv_timer = (TextView) findViewById(R.id.tv_timer);
        iv_body_type = (ImageView) findViewById(R.id.iv_body_type);

        current_body = bt.readBType();
        iv_body_type.setImageResource(imageList[current_body]);

        ib_startWorkout = (ImageButton) findViewById(R.id.ib_startWorkout);
        ib_stopWorkout = (ImageButton) findViewById(R.id.ib_stopWorkout);
        ib_pauseWorkout = (ImageButton) findViewById(R.id.ib_pauseWorkout);

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

        // Start timer
        ib_startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib_startWorkout.setVisibility(View.INVISIBLE);
                ib_startWorkout.setClickable(false);
                ib_stopWorkout.setClickable(true);
                ib_stopWorkout.setVisibility(View.VISIBLE);
                ib_pauseWorkout.setClickable(true);
                ib_pauseWorkout.setVisibility(View.VISIBLE);
                createDialog();
            }
        });

        ib_stopWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ib_stopWorkout.setClickable(false);
                ib_stopWorkout.setVisibility(View.INVISIBLE);
                ib_pauseWorkout.setClickable(false);
                ib_pauseWorkout.setVisibility(View.INVISIBLE);
                ib_startWorkout.setVisibility(View.VISIBLE);
                ib_startWorkout.setClickable(true);
                String s_time = t.stopTimer();
                formatMessage(workout, s_time);
            }
        });

        ib_pauseWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.pauseTimer();
            }
        });
    }
    /*
        Formats the string that is going to be saved to the file
     */
    public void formatMessage(String workout, String s_time)
    {
        String message;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        message = "[" + formattedDate + " " + workout + " " + s_time + " ";
        sf.saveToFile(message);
    }

    /*
        Creates a pop up dialog that lets the user choose a workout
     */
    private void createDialog()
    {
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);

       // LayoutInflater inflater = Home.this.getLayoutInflater();
        //ViewGroup parent = null;

       // View view = inflater.inflate(R.layout.choose_workout, parent, false);
       // builder.setView(view);

      //  builder.setTitle("Choose your workout!");

       // dialog = builder.create();

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.choose_workout);

        /*b_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg.getCheckedRadioButtonId();

                if(id == rb_legs.getId())
                    workout = "Legs";
                else if(id == rb_cycling.getId())
                    workout = "Cycling";
                else if(id == rb_shoulders.getId())
                    workout = "Shoulders";
                else if(id == rb_chest.getId())
                    workout = "Chest";
                else if(id == rb_back.getId())
                    workout = "Back";
                else if(id == rb_biceps.getId())
                    workout = "Biceps";
                else if(id == rb_triceps.getId())
                    workout = "Triceps";
                else if(id == rb_run.getId())
                    workout = "Run";

                b_workout=true;
                dialog.cancel();
                t.startTimer();
            }

        });
    */


        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
