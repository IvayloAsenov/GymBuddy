package com.k9.ivo.gymbuddy;

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
            case 1:
                if(money >= 100)
                    return 100;
                return -1;

            case 2:
                if(money >= 300)
                    return 300;
                return -1;

            case 3:
                if(money >= 850)
                    return 850;
                return -1;
        }
        return -1;
    }
}
