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

    final int BODY_CHANGE = 0;

    int[] imageList = new int[]
            {
                    R.drawable.body_one,
                    R.drawable.body_two,
                    R.drawable.body_three,
                    R.drawable.body_four
            };

    int imageList_size = imageList.length;
}
