package com.stac2021.mwproject.network;

import com.stac2021.mwproject.keep_data.KeepResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import server_info_data.AllInfoResponse;
import server_info_data.InfoListResponse;
import server_info_data.InfoSearchResponse;
import server_user_data.JoinData;
import server_user_data.JoinResponse;
import server_user_data.LoginData;
import server_user_data.LoginResponse;
import server_user_data.UserInfoResponse;

public interface ServiceApi {
    //로그인
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    // 회원가입
    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

    //user 정보 저장
    @GET("/user/info/{id}")
    Call<UserInfoResponse> userInfo(@Path("id") String id);

    // 메인 화면 리스트 [ 필요 데이터 : 프라이머리 키 id(다시 아이디 부여해도 되는지 모르겠음), 제목, 썸네일 ]
    @GET("/info/infoList/{type}")
    Call<List<AllInfoResponse>> listAllInfo(@Path("type") String type);

    // 정보 데이터
    @GET("/info/list/{id}")
    Call<List<InfoListResponse>> InfoList(@Path("id") String id);

    // 검색
    @GET("/info/search")
    Call<List<InfoSearchResponse>> InfoSearch(@Query("keyword") String keyword);

    // 즐겨찾기 추가
    @POST("/keep/{member_seq}/{info_seq}")
    Call<ResponseBody> insertKeep(@Path("member_seq") int memberSeq, @Path("info_seq") int infoSeq);

    // 즐겨찾기 삭제
    @POST("/keep/{member_seq}/{info_seq}")
    Call<String> deleteKeep(@Path("member_seq") int memberSeq, @Path("info_seq") int infoSeq);

    // 즐겨찾기 페이지
    @GET("/keep/list")
    Call<List<KeepResponse>> KeepList(@Query("user_id") String userId);
}