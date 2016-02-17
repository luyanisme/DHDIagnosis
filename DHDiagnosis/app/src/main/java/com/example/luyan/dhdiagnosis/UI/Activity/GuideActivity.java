package com.example.luyan.dhdiagnosis.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.utils.IntentUtils;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        findViewById(R.id.detect_model).setOnClickListener(this);
        findViewById(R.id.temp_task).setOnClickListener(this);
        findViewById(R.id.fault_diagnosis).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*跳转至巡检模块*/
            case R.id.detect_model:
                IntentUtils.startToActivity(GuideActivity.this,DetectActivity.class);
                break;

            /*跳转至临时任务*/
            case R.id.temp_task:

                break;

            /*跳转至精密故障诊断*/
            case R.id.fault_diagnosis:
                break;
        }

        /*动画效果*/
        overridePendingTransition(R.anim.move_right_in_activity,R.anim.move_left_out_activity);
    }
}
