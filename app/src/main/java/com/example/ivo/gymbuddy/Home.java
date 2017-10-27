package com.example.ivo.gymbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity implements BodyTypes{

    int current_body = 0;

    Button but;
    Button b_add_workout;
    Button b_viewWorkouts;

    ImageView body_type;
    TextView tv_timer;

    ImageButton ib_startWorkout;

    int i = 0;

    boolean b_workout = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        but = (Button) findViewById(R.id.button);
        b_add_workout = (Button) findViewById(R.id.LogAWorkout);
        b_viewWorkouts = (Button) findViewById(R.id.b_viewWorkouts);

        tv_timer = (TextView) findViewById(R.id.tv_timer);
        body_type = (ImageView) findViewById(R.id.body_type);
        ib_startWorkout = (ImageButton) findViewById(R.id.ib_startWorkout);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(current_body+1 >= imageList_size)
                    current_body=-1;

                body_type.setImageResource(imageList[current_body+1]);
                current_body++;
            }
        });

        b_add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddWorkout.class);
                startActivity(intent);
            }
        });

        b_viewWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SeeWorkouts.class);
                startActivity(intent);
            }
        });

        /*
        Timer that runs the method every second and increments the timer
        on click of StartWorkout
         */

        ib_startWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Timer timer = new Timer();

                b_workout = true;

                tv_timer.setVisibility(View.VISIBLE);

                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        Home.this.runOnUiThread(new Runnable() {
                            public void run() {
                                tv_timer.setText(Integer.toString(i));
                                i++;

                                if(i == 10)
                                    timer.cancel();
                            }
                        });
                    }
                }, 1000, 1000);

            }
        });
    }

}
