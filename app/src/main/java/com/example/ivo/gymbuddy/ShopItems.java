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

    protected boolean buyGym(int position, int money){

        switch (position){
            case 0:
                if(money >= 10)
                    return true;
                return false;

            case 1:
                if(money >= 50)
                    return true;
                return false;

            case 2:
                if(money >= 200)
                    return true;
                return false;
        }
        return false;
    }
}
