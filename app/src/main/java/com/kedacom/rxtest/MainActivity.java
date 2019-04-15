package com.kedacom.rxtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.kedacom.rxtest.ui.operators.SimpleExampleActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private Button mOperatorsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onViewCreate();

    }

    @Override
    protected void initView() {
        mOperatorsBtn = findViewById(R.id.operators_btn);
    }

    @Override
    protected void registerListener() {
        mOperatorsBtn.setOnClickListener(v->startActivity(new Intent(this, SimpleExampleActivity.class)));
    }


}
