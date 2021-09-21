package server_info_data;

import com.google.gson.annotations.SerializedName;

public class InfoListResponse {
    @SerializedName("info_type")
    public String type;
    @SerializedName("info_type_icon")
    public String infoTypeIcon;
    @SerializedName("title")
    public String title;
    @SerializedName("posting_time")
    public String postingTime;
    @SerializedName("content_path")
    public String contentPath;

    public String getToolBarType() {
        if (type.equals("0")) return "여성";
        else if (type.equals("1")) return "생활";

        return "0";
    }
}
