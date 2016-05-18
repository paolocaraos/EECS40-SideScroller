package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Paolo on 5/13/2016.
 */
public class World {

    private Player player;
    private int playerSpaceRightBound;
    private int playerSpaceLeftBound;

    private Rect worldSpace;
    private int rightBound;
    private int upperBound;
    private int leftBound;
    private int lowerBound;
    private int worldLength;

    private int screenWidth;
    private int screenHeight;

    public static final int scrollSpeedConstant = 30;
    private int scrollVelocity;

    private Vector<Terrain> terrainVector;

    private Bitmap background;

    public class UnitCell{
        public static final int cellLength = 150;

        int screenX;
        int screenY;

        int arrX;
        int arrY;

        UnitCell(int x, int y){
            arrX = x;
            arrY = y;

            screenX = cellLength/2 + cellLength*x;
            screenY = cellLength/2 + cellLength*y;
        }

        Terrain terrainBlock;

        void setTerrainBlock(Terrain terrainBlock){
            this.terrainBlock = terrainBlock;

            terrainBlock.setUnitCell(this);
        }

        int getScreenX(){
            return screenX;
        }

        int getScreenY(){
            return screenY;
        }

        int getArrX(){return arrX;}

        int getArrY(){return arrY;}

        int getCellLength(){
            return cellLength;
        }

        void deactivate(){
            terrainBlock.deactivate();
            terrainBlock = null;
        }
    }

    private UnitCell[][] cells = new UnitCell[80][6];

    World(Player player, int screenWidth, int screenHeight, Bitmap bg, Vector<Terrain> terrainVector){
        this.player = player;
        background = bg;

        for(int i = 0; i < getUnitCellArray().length; i++) {
            for(int j = 0; j < getUnitCellArray()[i].length; j++) {
                cells[i][j] = new World.UnitCell(i, j);
            }
        }

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        leftBound = 0;
        upperBound = 0;
        rightBound = cells[0][0].getCellLength() * cells.length;
        lowerBound = cells[0][0].getCellLength() * cells[0].length;

        worldLength = rightBound - leftBound;

        worldSpace = new Rect();
        worldSpace.set(leftBound, upperBound, rightBound, lowerBound);

        this.terrainVector = terrainVector;

        playerSpaceLeftBound = screenWidth/2 - player.getPlayerRadius();
        playerSpaceRightBound = screenWidth/2 + player.getPlayerRadius();

        scrollVelocity = 0;
    }

    UnitCell getUnitCell(int x, int y){
        return cells[x][y];
    }

    UnitCell[][] getUnitCellArray(){
        return cells;
    }

    Rect getWorldSpace(){
        return worldSpace;
    }

    void update(){
        int playerSpaceUpperBound = player.getUpperBound();
        int playerSpaceLowerBound = player.getLowerBound();

        if(terrainCollision(playerSpaceUpperBound, playerSpaceLowerBound)){
            scrollVelocity = 0;
        } else if(rightBound + scrollVelocity >= playerSpaceRightBound && leftBound + scrollVelocity <= playerSpaceLeftBound){
            leftBound += scrollVelocity;
            rightBound += scrollVelocity;
        } else{
            if(scrollVelocity > 0){
                leftBound = playerSpaceLeftBound;
                rightBound = playerSpaceLeftBound + worldLength;
            }else {
                leftBound = playerSpaceRightBound - worldLength;
                rightBound = playerSpaceRightBound;
            }

            scrollVelocity = 0;
        }
    }

    void reset(){
        for(int i = 0; i < getUnitCellArray().length; i++){
            getUnitCell(i, 0).deactivate();
            getUnitCell(i, 1).deactivate();
            getUnitCell(i, 2).deactivate();
            getUnitCell(i, 3).deactivate();
            getUnitCell(i, 4).deactivate();
            getUnitCell(i, 5).deactivate();
        }

        leftBound = 0;
        upperBound = 0;
        rightBound = cells[0][0].getCellLength() * cells.length;
        lowerBound = cells[0][0].getCellLength() * cells[0].length;

    }

    int getScrollVel(){
        return scrollVelocity;
    }

    int getUpperBound(){
        return upperBound;
    }

    int getLowerBound(){
        return lowerBound;
    }

    void move(PlayerView.Direction direction){
        if(direction == PlayerView.Direction.RIGHT){
            scrollVelocity = -scrollSpeedConstant;
        }else if(direction == PlayerView.Direction.LEFT){
            scrollVelocity = scrollSpeedConstant;
        }else{
            scrollVelocity = 0;
        }
    }

    void draw(Canvas canvas){
        worldSpace.set(leftBound, upperBound, rightBound, lowerBound);
        Paint p = new Paint(Color.BLACK);
        canvas.drawRect(worldSpace, p);
    }

    private boolean terrainCollision(int playerUpperBound, int playerLowerBound){
        int terrainY, terrainX, terrainRadius;
        Terrain terrain;

        Rect playerSpace = player.getPlayerSpace();


        for(int i = 0; terrainVector.elementAt(i).getStatus(); i++){
            terrain = terrainVector.elementAt(i);
            terrainRadius = terrain.getLength()/2 - 30;
            terrainY = terrain.getScreenY();
            terrainX = terrain.getScreenX();
            if(playerUpperBound <= terrainY + terrainRadius && playerLowerBound >= terrainY - terrainRadius)
            {
               if(scrollVelocity < 0 && terrainX >= playerSpaceRightBound){
                   if(playerSpace.intersects(playerSpace, terrain.getSpace()))
                       return true;
               }else if(scrollVelocity > 0 && terrainX <= playerSpaceLeftBound){
                   if(playerSpace.intersects(playerSpace, terrain.getSpace()))
                       return true;
               }
            }
        }

        return false;
    }
}
