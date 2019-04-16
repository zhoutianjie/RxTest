package com.kedacom.rxtest.ui.operators;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.kedacom.rxtest.BaseActivity;
import com.kedacom.rxtest.R;

/**
 *
 * Created by zhoutianjie on 2019/4/15.
 */

public class OperatorsActivity extends BaseActivity {


    private Button mSimpleBtn;
    private Button mMapBtn;
    private Button mZipBtn;
    private Button mDisposableBtn;
    private Button mTakeBtn;
    private Button mTimerBtn;
    private Button mIntervalBtn;
    private Button mSingleBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
        onViewCreate();
    }

    @Override
    protected void initView() {
        mSimpleBtn = findViewById(R.id.simple_btn);
        mMapBtn = findViewById(R.id.map_btn);
        mZipBtn = findViewById(R.id.zip_btn);
        mDisposableBtn = findViewById(R.id.disposable_btn);
        mTakeBtn = findViewById(R.id.take_btn);
        mTimerBtn = findViewById(R.id.timer_btn);
        mIntervalBtn = findViewById(R.id.interval_btn);
        mSingleBtn = findViewById(R.id.single_btn);
    }

    @Override
    protected void registerListener() {
        mSimpleBtn.setOnClickListener(v->startActivity(new Intent(this,SimpleExampleActivity.class)));
        mMapBtn.setOnClickListener(v->startActivity(new Intent(this,MapExampleActivity.class)));
        mZipBtn.setOnClickListener(v->startActivity(new Intent(this,ZipExampleActivity.class)));
        mDisposableBtn.setOnClickListener(v->startActivity(new Intent(this,DisposableExampleActivity.class)));
        mTakeBtn.setOnClickListener(v-> startActivity(new Intent(this,TakeExampleActivity.class)));
        mTimerBtn.setOnClickListener(v->startActivity(new Intent(this,TimerExampleActivity.class)));
        mIntervalBtn.setOnClickListener(v->startActivity(new Intent(this,IntervalExampleActivity.class)));
        mSingleBtn.setOnClickListener(v->startActivity(new Intent(this,SingleObserverExampleActivity.class)));
    }
}
