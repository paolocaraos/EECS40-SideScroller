package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Player {

    public static final int playerRadius = 150;
    private int leftBound;
    private int rightBound;
    private int upperBound;
    private int lowerBound;

    private int maxHealth;
    private int currentHealth;

    private int screenWidth;
    private int screenHeight;

    private int screenX;
    private int screenY;

    private final int moveSpeedConstant = 30;
    private int moveVelocity;

    private Vector<Bitmap> flyingRSprite;
    private Vector<Bitmap> flyingLSprite;
    private Vector<Bitmap> currentSpriteVector;
    private Bitmap currentSprite;
    private int flyingVectorSize;

    private Vector<Projectile> projectileVector;
    private int projectileVectorSize;
    private int currentBulletIndex;

    private static int currentFrameIndex;

    private Rect playerSpace;

    private Vector<Terrain> terrainList;
    private World world;

    private PlayerView.Direction currentDirection;
    private PlayerView.Direction faceDirection;

    Player(SpriteFactory spriteFactory, int screenWidth, int screenHeight) {

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        screenX = screenWidth / 2;
        screenY = screenHeight / 3;

        leftBound = screenX - playerRadius;
        rightBound = screenX + playerRadius;
        upperBound = screenY - playerRadius;
        lowerBound = screenY + playerRadius;

        playerSpace = new Rect();

        flyingRSprite = spriteFactory.getFlyingRVector();
        flyingLSprite = spriteFactory.getFlyingLVector();
        flyingVectorSize = flyingRSprite.size();
        currentSpriteVector = flyingLSprite;
        currentSprite = flyingRSprite.elementAt(0);

        currentDirection = PlayerView.Direction.RIGHT;

        moveVelocity = 0;
    }

    void draw(Canvas canvas) {
        playerSpace.set(screenX - playerRadius, screenY - playerRadius, screenX + playerRadius, screenY + playerRadius);
        canvas.drawBitmap(currentSprite, null, playerSpace, null);
    }

    void move(PlayerView.Direction direction) {
        currentDirection = direction;
    }

    PlayerView.Direction getFaceDirection(){
        return faceDirection;
    }

    void update() {
        switch (currentDirection) {
            case LEFT:
                currentSpriteVector = flyingLSprite;
                faceDirection = currentDirection;
                break;
            case RIGHT:
                currentSpriteVector = flyingRSprite;
                faceDirection = currentDirection;
                break;
            case UP:
                moveVelocity = -moveSpeedConstant;
                break;
            case DOWN:
                moveVelocity = moveSpeedConstant;
                break;
            case STOP:
                moveVelocity = 0;
                break;
            default:
                break;
        }
        //enemyCollision

        if(terrainCollision())
            moveVelocity = 0;

        screenY += moveVelocity;

        currentSprite = currentSpriteVector.elementAt(currentFrameIndex++);
        currentFrameIndex %= flyingVectorSize;
    }

    Rect getPlayerSpace(){
        return playerSpace;
    }

    int getUpperBound(){
        return upperBound;
    }

    int getLowerBound(){
        return lowerBound;
    }

    int getRightBound(){
        return  rightBound;
    }

    int getLeftBound(){
        return leftBound;
    }

    int getPositionY(){
        return screenY;
    }

    int getPlayerRadius(){
        return playerRadius;
    }

    void setTerrainList(Vector<Terrain> terrainList){
        this.terrainList = terrainList;
    }

    void setWorld(World world){
        this.world = world;
    }

    void setAmmo(Vector<Projectile> v){
        projectileVector = v;
        projectileVectorSize = v.size();
        currentBulletIndex = 0;
    }

    private boolean terrainCollision(){

        if(moveVelocity + screenY < world.getUpperBound() | moveVelocity + screenY > world.getLowerBound()){
            return true;
        }

        Terrain terrain;
        int terrainX, terrainY, terrainRadius;
        for(int i = 0; terrainList.elementAt(i).getStatus(); i ++){
            terrain = terrainList.elementAt(i);
            terrainRadius = terrain.getLength()/2 - 30;
            terrainX = terrain.getScreenX();
            terrainY = terrain.getScreenY();

            if(leftBound < terrainX + terrainRadius && rightBound > terrainX - terrainRadius){
                if(moveVelocity < 0 && terrainY < upperBound){
                    if(playerSpace.intersects(playerSpace, terrain.getSpace()))
                        return true;
                }else if(moveVelocity > 0 && terrainY > lowerBound){
                    if(playerSpace.intersects(playerSpace, terrain.getSpace()))
                        return true;
                }
            }
        }

        return false;
    }


    void shoot(){
        projectileVector.elementAt(currentBulletIndex++).shoot(this);
        currentBulletIndex %= projectileVectorSize;
    }

    void setMaxHealth(int h){
        maxHealth = h;
        currentHealth = h;
    }

    int getMaxHealth(){
        return maxHealth;
    }
}
