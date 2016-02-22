package com.example.luyan.dhdiagnosis.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.luyan.dhdiagnosis.MetaData.DeviceItem;
import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Activity.BaseActivity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luyan on 2/22/16.
 */
public class DeviceImageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<String> deviceLists;

    public DeviceImageAdapter (Context mcontext,ArrayList<String> deviceLists){
        this.mInflater = LayoutInflater.from(mcontext);
        this.deviceLists = deviceLists;
    }

    @Override
    public int getCount() {
        return deviceLists.size()+1;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.image_grid_item, null);
            holder = new ViewHolder();
            holder.deviceImage = (ImageView) convertView.findViewById(R.id.device_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }

        if (deviceLists.size() == 0) {
            holder.deviceImage.setImageResource(R.drawable.add_icon);
        }else {
            if (position == 0) {
                holder.deviceImage.setImageResource(R.drawable.add_icon);
            } else {

//                String imagePath = getData(deviceLists).get(position-1).get("image").toString();
                String imagePath = deviceLists.get(position-1);
                Bitmap bitmap= BitmapFactory.decodeFile(imagePath);
                holder.deviceImage.setImageBitmap(bitmap);
            }

        }

        return convertView;
    }

    private List<? extends Map<String, ?>> getData(ArrayList images) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < images.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", images.get(i));
            list.add(map);
        }

        return list;
    }

    /**
     * 存放控件
     */
    public final class ViewHolder {
        public ImageView deviceImage;
    }
}
