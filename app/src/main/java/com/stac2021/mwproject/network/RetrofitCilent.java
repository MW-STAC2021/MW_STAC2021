package com.stac2021.mwproject.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCilent {
    private final static String BASE_URL =
            "http://54.89.2360.270:3000/";
    private static Retrofit retrofit = null;
/*    private RetrofitClient(){

    }*/

    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
