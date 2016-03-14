package com.example.luyan.dhdiagnosis.UI.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luyan.dhdiagnosis.R;

public class SensorInfoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_info);

        if (savedInstanceState == null) {
            super.initNavi(R.id.sensor_info_container, "传感器信息", null);
        }
    }

}
