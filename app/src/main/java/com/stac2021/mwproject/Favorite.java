package com.stac2021.mwproject;

import android.graphics.drawable.Drawable;

public class Favorite {
    Drawable image;
    String title;
    String category;
    boolean checked;

    public Favorite(Drawable image, String title, String category, boolean checked) {
        this.image = image;
        this.title = title;
        this.category = category;
        this.checked = checked;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
