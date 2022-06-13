package com.stac2021.mwproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stac2021.mwproject.Favorite;
import com.stac2021.mwproject.InformationActivity;
import com.stac2021.mwproject.MypageCardViewAdapter;
import com.stac2021.mwproject.NoticeActivity;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.RecentActivity;
import com.stac2021.mwproject.app;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;
import com.stac2021.mwproject.other_data.KeepResponse;
import com.stac2021.mwproject.other_data.RecentlyResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageFragment extends Fragment {

    androidx.appcompat.widget.Toolbar tb;
    RecyclerView recyclerView;
    MypageCardViewAdapter adapter;
    TextView toolbar_title, username;
    ImageView toolbar_icon;
    private List<Favorite> infoList = new ArrayList<>();
    ArrayList<String> itemImage = new ArrayList<>();
    ArrayList<String> itemTitle = new ArrayList<>();
    ArrayList<String> itemId = new ArrayList<>();
    Button btnRecent;
    Context context;
    Button btn1, btn2,  btn4, btn5;
    LinearLayout btn3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mypage, container, false);
        tb = view.findViewById(R.id.toolbar) ;
        toolbar_title = view.findViewById(R.id.toolbar_title);
        toolbar_icon = view.findViewById(R.id.toolbar_icon);
        username = view.findViewById(R.id.my_page_user_name_jjin);
        username.setText(app.userName);

        toolbar_title.setText("마이페이지");
        toolbar_icon.setImageResource(R.drawable.icon_mypage);

        TextView myPage = view.findViewById(R.id.my_page_user_name_jjin);
        String userId = ((app)getActivity().getApplication()).getUserId();
        String userName = ((app)getActivity().getApplication()).getUserName();
Log.d("myapp", "mypage name : " + userName);
        myPage.setText(userName);
        ((AppCompatActivity)getActivity()).setSupportActionBar(tb);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
        Call<List<RecentlyResponse>> call = service.RecentlyList(userId);
        call.enqueue(new Callback<List<RecentlyResponse>>() {
            @Override
            public void onResponse(Call<List<RecentlyResponse>> call, Response<List<RecentlyResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RecentlyResponse> result = response.body();
                    if(result.size() != 0){
                        for (RecentlyResponse info : result) {
                            itemImage.add(info.thumbnailPath);
                            itemTitle.add(info.title);
                            itemId.add(info.id);
                        }
                        adapter = new MypageCardViewAdapter(itemId, itemImage, itemTitle, getContext());
                        recyclerView.setAdapter(adapter);
                    }
                }
                else{
                    Log.d("myapp", "bookMark - else err");
                }
            }

            @Override
            public void onFailure(Call<List<RecentlyResponse>> call, Throwable t) {
                Toast.makeText(context, "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        btnRecent = view.findViewById(R.id.btnRecent);

        btn1 = view.findViewById(R.id.mypage_btn1);
        btn2 = view.findViewById(R.id.mypage_btn2);
        btn3 = view.findViewById(R.id.mypage_btn3);
        btn4 = view.findViewById(R.id.mypage_btn4);
        btn5 = view.findViewById(R.id.mypage_btn5);

        btnRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), RecentActivity.class);
                startActivity(in);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), NoticeActivity.class);
                startActivity(in);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getContext(), InformationActivity.class);
                startActivity(in);
            }
        });


        return view;
    }

    private void recentlyData(String userId){

    }
}