package com.example.ivo.gymbuddy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by Ivo on 11/9/2017.
 */

public class SaveFile extends AppCompatActivity{

    Context context;

    public SaveFile(Context c)
    {
        context = c;
    }

    public void saveToFile(String message)
    {
        FileOutputStream fou = null;
        try {
            fou = context.openFileOutput("text.txt", MODE_APPEND | MODE_PRIVATE);
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

    public void saveToFile(User newUser) {
        FileOutputStream fou = null;
        try {
            fou = context.openFileOutput("text2.txt", MODE_APPEND | MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fou);
            try {
                osw.write(newUser.getName());
                osw.write(newUser.getEmail());
                osw.write(newUser.getAge());
                osw.write(newUser.getGender());
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
