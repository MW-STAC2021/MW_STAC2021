package server_user_data;

import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

public class UserInfoResponse {
    @SerializedName("id")
    public String userId;

    @SerializedName("name")
    public String userName;

    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
}
