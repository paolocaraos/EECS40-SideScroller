package com.example.henk.sidescroll;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Level {

    Canvas canvas;

    private int score;
    private int lives;

    private int currentLevel= 0;

    private Vector<Enemy> enemyVector;
    private Vector<Terrain> terrainList;
    private Player player;

    private World world;

    private int screenWidth;
    private int screenHeight;

    Level(Canvas canvas, int screenWidth, int screenHeight, Vector<Terrain> terrainList, Player player, Vector<Enemy> enemyVector){
        this.terrainList = terrainList;
        this.player = player;
        this.enemyVector = enemyVector;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.canvas = canvas;

        player.setTerrainList(terrainList);

        world = new World(player);
    }

    void initiateGrid(){
        int blockCounter = 0;

        switch(currentLevel) {
            case 0:
                for(int i = 0; i < world.getUnitCellArray().length; i++) {
                    world.getUnitCell(i, 5).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    if(i % 10 == 0) {
                        world.getUnitCell(i, 4).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    }
                }
                break;
            default:
                break;
        }

    }

    void draw(Canvas canvas){
        //terrain draw algorithm has room for optimization
        //Hint: change initial value of i to what the screen sees
        for(int i = 0; i < terrainList.size(); i++){
            terrainList.elementAt(i).draw(canvas);
        }
        player.draw(canvas);
    }

    void update(){
        for(int i = 0; i < terrainList.size(); i++){
            terrainList.elementAt(i).update();
        }
    }

    void move(PlayerView.Direction direction){
        player.face(direction);

    }
}