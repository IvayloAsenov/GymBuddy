package com.example.ivo.gymbuddy;

import java.text.SimpleDateFormat;
//import android.icu.util.Calendar;
import java.util.Calendar;
import android.icu.util.Output;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AddWorkout extends AppCompatActivity implements Workouts {

    Spinner spinner_workouts;
    Button b_save;
    EditText et_time;
    Button b_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        spinner_workouts = (Spinner) findViewById(R.id.workouts_spinner);
        b_save = (Button) findViewById(R.id.b_save);
        et_time = (EditText) findViewById(R.id.et_time);

        b_reset = (Button) findViewById(R.id.b_reset);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, workouts_list);

        spinner_workouts.setAdapter(adapter);

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


    /*
        Save method used to save data on file and read from it
        in Append | Private mode
     */
    public void Save()
    {
        String message;
        String s_workout = spinner_workouts.getSelectedItem().toString();
        String s_time = et_time.getText().toString();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        message = "[" + formattedDate + " " + s_workout + " " + s_time + " ";
        saveFile(message);
    }

    public void Save(String s_workout, String s_time)
    {
        String message;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());

        message = "[" + formattedDate + " " + s_workout + " " + s_time + " ";
        saveFile(message);
    }

    public void saveFile(String message)
    {
        FileOutputStream fou = null;

        WorkoutCounter wc = new WorkoutCounter(this);

        int workout_counter = wc.readWCounter();
        wc.writeWCounter();
        wc.setWCounter(workout_counter+1);

        workout_counter = wc.getWCounter();

        if(workout_counter > 2)
        {
            wc.setWCounter(0);
            Toast.makeText(getApplicationContext(), " test", Toast.LENGTH_LONG).show();
        }

        try {
            fou = openFileOutput("text.txt", MODE_APPEND | MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fou);
            try {
                osw.write(message);
                osw.flush();
                osw.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
