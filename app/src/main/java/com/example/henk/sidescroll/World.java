package com.example.henk.sidescroll;

import android.graphics.Rect;

/**
 * Created by Paolo on 5/13/2016.
 */
public class World {

    private Player player;

    private Rect worldSpace;

    public static final int scrollSpeedConstant = 30;
    static int scrollVelocity = 10;

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

    World(Player player){
        this.player = player;

        for(int i = 0; i < getUnitCellArray().length; i++) {
            for(int j = 0; j < getUnitCellArray()[i].length; j++) {
                cells[i][j] = new World.UnitCell(i, j);
            }
        }
    }

    UnitCell getUnitCell(int x, int y){
        return cells[x][y];
    }

    UnitCell[][] getUnitCellArray(){
        return cells;
    }

    void move(PlayerView.Direction direction){

    }

}
