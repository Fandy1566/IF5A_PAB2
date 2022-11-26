package com.if5a.tulisaja.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5a.tulisaja.databinding.ActivityLoginBinding;
import com.if5a.tulisaja.databinding.ActivityMainBinding;
import com.if5a.tulisaja.models.ValueNoData;
import com.if5a.tulisaja.services.APIService;
import com.if5a.tulisaja.services.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLoginToRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etLoginUsername.getText().toString();
                String password = binding.etLoginPassword.getText().toString();

                boolean bolehLogin = true;
                if (TextUtils.isEmpty(username)){
                    bolehLogin = false;
                    binding.etLoginUsername.setError("Username tidak boleh kosong");
                }
                if (TextUtils.isEmpty(password)){
                    bolehLogin = false;
                    binding.etLoginPassword.setError("Password tidak boleh kosong");
                }
                if (bolehLogin){
                    login(username,password);
                }
            }
        });
    }

    private void login(String username, String password) {
        showProgressBar();
        APIService api = Utilities.getRetrofit().create(APIService.class);
        api.loginUser(Utilities.TULIS_AJA_API_KEY,username,password).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(LoginActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar() {
        binding.loginProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        binding.loginProgressBar.setVisibility(View.GONE);
    }

}