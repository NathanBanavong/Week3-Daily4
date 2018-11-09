package com.example.consultants.week3_daily4.UI.main;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.consultants.week3_daily4.Constants;
import com.example.consultants.week3_daily4.R;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();

    }

    @Override
    public void showError(String error) {

    }

//    public void onMusicService(View view) {
//
//        switch (view.getId()){
//
//            case R.id.btnStartMusic:
//
//                break;
//
//            case R.id.btnStopMusic:
//
//
//
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachView();
    }


    public void onStartMusic(View view) {
        mainPresenter.setContext(getApplicationContext());
        mainPresenter.StartMusic();
    }

    public void onStopMusic(View view) {
        mainPresenter.setContext(getApplicationContext());
        mainPresenter.StopMusic();
    }
}
