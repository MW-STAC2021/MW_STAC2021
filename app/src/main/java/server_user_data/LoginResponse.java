package server_user_data;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public Data data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
