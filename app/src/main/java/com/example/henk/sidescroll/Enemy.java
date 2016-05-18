package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Enemy {
    private int damage;

    private int screenX;
    private int screenY;
    private int radius;

    private int leftBound;
    private int rightBound;
    private int upperBound;
    private int lowerBound;

    private int screenWidth;
    private int screenHeight;

    private boolean isActive;

    private int verticalSpeedConstant;
    private int verticalVelocity;

    private int expReward;

    private Bitmap currentSprite;
    private Vector<Bitmap> leftSpriteVector;
    private Vector<Bitmap> rightSpriteVector;
    private Vector<Bitmap> currentSpriteVector;
    private int spriteVectorSize;
    private int currentSpriteIndex;

    private Vector<Terrain> terrainVector;

    private World world;

    private Rect space;

    private Player player;

    Enemy(SpriteFactory f, int damage, int speed, int radius, int exp, World world, Vector<Terrain> terrainVector, Player player){
        this.damage = damage;
        this.radius = radius;
        this.player = player;

        leftSpriteVector = f.getMoltresL();
        rightSpriteVector = f.getMoltresR();

        currentSpriteVector = leftSpriteVector;

        spriteVectorSize = currentSpriteVector.size();

        currentSpriteIndex = 0;

        this.terrainVector = terrainVector;

        verticalSpeedConstant = speed;
        verticalVelocity = speed;

        expReward = exp;

        this.world = world;

        space = new Rect();
    }

    void setSpawnPosition(World.UnitCell cell){
        isActive = true;

        screenY = cell.getScreenY();
        screenX = cell.getScreenX();
    }

    void die(){
        isActive = false;
        currentSpriteIndex = 0;
    }

    int getExp(){
        return expReward;
    }

    boolean getStatus(){
        return isActive;
    }

    Rect getSpace(){
        return space;
    }

    void draw(Canvas canvas){
        if(isActive){
            space.set(leftBound, upperBound, rightBound, lowerBound);
            canvas.drawBitmap(currentSprite, null, space, null);
        }
    }



    void update(int scrollVel){
        if(isActive) {
            screenX += scrollVel;

            leftBound = screenX - radius;
            rightBound = screenX + radius;

            if(verticalCollision()){
                verticalVelocity = -verticalVelocity;
            }

            screenY += verticalVelocity;

            if(screenY < world.getUpperBound() + 100 | screenY > world.getLowerBound() -100) {
                verticalVelocity = -verticalVelocity;
                screenY = (world.getLowerBound() - world.getUpperBound()) / 2;
            }

            upperBound = screenY - radius;
            lowerBound = screenY + radius;

            currentSprite = currentSpriteVector.elementAt(currentSpriteIndex++);
            currentSpriteIndex %= spriteVectorSize;

            playerCollision();
        }
    }


    private boolean verticalCollision(){

        if(verticalVelocity + screenY < world.getUpperBound() + 50 | verticalVelocity + screenY > world.getLowerBound() - 50){
            return true;
        }

        Terrain terrain;
        int terrainX, terrainY, terrainRadius;
        for(int i = 0; terrainVector.elementAt(i).getStatus(); i ++){
            terrain = terrainVector.elementAt(i);
            terrainRadius = terrain.getLength()/2 - 30;
            terrainX = terrain.getScreenX();
            terrainY = terrain.getScreenY();

            if(leftBound < terrainX + terrainRadius && rightBound > terrainX - terrainRadius){
                if(verticalVelocity < 0 && terrainY < upperBound){
                    if(space.intersects(space, terrain.getSpace()))
                        return true;
                }else if(verticalVelocity > 0 && terrainY > lowerBound){
                    if(space.intersects(space, terrain.getSpace()))
                        return true;
                }
            }
        }

        return false;
    }

    private void playerCollision(){
        if(space.intersects(player.getPlayerSpace(), space)){
            player.takeDamage(damage);
        }
    }

    void setTerrainVector(Vector<Terrain> v){
        terrainVector = v;
    }
}
