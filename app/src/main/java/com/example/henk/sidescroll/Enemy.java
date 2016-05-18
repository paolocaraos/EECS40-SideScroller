package com.example.henk.sidescroll;

import android.graphics.Bitmap;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Enemy {
    private int damage;

    private int screenX;
    private int screenY;

    private int screenWidth;
    private int screenHeight;

    private boolean isAlive;
    private boolean isOnScreen;

    private int scrollSpeedConstant;
    private int verticalSpeedConstant;

    private int health;

    private int expReward;

    private Bitmap currentSprite;
    private Vector<Bitmap> leftSpriteVector;
    private Vector<Bitmap> rightSpriteVector;
    private Vector<Bitmap> currentSpriteVector;

    Enemy(SpriteFactory f, int damage, int speed, int size, int health, int exp){

    }
}
