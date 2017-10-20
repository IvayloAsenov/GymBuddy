package com.example.ivo.gymbuddy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Home extends AppCompatActivity implements BodyTypes{

    int current_body = 0;
    Button but;
    ImageView body_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        but = (Button) findViewById(R.id.button);
        body_type = (ImageView) findViewById(R.id.body_type);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(current_body+1 >= imageList_size)
                    current_body=-1;

                body_type.setImageResource(imageList[current_body+1]);
                current_body++;
            }
        });
    }

}
