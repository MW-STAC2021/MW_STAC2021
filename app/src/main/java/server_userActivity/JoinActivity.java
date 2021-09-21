package server_userActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.stac2021.mwproject.MainActivity;
import com.stac2021.mwproject.R;
import com.stac2021.mwproject.network.RetrofitClient;
import com.stac2021.mwproject.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import server_user_data.JoinData;
import server_user_data.JoinResponse;

public class JoinActivity extends AppCompatActivity {
    private ServiceApi service;
    private ProgressBar mProgressView;
    private EditText viewName;
    private EditText viewId;
    private EditText viewPw;
    private EditText viewCheckPw;
    private EditText viewEmail;
    private Button JoinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinin);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        mProgressView = (ProgressBar) findViewById(R.id.join_progress);

        viewName = findViewById(R.id.joinName);
        viewId = findViewById(R.id.joinId);
        viewPw = findViewById(R.id.joinPw);
        viewCheckPw = findViewById(R.id.joinCheckPw);
        viewEmail = findViewById(R.id.joinEmail);
        JoinBtn = findViewById(R.id.joinBtn);

        JoinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptJoin();
            }
        });
    }

    private void attemptJoin() {
        viewName.setError(null);
        viewId.setError(null);
        viewPw.setError(null);
        viewCheckPw.setError(null);
        viewEmail.setError(null);

        String inputName = viewName.getText().toString();
        String inputId = viewId.getText().toString();
        String inputPw = viewPw.getText().toString();
        String inputCheckPw = viewCheckPw.getText().toString();
        String inputEmail = viewEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (inputName.isEmpty()) {
            viewName.setError("이름을 입력해주세요.");
            focusView = viewName;
            cancel = true;
        }
        if (inputId.isEmpty()) {
            viewId.setError("아이디를 입력해주세요.");
            focusView = viewId;
            cancel = true;
        }
        if (inputEmail.isEmpty()) {
            viewEmail.setError("이메일을 입력해주세요.");
            focusView = viewEmail;
            cancel = true;
        } else if (!isEmailValid(inputEmail)) {
            viewEmail.setError("유효한 이메일 형식이 아닙니다.");
            focusView = viewEmail;
            cancel = true;
        }
        if (inputPw.isEmpty()) {
            viewPw.setError("비밀번호를 입력해주세요.");
            focusView = viewPw;
            cancel = true;
        } else if (!isPasswordValid(inputPw)) {
            viewPw.setError("8자 이상의 비밀번호를 입력하세요");
            focusView = viewPw;
            cancel = true;
        }

        if (!inputPw.equals(inputCheckPw)) {
            viewCheckPw.setError("비밀번호가 일치하지 않습니다.");
            focusView = viewPw;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(inputId, inputName, inputEmail, inputPw));
            Log.d("myapp", inputId + "  " + inputName);
            showProgress(true);

        }

    }
    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(JoinActivity.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                showProgress(false);

                if (result.getCode() == 200) {
                    //액티비티 종료
                    finish();

                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
                t.printStackTrace();
                showProgress(false);

            }

        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }
    private void showProgress(boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}