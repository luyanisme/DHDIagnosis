package com.example.luyan.dhdiagnosis.UI.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.luyan.dhdiagnosis.R;
import com.example.luyan.dhdiagnosis.UI.Fragment.NaviFragment;

public class BaseActivity extends AppCompatActivity implements NaviFragment.AmendNaviDelegate {

    /*响应键盘事件*/
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        inputMethodManager = (InputMethodManager)getSystemService(BaseActivity.this.INPUT_METHOD_SERVICE);
    }

    /*创建导航栏*/
    public void initNavi(int activityId,String naviTitle,String naviRightTitle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NaviFragment naviFragment = new NaviFragment();
        naviFragment.setNaviTitleStr(naviTitle);
        naviFragment.setNaviRightTitle(naviRightTitle);
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

    public void hideSoftKeyboard() {
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        hideSoftKeyboard();
        return super.onTouchEvent(event);
    }
}
