package com.example.ivo.gymbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Jonathan on 2017-11-13.
 */

public class RegistrationScreen extends AppCompatActivity implements AgesGenders {
    EditText et_name;
    EditText et_email;
    EditText et_password;
    Button b_register;
    Spinner s_age;
    Spinner s_gender;
    User newUser;
    SaveFile sf_users;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        et_password = (EditText) findViewById(R.id.editText3);

        sf_users = new SaveFile(this);

        et_name = (EditText) findViewById(R.id.editText);

        s_age = (Spinner) findViewById(R.id.spinner2);

        s_gender = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> adapterAges = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, ages);
        s_age.setAdapter(adapterAges);

        ArrayAdapter<String> adapterGenders = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        s_gender.setAdapter(adapterGenders);

        b_register = (Button) findViewById(R.id.bt_register);
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                newUser = createUser();
                sf_users.saveToFile(newUser);

            }
        });

    }

    protected User createUser() {
        String userName = et_name.getText().toString();
        String userEmail = et_email.getText().toString();
        String userPassword = et_password.getText().toString();
        String userAge = s_age.getSelectedItem().toString();
        String userGender = s_gender.getSelectedItem().toString();
        return new User(userName, userEmail, userPassword, userAge, userGender);
    }

}

