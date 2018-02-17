package com.example.ivo.gymbuddy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShopScreen extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_screen);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        ViewPagerAdapter vpa = new ViewPagerAdapter(this);

        viewPager.setAdapter(vpa);
    }
}
