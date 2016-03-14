package com.example.luyan.dhdiagnosis.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by luyan on 3/1/16.
 */
public class HexParseUtils {

    /*无信号*/
    public static final int NO_SIGNAL = 0000;

    /*传感器已连接*/
    public static final int SENSOR_BATTERY = 9990;

    /*传感器参数*/
    public static final int SENSOR_PARAMS = 9991;

    /*传感器采样*/
    public static final int SENSOR_SAMPLING = 9992;

    private static final String HEX = "0123456789ABCDEF";

    public static byte[] parseToSingleBundle(byte[] bytes) {

        int index;

        /*包总长度*/
        int totalLength;

        /*单个包*/
        byte[] singleBoundle = new byte[0];

        for (int i = 0; i < bytes.length; i++) {
            if (toHex(bytes[i]).equals("81")) {
                int byteLength = Integer.parseInt("" + toHex(bytes[i + 1]) + toHex(bytes[i + 2]), 16);
                /*2表示长度位，1表示包头位，1表示校验位，(依次向后)*/
                totalLength = byteLength + 2 + 1 + 1;
                singleBoundle = Arrays.copyOfRange(bytes, i, i + totalLength);
                break;
            }
        }
        return singleBoundle;
    }

    public static final int parseBundle(byte[] bytes) {

        if (bytes.length > 0) {
            String param_1 = toHex(bytes[3]);
            String param_2 = toHex(bytes[4]);

            if (param_1.equals("04") && param_2.equals("14")) {
                return SENSOR_BATTERY;
            } else if (param_1.equals("01") && param_2.equals("12")) {
                return SENSOR_PARAMS;
            } else if (param_1.equals("01") && param_2.equals("18")) {
                return SENSOR_SAMPLING;
            } else {
                return NO_SIGNAL;
            }
        } else {
            return NO_SIGNAL;
        }
    }

    public static final ArrayList dataOfBundle(int type, byte[] bytes) {

        ArrayList<Object> data = new ArrayList<>();
        switch (type) {
            case SENSOR_BATTERY:
                data.add(String.valueOf(bytes[5]));
                break;
            case SENSOR_PARAMS:

                String k1 = "" + String.valueOf(toHex(bytes[7])) +
                        String.valueOf(toHex(bytes[8])) +
                        String.valueOf(toHex(bytes[9])) +
                        String.valueOf(toHex(bytes[10]));

                String k2 = "" + String.valueOf(toHex(bytes[11])) +
                        String.valueOf(toHex(bytes[12])) +
                        String.valueOf(toHex(bytes[13])) +
                        String.valueOf(toHex(bytes[14]));

                String k3 = "" + String.valueOf(toHex(bytes[15])) +
                        String.valueOf(toHex(bytes[16])) +
                        String.valueOf(toHex(bytes[17])) +
                        String.valueOf(toHex(bytes[18]));

                /*三个系数*/
                float param_1, param_2, param_3;

                try {
                    param_1 = Float.intBitsToFloat(Integer.valueOf(k1, 16));
                    param_2 = Float.intBitsToFloat(Integer.valueOf(k2, 16));
                    param_3 = Float.intBitsToFloat(Integer.valueOf(k3, 16));
                } catch (Exception e) {
                    param_1 = (float) 1.0;
                    param_2 = (float) 1.0;
                    param_3 = (float) 1.0;
                }

                data.add(String.valueOf(bytes[5]));//仪器类型
                data.add(String.valueOf(bytes[6]));//仪器版本
                data.add(param_1);
                data.add(param_2);
                data.add(param_3);
                break;
            case SENSOR_SAMPLING:
                byte[] sampleData = Arrays.copyOfRange(bytes, 9,Integer.parseInt("" + toHex(bytes[1]) + toHex(bytes[2]), 16)+3);
                for (int i = 0; i < sampleData.length / 3; i++) {
                    String pm = "" + String.valueOf(toHex(sampleData[3 * i])) +
                            String.valueOf(toHex(sampleData[3 * i + 1])) +
                            String.valueOf(toHex(sampleData[3 * i + 2]));

                    /*
                    *添加计算公式
                    */

                    data.add(((float)Integer.valueOf(pm, 16)-2737000)/100);
                }
                break;
            default:
                break;
        }
        return data;
    }

    /*字节转换16进制*/
    private static String toHex(byte b) {
        return ("" + HEX.charAt(0xf & b >> 4) + HEX.charAt(b & 0xf));
    }

}
