package com.example.consultants.week3_daily4.UI.main;

import android.content.Context;

import com.example.consultants.week3_daily4.UI.base.BasePresenter;
import com.example.consultants.week3_daily4.UI.base.BaseView;

public interface MainContract {

    //going from presenter to view ->
    interface View extends BaseView {

    }

    //going from activity to presenter
    interface Presenter extends BasePresenter<View> {
        void StartMusic();
        void StopMusic();

    }
}
