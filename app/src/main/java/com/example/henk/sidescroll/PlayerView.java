package com.example.henk.sidescroll;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private Vector<Terrain> terrainList= new Vector<Terrain>(200,5);
    private Player player;

    SpriteFactory spriteFactory;

    private int screenWidth;
    private int screenHeight;

    Bitmap up;
    Bitmap down;
    Bitmap left;
    Bitmap right;
    Bitmap shoot;
    Rect upSpace;
    Rect downSpace;
    Rect leftSpace;
    Rect rightSpace;
    Rect shootSpace;
    Rect healthBar;
    Rect currentHealth;
    Paint paint;

    Bitmap controls;

    public enum Direction{RIGHT, LEFT, UP, DOWN, STOP}

    public PlayerView(Context context){
        super(context);

        getHolder().addCallback(this);
        setFocusable(true);

        canvas = new Canvas();
    }

    public void draw(Canvas canvas){
        canvas.drawColor(Color.WHITE);
        //draw level
        level.draw(canvas);

        //health
        healthBar.set(screenWidth - 170, screenHeight - 592, screenWidth - 70, screenHeight - 92);
        currentHealth.set(screenWidth - 165, screenHeight - 587, screenWidth - 75, screenHeight - 97);
        //border
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(healthBar,paint);
        //fill
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GREEN);
        canvas.drawRect(currentHealth,paint);

        //draw arrowkeys;
        upSpace.set(screenWidth / 2 - 100, screenHeight - 642, screenWidth / 2 + 100, screenHeight - 442);
        downSpace.set(screenWidth/2 - 100, screenHeight - 242, screenWidth/2 + 100, screenHeight- 42);
        leftSpace.set(screenWidth/2 - 300, screenHeight - 442 ,screenWidth/2 - 100, screenHeight - 242);
        rightSpace.set(screenWidth/2 + 100, screenHeight - 442, screenWidth/2 + 300, screenHeight - 242);
        shootSpace.set(screenWidth/2 - 100, screenHeight - 442, screenWidth/2 + 100, screenHeight - 242);
        canvas.drawBitmap(up, null, upSpace, null);
        canvas.drawBitmap(down, null, downSpace, null);
        canvas.drawBitmap(left, null, leftSpace, null);
        canvas.drawBitmap(right, null, rightSpace, null);
        canvas.drawBitmap(shoot, null, shootSpace, null);

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
        gt = new GameThread(this);
        gt.start();

        upSpace = new Rect();
        downSpace = new Rect();
        leftSpace = new Rect();
        rightSpace = new Rect();
        shootSpace = new Rect();
        healthBar = new Rect();
        currentHealth = new Rect();


        //arrowkey
        up = BitmapFactory.decodeResource(getResources(),R.mipmap.up);
        down = BitmapFactory.decodeResource(getResources(),R.mipmap.down);
        left = BitmapFactory.decodeResource(getResources(),R.mipmap.left);
        right = BitmapFactory.decodeResource(getResources(),R.mipmap.right);
        shoot = BitmapFactory.decodeResource(getResources(),R.mipmap.shoot);

        spriteFactory = new SpriteFactory();
        //Initialize bitmaps

        //initiate spritefactory and pass the bitmaps in

        spriteFactory.addBlockSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.brickblock));
        //spriteFactory.addCloudSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.lowrescloud));
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

        /*
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
        spriteFactory.addFireLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.fire_l11));
        */

        spriteFactory.addProjectileLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_l0));
        spriteFactory.addProjectileLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_l1));
        spriteFactory.addProjectileLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_l2));
        spriteFactory.addProjectileLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_l3));
        spriteFactory.addProjectileRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_r0));
        spriteFactory.addProjectileRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_r1));
        spriteFactory.addProjectileRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_r2));
        spriteFactory.addProjectileRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.projectile_r3));


        spriteFactory.addImpactLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_l0));
        spriteFactory.addImpactLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_l1));
        spriteFactory.addImpactLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_l2));
        spriteFactory.addImpactLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_l3));
        spriteFactory.addImpactLSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_l4));
        spriteFactory.addImpactRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_r0));
        spriteFactory.addImpactRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_r1));
        spriteFactory.addImpactRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_r2));
        spriteFactory.addImpactRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_r3));
        spriteFactory.addImpactRSprite(BitmapFactory.decodeResource(getResources(), R.mipmap.impact_r4));

        screenHeight = getHeight();
        screenWidth = getWidth();

        //spriteFactory.addBackground(BitmapFactory.decodeResource(getResources(), R.mipmap.sidescroll_background));

        //give terrain, player and enemy their sprites
        for(int i = 0; i < terrainList.capacity(); i++){
            terrainList.add(new Terrain(spriteFactory.getBlock(), getWidth()));
        }

        player = new Player(spriteFactory, getWidth(), getHeight());
        level = new Level(canvas, getWidth(), getHeight(), terrainList, player, enemyVector, spriteFactory);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        gt.interrupt();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        //Get touch coordinates
        float touch_X = e.getX();
        float touch_Y = e.getY();
        //Switch case
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(touch_X >= (screenWidth/2 - 100) && touch_X <= (screenWidth/2 + 100)
                        && touch_Y >= (screenHeight - 642) && touch_Y <= (screenHeight - 442)){
                    //move up
                    level.move(Direction.UP);
                }
                else if(touch_X >= (screenWidth/2 - 100) && touch_X <= (screenWidth/2 + 100)
                        && touch_Y >= (screenHeight - 242) && touch_Y <= (screenHeight- 42)){
                    //move down
                    level.move(Direction.DOWN);
                }
                else if(touch_X >= (screenWidth/2 - 300) && touch_X <= (screenWidth/2 - 100)
                        && touch_Y >= (screenHeight - 442) && touch_Y <= (screenHeight - 242)){
                    //move left
                    level.move(Direction.LEFT);
                }
                else if(touch_X >= (screenWidth/2 + 100) && touch_X <= (screenWidth/2 + 300)
                        && touch_Y >= (screenHeight - 442) && touch_Y <= (screenHeight - 242)){
                    //move right
                    level.move(Direction.RIGHT);
                }
                else if(touch_X >= (screenWidth/2 - 100) && touch_X <= (screenWidth/2 + 100)
                        && touch_Y >= (screenHeight - 442) && touch_Y <= (screenHeight - 242)){
                    //shoot projectile
                    level.move(Direction.STOP);
                    player.shoot();
                }
                break;
            case MotionEvent.ACTION_UP:
                //stop
                level.move(Direction.STOP);
                break;
        }


        return false;
    }

}