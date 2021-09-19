package com.stac2021.mwproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_info_data.InfoListResponse;

public class CardViewActivity extends AppCompatActivity {

    TextView typeTitle;
    ImageView typeIcon;
    TextView viewTitle;
    TextView viewDate;
    TextView viewContent;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        typeTitle = findViewById(R.id.details_type_title);
        typeIcon = findViewById(R.id.details_type_icon);
        viewTitle = findViewById(R.id.details_view_title);
        viewDate = findViewById(R.id.details_view_date);
        viewContent = findViewById(R.id.details_view_content);

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //ReadTextFile();
        File file = new File("http://54.89.236.27:3000/infoContent/" + id);

        detailsData();
    }

    private void detailsData() {
        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        //Call<List<AllInfoResponse>> call = service.listAllInfo("all");
        Call<List<InfoListResponse>> call = service.InfoList(id);
        call.enqueue(new Callback<List<InfoListResponse>>() {

            @Override
            public void onResponse(Call<List<InfoListResponse>> call, Response<List<InfoListResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<InfoListResponse> result = response.body();
                    Log.d("myapp", "InfoListResponse : " + String.valueOf(result));
                    for (InfoListResponse info : result) {
                        typeTitle.setText(info.getToolBarType());
                        viewTitle.setText(info.getViewTitle());
                        getTypeImg(info.infoTypeIcon);
                        //날짜만 추출
                        String date = info.getViewPostingTime().substring(0, 10);
                        Log.d("myapp", "날짜 : " + date);
                        viewDate.setText(date);

                        //txt 파일 읽기
                        String contentPath = "http://54.89.236.27:3000/infoContent/" + info.getViewContentPath();
                        getContent(contentPath);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<InfoListResponse>> call, Throwable t) {

            }
        });
    }

    private void getTypeImg(String num){
        switch (num) {
            //여성 - 생리대
            case "00":
                typeIcon.setImageResource(R.drawable.info_icon00);
            //여성 - 브래지어
            case "01":
                typeIcon.setImageResource(R.drawable.info_icon01);
                break;
            case "10": // 생활 - 음식
                typeIcon.setImageResource(R.drawable.info_icon10);
                break;
            case "11":
            case "12": // 생활 - 청소 / 빨래
                typeIcon.setImageResource(R.drawable.info_icon11);
                break;
            case "13": // 생활 - 수리 / 고장
                typeIcon.setImageResource(R.drawable.info_icon13);
            case "14": // 생활 - 쓰레기
                typeIcon.setImageResource(R.drawable.info_icon14);
                break;
        }
    }
    private void getContent(String path){
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(path);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String str = null;
            while ((str = reader.readLine()) != null) {
                sb.append(str + "\n");
            }

            viewContent.setText(sb.toString());
            //Log.d("myapp", sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}