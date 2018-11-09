package com.example.consultants.week3_daily4.UI.main;

import android.content.Context;
import android.content.Intent;

import com.example.consultants.week3_daily4.Constants;
import com.example.consultants.week3_daily4.UI.Service.MyMusicService;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View view;
    private Context context;


    @Override
    public void StartMusic() {
        Intent musicIntent = new Intent(context, MyMusicService.class);
        musicIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        musicIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        context.startService(musicIntent);
    }

    public void setContext(Context context){
        this.context = context;
    }
    
    @Override
    public void StopMusic() {
        Intent musicIntent = new Intent(context, MyMusicService.class);
        musicIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        musicIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        context.startService(musicIntent);
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
