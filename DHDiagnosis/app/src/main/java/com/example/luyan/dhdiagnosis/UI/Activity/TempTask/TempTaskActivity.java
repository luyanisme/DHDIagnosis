package com.example.luyan.dhdiagnosis.UI.Activity.TempTask;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Activity.BaseActivity;
import com.example.luyan.dhdiagnosis.UI.Activity.DetectActivity;
import com.example.luyan.dhdiagnosis.UI.Activity.LocateDeviceActivity;
import com.example.luyan.dhdiagnosis.utils.IntentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempTaskActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;
    private TextView abstractView;

    /*测试数据*/
    private String[] testStrings = {"临时任务1", "临时任务2", "临时任务3", "临时任务4","新建临时任务"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_task);

        listView = (ListView) findViewById(R.id.list_view);
        abstractView = (TextView) findViewById(R.id.content);

        findViewById(R.id.check_detail_info).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);

        if (savedInstanceState == null){
            super.initNavi(R.id.temp_task_container,"临时任务列表",null);
        }

        initList();
        abstractView.setText("临时任务1\n测量位置:"+"1号风机轴承垂直\n"+"测量内容:"+"振动加速度(高频)\n"+"测量时间:"+"2015-12-15\n"+"测量原因:"+"巡检时有异常\n"+"测量值大小:"+"10m/s");
    }

    /*创建list*/
    public void initList() {

        SimpleAdapter adapter = new SimpleAdapter(this, getData(testStrings), R.layout.workspace_list_item, new String[]{"workspace"}, new int[]{R.id.work_space_name});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentUtils.startToActivity(TempTaskActivity.this,NewTempTaskActivity.class);
                /*动画效果*/
                overridePendingTransition(R.anim.move_right_in_activity,R.anim.move_left_out_activity);
            }
        });
    }

    private List<? extends Map<String, ?>> getData(String[] strs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < strs.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String str = strs[i];
            map.put("workspace", str);
            list.add(map);

        }

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check_detail_info:
                break;

            case R.id.delete:
                break;

        }
    }
}
