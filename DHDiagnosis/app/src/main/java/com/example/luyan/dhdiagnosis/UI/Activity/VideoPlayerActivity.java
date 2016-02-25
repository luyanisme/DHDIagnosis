package com.example.luyan.dhdiagnosis.UI.Activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.utils.FileUtils;

import java.io.File;

public class VideoPlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    MediaPlayer player;
    SurfaceView surface;
    SurfaceHolder surfaceHolder;
    Button play,pause,delete,back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        play=(Button)findViewById(R.id.button1);
        pause=(Button)findViewById(R.id.button2);
        delete=(Button)findViewById(R.id.button3);
        back=(Button)findViewById(R.id.button4);
        surface=(SurfaceView)findViewById(R.id.surface);

        surfaceHolder=surface.getHolder();//SurfaceHolder是SurfaceView的控制接口
        surfaceHolder.addCallback(this); //因为这个类实现了SurfaceHolder.Callback接口，所以回调参数直接this
//        surfaceHolder.setFixedSize(320, 220);//显示的分辨率,不设置为视频默认
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//Surface类型

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.start();
            }});
        pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                player.pause();
            }});
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deleteVideo();
            }});
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }});
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        player=new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDisplay(surfaceHolder);
        //设置显示视频显示在SurfaceView上
        try {
            player.setDataSource(FileUtils.STATION_VIDEO_PATH);
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
        //Activity销毁时停止播放，释放资源。不做这个操作，即使退出还是能听到视频播放的声音
    }

    /*删除该视频*/
    private void deleteVideo() {
        File file = new File(FileUtils.STATION_VIDEO_PATH);
        if (file.exists()){
            file.delete();
            finish();
        }
    }
}
