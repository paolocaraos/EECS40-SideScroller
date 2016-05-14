package com.example.henk.sidescroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class PlayerView extends SurfaceView implements SurfaceHolder.Callback{

    private GameThread gt;

    private Level level;

    Canvas canvas;

    private Vector<Enemy> enemyVector = new Vector<Enemy>(3, 2);
    private Terrain[][] terrainGrid = new Terrain[100][7];
    private Player player;

    SpriteFactory spriteFactory;

    Bitmap controls;

    public PlayerView(Context context){
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);

        canvas = new Canvas();
    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        //draw level

        //update level
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,
                               int format, int width, int height){
        // Respond to surface changes
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        spriteFactory = new SpriteFactory();
        //Initialize bitmaps

        //initiate spritefactory and pass the bitmaps in
        spriteFactory.addBlockSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.brickblock));
        spriteFactory.addRunningSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.run0));
        spriteFactory.addRunningSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.run1));
        spriteFactory.addRunningSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.run2));
        spriteFactory.addRunningSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.run3));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runleft0));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runleft1));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runleft2));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runleft3));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand0));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand1));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand2));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand3));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand4));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand5));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand6));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand7));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand8));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand9));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand10));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand11));
        spriteFactory.addIdleSpriteR(BitmapFactory.decodeResource(getResources(), R.mipmap.stand12));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft0));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft1));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft2));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft3));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft4));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft5));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft6));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft7));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft8));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft9));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft10));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft11));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standleft12));



        //give terrain, player and enemy their sprites
        for(int i = 0; i < terrainGrid.length; i++){
            for(int j = 0; j < terrainGrid[i].length; j++){
                terrainGrid[i][j] = new Terrain(spriteFactory.getBlock(),i,j,getWidth());
            }
        }

        player = new Player(spriteFactory, getWidth(), getHeight());
        level = new Level(canvas, getWidth(), getHeight(), terrainGrid, player, enemyVector);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        gt.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        return false;
    }

}