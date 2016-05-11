package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

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

    private Vector<Bitmap> runningSpriteR;
    private Vector<Bitmap> runningSpriteL;
    private Vector<Bitmap> idleSpriteR;
    private Vector<Bitmap> idleSpriteL;
    private Vector<Bitmap> shootingSprite;
    private Bitmap fallingSprite;

    private Rect playerSpace;

    private Terrain[][] terrainGrid;

    enum Status{
        IDLE,
        RUNNING,
        SHOOTING,
        FALLING
    }

    Player(SpriteFactory spriteFactory, int screenWidth, int screenHeight){
        runningSpriteR = spriteFactory.getRunningRVector();
        runningSpriteL = spriteFactory.getRunningLVector();
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

    Rect getPlayerSpace(){
        return playerSpace;
    }

    void setTerrainGrid(Terrain[][] terrainGrid){
        this.terrainGrid = terrainGrid;
    }

}
