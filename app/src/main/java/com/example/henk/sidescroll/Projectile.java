package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Projectile {

    private final int speedConstant = 30;
    private int velocity;

    private int screenWidth;
    private int screenHeight;

    private int screenX;
    private int screenY;
    private int radius;

    private boolean isActive;

    private Vector<Bitmap> leftSpriteVector;
    private Vector<Bitmap> rightSpriteVector;
    private Vector<Bitmap> currentSpriteVector;
    private Bitmap currentSprite;

    private int currentSpriteIndex;
    private int spriteVectorSize;

    PlayerView.Direction direction;

    private SpriteFactory spriteFactory;

    private Vector<Terrain> terrainVector;
    private Vector<Enemy> enemyVector;

    private Rect space;

    Projectile(SpriteFactory f, int screenWidth, int screenHeight, Vector<Terrain> terrainVector, Vector<Enemy> enemyVector, int radius){
        spriteFactory = f;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.terrainVector = terrainVector;
        this.enemyVector = enemyVector;

        leftSpriteVector = f.getProjectileL();
        rightSpriteVector = f.getProjectileR();

        spriteVectorSize = rightSpriteVector.size();

        space = new Rect();

        this.radius = radius;
    }

    void shoot(Player player) {
        if(!isActive){
            direction = player.getFaceDirection();

            screenY = player.getPositionY();

            switch (direction) {
                case RIGHT:
                    screenX = player.getRightBound();
                    currentSpriteVector = rightSpriteVector;
                    velocity = speedConstant;
                    break;
                case LEFT:
                    screenX = player.getLeftBound();
                    currentSpriteVector = leftSpriteVector;
                    velocity = -speedConstant;
                    break;
                default:
                    break;
            }

            currentSprite = currentSpriteVector.elementAt(currentSpriteIndex++);
            isActive = true;
        }
    }

    boolean getStatus(){
        return isActive;
    }

    void update(){
        if(isActive) {
            currentSprite = currentSpriteVector.elementAt(currentSpriteIndex++);
            currentSpriteIndex %= spriteVectorSize;

            screenX += velocity;

            if (collision()) {
                System.out.println("Resetting");
                deactivate();
            }
        }
    }

    void draw(Canvas canvas){
        if(isActive) {
            space.set(screenX - radius, screenY - radius, screenX + radius, screenY + radius);
            canvas.drawBitmap(currentSprite, null, space, null);
        }
    }

    private boolean collision(){
        if(screenX > screenWidth | screenX < 0)
            return true;

        for(int i = 0; i < terrainVector.size() & terrainVector.elementAt(i).getStatus(); i++){
            if(space.intersects( space, terrainVector.elementAt(i).getSpace())){
                return true;
            }
        }
        return false;
        //Check for enemy collision
    }

    private void deactivate(){
        isActive = false;
        currentSpriteIndex = 0;
    }
}
