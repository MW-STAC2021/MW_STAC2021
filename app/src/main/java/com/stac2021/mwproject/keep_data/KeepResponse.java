package com.stac2021.mwproject.keep_data;

import com.google.gson.annotations.SerializedName;

public class KeepResponse {

    @SerializedName("info_type")
    public String infoType;
    @SerializedName("title")
    public String title;
    @SerializedName("info_id")
    public String id;
    @SerializedName("thumbnail_path")
    public String thumbnailPath;

    public String getToolBarType() {
        if (infoType.equals("0")) return "여성";
        else if (infoType.equals("1")) return "생활";

        return "0";
    }
}
