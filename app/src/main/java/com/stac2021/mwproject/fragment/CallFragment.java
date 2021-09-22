package com.stac2021.mwproject.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.stac2021.mwproject.R;
import com.stac2021.mwproject.callListView.CallReportListViewAdapter;

public class CallFragment extends Fragment {

    //report list view
    private ListView reportListView;
    private CallReportListViewAdapter reportAdpater;

    //lawfirm list view
    private ListView lawfirmListView;
    private CallReportListViewAdapter lawfirmAdpater;

    TextView toolbar_title;
    ImageView toolbar_icon;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = (ListAdapter) listView.getAdapter();
        if (listAdapter == null) return;
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_call, container, false);

        toolbar_title = v.findViewById(R.id.toolbar_title);
        toolbar_icon = v.findViewById(R.id.toolbar_icon);

        toolbar_title.setText("전화번호");
        toolbar_icon.setImageResource(R.drawable.icon_call);

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
        reportListView.setAdapter(reportAdpater);
        reportAdpater.notifyDataSetChanged();

        //report list view
        lawfirmAdpater = new CallReportListViewAdapter();
        lawfirmListView = (ListView) v.findViewById(R.id.lawfirmListView);
        lawfirmListView.setAdapter(lawfirmAdpater);

        lawfirmAdpater.addItem("법무법인 굳센 (24시)", "02-598-8284");
        lawfirmAdpater.addItem("K2 법률사무소 (24시)", "02-594-4777");
        lawfirmAdpater.addItem("지솔 법률사무소 (무료)", "02-591-5921");
        lawfirmAdpater.addItem("법무법인 이룸 (무료)", "010-9603-3350");
        lawfirmAdpater.addItem("법무법인 심평 (무료, 지···", "010-6624-1728");

        lawfirmListView.setAdapter(lawfirmAdpater);
        lawfirmAdpater.notifyDataSetChanged();
        setListViewHeightBasedOnChildren(reportListView);
        setListViewHeightBasedOnChildren(lawfirmListView);

        return v;
    }
}
