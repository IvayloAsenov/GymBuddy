// File Name: Home.java
// Developers: Ivaylo Asenov
// Purpose: Takes care of the home screen and links all the classes together

package com.example.ivo.gymbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivo.gymbuddy.ChallengePackage.Challenges;
import com.example.ivo.gymbuddy.ChallengePackage.InformationDailyChallenges;
import com.example.ivo.gymbuddy.ChallengePackage.InformationWeeklyChallenges;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Main screen of the app. Takes care of button clicks
 *
 * @author Ivaylo Asenov
 */

public class Home extends AppCompatActivity implements BodyTypes, GymBackgrounds{

    ImageButton b_add_workout;
    ImageButton b_viewWorkouts;
    ImageButton ib_shop;

    ImageView iv_body_type;

    LinearLayout iv_background;

    TextView tv_scoreCounter;
    TextView tv_timer;

    ImageButton ib_startWorkout;
    ImageButton ib_pauseWorkout;
    ImageButton ib_stopWorkout;

    int money=1500;
    String workout=""; // String that will hold the current workout

    int current_body = 0; // Variable used to cycle through body types

    int daily_challenge; // Variable used to store challenge
    int weekly_challenge;

    SaveFile sf;
    BodyType bt;
    TimerSwitch t;
    Challenges c;
    ChooseWorkout cw;
    InformationDailyChallenges idc;
    InformationWeeklyChallenges iwc;
    Score s;
    Gym g;

    /**
     * Method that runs once app starts
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Create objects
        sf = new SaveFile(this);
        bt = new BodyType(this);
        t = new TimerSwitch(this);
        c = new Challenges(this);
        cw = new ChooseWorkout(this);
        idc = new InformationDailyChallenges(this);
        iwc = new InformationWeeklyChallenges(this);
        s = new Score(this);
        g = new Gym(this);

        // Get daily challenge
        daily_challenge = c.getDailyChallenge();
        weekly_challenge = c.getWeeklyChallenge();

        // Create views
        b_add_workout = (ImageButton) findViewById(R.id.LogAWorkout);
        b_viewWorkouts = (ImageButton) findViewById(R.id.b_viewWorkouts);

        tv_timer = (TextView) findViewById(R.id.tv_timer);

        tv_scoreCounter = (TextView) findViewById(R.id.tv_scoreCounter);
        tv_scoreCounter.setText(Integer.toString(s.getCurrentScore()));

        iv_body_type = (ImageView) findViewById(R.id.iv_body_type);

        iv_background = (LinearLayout) findViewById(R.id.background);

        current_body = bt.readBType();
        iv_body_type.setImageResource(imageList[current_body]);

        //iv_background.setBackground(ContextCompat.getDrawable(this, backgroundList[1]));
        iv_background.setBackgroundResource(backgroundList[g.getCurrentGym()]);

        ib_startWorkout = (ImageButton) findViewById(R.id.ib_startWorkout);
        ib_stopWorkout = (ImageButton) findViewById(R.id.ib_stopWorkout);
        ib_pauseWorkout = (ImageButton) findViewById(R.id.ib_pauseWorkout);

        ib_shop = (ImageButton) findViewById(R.id.ib_shop);

        // Update money
        s.setCurrentScore(2000);
        money = s.getCurrentScore();

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
                ib_shop.setVisibility(View.INVISIBLE);
                ib_shop.setClickable(true);
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
                ib_shop.setVisibility(View.VISIBLE);
                ib_shop.setClickable(false);

                String s_time = t.stopTimer();
                workout = cw.getWorkout();
                formatMessage(workout, s_time);

                int minutes = t.getMinutes();

                // If challenge is completed, then add score
                if (idc.checkCompletion(daily_challenge, minutes, workout))
                {
                    money = s.getCurrentScore();
                    money += 1;

                    tv_scoreCounter.setText(Integer.toString(money));
                    s.setCurrentScore(money);
                }

                if (iwc.checkCompletion(weekly_challenge, minutes, workout))
                {
                    money = s.getCurrentScore();
                    money += 5;

                    tv_scoreCounter.setText(Integer.toString(money));
                    s.setCurrentScore(money);
                }
            }
        });

        ib_pauseWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t.pauseTimer();
            }
        });

        ib_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShopScreen.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Formats the string that is going to be saved to the file
     * @param workout String workout
     * @param s_time String time
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

    /**
     * Creates a popup dialog that lets the user choose a workout
     */
    private void createDialog()
    {
        cw.showDialog(t);
    }


    /**
     * Takes care when app is resumed
     */
    @Override
    protected void onResume() {

        super.onResume();

        int background;
        tv_scoreCounter.setText(Integer.toString(s.getCurrentScore()));

        background = g.getCurrentGym();

        switch(background){
            case -1:
                iv_background.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundList[0]));
                break;
            case 0:
                iv_background.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundList[0]));
                break;
            case 1:
                iv_background.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundList[1]));
                break;
            case 2:
                iv_background.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundList[2]));
                break;
            case 3:
                iv_background.setBackgroundDrawable(ContextCompat.getDrawable(this, backgroundList[3]));
                break;
        }

    }
}
