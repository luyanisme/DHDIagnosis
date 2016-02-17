package com.example.luyan.dhdiagnosis.UI.Activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.MetaData.DeviceItem;
import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.ChartFragment;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;

public class DiagnosisActivity extends BaseActivity implements NaviFragment.AmendNaviDelegate, View.OnClickListener {

    private TextView deviceName;
    private TextView deviceTextState;
    private ImageView deviceState;
    private Boolean isRunning = false;//检测设备是否正在被检测

    private static final String TAG = "DiagnosisActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        DeviceItem deviceItem = getIntent().getParcelableExtra(DeviceListActivity.EXTRA_DEVICE_MESSAGE);
        deviceName = (TextView) findViewById(R.id.device_name);
        deviceState = (ImageView) findViewById(R.id.device_state);
        deviceTextState = (TextView) findViewById(R.id.device_text_state);

        /*添加Button事件*/
        findViewById(R.id.start_detect).setOnClickListener(this);//开始测量
        findViewById(R.id.pre_station).setOnClickListener(this);//上一个测点
        findViewById(R.id.next_station).setOnClickListener(this);//下一个测点
        findViewById(R.id.station_record).setOnClickListener(this);//测点记录
        findViewById(R.id.remove_record).setOnClickListener(this);//删除本次测点

        /*初始化设备状态*/
        deviceName.setText(deviceItem.getDeviceName());
        deviceTextState.setText(deviceItem.getDeviceRunning() == 0 ? "设备运行" : "设备停止");
        deviceState.setBackgroundColor(deviceItem.getDeviceRunning() == 0 ? Color.rgb(0, 255, 0) : Color.rgb(255, 0, 0));

        if (savedInstanceState == null) {
            super.initNavi(R.id.diagnosis_container, getIntent().getStringExtra(DeviceListActivity.EXTRA_TITLE_MESSAGE));
        }

        initChartFragment();
    }

    public void initChartFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChartFragment chartFragment = new ChartFragment(this);
        fragmentTransaction.add(R.id.diagnosis_container, chartFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_detect:
                isRunning = true;
                break;

            case R.id.pre_station:
                break;

            case R.id.next_station:
                break;

            case R.id.station_record:
                break;

            case R.id.remove_record:
                break;
        }
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
}
