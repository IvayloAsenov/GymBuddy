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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_LONG;

public class ShopScreen extends AppCompatActivity {

    ViewPager viewPager;

    ImageButton ib_buy;
    ImageButton ib_setBg;

    TextView tv_status;

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

        tv_status = (TextView) findViewById(R.id.tv_statusText);

        ownedGyms = g.getOwnedGyms();

        setButtons();

        ib_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((deductMoney = si.buyGym(viewPager.getCurrentItem(), money)) != -1){
                    // had enough money to buy DONE
                    // change button to set background
                    // update money DONE
                    money = money - deductMoney;
                    s.setCurrentScore(money);

                    g.saveGym(viewPager.getCurrentItem());
                    Toast.makeText(getApplicationContext(), "BOUGHT GYM" + viewPager.getCurrentItem(), LENGTH_LONG).show();

                    ib_buy.setClickable(false);
                    ib_buy.setVisibility(View.INVISIBLE);

                    ib_setBg.setVisibility(View.VISIBLE);
                    ib_setBg.setClickable(true);

                }else{
                    // didn't have enough money to buy DONE
                    // make button shake with red text??
                    Toast.makeText(getApplicationContext(), "NOT ENOUGH MONEY", LENGTH_LONG).show();
                }
            }
        });

        ib_setBg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                g.setCurrentGym(viewPager.getCurrentItem());

                // Change background from previous activity?
                ib_setBg.setClickable(false);
                ib_setBg.setVisibility(View.INVISIBLE);

                tv_status.setVisibility(View.VISIBLE);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(getApplicationContext(), "Page Scrolled", LENGTH_LONG).show();
                ownedGyms = g.getOwnedGyms();
                tv_status.setVisibility(View.INVISIBLE);
                setButtons();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setButtons(){
        if(ownedGyms.get(viewPager.getCurrentItem()) == 1){
            ib_buy.setVisibility(View.INVISIBLE);
            ib_buy.setClickable(false);

            ib_setBg.setVisibility(View.VISIBLE);
            ib_setBg.setClickable(true);
        }else{
            ib_buy.setVisibility(View.VISIBLE);
            ib_buy.setClickable(true);

            ib_setBg.setVisibility(View.INVISIBLE);
            ib_setBg.setClickable(false);
        }

        if(viewPager.getCurrentItem() == g.getCurrentGym()){
            tv_status.setVisibility(View.VISIBLE);
            ib_setBg.setVisibility(View.INVISIBLE);
            ib_setBg.setClickable(false);
        }
    }
}
