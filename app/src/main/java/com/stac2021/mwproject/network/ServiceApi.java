package com.stac2021.mwproject.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import server_user_data.JoinData;
import server_user_data.JoinResponse;
import server_user_data.LoginData;
import server_user_data.LoginResponse;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}
