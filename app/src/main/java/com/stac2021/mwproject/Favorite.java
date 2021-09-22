package com.stac2021.mwproject;

import android.content.Context;
import android.util.Log;

import com.stac2021.mwproject.other_data.KeepResponse;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    Context context;
    ArrayList<String> favId = new ArrayList<>();

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

    public Favorite() {
    }
    public Favorite(String img, String title) {
        this.image = img;
        this.title = title;
    }
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

    public void getUserId() {
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

    public boolean isChecked(String infoId) {
        if (favId.contains(infoId)) {
            return true;
        } else return false;
    }

    public void insertKeep(final String infoId) {
        getUserId();
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<ResponseBody> call = service.insertKeep(userId, infoId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
//                    Log.d("myapp", "insertKeep");
                } else {
//                    Log.e("myapp", "insertKeep 이미 있음");
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
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<String> call = service.deleteKeep(userId, infoId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void keepList() {
        getUserId();
        //Log.d("favo", "== 유저 아이디 : " + userId );
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<KeepResponse>> call = service.KeepList(userId);
        call.enqueue(new Callback<List<KeepResponse>>() {
            @Override
            public void onResponse(@NotNull Call<List<KeepResponse>> call, @NotNull Response<List<KeepResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<KeepResponse> result = response.body();
                    if (result.size() != 0) {
                        for (KeepResponse info : result) {
                            favId.add(info.id);
                            //Log.d("favo", info.id);
                        }
                    }
                } else {
                    Log.d("myapp", "Favorite - else err");
                }
            }

            @Override
            public void onFailure(Call<List<KeepResponse>> call, Throwable t) {

            }
        });
    }
}
