package com.example.ivo.gymbuddy;

import java.text.SimpleDateFormat;
//import android.icu.util.Calendar;
import java.util.Calendar;

import android.content.Context;
import android.icu.util.Output;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Let's the user add a workout he has already completed
 *
 * @author IvayloA
 */
public class AddWorkout extends AppCompatActivity implements Workouts {

    Spinner spinner_workouts;
    Button b_save;
    EditText et_time_hours;
    EditText et_time_minutes;
    Button b_reset;
    Context home_context;
    SaveFile sf;
    TextView tv_muscle;

    /**
     * Create method
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        sf = new SaveFile(this);

        spinner_workouts = (Spinner) findViewById(R.id.workouts_spinner);
        b_save = (Button) findViewById(R.id.b_save);
        et_time_hours = (EditText) findViewById(R.id.et_time_hours);
        et_time_minutes = (EditText) findViewById(R.id.et_time_minutes);
        tv_muscle = (TextView) findViewById(R.id.tv_muscle);

        b_reset = (Button) findViewById(R.id.b_reset);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, workouts_list);

        spinner_workouts.setAdapter(adapter);

        spinner_workouts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = getWorkoutFromId(id);
                tv_muscle.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fou = openFileOutput("text.txt", MODE_PRIVATE);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private String getWorkoutFromId(long id) {
        if (id == 0)      return "Chest";
        else if (id == 1) return "Back";
        else if (id == 2) return "Shoulders";
        else if (id == 3) return "Arms";
        else if (id == 4) return "Legs";
        else if (id == 5) return "Abs";
        else if (id == 6) return "Run";
        else if (id == 7) return "Other";

        else return "Other";
    }
    /**
     * Save method used to save data on file and read from it in
     * Append | Private mode
     */
    public void Save()
    {
        String message;
        String s_workout = spinner_workouts.getSelectedItem().toString();

        String s_time_hours = et_time_hours.getText().toString();
        String s_time_minutes = et_time_minutes.getText().toString();

        if(s_time_hours.equals("") || s_time_minutes.equals("")) {
            Toast.makeText(this.getApplicationContext(), "Bad input!", Toast.LENGTH_SHORT);
            return;
        }

        int hours = Integer.parseInt(s_time_hours);
        int minutes = Integer.parseInt(s_time_minutes);

        if(minutes >= 60) {
            Toast.makeText(this.getApplicationContext(), "Bad input!", Toast.LENGTH_LONG);
            return;
        }

        String s_minutes = (minutes < 10) ? "0" + Integer.toString(minutes) : Integer.toString(minutes);
        String s_hours = (hours == 0) ? "0" : Integer.toString(hours);

        String s_time = s_hours + ":" + String.format("%02d", minutes)
                + ":" + "00";

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        message = "[" + formattedDate + " " + s_workout + " " + s_time + " ";

        Toast.makeText(this.getApplicationContext(), "Workout successfully logged!", Toast.LENGTH_SHORT);
        sf.saveToFile(message);
    }
}
