package com.stac2021.mwproject;

import android.util.Log;

import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Recently {
    private volatile static Recently instance;

    static String userId;
    public static Recently getInstance() {
        if (instance == null) {
            synchronized (Recently.class) {
                if (instance == null) {
                    instance = new Recently();
                }
            }
        }
        return instance;
    }
    public Recently() {
    }
    public void getUserId() {
        userId = app.getUserId();
    }

    public void insertRecently(final String infoId) {
        getUserId();
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<ResponseBody> call = service.insertRecently(userId, infoId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("myapp", "insertInfoRcently 실패");
            }
        });
    }

    public void deleteRecently() {
        getUserId();
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<String> call = service.deleteRecently(userId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
