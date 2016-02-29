package com.example.luyan.dhdiagnosis.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;
import com.example.luyan.dhdiagnosis.utils.IntentUtils;


public class LocateDeviceActivity extends BaseActivity implements NaviFragment.AmendNaviDelegate, View.OnClickListener {

    private String naviTitle;
    public final static String EXTRA_TITLE_MESSAGE = "com.example.luyan.dhdiagnosis.LOCALMESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_device);

        naviTitle = getIntent().getStringExtra(IntentUtils.INTENT_STRING_PARAM);

        if (savedInstanceState == null) {
            super.initNavi(R.id.locate_device_container, naviTitle, null);
        }

        findViewById(R.id.scanQR).setOnClickListener(this);
        findViewById(R.id.scanRFID).setOnClickListener(this);
        findViewById(R.id.choose_in_path).setOnClickListener(this);
        findViewById(R.id.default_detect).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scanQR:
                IntentUtils.startToActivity(LocateDeviceActivity.this, ScanQRActivity.class);
                overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                break;

            case R.id.scanRFID:

                break;

            case R.id.choose_in_path:
                IntentUtils.startToActivityWithString(LocateDeviceActivity.this, DeviceListActivity.class, naviTitle);
                break;

            case R.id.default_detect:

                break;
        }

        /*动画效果*/
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

}
