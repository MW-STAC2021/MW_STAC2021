package server_user_data;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("id")
    String userId;

    @SerializedName("password")
    String userPwd;

    public LoginData(String userId, String userPwd){
        this.userId = userId;
        this.userPwd = userPwd;
    }
}
