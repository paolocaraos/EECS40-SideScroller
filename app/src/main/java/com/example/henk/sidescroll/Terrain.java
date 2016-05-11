package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Henk on 2016/5/4.
 */
public class Terrain {
    private static final int blockLength = 100;
    public static final int scrollSpeedConstant = 30;

    private static int currentScrollVelocity;
    private static Player player;

    private int arr_x;
    private int arr_y;

    private int screenX;
    private int screenY;

    private int screenWidth;

    private boolean solid;
    private boolean isOnScreen;

    private Bitmap sprite;
    private Rect terrainSpace;

    Terrain(Bitmap terrainSprite, int x, int y, int screen_width){
        sprite = terrainSprite;
        arr_x = x;
        arr_y = y;

        screenX = 50 + blockLength*x;
        screenY = 50 + blockLength*y;

        screenWidth = screen_width;

        terrainSpace = new Rect();
    }

    void draw(Canvas canvas){
        if(isOnScreen & solid){
            //draw it

        }

    }

    void setSolid(){
        solid = true;
    }

    void deSolidify(){ solid = false; }


    void update(){
        isOnScreen = ((screenX <= screenWidth + blockLength) && (screenX >= -blockLength));
        screenX += currentScrollVelocity;
    }

    void setCurrentScrollVelocity(PlayerView.Direction direction){
        switch(direction){
            case STAY:
                currentScrollVelocity = 0;
                break;
            case RIGHT:
                currentScrollVelocity = scrollSpeedConstant;
                break;
            case LEFT:
                currentScrollVelocity = -scrollSpeedConstant;
                break;
            default:
                break;
        }
    }

    int getBlockLength(){
        return blockLength;
    }

    void setPlayer(Player player){
        this.player = player;
    }
}
