package com.example.luyan.dhdiagnosis.UI.Activity.TempTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Activity.BaseActivity;

public class NewTempTaskActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_temp_task);

        if (savedInstanceState == null){
            super.initNavi(R.id.new_temp_task_container,"新建临时任务",null);
        }
    }
}
