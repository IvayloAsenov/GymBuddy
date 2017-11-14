package com.example.ivo.gymbuddy;

import android.content.Context;
import android.content.SharedPreferences;
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
        SharedPreferences userPreferences = context.getSharedPreferences(newUser.getName(), MODE_PRIVATE);
        /**For later implementation */
        // String userExist = userPreferences.getString(newUser.getName(), null);

        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString("email", newUser.getEmail());
        editor.putString("password", newUser.getPassowrd());
        editor.putString("age", newUser.getAge());
        editor.putString("gender", newUser.getGender());
        editor.commit();
    }
}
