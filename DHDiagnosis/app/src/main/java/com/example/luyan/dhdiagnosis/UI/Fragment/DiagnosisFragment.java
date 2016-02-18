package com.example.luyan.dhdiagnosis.UI.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.MetaData.DeviceItem;
import com.example.luyan.dhdiagnosis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiagnosisFragment extends Fragment implements View.OnClickListener {


    private TextView deviceName;
    private TextView deviceTextState;
    private ImageView deviceState;
    private ImageView deviceShowImage;
    private Boolean isRunning = false;//检测设备是否正在被检测
    private FragmentManager fm;
    private Context mcontext;
    private DeviceItem deviceItem;

    public DiagnosisFragment(FragmentManager fm, Context context, DeviceItem deviceItem) {
        // Required empty public constructor
        this.fm = fm;
        this.mcontext = context;
        this.deviceItem = deviceItem;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_diagnosis, container, false);
        deviceName = (TextView) view.findViewById(R.id.device_name);
        deviceState = (ImageView) view.findViewById(R.id.device_state);
        deviceTextState = (TextView) view.findViewById(R.id.device_text_state);
        deviceShowImage = (ImageView) view.findViewById(R.id.device_show_image);

        /*添加Button事件*/
        view.findViewById(R.id.start_detect).setOnClickListener(this);//开始测量
        view.findViewById(R.id.pre_station).setOnClickListener(this);//上一个测点
        view.findViewById(R.id.next_station).setOnClickListener(this);//下一个测点
        view.findViewById(R.id.station_record).setOnClickListener(this);//测点记录
        view.findViewById(R.id.remove_record).setOnClickListener(this);//删除本次测点
        view.findViewById(R.id.scanQR).setOnClickListener(this);//开启扫描二维码
        view.findViewById(R.id.scanRFID).setOnClickListener(this);//开启RFID

        /*初始化设备状态*/
        deviceName.setText(deviceItem.getDeviceName());
        deviceTextState.setText(deviceItem.getDeviceRunning() == 0 ? "设备运行" : "设备停止");
        deviceState.setBackgroundColor(deviceItem.getDeviceRunning() == 0 ? Color.rgb(0, 255, 0) : Color.rgb(255, 0, 0));

        initChartFragment();
        return view;
    }

    public void initChartFragment() {
        FragmentManager fragmentManager = fm;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChartFragment chartFragment = new ChartFragment(mcontext);
        fragmentTransaction.add(R.id.diagnosis_chart_container, chartFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_detect:
//                isRunning = true;
                break;

            case R.id.pre_station:
                break;

            case R.id.next_station:
                break;

            case R.id.station_record:
                break;

            case R.id.remove_record:
                break;

            case R.id.scanQR:
                break;

            case R.id.scanRFID:
                break;
        }
    }
}
