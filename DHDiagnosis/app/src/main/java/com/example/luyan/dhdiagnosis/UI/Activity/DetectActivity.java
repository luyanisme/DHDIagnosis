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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;
import com.example.luyan.dhdiagnosis.utils.IntentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetectActivity extends BaseActivity implements NaviFragment.AmendNaviDelegate {

    private ListView listView;

    private TextView fml;
    private TextView thisPlanTime;
    private TextView nextPlanTime;
    private TextView dataDetect;
    private TextView deviceDetect;

    /*测试数据*/
    private String[] testStrings = {"xxxx车间工艺路径巡检", "xxxx车间工艺路径巡检1", "xxxx车间工艺路径巡检2",
            "xxxx车间工艺路径巡检3",
            "xxxx车间工艺路径巡检4",
            "xxxx车间工艺路径巡检5",
            "xxxx车间工艺路径巡检6",
            "xxxx车间工艺路径巡检7",
            "xxxx车间工艺路径巡检8",
            "xxxx车间工艺路径巡检9",
            "xxxx车间工艺路径巡检10",
            "xxxx车间工艺路径巡检11",
            "xxxx车间工艺路径巡检12",
            "xxxx车间工艺路径巡检13",
            "xxxx车间工艺路径巡检14",
            "xxxx车间工艺路径巡检15",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            super.initNavi(R.id.detect_container,"路径列表",null);
        }
        setContentView(R.layout.activity_detect);

        listView = (ListView) findViewById(R.id.list_view);
        fml = (TextView) findViewById(R.id.fml);
        thisPlanTime = (TextView) findViewById(R.id.this_plan);
        nextPlanTime = (TextView) findViewById(R.id.next_plan);
        dataDetect = (TextView) findViewById(R.id.already_data_detect);
        deviceDetect = (TextView) findViewById(R.id.already_device_detect);

        initList();
    }

    /*创建list*/
    public void initList() {

        SimpleAdapter adapter = new SimpleAdapter(this, getData(testStrings), R.layout.workspace_list_item, new String[]{"workspace"}, new int[]{R.id.work_space_name});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentUtils.startToActivityWithString(DetectActivity.this,LocateDeviceActivity.class,testStrings[position]);
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


}
