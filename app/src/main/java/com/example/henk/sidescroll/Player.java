package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Player {

    private int screenX;
    private int screenY;

    private Bitmap[] runningSprite;
    private Bitmap[] idleSprite;
    private Bitmap[] shootingSprite;

    enum Status{
        IDLE,
        RUNNING,
        SHOOTING
    }

    Player(int screenWidth, int screenHeight, ){

    }

    void draw(Canvas canvas){

    }


    void update(){

    }
}
