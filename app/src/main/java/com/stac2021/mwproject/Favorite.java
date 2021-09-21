package com.stac2021.mwproject;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Favorite {
    boolean checked;

    String image;
    String title;
    String id;
    String type;

    public Favorite(String img, String title, String type, String id, boolean ch) {
        this.image = img;
        this.title = title;
        this.type = type;
        this.id = id;
        this.checked = ch;

    }

    public String getImage() {
        return image;
    }

    public String getId() {
        Log.d("myapp", "id : " + id);
        return id;  }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String category) {
        this.type = category;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
