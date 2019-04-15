package com.kedacom.rxtest.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.kedacom.rxtest.R;
import com.kedacom.rxtest.utils.AppConstant;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 简单RxJava示例
 * Created by zhoutianjie on 2019/4/15.
 */

public class SimpleExampleActivity extends BaseOperatorActivity {

    private Button mTestBtn;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        onViewCreate();
    }

    @Override
    protected void initView() {
        mTestBtn = findViewById(R.id.test_btn);
        mTextView = findViewById(R.id.textView);
    }

    @Override
    protected void registerListener() {
        mTestBtn.setOnClickListener(v->doTest());
    }

    private void doTest(){
       getObservable().subscribe(getObserver());
    }

    @Override
    protected Observable getObservable(){
        return Observable.just("data1","data2");
    }

    @Override
    protected Observer getObserver(){
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                mTextView.append(" onNext : value : " + s);
                mTextView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + s);
            }

            @Override
            public void onError(Throwable e) {
                mTextView.append(" onError : " + e.getMessage());
                mTextView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mTextView.append(" onComplete");
                mTextView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onComplete");
            }
        };
    }

}
