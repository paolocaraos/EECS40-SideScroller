package com.example.henk.sidescroll;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Level {

    private int score;
    private int lives;

    private int currentLevel;

    private Vector<Enemy> enemyVector;
    private Vector<Terrain> terrainVector;

    private Player player;

    private int screenWidth;
    private int screenHeight;

    Level(int screenWidth, int screenHeight, Vector<Terrain> terrainVector, Player player, Vector<Enemy> enemyVector){
        this.terrainVector = terrainVector;
        this.player = player;
        this.enemyVector = enemyVector;

        this.screenWidth = screenWidth;
        this. screenHeight = screenHeight;
    }


}