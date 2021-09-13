package server_info_data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class AllInfoResponse {
    @SerializedName("info_id") public int infoId;
    @SerializedName("title") public String infoTitle;
    @SerializedName("thumbnail_path") public String thumbnailPath;
//    @SerializedName("info_type") public int infoType;
//    @SerializedName("info_type_image") public int infoTypeImage;
//    @SerializedName("posting_time") public String infoPostingTime;
//    @SerializedName("image_path") public String imagePath;
}