package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Player {

    public static final int playerRadius = 150;
    private int leftBound;
    private int rightBound;
    private int upperBound;
    private int lowerBound;

    private int screenWidth;
    private int screenHeight;

    private int screenX;
    private int screenY;

    private Vector<Bitmap> flyingRSprite;
    private Vector<Bitmap> flyingLSprite;
    private int flyingVectorSize;

    private Vector<Bitmap> fireRSprite;
    private Bitmap fireLSprite;

    private static int currentFrameIndex;
    private Bitmap currentFlyingSprite;

    private Rect playerSpace;

    private Vector<Terrain> terrainList;

    private PlayerView.Direction currentDirection = PlayerView.Direction.RIGHT;

    enum AnimationStatus{
        FLYING,
        SHOOTING,
    }

    Player(SpriteFactory spriteFactory, int screenWidth, int screenHeight){
        flyingRSprite = spriteFactory.getFlyingRVector();
        flyingLSprite = spriteFactory.getFlyingLVector();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        flyingVectorSize = flyingLSprite.size();
        currentFlyingSprite = flyingRSprite.get(0);

        screenX = screenWidth/2;
        screenY = screenHeight/3;

        leftBound = screenX - playerRadius;
        rightBound = screenX + playerRadius;
        upperBound = screenY - playerRadius;
        lowerBound = screenY + playerRadius;

        playerSpace = new Rect();
    }

    void draw(Canvas canvas){
        playerSpace.set(screenX - playerRadius, screenY - playerRadius, screenX + playerRadius, screenY +playerRadius);
        canvas.drawBitmap(currentFlyingSprite, null, playerSpace, null);
    }

    void faceDirection(PlayerView.Direction direction){
        currentDirection = direction;
    }

    void update(){
        currentFlyingSprite = (currentDirection == PlayerView.Direction.RIGHT)?
                flyingRSprite.elementAt(currentFrameIndex++) : flyingRSprite.elementAt(currentFrameIndex++);
        currentFrameIndex = currentFrameIndex % flyingVectorSize;
    }

    Rect getPlayerSpace(){
        return playerSpace;
    }

    int getUpperBound(){
        return upperBound;
    }

    int getLowerBound(){
        return lowerBound;
    }

    int getPlayerRadius(){
        return playerRadius;
    }

    void setTerrainList(Vector<Terrain> terrainList){
        this.terrainList = terrainList;
    }

}
