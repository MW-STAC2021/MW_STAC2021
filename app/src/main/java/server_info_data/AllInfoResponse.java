package server_info_data;

import com.google.gson.annotations.SerializedName;

public class AllInfoResponse {

//    int infoId;
//    String infoTitle;
//    String thumbnailPath;

    @SerializedName("info_id")
    public int infoId;
    @SerializedName("title")
    public String infoTitle;
    @SerializedName("thumbnail_path")
    public String thumbnailPath;
    @SerializedName("info_type") public String infoType;
//    @SerializedName("info_type_image") public int infoTypeImage;
//    @SerializedName("posting_time") public String infoPostingTime;
//    @SerializedName("image_path") public String imagePath;

    public AllInfoResponse() {

    }

    public String getToolBarType() {
        if (infoType.equals("0")) return "여성";
        else if (infoType.equals("1")) return "생활";

        return "0";
    }
    public AllInfoResponse(int id, String title, String thumbnailPath) {
        this.infoId = id;
        this.infoTitle = title;
        this.thumbnailPath = thumbnailPath;
    }

    public int getId() {
        return infoId;
    }

    public String getTitle() {
        return infoTitle;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }
}