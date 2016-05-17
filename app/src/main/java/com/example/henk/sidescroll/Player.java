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

    private Vector<Bitmap> fireRSprite;
    private Bitmap fireLSprite;

    private static int currentFrameIndex;

    private Rect playerSpace;

    private Vector<Terrain> terrainList;
    private World world;

    private PlayerView.Direction currentDirection;

    enum AnimationStatus {
        FLYING,
        SHOOTING,
    }

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

        moveVelocity = moveSpeedConstant;
    }

    void draw(Canvas canvas) {
        playerSpace.set(screenX - playerRadius, screenY - playerRadius, screenX + playerRadius, screenY + playerRadius);
        canvas.drawBitmap(currentSprite, null, playerSpace, null);
    }

    void move(PlayerView.Direction direction) {
        currentDirection = direction;
    }

    void update() {
        switch (currentDirection) {
            case LEFT:
                currentSpriteVector = flyingLSprite;
                break;
            case RIGHT:
                currentSpriteVector = flyingRSprite;
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
        if(verticalCollision())
            moveVelocity = -moveVelocity;

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

    int getPlayerRadius(){
        return playerRadius;
    }

    void setTerrainList(Vector<Terrain> terrainList){
        this.terrainList = terrainList;
    }

    void setWorld(World world){
        this.world = world;
    }

    private boolean verticalCollision(){
        boolean collision = false;

        if(moveVelocity + screenY < world.getUpperBound() | moveVelocity + screenY > world.getLowerBound()){
            return true;
        }

        Terrain terrain;
        int terrainX, terrainY, terrainRadius;
        for(int i = 0; terrainList.elementAt(i).getStatus(); i ++){
            terrain = terrainList.elementAt(i);
            terrainRadius = terrain.getBlockLength()/2 - 30;
            terrainX = terrain.getScreenX();
            terrainY = terrain.getScreenY();

            if(leftBound < terrainX + terrainRadius && rightBound > terrainX - terrainRadius){
                if(moveVelocity < 0 && terrainY < upperBound){
                    collision |= playerSpace.intersects(playerSpace, terrain.getSpace());
                }else if(moveVelocity > 0 && terrainY > lowerBound){
                    collision |= playerSpace.intersects(playerSpace, terrain.getSpace());
                }
            }
        }

        return collision;
    }

}
