package server_userActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stac2021.mwproject.MainActivity;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_user_data.LoginData;
import server_user_data.LoginResponse;

public class LoginActivity extends AppCompatActivity {

    ServiceApi service;
    private ProgressBar mProgressView;
    private EditText viewId;
    private EditText viewPw;
    private Button loginBtn;
    private Button intentJoin;

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

        intentJoin = findViewById(R.id.intentJoin);
        intentJoin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
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
            viewPw.setError("6자 이상의 비밀번호를 입력해주세요.");
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
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    //액티비티 종료
                    finish();

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
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
}