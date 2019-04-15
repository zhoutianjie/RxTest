package com.kedacom.rxtest.ui.operators;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.kedacom.rxtest.R;
import com.kedacom.rxtest.utils.AppConstant;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * CompositeDisposable 示例，关闭Activity的时候注意要取消订阅，防止某些网络请求相关的处理导致内存泄漏
 * Created by zhoutianjie on 2019/4/15.
 */

public class DisposableExampleActivity extends BaseOperatorActivity {

    private Button mTestBtn;
    private TextView mTextView;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_example);
        onViewCreate();
    }

    @Override
    protected Observable<String> getObservable() {
        return Observable.defer(()->{
            SystemClock.sleep(2000);
            return Observable.just("one", "two", "three", "four", "five");
        });
    }

    @Override
    protected DisposableObserver<String> getObserver() {
        return new DisposableObserver<String>() { //DisposableObserver 对应的是subscribeWith
            @Override
            public void onNext(String s) {
                mTextView.append(" onNext : value : " + s);
                mTextView.append(AppConstant.LINE_SEPARATOR);
                Log.d(TAG, " onNext value : " + s);
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
        disposable.add(getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
