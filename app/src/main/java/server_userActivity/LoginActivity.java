package server_userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stac2021.mwproject.MainActivity;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.UserInfoItem;
import com.stac2021.mwproject.app;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_user_data.LoginData;
import server_user_data.LoginResponse;
import server_user_data.UserInfoResponse;

public class LoginActivity extends AppCompatActivity {

    ServiceApi service;
    private ProgressBar mProgressView;
    private EditText viewId;
    private EditText viewPw;
    private Button loginBtn;
    private Button unLoginBtn;
    private Button intentJoin;
    private TextView myPage;
    private TextView period;
    private String userName;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        service = RetrofitClient.getClient().create(ServiceApi.class);
        mProgressView = findViewById(R.id.login_progress);

        viewId = findViewById(R.id.loginId);
        viewPw = findViewById(R.id.loginPw);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // 회원가입 인텐트
        intentJoin = findViewById(R.id.intentJoin);
        intentJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        // 아이디 찾기 인텐트
        intentJoin = findViewById(R.id.btn_find_id);
        intentJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(intent);
            }
        });
        // 비밀번호 찾기 인텐트
        intentJoin = findViewById(R.id.btn_find_pw);
        intentJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
                startActivity(intent);
            }
        });
        // 비회원 로그인 인텐트
        unLoginBtn = findViewById(R.id.unLoginBtn);
        unLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void attemptLogin(){
        viewId.setError(null);
        viewPw.setError(null);

        String inputId = viewId.getText().toString();
        String inputPw = viewPw.getText().toString();

        boolean cancel = false;

        View focusView = null;

        if (inputPw.isEmpty()) {
            viewPw.setError("비밀번호를 입력해주세요.");
            focusView = viewPw;
            cancel = true;
        } else if (!isPasswordValid(inputPw)) {
            viewPw.setError("8자 이상의 비밀번호를 입력해주세요.");
            focusView = viewPw;
            cancel = true;
        }

        if (inputId.isEmpty()) {
            viewId.setError("아이디를 입력해주세요.");
            focusView = viewId;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(inputId, inputPw));
            showProgress(true);
        }
    }
    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    //로그인 성공 시 메인화면으로 이동
                    String idid = result.getUserId();
                    Log.d("myapp", result.getMessage());
                    userInfoSave(result.getUserId());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //액티비티 종료
                    finish();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "인터넷 연결이 필요합니다.", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생",t.getMessage());
                t.printStackTrace();
                showProgress(false);
            }
        });

    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    private void userInfoSave(String id){
        Call<UserInfoResponse> call = service.userInfo(id);
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UserInfoResponse result = response.body();
                    Log.d("myapp", result.userId);
                    Log.d("myapp", result.userName);
                    userId = result.userId;
                    userName = result.userName;
                    getUserInfoItem();
                    app.setUserId(userId);
                    app.setUserName(userName);
//                    myPage = findViewById(R.id.my_page_user_name);
//                    myPage.setText(result.getUserName());
//                    period = findViewById(R.id.period_user_name);
//                    period.setText(result.getUserName());
                    // 텍스트 뷰에 네임 값을 넣으려고 하니 오류 발생
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                Log.e("myapp", "에러 : " + t.getMessage());
                Toast.makeText(getApplicationContext(), "사용자 조회 실패", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private UserInfoItem getUserInfoItem(){
        UserInfoItem item = new UserInfoItem();
        item.name = userName;
        item.id  = userId;
        Log.d("myapp", item.name);
        Log.d("myapp", item.id);
        return item;
    }
}