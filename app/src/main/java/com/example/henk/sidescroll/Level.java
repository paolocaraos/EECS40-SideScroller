package com.example.henk.sidescroll;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Level {

    private Canvas canvas;

    private int score;
    private int lives;

    private int currentLevel;

    private Vector<Enemy> enemyVector;
    private Terrain[][] terrainGrid;
    private Player player;

    private int screenWidth;
    private int screenHeight;

    Level(Canvas canvas, int screenWidth, int screenHeight, Terrain[][] terrainGrid, Player player, Vector<Enemy> enemyVector){
        this.terrainGrid = terrainGrid;
        this.player = player;
        this.enemyVector = enemyVector;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    void initiateGrid(){
        switch(currentLevel) {
            case 0:
                for (int i = 0; i < terrainGrid.length; i++) {
                    if(i % 10 == 0)
                        terrainGrid[1][i].setSolid();
                    terrainGrid[0][i].setSolid();
                }
                break;
            default:
                break;
        }

    }

    void draw(Canvas canvas){
        //terrain draw algorithm has room for optimization
        //Hint: change initial value of i to what the screen sees
        for(int i = 0; i < terrainGrid.length; i++){
            for (int j = 0; j < terrainGrid[i].length; j++){
                terrainGrid[i][j].draw(canvas);
            }
        }
        player.draw(canvas);
    }
}