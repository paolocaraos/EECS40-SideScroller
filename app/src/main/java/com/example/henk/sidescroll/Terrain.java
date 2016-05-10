package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Henk on 2016/5/4.
 */
public class Terrain {
    private static final int blockLength = 100;

    private int arr_x;
    private int arr_y;

    private boolean solid;
    private boolean isOnScreen;

    private int screenWidth;

    private Bitmap icon;
    private Rect terrainSpace;

    Terrain(Bitmap terrainIcon, int x, int y, int screen_width){
        icon = terrainIcon;
        arr_x = x;
        arr_y = y;

        screenWidth = screen_width;

        this.destroyable = destroyable;

        terrainSpace = new Rect();
    }

    void draw(Canvas canvas, int playerCoordinate){

    }

}
