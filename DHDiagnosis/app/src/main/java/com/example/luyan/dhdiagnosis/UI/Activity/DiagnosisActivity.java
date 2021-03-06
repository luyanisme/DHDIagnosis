package com.example.luyan.dhdiagnosis.UI.Activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.MetaData.DeviceItem;
import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.ChartContainerFragment;
import com.example.luyan.dhdiagnosis.UI.Fragment.ChartFragment;
import com.example.luyan.dhdiagnosis.UI.Fragment.DiagnosisFragment;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;
import com.example.luyan.dhdiagnosis.utils.IntentUtils;

import java.util.ArrayList;

import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class DiagnosisActivity extends BaseActivity implements NaviFragment.AmendNaviDelegate, DiagnosisFragment.DiagnosisDelegate {

    private boolean isRunning;

    private static final String TAG = "DiagnosisActivity";

    private DiagnosisFragment diagnosisFragment;
    private ChartContainerFragment chartContainerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        DeviceItem deviceItem = getIntent().getParcelableExtra(DeviceListActivity.EXTRA_DEVICE_MESSAGE);

        if (savedInstanceState == null) {
            super.initNavi(R.id.diagnosis_container, getIntent().getStringExtra(DeviceListActivity.EXTRA_TITLE_MESSAGE),null);
        }


        diagnosisFragment = new DiagnosisFragment(getSupportFragmentManager(),this,deviceItem);
        diagnosisFragment.setDelegate(this);
        chartContainerFragment = new ChartContainerFragment(getSupportFragmentManager(),this);

        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        verticalViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                ArrayList<Fragment> list = new ArrayList<Fragment>();
                list.add(diagnosisFragment);
                list.add(chartContainerFragment);
                return list.get(position);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isRunning) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                showDenyDialog();
            }
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                DiagnosisActivity.this.finish();
                overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
            }
        }

        return false;

    }

    public void showDenyDialog() {
        new AlertDialog.Builder(this).setTitle("返回提示框").setMessage("数据正在采集中，请稍后再试！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    /*开始测试*/
    @Override
    public void startDetect() {

    }

    /*上个测点*/
    @Override
    public void preStation() {

    }

    /*下个测点*/
    @Override
    public void nextStation() {

    }

    /*测点记录*/
    @Override
    public void stationRecord() {
        IntentUtils.startToActivity(DiagnosisActivity.this,StationRecordActivity.class);
        /*动画效果*/
        overridePendingTransition(R.anim.move_right_in_activity,R.anim.move_left_out_activity);
    }

    /*删除测点*/
    @Override
    public void removeRecord() {

    }

    /*扫描二维码*/
    @Override
    public void scanQR() {

    }

    /*扫描RFID*/
    @Override
    public void scanRFID() {

    }
}
