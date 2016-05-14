package com.example.henk.sidescroll;

import android.graphics.Bitmap;

import java.util.Vector;

/**
 * Created by Paolo on 5/10/2016.
 */
public class SpriteFactory {
    Vector<Bitmap> flyingR = new Vector<Bitmap>(8, 1);
    Vector<Bitmap> flyingL = new Vector<Bitmap>(8,1);
    Vector<Bitmap> fireR = new Vector<Bitmap>(11, 1);
    Vector<Bitmap> fireL = new Vector<Bitmap>(11,1);
    Bitmap block;

    void addFlyingRSprite(Bitmap sprite){
        flyingR.add(sprite);
    }

    void addFlyingLSprite(Bitmap sprite){
        flyingL.add(sprite);
    }

    void addFireRSprite(Bitmap sprite){
        fireR.add(sprite);
    }

    void addFireLSprite(Bitmap sprite){
        fireL.add(sprite);
    }

    void addBlockSprite(Bitmap sprite){
        block = sprite;
    }

    Bitmap getBlock(){
        return block;
    }

    Vector<Bitmap> getFlyingRVector(){
        return flyingR;
    }

    Vector<Bitmap> getFlyingLVector(){
        return flyingL;
    }

}
