package com.kedacom.rxtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhoutianjie on 2019/4/15.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onViewCreate(){
        initView();
        registerListener();
    }

    protected abstract void initView();
    protected abstract void registerListener();
}
