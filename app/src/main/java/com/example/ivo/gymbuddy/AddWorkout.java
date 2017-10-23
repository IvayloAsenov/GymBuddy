package com.example.ivo.gymbuddy;

import android.icu.util.Output;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
    Button b_load;
    TextView textview;

    int data_block = 100; //Reads 100 bytes at a time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        spinner_workouts = (Spinner) findViewById(R.id.workouts_spinner);
        b_save = (Button) findViewById(R.id.b_save);
        b_load = (Button) findViewById(R.id.b_load);
        textview = (TextView) findViewById(R.id.text);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, workouts_list);

        spinner_workouts.setAdapter(adapter);

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        b_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Load();
            }
        });
    }


    /*
        Save/Load methods used to save data on file and read from it
        in Append | Private mode
     */

    public void Save()
    {
        String message = spinner_workouts.getSelectedItem().toString();
        FileOutputStream fou = null;

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

    public void Load()
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
                textview.setText(final_data);
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
