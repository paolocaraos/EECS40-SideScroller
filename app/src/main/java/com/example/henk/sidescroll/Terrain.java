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
        if(isOnScreen){ //draw it
            terrainSpace.set(screenX - blockLength/2, screenY - blockLength/2, screenX + blockLength/2, screenY + blockLength/2);
            canvas.drawBitmap(sprite, null, terrainSpace, null );
        }
    }

    void setUnitCell(World.UnitCell cell){
        this.cell = cell;
        blockLength = cell.getCellLength();

        screenY = cell.getScreenY();
        screenX = cell.getScreenX();
    }


    void update(){
        screenX -= cell.getScrollVelocity();

        isOnScreen = ((screenX <= screenWidth + blockLength) && (screenX >= -blockLength));
    }

}
