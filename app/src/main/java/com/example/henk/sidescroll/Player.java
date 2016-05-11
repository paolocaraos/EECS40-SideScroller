package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Player {

    public static final int gravity = 5;

    public static final int playerRadius = 50;

    private int screenWidth;
    private int screenHeight;

    private int screenX;
    private int screenY;

    private Vector<Bitmap> runningSprite;
    private Vector<Bitmap> idleSprite;
    private Vector<Bitmap> shootingSprite;
    private Bitmap fallingSprite;

    enum Status{
        IDLE,
        RUNNING,
        SHOOTING,
        FALLING
    }

    Player(SpriteFactory spriteFactory, int screenWidth, int screenHeight){
        runningSprite = spriteFactory.getRunningVector();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        screenX = screenWidth/2;
    }

    void spawn(Terrain block){
        block.getBlockLength();
    }

    void draw(Canvas canvas){


    }


    void update(){

    }

}
