package com.example.luyan.dhdiagnosis.UI.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.luyan.dhdiagnosis.R;


public class NaviFragment extends Fragment implements View.OnClickListener {

    private Button    leftButton;
    private Button    rigthButton;
    private TextView  naviTitle;
    private String    naviTitleStr;
    private String    naviRightTitle;

    public AmendNaviDelegate delegate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_navi, container, false);
        leftButton = (Button) view.findViewById(R.id.navi_left);
        rigthButton = (Button) view.findViewById(R.id.navi_right);
        naviTitle = (TextView) view.findViewById(R.id.navi_title);
        naviTitle.setText(naviTitleStr == null ? "" : naviTitleStr);

        leftButton.setOnClickListener(this);
        rigthButton.setOnClickListener(this);

        if (naviRightTitle != null) {
            rigthButton.setText(naviRightTitle);
            rigthButton.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public AmendNaviDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(AmendNaviDelegate delegate) {
        this.delegate = delegate;
    }

    public void setNaviTitleStr(String naviTitleStr) {
        this.naviTitleStr = naviTitleStr;
    }

    public String getNaviTitleStr() {
        return naviTitleStr;
    }

    public void setNaviRightTitle(String naviRightTitle) {
        this.naviRightTitle = naviRightTitle;
    }

    public String getNaviRightTitle() {
        return naviRightTitle;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navi_left:
                if (delegate != null) {
                    delegate.leftNavi();
                }
                break;

            case R.id.navi_right:
                if (delegate != null) {
                    delegate.rightNavi();
                }
                break;
        }
    }

    public interface AmendNaviDelegate {
        public void leftNavi();

        public void rightNavi();
    }


}
