package com.kedacom.rxtest;

import android.content.Intent;

import android.os.Bundle;

import android.widget.Button;

import com.kedacom.rxtest.ui.operators.OperatorsActivity;


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
        mOperatorsBtn.setOnClickListener(v->startActivity(new Intent(this, OperatorsActivity.class)));
    }


}
