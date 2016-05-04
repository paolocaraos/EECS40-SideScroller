package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by Henk on 2016/5/4.
 */
public class Terrain {
    int x;
    int y;

    boolean destroyable;
    static final int block_length = 60;
    Bitmap icon;
    Rect terrainSpace;

}
