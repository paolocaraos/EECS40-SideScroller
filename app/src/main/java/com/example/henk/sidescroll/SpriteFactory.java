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
    Vector<Bitmap> projectileL = new Vector<Bitmap>(4,1);
    Vector<Bitmap> projectileR = new Vector<Bitmap>(4,1);
    Vector<Bitmap> impactL = new Vector<Bitmap>(5,1);
    Vector<Bitmap> impactR = new Vector<Bitmap>(5,1);
    Vector<Bitmap> moltresL = new Vector<Bitmap>(4,1);
    Bitmap block;
    Bitmap cloud;
    Bitmap rarecandy;
    Bitmap background;

    void addBackgroundSprite (Bitmap sprite) { background = sprite; }

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

    void addProjectileLSprite(Bitmap sprite) { projectileL.add(sprite); }

    void addProjectileRSprite(Bitmap sprite) { projectileR.add(sprite); }

    void addImpactRSprite(Bitmap sprite) { impactR.add(sprite); }

    void addImpactLSprite(Bitmap sprite) { impactL.add(sprite); }

    void addMoltresLSprite(Bitmap sprite) { moltresL.add(sprite); }

    void addBlockSprite(Bitmap sprite){
        block = sprite;
    }

    void addCloudSprite(Bitmap sprite) { cloud = sprite; }

    void addRareCandySprite(Bitmap sprite) { rarecandy = sprite; }


    Bitmap getCloud() { return cloud; }

    Bitmap getBlock(){
        return block;
    }

    Vector<Bitmap> getFlyingRVector(){
        return flyingR;
    }

    Vector<Bitmap> getFlyingLVector(){
        return flyingL;
    }

    Vector<Bitmap> getProjectileL(){ return projectileL;}

    Vector<Bitmap> getProjectileR() { return  projectileR;}

}
