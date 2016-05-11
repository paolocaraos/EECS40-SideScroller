package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Player {

    public static final int gravity = 5;

    private int screenX;
    private int screenY;

    private Vector<Bitmap> runningSprite;
    private Vector<Bitmap> idleSprite;
    private Vector<Bitmap> shootingSprite;

    enum Status{
        IDLE,
        RUNNING,
        SHOOTING
    }

    Player(int screenWidth, int screenHeight){

    }

    void draw(Canvas canvas){

    }


    void update(){

    }

}
