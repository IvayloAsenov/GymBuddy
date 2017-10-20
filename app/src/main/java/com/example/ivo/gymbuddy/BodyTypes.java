package com.example.ivo.gymbuddy;

/**
 * Created by Ivo on 10/19/2017.
 *
 * Stores every body image in an array
 * where arr[0] is the fattest and i[size]
 * is the fittest
 */


public interface BodyTypes
{
    int[] imageList = new int[]
            {
                    R.drawable.body_1,
                    R.drawable.body_2,
                    R.drawable.body_3,
                    R.drawable.body_4
            };

    int imageList_size = imageList.length;
}
