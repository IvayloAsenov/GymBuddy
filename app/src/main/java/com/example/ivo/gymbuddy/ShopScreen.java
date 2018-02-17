package com.example.ivo.gymbuddy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class ShopScreen extends AppCompatActivity {

    ViewPager viewPager;
    ImageButton ib_buy;

    int money;

    ShopItems si;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_screen);

        si = new ShopItems(this);

        money = getIntent().getIntExtra("MONEY", 0);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter vpa = new ViewPagerAdapter(this);
        viewPager.setAdapter(vpa);

        ib_buy = (ImageButton) findViewById(R.id.ib_buy);

        ib_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "this is " + viewPager.getCurrentItem(), LENGTH_LONG).show();
                if(si.buyGym(viewPager.getCurrentItem(), money)){
                    // had enough money to buy
                    // change button to set background
                    // update money
                    Toast.makeText(getApplicationContext(), "BOUGHT GYM", LENGTH_LONG).show();
                    money = 0;
                }else{
                    // didn't have enough money to buy
                    // display error message
                    Toast.makeText(getApplicationContext(), "NOT ENOUGH MONEY", LENGTH_LONG).show();
                }
            }
        });
    }

    public int getMoney(){
        return money;
    }
}
