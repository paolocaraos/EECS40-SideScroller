package com.example.henk.sidescroll;

import android.graphics.Bitmap;

import java.util.Vector;

/**
 * Created by Paolo on 5/10/2016.
 */
public class SpriteFactory {
    private Vector<Bitmap> running = new Vector<Bitmap>(5, 2);
    private Vector<Bitmap> idle = new Vector<Bitmap>(5, 2);
    private Bitmap block;

    void addRunningSprite(Bitmap sprite){
        running.add(sprite);
    }

    void addIdleSprite(Bitmap sprite){
        idle.add(sprite);
    }

    void addBlockSprite(Bitmap sprite){
        block = sprite;
    }
}