package com.kedacom.rxtest.ui.operators;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.kedacom.rxtest.R;
import com.kedacom.rxtest.model.User;
import com.kedacom.rxtest.utils.AppConstant;
import com.kedacom.rxtest.utils.Utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * zip操作符示例
 * Created by zhoutianjie on 2019/4/15.
 */

public class ZipExampleActivity extends BaseOperatorActivity{

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
        Observable.zip(getCricketFansObservable(),getFootballFansObservable()
                ,(cricketFans,footballFans)->Utils.filterUserWhoLovesBoth(cricketFans,footballFans))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    @Override
    protected Observable getObservable() {
        return null;
    }

    @Override
    protected Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<User> userList) {
                mTextView.append(" onNext");
                mTextView.append(AppConstant.LINE_SEPARATOR);
                for (User user : userList) {
                    mTextView.append(" firstName : " + user.firstname);
                    mTextView.append(AppConstant.LINE_SEPARATOR);
                }
                Log.d(TAG, " onNext : " + userList.size());
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



    private Observable<List<User>> getCricketFansObservable(){
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> emitter) throws Exception {
                if(!emitter.isDisposed()){
                    emitter.onNext(Utils.getUserListWhoLovesCricket());
                    emitter.onComplete();
                }
            }
        });
    }

    private Observable<List<User>> getFootballFansObservable(){
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> emitter) throws Exception {
                if(!emitter.isDisposed()){
                    emitter.onNext(Utils.getUserListWhoLovesFootball());
                    emitter.onComplete();
                }
            }
        });
    }
}
