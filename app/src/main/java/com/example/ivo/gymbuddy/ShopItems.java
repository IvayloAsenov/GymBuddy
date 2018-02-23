package com.example.ivo.gymbuddy;

import android.app.Activity;

/**
 * Created by Ivo on 2/15/2018.
 */

class ShopItems {

    Activity activity;

    protected ShopItems(Activity a){
        activity = a;
    }

    protected int buyGym(int position, int money){

        switch (position){
            case 0:
                if(money >= 10)
                    return 10;
                return -1;

            case 1:
                if(money >= 50)
                    return 50;
                return -1;

            case 2:
                if(money >= 200)
                    return 200;
                return -1;
        }
        return -1;
    }
}
