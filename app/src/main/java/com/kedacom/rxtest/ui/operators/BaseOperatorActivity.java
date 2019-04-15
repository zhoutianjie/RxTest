package com.kedacom.rxtest.ui.operators;


import com.kedacom.rxtest.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by zhoutianjie on 2019/4/15.
 */

public abstract class BaseOperatorActivity extends BaseActivity{

    protected abstract Observable getObservable();
    protected abstract Observer  getObserver();
}
