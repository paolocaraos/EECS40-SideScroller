package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Henk on 2016/5/4.
 */
public class Terrain {
    private static int blockLength;

    private static Player player;

    private int screenX;
    private int screenY;

    private int screenWidth;
    private boolean isOnScreen;

    private Bitmap sprite;
    private Rect terrainSpace;

    private World.UnitCell cell;

    Terrain(Bitmap terrainSprite, int screen_width){
        sprite = terrainSprite;
        screenWidth = screen_width;

        terrainSpace = new Rect();
    }

    void draw(Canvas canvas){
        if(isOnScreen){
            //draw it

        }
    }

    void setUnitCell(World.UnitCell cell){
        this.cell = cell;
        blockLength = cell.getCellLength();
    }


    void update(){
        screenX = cell.getScreenX();
        screenY = cell.getScreenY();

        isOnScreen = ((screenX <= screenWidth + blockLength) && (screenX >= -blockLength));
    }

}
