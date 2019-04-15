package com.kedacom.rxtest.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.kedacom.rxtest.R;
import com.kedacom.rxtest.utils.AppConstant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Timer 示例 延迟一段时间以后 发送一个简单数字0(看到这里是不是可以想到用Timer做一个超时请求)
 * Created by zhoutianjie on 2019/4/15.
 */

public class TimerExampleActivity extends BaseOperatorActivity {

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
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    @Override
    protected Observable<Long> getObservable() {
        return Observable.timer(2, TimeUnit.SECONDS);
    }

    @Override
    protected Observer<Long> getObserver() {
        return new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                mTextView.append(" onSubscribe : value : " + d.isDisposed());
                mTextView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(Long aLong) {
                mTextView.append(" onNext : value : " + aLong);
                mTextView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext : value : " + aLong);

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
