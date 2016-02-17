package com.example.luyan.dhdiagnosis.UI.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;

public class BaseActivity extends AppCompatActivity implements NaviFragment.AmendNaviDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    /*创建导航栏*/
    public void initNavi(int activityId,String naviTitle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NaviFragment naviFragment = new NaviFragment();
        naviFragment.setNaviTitleStr(naviTitle);
        naviFragment.setDelegate(this);
        fragmentTransaction.add(activityId, naviFragment);
        fragmentTransaction.commit();

    }

    /*left button of navi*/
    @Override
    public void leftNavi() {
        this.finish();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

    /*right button of navi*/
    @Override
    public void rightNavi() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

}
