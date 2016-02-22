package com.example.luyan.dhdiagnosis.UI.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luyan.dhdiagnosis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartContainerFragment extends Fragment implements View.OnClickListener {

    private FragmentManager fm;
    private Context mcontext;
    private TrendChartFragment trendChartFragment;
    private WaveChartFragment waveChartFragment;
    private FrequencyChartFragment frequencyChartFragment;

    public ChartContainerFragment(FragmentManager fm,Context context) {
        // Required empty public constructor
        this.fm = fm;
        this.mcontext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_container, container, false);
        view.findViewById(R.id.trend_chart_btn).setOnClickListener(this);
        view.findViewById(R.id.wave_chart_btn).setOnClickListener(this);
        view.findViewById(R.id.frequency_chart_btn).setOnClickListener(this);

        initTrendChart();
        return view;
    }

    public void initTrendChart(){
        TrendChartFragment trendChartFragment = new TrendChartFragment();
        fm.beginTransaction().add(R.id.chart_fragment_area, trendChartFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.trend_chart_btn:
                if (trendChartFragment == null){
                    trendChartFragment = new TrendChartFragment();
                }
                fm.beginTransaction().replace(R.id.chart_fragment_area, trendChartFragment).commit();
                break;

            case R.id.wave_chart_btn:
                if (waveChartFragment == null){
                    waveChartFragment = new WaveChartFragment(mcontext);
                }
                fm.beginTransaction().replace(R.id.chart_fragment_area,waveChartFragment).commit();
                break;

            case R.id.frequency_chart_btn:
                if (frequencyChartFragment == null){
                    frequencyChartFragment = new FrequencyChartFragment();
                }
                fm.beginTransaction().replace(R.id.chart_fragment_area,frequencyChartFragment).commit();
                break;
        }
    }
}
