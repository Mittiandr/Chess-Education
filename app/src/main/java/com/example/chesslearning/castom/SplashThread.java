package com.example.chesslearning.castom;

import android.os.Handler;

public class SplashThread extends Thread{
    private final Handler handler;
    private final Runnable callBack;

    public SplashThread(Handler handler, Runnable callBack) {
        this.handler = handler;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
            super.run();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        handler.post(callBack);
    }
}
