package com.stac2021.mwproject.network;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import server_info_data.AllInfoResponse;
import server_user_data.JoinData;
import server_user_data.JoinResponse;
import server_user_data.LoginData;
import server_user_data.LoginResponse;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    @FormUrlEncoded
    @POST("/post/list")
    Call<ResponseBody> postList(@Field("data") String data);

    //메인 화면 리스트 [ 필요 데이터 : 프라이머리 키 id(다시 아이디 부여해도 되는지 모르겠음), 제목, 썸네일 ]
    @GET("/info/AllInfo")
    Call<AllInfoResponse> listAllInfo(//@Query("info_type") int infoType,
                                            //@Query("info_type_image") int infoTypeImage,
                                            //@Query("posting_time") String infoPostingTime,
                                            //@Query("image_path") String imagePath
    );
}
/*
CREATE TABLE info(
	-- primary key | id 자동 생성
	info_id INT PRIMARY KEY AUTO_INCREMENT,
	-- 통합, 여성, 생활 정보(0, 1)
	info_type INT NOT NULL,
	-- 생활, 여성 이미지 (0, 1, 2, 0)
	info_type_image INT NOT NULL,
	-- 제목
	title VARCHAR(255) NOT NULL,
	-- 글 추가 날짜
	posting_time DATE,
	-- 이미지 주소 저장
	image_path VARCHAR(255) NOT NULL
);
 */