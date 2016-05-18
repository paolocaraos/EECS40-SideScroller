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

    private Vector<Enemy> enemyVector = new Vector<Enemy>(3, 10);
    private Vector<Terrain> terrainList;
    private Vector<Projectile> playerProjectileVector = new Vector<Projectile>(2, 10);
    private Vector<Item> itemVector = new Vector<Item>(3,3);
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

    private Boolean gameOver;
    private Boolean victory;

    Level(Canvas canvas,
          int screenWidth, int screenHeight,
          Vector<Terrain> terrainList, Player player,
          SpriteFactory spriteFactory)
    {
        currentLevel = 98;
        points = 0;

        victory = false;

        gameOver = false;

        currentHealth = new Rect();
        currPaint =  new Paint();

        this.terrainList = terrainList;
        this.player = player;

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
                player.setMaxHealth(1000);

                //Determine number of ammo, and ammo size
                for(int i = 0; i < 3; i++){
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

                world.getUnitCell(11, 3).setTerrainBlock(terrainList.elementAt(blockCounter++));

                //Spawn enemies
                for(int i = 0; i < 3; i++){
                    enemyVector.add(new Enemy(spriteFactory, 10, 10, 100, 50, world, terrainList, player ));
                }
                enemyVector.elementAt(0).setSpawnPosition(world.getUnitCell(37, 3));
                enemyVector.elementAt(1).setSpawnPosition(world.getUnitCell(50, 3));
                enemyVector.elementAt(2).setSpawnPosition(world.getUnitCell(10, 3));


                //Spawn items
                for(int i = 0; i < 3; i++){
                    itemVector.add(new Item(spriteFactory, 1000, world, player));
                }

                itemVector.elementAt(0).setSpawnPosition(world.getUnitCell(1, 3));
                itemVector.elementAt(1).setSpawnPosition(world.getUnitCell(2, 3));
                itemVector.elementAt(2).setSpawnPosition(world.getUnitCell(70, 3));

                break;
            case 99:
                player.setMaxHealth(1500);
                //Determine number of ammo, and ammo size
                for(int i = 0; i < 10; i++){
                    playerProjectileVector.add(new Projectile(spriteFactory, screenWidth, screenHeight, terrainList, enemyVector, 90));
                }
                player.setAmmo(playerProjectileVector);

                //Set up obstacles
                for(int i = 0; i < terrainList.capacity(); i++){
                    terrainList.add(new Terrain(spriteFactory.getCloud(), screenWidth));
                }

                for(int i = 0; i < world.getUnitCellArray().length; i++) {
                    if(i % 5 == 0) {
                        world.getUnitCell(i, 4).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell(i, 5).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    }
                    else if(i % 4 == 0){
                        world.getUnitCell(i, 0).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell(i, 1).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    }
                }
                for(int i = 1; i < 4; i++) {
                    world.getUnitCell(0, i).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    world.getUnitCell(79, i).setTerrainBlock(terrainList.elementAt(blockCounter++));
                }

                //Spawn enemies
                for(int i = 0; i < 3; i++){
                    enemyVector.add(new Enemy(spriteFactory, 20, 20, 80, 500, world, terrainList, player ));
                }
                enemyVector.elementAt(0).setSpawnPosition(world.getUnitCell(37, 3));
                enemyVector.elementAt(1).setSpawnPosition(world.getUnitCell(50, 3));
                enemyVector.elementAt(2).setSpawnPosition(world.getUnitCell(10, 3));

                player.setTerrainList(terrainList);


                //Spawn items
                for(int i = 0; i < 3; i++){
                    itemVector.add(new Item(spriteFactory, 2000, world, player));
                }

                itemVector.elementAt(0).setSpawnPosition(world.getUnitCell(1, 3));
                itemVector.elementAt(1).setSpawnPosition(world.getUnitCell(2, 3));
                itemVector.elementAt(2).setSpawnPosition(world.getUnitCell(70, 3));

                break;
            case 100:
                //Set Max health and heal
                player.setMaxHealth(1800);

                //Determine number of ammo, and ammo size
                for(int i = 0; i < 15; i++){
                    playerProjectileVector.add(new Projectile(spriteFactory, screenWidth, screenHeight, terrainList, enemyVector, 120));
                }
                player.setAmmo(playerProjectileVector);

                //Set up obstacles
                for(int i = 0; i < terrainList.capacity(); i++){
                    terrainList.add(new Terrain(spriteFactory.getCloud(), screenWidth));
                }
                for(int i = 0; i < world.getUnitCellArray().length; i++) {
                    if (i % 10 == 0 && i != 0 && i != 80){
                        world.getUnitCell(i, 3).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell((i-1), 4).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell((i+1), 4).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell((i-2), 5).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell((i+2), 5).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    }
                    else if(i % 5 == 0) {
                        world.getUnitCell(i, 2).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell(i, 3).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell((i+1), 2).setTerrainBlock(terrainList.elementAt(blockCounter++));
                        world.getUnitCell((i+1), 3).setTerrainBlock(terrainList.elementAt(blockCounter++));
                    }
                }
                player.setTerrainList(terrainList);
                //Spawn enemies

                enemyVector.add(new Enemy(spriteFactory, 50, 30, 50, 500, world, terrainList, player ));
                enemyVector.elementAt(0).setSpawnPosition(world.getUnitCell(37, 3));
                //Spawn items



                for(int i = 0; i < 10; i++){
                    itemVector.add(new Item(spriteFactory, 10000, world, player));
                }

                itemVector.elementAt(0).setSpawnPosition(world.getUnitCell(1, 3));
                itemVector.elementAt(1).setSpawnPosition(world.getUnitCell(2, 3));
                itemVector.elementAt(2).setSpawnPosition(world.getUnitCell(70, 3));

                itemVector.elementAt(3).setSpawnPosition(world.getUnitCell(16, 3));
                itemVector.elementAt(4).setSpawnPosition(world.getUnitCell(32, 3));
                itemVector.elementAt(5).setSpawnPosition(world.getUnitCell(72, 3));

                itemVector.elementAt(6).setSpawnPosition(world.getUnitCell(17, 3));
                itemVector.elementAt(7).setSpawnPosition(world.getUnitCell(52, 3));
                itemVector.elementAt(8).setSpawnPosition(world.getUnitCell(65, 3));

                itemVector.elementAt(9).setSpawnPosition(world.getUnitCell(42, 3));
                break;
            default:
                break;
        }
    }

    void draw(Canvas canvas){
        world.draw(canvas);
        player.draw(canvas);

        for(int i = 0; i < enemyVector.size(); i++){
            enemyVector.elementAt(i).draw(canvas);
        }

        for (int i = 0; i < playerProjectileVector.size(); i++){
            playerProjectileVector.elementAt(i).draw(canvas);
        }

        for(int i = 0; i < itemVector.size(); i++){
            itemVector.elementAt(i).draw(canvas);
        }

        //draw health bar
        currentHealth.set(screenWidth - 165,  screenHeight - 97 - (490*currHealthPercent)/100, screenWidth - 75, screenHeight - 97);
        currPaint.setStyle(Paint.Style.FILL);
        currPaint.setColor(Color.GREEN);
        canvas.drawRect(currentHealth, currPaint);

        //draw items

        //terrain draw algorithm has room for optimization
        //Hint: change initial value of i to what the screen sees
        for (int i = 0; i < terrainList.size(); i++){
            terrainList.elementAt(i).draw(canvas);
        }
    }

    private void deactivate(){
        world.reset();
        terrainList.removeAllElements();
        System.out.println("terrainVector cleaned");
        enemyVector.removeAllElements();
        System.out.println("enemyVector cleaned");
        playerProjectileVector.removeAllElements();
        System.out.println("bullets cleaned");
        itemVector.removeAllElements();
    }

    void update() {
        if(!victory){
            if (initiatingNextLevel) {
                initiateWorld();
                initiatingNextLevel = false;

            }

            player.update();
            world.update();

            for (int i = 0; i < playerProjectileVector.size(); i++) {
                points += playerProjectileVector.elementAt(i).update();
            }

            for (int i = 0; i < terrainList.size(); i++) {
                terrainList.elementAt(i).update(world.getScrollVel());
            }
            for(int i = 0; i < itemVector.size(); i++){
                itemVector.elementAt(i).update(world.getScrollVel(), this);
            }

            int deathCount = 0;
            for (int i = 0; i < enemyVector.size(); i++) {
                enemyVector.elementAt(i).update(world.getScrollVel());
                if (enemyVector.elementAt(i).getStatus() == false) {
                    deathCount++;
                }
            }

            if (enemyVector.size() == deathCount) {
                finishedLevel = true;
            }

            currHealthPercent = player.getCurrHealth();
            System.out.println("Current health = " + currHealthPercent);
            //if dead, gameOver
            if (currHealthPercent <= 0) {
                gameOver = true;
            }


            if (finishedLevel) {
                deactivate();
                currentLevel++;
                if (currentLevel <= 100)
                    initiatingNextLevel = true;
                else
                    victory = true;
                finishedLevel = false;
            }
        }
    }

    void move(PlayerView.Direction direction){
        player.move(direction);
        world.move(direction);
    }

    int getPoints(){
        return points;
    }

    void addPoints(int i){
        points += i;
    }

    int getLevel(){
        return currentLevel;
    }

    boolean getVictory(){
        return victory;
    }

    boolean getGameOver(){
        return gameOver;
    }
}