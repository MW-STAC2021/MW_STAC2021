package com.stac2021.mwproject.callListView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.stac2021.mwproject.R;

import java.util.ArrayList;

public class CallReportListViewAdapter extends BaseAdapter {

    private TextView reportText;
    private TextView reportNum;
    private ImageButton reportCall;

    private TextView lawfirmText;
    private TextView lawfirmNum;
    private ImageButton lawfirmCall;

    private ArrayList<CallReportListViewItem> reportListViewItemList = new ArrayList<CallReportListViewItem>();
    //private ArrayList<CallListViewItem> lawfirmListViewItemList = new ArrayList<CallListViewItem>();

    public CallReportListViewAdapter() {    }

    //Adapter에 사용되는 데이터 수 리턴
    @Override
    public int getCount() {
        return reportListViewItemList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_call_item, viewGroup, false);
        }

        reportText = (TextView) view.findViewById(R.id.reportText);
        reportNum = (TextView) view.findViewById(R.id.reportNum);
        reportCall = (ImageButton) view.findViewById(R.id.reportCall);


        final CallReportListViewItem listViewItem = reportListViewItemList.get(i);

        reportText.setText(listViewItem.getText());
        reportNum.setText(listViewItem.getNum());

        reportCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                String telNum = "tel:" + listViewItem.getNum();
                //Log.d("myapp", telNum);
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(telNum));
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public Object getItem(int i) {
        return reportListViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(String text, String num){
        CallReportListViewItem item = new CallReportListViewItem();

        item.setText(text);
        item.setNum(num);
        reportListViewItemList.add(item);

    }


}
