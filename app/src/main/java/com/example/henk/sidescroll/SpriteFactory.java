package com.example.henk.sidescroll;

import android.graphics.Bitmap;

import java.util.Vector;

/**
 * Created by Paolo on 5/10/2016.
 */
public class SpriteFactory {
    Vector<Bitmap> runningR = new Vector<Bitmap>(5, 1);
    Vector<Bitmap> runningL = new Vector<Bitmap>(5,1);
    Vector<Bitmap> idleR = new Vector<Bitmap>(13, 1);
    Vector<Bitmap> idleL = new Vector<Bitmap>(13,1);
    Bitmap jumpL;
    Bitmap jumpR;
    Bitmap block;

    void addJumpLeftSprite(Bitmap sprite){
        jumpL = sprite;
    }

    void addJumpRightSprite(Bitmap sprite){
        jumpR = sprite;
    }

    void addRunningSpriteR(Bitmap sprite){
        runningR.add(sprite);
    }

    void addRunningSpriteL(Bitmap sprite){
        runningL.add(sprite);
    }

    void addIdleSpriteR(Bitmap sprite){
        idleR.add(sprite);
    }

    void addIdleSpriteL(Bitmap sprite){
        idleL.add(sprite);
    }

    void addBlockSprite(Bitmap sprite){
        block = sprite;
    }

    Bitmap getBlock(){
        return block;
    }

    Vector<Bitmap> getRunningRVector(){
        return runningR;
    }

    Vector<Bitmap> getRunningLVector(){
        return runningL;
    }

}
