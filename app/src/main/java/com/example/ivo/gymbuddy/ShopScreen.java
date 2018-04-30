/**
 * ShopScreen
 *
 * @author Ivaylo Asenov
 */

package com.example.ivo.gymbuddy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class ShopScreen extends AppCompatActivity {

    ViewPager viewPager;

    ImageButton ib_buy;
    ImageButton ib_setBg;

    private int money;
    private int deductMoney;

    ShopItems si;
    Score s;

    ArrayList<Integer> ownedGyms;

    Gym g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_screen);

        g = new Gym(this);
        ownedGyms = new ArrayList<>();

        si = new ShopItems(this);
        s = new Score(this);

        money = s.getCurrentScore();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter vpa = new ViewPagerAdapter(this);
        viewPager.setAdapter(vpa);

        ib_buy = (ImageButton) findViewById(R.id.ib_buy);
        ib_setBg = (ImageButton) findViewById(R.id.ib_setBg);

        ib_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((deductMoney = si.buyGym(viewPager.getCurrentItem(), money)) != -1){
                    // had enough money to buy DONE
                    // change button to set background
                    // update money DONE
                    //Toast.makeText(getApplicationContext(), "BOUGHT GYM", LENGTH_LONG).show();
                    money = money - deductMoney;
                    s.setCurrentScore(money);

                    g.saveGym(viewPager.getCurrentItem());
                    Toast.makeText(getApplicationContext(), "BOUGHT GYM" + viewPager.getCurrentItem(), LENGTH_LONG).show();

                    ib_buy.setClickable(false);
                    ib_buy.setVisibility(View.INVISIBLE);


                }else{
                    // didn't have enough money to buy DONE
                    // display error message
                    Toast.makeText(getApplicationContext(), "NOT ENOUGH MONEY", LENGTH_LONG).show();
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getApplicationContext(), "Page Scrolled", LENGTH_LONG).show();
                ownedGyms = g.getOwnedGyms();

                if(ownedGyms.get(position) == 1){
                    ib_buy.setVisibility(View.INVISIBLE);
                    ib_setBg.setVisibility(View.VISIBLE);
                }else{
                    ib_buy.setVisibility(View.VISIBLE);
                    ib_setBg.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
