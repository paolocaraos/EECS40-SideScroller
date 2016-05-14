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

    private Vector<Bitmap> flyingRSprite;
    private Vector<Bitmap> flyingLSprite;
    private Vector<Bitmap> fireRSprite;
    private Vector<Bitmap> fireLSprite;

    private Bitmap fallingSprite;

    private Rect playerSpace;

    private Vector<Terrain> terrainList;

    enum Status{
        IDLE,
        RUNNING,
        SHOOTING,
        FALLING,
    }

    Player(SpriteFactory spriteFactory, int screenWidth, int screenHeight){
        flyingRSprite = spriteFactory.getFlyingRVector();
        flyingLSprite = spriteFactory.getFlyingLVector();
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

    void setTerrainList(Vector<Terrain> terrainList){
        this.terrainList = terrainList;
    }

}
