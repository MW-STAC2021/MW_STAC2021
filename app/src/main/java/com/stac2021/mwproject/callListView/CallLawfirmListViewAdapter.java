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

public class CallLawfirmListViewAdapter extends BaseAdapter {

    private TextView lawfirmText;
    private TextView lawfirmNum;
    private ImageButton lawfirmCall;

    private ArrayList<CallLawfirmListViewItem> lawfirmListViewItemList = new ArrayList<CallLawfirmListViewItem>();

    public CallLawfirmListViewAdapter() {    }

    //Adapter에 사용되는 데이터 수 리턴
    @Override
    public int getCount() {
        return lawfirmListViewItemList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_call_report_item, viewGroup, false);
        }

        lawfirmText = (TextView) view.findViewById(R.id.lawfirmText);
        lawfirmNum = (TextView) view.findViewById(R.id.lawfirmNum);
        lawfirmCall = (ImageButton) view.findViewById(R.id.lawfirmCall);


        final CallLawfirmListViewItem listViewItem = lawfirmListViewItemList.get(i);

        lawfirmText.setText(listViewItem.getText());
        lawfirmNum.setText(listViewItem.getNum());

        lawfirmCall.setOnClickListener(new View.OnClickListener(){
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
        return lawfirmListViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(String text, String num){
        CallLawfirmListViewItem item = new CallLawfirmListViewItem();

        item.setText(text);
        item.setNum(num);
        lawfirmListViewItemList.add(item);

    }


}
