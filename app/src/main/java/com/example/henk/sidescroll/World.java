package com.example.henk.sidescroll;

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

    private int screenWidth;
    private int screenHeight;

    public static final int scrollSpeedConstant = 30;
    int scrollVelocity = 30;

    private Vector<Terrain> terrainVector;

    public class UnitCell{
        public static final int cellLength = 200;

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

        int getCellLength(){
            return cellLength;
        }

        int getScrollVelocity(){
            return scrollVelocity;
        }
    }

    private UnitCell[][] cells = new UnitCell[80][6];

    World(Player player, int screenWidth, int screenHeight){
        this.player = player;

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

        worldSpace = new Rect();
        worldSpace.set(leftBound, upperBound, rightBound, lowerBound);

        playerSpaceLeftBound = screenWidth/2 - player.getPlayerRadius();
        playerSpaceRightBound = screenWidth/2 + player.getPlayerRadius();
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
        System.out.println("leftBound = "+ leftBound + "; playerSpaceLeftBound = "+ playerSpaceLeftBound);
        if(rightBound + scrollVelocity >= playerSpaceRightBound && leftBound + scrollVelocity <= playerSpaceLeftBound){
            leftBound += scrollVelocity;
            rightBound += scrollVelocity;
        } else{
            scrollVelocity = 0;
        }

        worldSpace.set(leftBound, upperBound, rightBound, lowerBound);
    }

    int getScrollVel(){
        return scrollVelocity;
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

}
