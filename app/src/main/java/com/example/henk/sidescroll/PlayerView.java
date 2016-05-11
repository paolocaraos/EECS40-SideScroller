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
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runL0));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runL1));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runL2));
        spriteFactory.addRunningSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.runL3));
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
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL0));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL1));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL2));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL3));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL4));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL5));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL6));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL7));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL8));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL9));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL10));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL11));
        spriteFactory.addIdleSpriteL(BitmapFactory.decodeResource(getResources(), R.mipmap.standL12));


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