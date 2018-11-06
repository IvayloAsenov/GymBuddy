package com.k9.ivo.gymbuddy;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView workoutIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_workouts);

        context = getApplicationContext();
        linearLayout = (LinearLayout) findViewById(R.id.rl);

        data_read = Load();
        String[] workouts_arr = data_read.split("\\["); //Separates the data workouts in an array of workouts

        StringTokenizer st;

        for(String s : workouts_arr) {

            workoutType = new TextView(context);
            date = new TextView(context);
            duration = new TextView(context);
            cardView = new CardView(context);
            workoutIcon = new ImageView(context);

            st = new StringTokenizer(s);
            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                if(count == 0) {
                    date = makeText(token);
                    date.setTextSize(24);
                    date.setTypeface(null, Typeface.BOLD);
                    count++;
                } else if(count == 1) {
                    workoutType = makeText(token);
                    workoutType.setTextSize(18);
                    count++;
                } else {
                    duration = makeText(token);
                    duration.setTextSize(18);
                    count = 0;
                }
            }

            if(!workoutType.getText().toString().equals("")) {
                cardView = makeCard(workoutType, date, duration);
                linearLayout.addView(cardView, 0);
            }
        }
    }

    private CardView makeCard(TextView workoutType, TextView date, TextView duration) {
        CardView cardView = new CardView(context);

        Resources r = context.getResources();
        int widthPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                320,
                r.getDisplayMetrics()
        );

        int heightPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                100,
                r.getDisplayMetrics()
        );

        Toolbar.LayoutParams params = new Toolbar.LayoutParams(
                widthPx,heightPx
        );

        int leftMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                0,
                r.getDisplayMetrics()
        );
        int rightMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                0,
                r.getDisplayMetrics()
        );
        int topMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                0,
                r.getDisplayMetrics()
        );
        int bottomMargin = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15,
                r.getDisplayMetrics()
        );

        params.setMargins(leftMargin,topMargin,rightMargin,bottomMargin);
        params.gravity = Gravity.CENTER_HORIZONTAL;

        cardView.setLayoutParams(params);
        cardView.setRadius(20);

        cardView.setContentPadding(15, 15, 0, 15);
        cardView.setCardBackgroundColor(Color.parseColor("#82CAFA"));
        cardView.setMaxCardElevation(15);
        cardView.setCardElevation(15);

        Toolbar.LayoutParams wrap_content_params = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        Toolbar.LayoutParams match_params_height = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
        );

        Toolbar.LayoutParams match_params = new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );

        LinearLayout main_layout = new LinearLayout(context);

        LinearLayout vertical_layout = new LinearLayout(context);
        vertical_layout.setOrientation(LinearLayout.VERTICAL);
        vertical_layout.setLayoutParams(wrap_content_params);
        LinearLayout horizontal_layout_one = new LinearLayout(context);
        horizontal_layout_one.setLayoutParams(wrap_content_params);
        LinearLayout horizontal_layout_two = new LinearLayout(context);
        horizontal_layout_two.setLayoutParams(wrap_content_params);
        LinearLayout horizontal_layout_three = new LinearLayout(context);
        horizontal_layout_three.setLayoutParams(wrap_content_params);

        LinearLayout vertical_layout_two = new LinearLayout(context);
        vertical_layout_two.setOrientation(LinearLayout.VERTICAL);

        workoutIcon = makeIcon(workoutType.getText().toString());

        vertical_layout_two.addView(workoutIcon);

        horizontal_layout_one.addView(date);
        horizontal_layout_two.addView(duration);
        horizontal_layout_three.addView(workoutType);

        vertical_layout.addView(horizontal_layout_one);
        vertical_layout.addView(horizontal_layout_two);
        vertical_layout.addView(horizontal_layout_three);

        vertical_layout.setLayoutParams(wrap_content_params);

        main_layout.setLayoutParams(params);
        match_params.setMargins(260, 0, 0, 0);
        vertical_layout_two.setLayoutParams(match_params);
        vertical_layout_two.setGravity(Gravity.END);

        main_layout.addView(vertical_layout);
        main_layout.addView(vertical_layout_two);

        cardView.addView(main_layout);

        return cardView;
    }

    private ImageView makeIcon(String workout) {

        ImageView imageView = new ImageView(context);

        if(workout.equals("Chest"))
            imageView.setImageResource(R.drawable.chest);
        else if(workout.equals("Back"))
            imageView.setImageResource(R.drawable.back);
        else if(workout.equals("Shoulders"))
            imageView.setImageResource(R.drawable.shoulders);
        else if(workout.equals("Arms"))
            imageView.setImageResource(R.drawable.arms);
        else if(workout.equals("Legs"))
            imageView.setImageResource(R.drawable.legs);
        else if(workout.equals("Abs"))
            imageView.setImageResource(R.drawable.abs);
        else if(workout.equals("Run"))
            imageView.setImageResource(R.drawable.run);
        else if(workout.equals("Other"))
            imageView.setImageResource(R.drawable.other);


        imageView.setBackgroundColor(Color.TRANSPARENT);
        return imageView;
    }

    private TextView makeText(String token) {
        TextView textView = new TextView(context);
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
