package com.stac2021.mwproject;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorite {
    boolean checked;
    String image;
    String title;
    String id;
    String type;
    static String userId;
    String infoId;
    Context context;

    private volatile static Favorite instance;

    public static Favorite getInstance() {
        if (instance == null) {
            synchronized (Favorite.class) {
                if (instance == null) {
                    instance = new Favorite();
                }
            }
        }
        return instance;
    }

    public Favorite(){}
    public Favorite(String img, String title, String type, String id, boolean ch) {
        this.image = img;
        this.title = title;
        this.type = type;
        this.id = id;
        this.checked = ch;

    }

    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public void getUserId(){
        userId = app.getUserId();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void insertKeep(final String infoId){
        getUserId();
        Log.d("myapp", "!! insert 도달 !!");
        Log.d("myapp", "== 유저 아이디 : " + userId + "  글 아이디 : " + infoId);
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<ResponseBody> call = service.insertKeep(userId, infoId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Log.d("myapp", "insertKeep");
                } else {
                    Log.e("myapp", "insertKeep 이미 있음");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("myapp", "insertKeep 실패");
            }
        });
    }
    public void deleteKeep(final String infoId) {
        getUserId();
//        Log.d("myapp", "!! delete 도달 !!");
//        Log.d("myapp", "== 유저 아이디 : " + userId + "  글 아이디 : " + infoId);

        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<String> call = service.deleteKeep(userId, infoId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!(response.isSuccessful())) {
                    //토스트가 뜨질 않는다!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    Log.d("myapp", "즐겨찾기 제거 ㅠㅠ 실패 ㅠㅠ");
                } else {

                    Toast.makeText(context, "즐겨찾기 제거", Toast.LENGTH_LONG).show();
                    Log.d("myapp", "즐겨찾기 제거");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
