package com.example.henk.sidescroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private Vector<Terrain> terrainList= new Vector<Terrain>(100,5);
    private Player player;

    SpriteFactory spriteFactory;

    Bitmap controls;

    public enum Direction{RIGHT, LEFT}

    public PlayerView(Context context){
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);

        canvas = new Canvas();
    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        Paint p = new Paint(Color.BLUE);
        canvas.drawRect(10, 10, 30, 30, p);
        //draw level
        level.draw(canvas);

        //update level
        level.update();
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
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right0));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right1));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right2));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right3));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right4));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right5));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right6));
        spriteFactory.addFlyingRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.right7));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left0));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left1));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left2));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left3));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left4));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left5));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left6));
        spriteFactory.addFlyingLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.left7));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r1));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r2));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r3));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r4));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r5));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r6));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r7));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r8));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r9));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r10));
        spriteFactory.addFireRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_r11));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l1));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l2));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l3));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l4));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l5));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l6));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l7));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l8));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l9));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l10));
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l11));

        //give terrain, player and enemy their sprites
        for(int i = 0; i < terrainList.size(); i++){
            terrainList.add(new Terrain(spriteFactory.getBlock(), getWidth()));
        }

        player = new Player(spriteFactory, getWidth(), getHeight());
        level = new Level(canvas, getWidth(), getHeight(), terrainList, player, enemyVector);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        gt.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        //Get touch coordinates


        //Switch case



            //Pass direction, level.move(direction)


        return false;
    }

}