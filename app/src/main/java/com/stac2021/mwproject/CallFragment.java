package com.stac2021.mwproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.stac2021.mwproject.callListView.CallReportListViewAdapter;

public class CallFragment extends Fragment {

    //report list view
    private ListView reportListView;
    private CallReportListViewAdapter reportAdpater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call_report_list, container, false);

        //report list view
        reportAdpater = new CallReportListViewAdapter();
        reportListView = (ListView) v.findViewById(R.id.reportListView);
        reportListView.setAdapter(reportAdpater);

        reportAdpater.addItem("사이버 경찰청", "112");
        reportAdpater.addItem("여성 긴급 전화 (24시)", "1366");
        reportAdpater.addItem("디지털 성범죄 피해자 ···", "02-735-8994");
        reportAdpater.addItem("한국 성폭력 상담소", "02-338-5801");
        reportAdpater.addItem("한국 사이버 성폭력 대···", "02-817-7959");
        reportAdpater.addItem("한국 여성의 전화 (여성폭력)", "02-2263-6465");
        reportAdpater.addItem("청소년 상담 전화", "1388");

        reportAdpater.notifyDataSetChanged();

        return v;
    }
}