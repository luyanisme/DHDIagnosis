package com.example.luyan.dhdiagnosis.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.Adapter.DeviceAdapter;
import com.example.luyan.dhdiagnosis.MetaData.DeviceItem;
import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;
import com.example.luyan.dhdiagnosis.utils.IntentUtils;

import java.util.ArrayList;

public class DeviceListActivity extends BaseActivity implements NaviFragment.AmendNaviDelegate {

    private String naviTitle;
    public final static String EXTRA_TITLE_MESSAGE = "com.example.luyan.dhdiagnosis.DEVICEMESSAGE";
    public final static String EXTRA_DEVICE_MESSAGE = "com.example.luyan.dhdiagnosis.DEVICEITEM";

    private DeviceAdapter deviceAdapter;
    private ListView listView;
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    AlertDialog alertDialog = new AlertDialog.Builder(DeviceListActivity.this).create();
                    alertDialog.show();
                    Window window = alertDialog.getWindow();
                    window.setContentView(R.layout.device_detail_dialog);
                    TextView tv_title = (TextView) window.findViewById(R.id.tv_dialog_title);
                    TextView tv_message = (TextView) window.findViewById(R.id.tv_dialog_message);
                    tv_message.setText("当前点击第" + msg.getData().getString("signal") + "项");
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        listView = (ListView) findViewById(R.id.devices_list);

        naviTitle = getIntent().getStringExtra(IntentUtils.INTENT_STRING_PARAM);

        if (savedInstanceState == null){
            super.initNavi(R.id.device_list_container,naviTitle);
        }

        initList();
    }

    public void initList() {
        final ArrayList deviceList = new ArrayList<DeviceItem>();
        DeviceItem device_1 = new DeviceItem("设备A", 1, 1);
        DeviceItem device_2 = new DeviceItem("设备B", 0, 0);
        DeviceItem device_3 = new DeviceItem("设备C", 1, 1);
        deviceList.add(device_1);
        deviceList.add(device_2);
        deviceList.add(device_3);

        deviceAdapter = new DeviceAdapter(this, deviceList, myHandler);
        listView.setAdapter(deviceAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_TITLE_MESSAGE,naviTitle);
                intent.putExtra(EXTRA_DEVICE_MESSAGE,(DeviceItem)deviceList.get(position));
                intent.setClass(DeviceListActivity.this,DiagnosisActivity.class);
                startActivity(intent);

                /*动画效果*/
                overridePendingTransition(R.anim.move_right_in_activity,R.anim.move_left_out_activity);
            }
        });
    }

}
