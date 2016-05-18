package com.example.henk.sidescroll;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Vector;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Level {

    Canvas canvas;

    private int currentLevel;

    private Vector<Enemy> enemyVector;
    private Vector<Terrain> terrainList;
    private Vector<Projectile> playerProjectileVector = new Vector<Projectile>(2, 10);
    private Player player;

    private World world;

    private int screenWidth;
    private int screenHeight;

    private SpriteFactory spriteFactory;

    private boolean initiatingNextLevel;
    private boolean finishedLevel;

    private int currHealthPercent = 100;
    private Rect currentHealth;
    private Paint currPaint;

    private int points;

    Level(Canvas canvas,
          int screenWidth, int screenHeight,
          Vector<Terrain> terrainList, Player player, Vector<Enemy> enemyVector,
          SpriteFactory spriteFactory)
    {
        currentLevel = 98;
        points = 0;

        currentHealth = new Rect();
        currPaint =  new Paint();

        this.terrainList = terrainList;
        this.player = player;
        this.enemyVector = enemyVector;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        this.canvas = canvas;

        world = new World(player, screenWidth, screenHeight, null, terrainList);

        player.setTerrainList(terrainList);

        this.spriteFactory = spriteFactory;

        player.setWorld(world);

        initiatingNextLevel = true;
    }

    private void initiateWorld(){
        int blockCounter = 0;

        switch(currentLevel) {
            case 98:
                //Set Max health and heal
                player.setMaxHealth(100);

                //Determine number of ammo, and ammo size
                for(int i = 0; i < playerProjectileVector.capacity(); i++){
                    playerProjectileVector.add(new Projectile(spriteFactory, screenWidth, screenHeight, terrainList, enemyVector, 80));
                }
                player.setAmmo(playerProjectileVector);

                //Set up obstacles
                for(int i = 0; i < world.getUnitCellArray().length; i++) {
                    world.getUnitCell(i, 5).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    world.getUnitCell(i, 0).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    if(i % 10 == 0) {
                        world.getUnitCell(i, 4).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    }
                }
                for(int i = 1; i < 4; i++) {
                    world.getUnitCell(0, i).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    world.getUnitCell(79, i).setTerrainBlock(terrainList.elementAt(blockCounter++));
                }

                //Spawn enemies

                //Spawn items

                break;
            default:
                break;
        }
    }

    void draw(Canvas canvas){
        world.draw(canvas);
        player.draw(canvas);

        for (int i = 0; i < playerProjectileVector.size(); i++){
            playerProjectileVector.elementAt(i).draw(canvas);
        }

        //draw health bar
        currentHealth.set(screenWidth - 165,  screenHeight - 97 - 490*(currHealthPercent/100), screenWidth - 75, screenHeight - 97);
        currPaint.setStyle(Paint.Style.FILL);
        currPaint.setColor(Color.GREEN);
        canvas.drawRect(currentHealth, currPaint);

        //draw items

        //terrain draw algorithm has room for optimization
        //Hint: change initial value of i to what the screen sees
        for(int i = 0; i < terrainList.size(); i++){
            terrainList.elementAt(i).draw(canvas);
        }
    }

    private void deactivate(){
        world.reset();
        playerProjectileVector.removeAllElements();
    }

    void update(){

        if(initiatingNextLevel){
            initiateWorld();
            initiatingNextLevel = false;
        }

        player.update();
        world.update();

        for(int i = 0; i < playerProjectileVector.size(); i++){
            playerProjectileVector.elementAt(i).update();
        }

        for(int i = 0; i < terrainList.size(); i++) {
            terrainList.elementAt(i).update(world.getScrollVel());
        }


        //if dead, gameOver

        //if enemies dead level up

        if(finishedLevel){
            deactivate();
            initiatingNextLevel = true;
            currentLevel++;
        }
    }

    void move(PlayerView.Direction direction){
        player.move(direction);
        world.move(direction);
    }

    int getPoints(){
        return points;
    }

    int getLevel(){
        return currentLevel;
    }
}