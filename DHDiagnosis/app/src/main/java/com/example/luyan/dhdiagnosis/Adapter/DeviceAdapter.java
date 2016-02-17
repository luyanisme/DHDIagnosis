package com.example.luyan.dhdiagnosis.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.MetaData.DeviceItem;
import com.example.luyan.dhdiagnosis.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luyan on 2/16/16.
 */
public class DeviceAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mcontext;
    private ArrayList<DeviceItem> deviceLists;
    private Handler handler;

    @Override
    public int getCount() {
        return deviceLists.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.devices_list_item, null);
            holder = new ViewHolder();
            /**得到各个控件的对象*/
            holder.deviceName = (TextView) convertView.findViewById(R.id.device_name);
            holder.deviceRunning = (Switch) convertView.findViewById(R.id.device_running);
            holder.deviceState = (TextView) convertView.findViewById(R.id.device_state);
            holder.deviceDetail = (Button) convertView.findViewById(R.id.device_detail);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }

        holder.deviceName.setText(getData(deviceLists).get(position).get("deviceName").toString());
        holder.deviceRunning.setChecked(getData(deviceLists).get(position).get("deviceRunning").toString().equals("0") ? true : false);
        holder.deviceState.setText(getData(deviceLists).get(position).get("deviceState").toString().equals("0") ? "已检" : "未检");

        holder.deviceDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MyListViewBase", "你点击了按钮" + position);
                Message message = new Message();
                message.what = 0;
                Bundle bundle = new Bundle();
                bundle.putString("signal", String.valueOf(position));
                message.setData(bundle);
                handler.sendMessage(message);

            }
        });
        return convertView;
    }

    public DeviceAdapter(Context mcontext, ArrayList<DeviceItem> deviceLists,Handler handler) {
        this.mInflater = LayoutInflater.from(mcontext);
        this.deviceLists = deviceLists;
        this.handler = handler;
    }

    private List<? extends Map<String, ?>> getData(ArrayList strs) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < strs.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            DeviceItem device = (DeviceItem) strs.get(i);
            map.put("deviceName", device.getDeviceName());
            map.put("deviceRunning", device.getDeviceRunning());
            map.put("deviceState", device.getDeviceState());
            list.add(map);

        }

        return list;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public TextView deviceName;
        public Switch   deviceRunning;
        public TextView deviceState;
        public Button   deviceDetail;
    }
}
