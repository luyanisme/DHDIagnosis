package com.example.luyan.dhdiagnosis.UI.Activity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.czt.mp3recorder.MP3Recorder;
import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AudioActivity extends AppCompatActivity {

    /*录音按钮*/
    private ImageView record;

    /*录音播放*/
    private ImageView recordPlay;

    /*删除录音*/
    private ImageView delete;

    /*确认录音*/
    private ImageView confirm;

    /*是否正在录音*/
    private boolean isRecording = false;

    /*是否正在播放录音*/
    private boolean isPlaying = false;

    private int mTimeCount;// 时间计数

    private Timer mTimer;// 计时器

    private TextView countShow;//计时时间显示

    private MediaPlayer mPlayer;

    private MP3Recorder mRecorder = new MP3Recorder(new File(FileUtils.STATION_AUDIO_PATH));

    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            countShow.setText(msg.what+"");
            if (mTimeCount == 10) {
                stopTimer();
                mRecorder.stop();
                showOtherWidgt();
                record.setImageResource(R.mipmap.record_start);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        record = (ImageView) findViewById(R.id.record);
        record.setImageResource(R.mipmap.record_start);
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    Toast.makeText(AudioActivity.this, "录音正在播放，请稍后再试！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isRecording) {
                    record.setImageResource(R.mipmap.record_start);
                    stopTimer();
                    mRecorder.stop();
                    showOtherWidgt();
                    isRecording = false;
                } else {
                    record.setImageResource(R.mipmap.record_stop);
                    configTimer();
                    FileUtils.appFileDir("audio");
                    try {
                        mRecorder.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(AudioActivity.this, "录音时间最长1分钟", Toast.LENGTH_SHORT).show();
                    hideOtherWidgt();
                    isRecording = true;
                }
            }
        });

        recordPlay = (ImageView) findViewById(R.id.record_play);
        recordPlay.setImageResource(R.mipmap.record_play);
        recordPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    recordPlay.setImageResource(R.mipmap.record_play);
                    pausePlay();
                    isPlaying = false;
                } else {
                    recordPlay.setImageResource(R.mipmap.record_pause);
                    startPlay();
                    isPlaying = true;
                }
            }
        });

        delete = (ImageView) findViewById(R.id.record_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying){
                    Toast.makeText(AudioActivity.this, "录音正在播放，请稍后再试！", Toast.LENGTH_SHORT).show();
                    return;
                }
                File file = new File(FileUtils.STATION_AUDIO_PATH);
                file.delete();
                finish();
            }
        });

        confirm = (ImageView) findViewById(R.id.record_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (FileUtils.fileIsExists(FileUtils.STATION_AUDIO_PATH)){
            showOtherWidgt();
        }

        countShow = (TextView) findViewById(R.id.numShow);
        mPlayer = new MediaPlayer();
    }

    private void configTimer(){
        mTimeCount = 0;// 时间计数器重新赋值
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                mTimeCount++;
                Message msg = new Message();
                msg.what = mTimeCount;
                handler.sendMessage(msg);
            }
        }, 0, 1000);
    }

    private void stopTimer(){
        mTimer.cancel();
    }

    private void startPlay(){
        try {
            recordPlay.setClickable(false);
            mPlayer.reset();
            //设置要播放的文件
            mPlayer.setDataSource(FileUtils.STATION_AUDIO_PATH);
            mPlayer.prepare();
            //播放
            mPlayer.start();
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    recordPlay.setImageResource(R.mipmap.record_play);
                    isPlaying = false;
                    recordPlay.setClickable(true);
                }
            });
        }catch(Exception e){
        }
    }

    private void pausePlay(){
        if (mPlayer != null){
            mPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecorder.stop();
        mPlayer.release();
        mPlayer = null;
    }

    private void showOtherWidgt(){
        recordPlay.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        confirm.setVisibility(View.VISIBLE);
    }

    private void hideOtherWidgt(){
        recordPlay.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isRecording) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                Toast.makeText(AudioActivity.this, "正在录音，请稍后再试！", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
        }

        return false;

    }

}
