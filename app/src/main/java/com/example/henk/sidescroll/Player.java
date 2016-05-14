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

    public static final int playerRadius = 200;

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

        playerSpace = new Rect();
    }

    void draw(Canvas canvas){
        playerSpace.set(screenX - playerRadius, screenY - playerRadius, screenX + playerRadius, screenY +playerRadius);
        canvas.drawBitmap(currentFlyingSprite, null, playerSpace, null);
    }

    void face(PlayerView.Direction direction){
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

    void setTerrainList(Vector<Terrain> terrainList){
        this.terrainList = terrainList;
    }

}
