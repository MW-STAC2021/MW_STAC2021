package com.stac2021.mwproject.callListView;

public class CallLawfirmListViewItem {

    private String text;
    private String num;

    public void setText(String _text){
        text = _text;
    }

    public void setNum(String _num) {
        num = _num;
    }

    public String getText(){
        return this.text;
    }

    public String getNum(){
        return this.num;
    }
}
