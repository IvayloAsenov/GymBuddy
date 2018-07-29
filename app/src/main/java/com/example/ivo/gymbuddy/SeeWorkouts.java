package com.example.ivo.gymbuddy;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SeeWorkouts extends AppCompatActivity {

    final int data_block = 100;
    private String data_read;

    int count = 0;

    private Context context;
    LinearLayout linearLayout;

    private TextView workoutType;
    private TextView date;
    private TextView duration;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_workouts);

        context = getApplicationContext();
        linearLayout = (LinearLayout) findViewById(R.id.rl);

        data_read = Load();
        String[] workouts_arr = data_read.split("\\["); //Separates the data workouts in an array of workouts

        StringTokenizer st;

        workoutType = new TextView(context);
        date = new TextView(context);
        duration = new TextView(context);
        cardView = new CardView(context);

        for(String s : workouts_arr) {
            st = new StringTokenizer(s);
            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                if(count == 0) {
                    date = makeText(token);
                } else if(count == 1) {
                    workoutType = makeText(token);
                } else {
                    duration = makeText(token);
                    count = 0;
                }

                count++;
            }

            cardView = makeCard(workoutType, date, duration);
            linearLayout.addView(cardView);
        }
    }

    private CardView makeCard(TextView workoutType, TextView date, TextView duration) {
        CardView cardView = new CardView(context);
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT
        );

        cardView.setLayoutParams(params);
        cardView.setRadius(9);
        cardView.setContentPadding(15, 15, 15, 15);
        cardView.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));
        cardView.setMaxCardElevation(15);
        cardView.setCardElevation(9);

        LinearLayout lr = new LinearLayout(context);
        lr.setLayoutParams(params);
        lr.setOrientation(LinearLayout.HORIZONTAL);

        lr.addView(date);
        lr.addView(workoutType);
        lr.addView(duration);

        cardView.addView(lr);

        return cardView;
    }

    private TextView makeText(String token) {
        TextView textView = new TextView(context);
        Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT
        );

        textView.setText(token);
        return textView;
    }
    public String Load()
    {
        try {
            FileInputStream fis = openFileInput("text.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            char[] data = new char[data_block];
            String final_data="";
            int size;

            try {
                while((size = isr.read(data)) > 0)
                {
                    String read_data = String.copyValueOf(data, 0, size);
                    final_data += read_data;
                    data = new char[data_block];
                }
                return final_data;
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return "error";
    }
}
