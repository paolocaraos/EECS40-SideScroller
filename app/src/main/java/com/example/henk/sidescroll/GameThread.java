package com.example.henk.sidescroll;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Henk on 2016/5/4.
 */
public class GameThread extends Thread {
    PlayerView pv;
    public GameThread(PlayerView pv){
        this.pv = pv;
    }

    public void run(){
        SurfaceHolder sHolder = pv.getHolder();
        while( !Thread.interrupted()){
            Canvas c = sHolder.lockCanvas(null);
            try {
                synchronized (sHolder) {
                    pv.draw(c);
                }
            }catch(Exception e){
            }finally{
                if(c != null){
                    sHolder.unlockCanvasAndPost(c);
                }
            }

            try{
                Thread.sleep(1);
            }catch(InterruptedException e){
                return;
            }
        }
    }
}