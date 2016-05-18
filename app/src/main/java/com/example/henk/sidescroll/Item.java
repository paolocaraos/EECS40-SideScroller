package com.example.henk.sidescroll;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Paolo on 5/4/2016.
 */
public class Item {
    private int screenX;
    private int screenY;
    private final int radius = 50;

    private boolean isActive;

    private int expReward;

    private World world;
    private int upperBoundWorld;
    private int lowerBoundWorld;

    private Rect space;

    private Player player;

    private Bitmap sprite;

    Item(SpriteFactory f, int exp, World world, Player player){
        space =new Rect();

        sprite = f.getItem();

        expReward = exp;

        this.player = player;

        this.world = world;

        upperBoundWorld = world.getUpperBound();
        lowerBoundWorld = world.getLowerBound();
    }

    void setSpawnPosition(World.UnitCell cell){
        isActive = true;
        screenX = cell.getScreenX();
        screenY = cell.getScreenY();
    }

    void draw(Canvas canvas){
        if(isActive){
            space.set(screenX - radius, screenY - radius, screenX + radius, screenY + radius);
            canvas.drawBitmap(sprite, null, space, null);
        }
    }

    void update(int vel, Level level){
        if(isActive) {
            screenX += vel;

            if (screenY < world.getUpperBound() + 100 | screenY > world.getLowerBound() - 100) {
                screenY = (world.getLowerBound() - world.getUpperBound()) / 2;
            }

            playerCollision(level);
        }

    }


    private void playerCollision(Level level){
        if(space.intersects(player.getPlayerSpace(), space)){
            isActive = false;
            level.addPoints(expReward);
        }
    }
}
