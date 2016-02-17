package com.example.luyan.dhdiagnosis.MetaData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luyan on 2/16/16.
 */
public class DeviceItem implements Parcelable{

    private String deviceName;
    private int deviceRunning;
    private int deviceState;

    public void setDeviceName(String deviceName) {
        deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceRunning(String deviceRunning) {
        deviceRunning = deviceRunning;
    }

    public int getDeviceRunning() {
        return deviceRunning;
    }

    public void setDeviceState(String deviceState) {
        deviceState = deviceState;
    }

    public int getDeviceState() {
        return deviceState;
    }

    public DeviceItem (String deviceName,int deviceRunning,int deviceState){
        this.deviceName = deviceName;
        this.deviceRunning = deviceRunning;
        this.deviceState = deviceState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceName);
        dest.writeInt(deviceRunning);
        dest.writeInt(deviceState);
    }

    public static final Parcelable.Creator<DeviceItem> CREATOR = new Creator<DeviceItem>()
    {
        @Override
        public DeviceItem createFromParcel(Parcel source) {
            return new DeviceItem(source.readString(),source.readInt(),source.readInt());
        }

        @Override
        public DeviceItem[] newArray(int size) {
            return new DeviceItem[0];
        }
    };
}
