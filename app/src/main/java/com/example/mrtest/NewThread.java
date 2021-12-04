package com.example.mrtest;

import android.content.Intent;

import java.util.Random;

public class NewThread implements Runnable{
    Intent intent;
    MainActivity mainActivity;
    public NewThread(Intent intent,MainActivity activity){
        this.intent=intent;
        mainActivity=activity;
    }

    @Override
    public void run() {
        try {
            Random random =new Random();
            int num= random.nextInt(2000)+1000;

            Thread.sleep(num);
            mainActivity.st(intent);

        }catch(Exception i){
            i.printStackTrace();
        }
    }
}
