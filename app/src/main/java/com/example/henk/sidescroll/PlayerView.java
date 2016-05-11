package com.example.henk.sidescroll;

import android.content.Context;
import android.graphics.Bitmap;
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
        //Initialize bitmaps
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