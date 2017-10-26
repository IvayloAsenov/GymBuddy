package com.example.ivo.gymbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SeeWorkouts extends AppCompatActivity {

    final int data_block = 100;

    TextView tv_data;
    private String data_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_workouts);

        tv_data = (TextView) findViewById(R.id.tv_data);

        data_read = Load();

        String[] workouts_arr = data_read.split("\\["); //Separates the data workouts in an array of workouts

        StringTokenizer st;

        for(String s : workouts_arr) {
            tv_data.append("\n");

            st = new StringTokenizer(s);
            while(st.hasMoreTokens())
                tv_data.append(st.nextToken() + "\t");

        }
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
