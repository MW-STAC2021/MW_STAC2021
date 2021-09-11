package server_user_data;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("id")
    private String userId;

    @SerializedName("name")
    private String userName;

    @SerializedName("email")
    private String userEmail;

    @SerializedName("password")
    private String userPw;

    public JoinData(String userName, String userId, String userEmail, String userPwd){
        this.userName = userName;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPw = userPwd;
    }
}
