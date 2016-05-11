package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Henk on 2016/5/4.
 */
public class Terrain {
    private static final int blockLength = 100;

    public static final int scrollSpeed = 30;

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


        screenX =

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


    void update(int playerCoordinate_X){
       //if coordinates within screen width, set isOnScreen true, else false
    }

    int getBlockLength(){
        return blockLength;
    }
}
