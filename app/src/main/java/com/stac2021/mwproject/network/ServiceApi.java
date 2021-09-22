package com.stac2021.mwproject.network;

import com.stac2021.mwproject.other_data.KeepResponse;
import com.stac2021.mwproject.other_data.RecentlyResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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
    @POST("/keep/{user_id}/{info_id}")
    Call<ResponseBody> insertKeep(@Path("user_id") String userId, @Path("info_id") String infoId);

    // 즐겨찾기 삭제
    @DELETE("/keep/{user_id}/{info_id}")
    Call<String> deleteKeep(@Path("user_id") String userId, @Path("info_id") String infoId);

    // 즐겨찾기 페이지
    @GET("/keep/list")
    Call<List<KeepResponse>> KeepList(@Query("user_id") String userId);

    // 최근 본 정보 추가
    @POST("/recently/{user_id}/{info_id}")
    Call<ResponseBody> insertRecently(@Path("user_id") String userId, @Path("info_id") String infoId);

    // 최근 본 정보 삭제
    @DELETE("/recently/{user_id}")
    Call<String> deleteRecently(@Path("user_id") String userId);

    // 최근 본 정보 페이지
    @GET("/recently/list")
    Call<List<RecentlyResponse>> RecentlyList(@Query("user_id") String userId);
}