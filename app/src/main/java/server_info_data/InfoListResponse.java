package server_info_data;

import com.google.gson.annotations.SerializedName;

public class InfoListResponse {
    @SerializedName("info_type")
    public String infoType;
    @SerializedName("info_type_icon")
    public String infoTypeIcon;

    @SerializedName("title")
    public String title;
    @SerializedName("posting_time")
    public String postingTime;
    @SerializedName("content_path")
    public String contentPath;

    public String getToolBarType() {
        if (infoType.equals("0")) return "여성";
        else if (infoType.equals("1")) return "통합";

        return "0";
    }

    public String getToolBarIcon() {
        return infoTypeIcon;
    }

    public String getViewTitle() {
        return title;
    }

    public String getViewPostingTime() {
        return postingTime;
    }

    public String getViewContentPath() {
        return contentPath;
    }
}
