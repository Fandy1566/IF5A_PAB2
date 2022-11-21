package com.if5a.tulisaja.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5a.tulisaja.databinding.ActivityRegisterBinding;
import com.if5a.tulisaja.models.ValueNoData;
import com.if5a.tulisaja.services.APIService;
import com.if5a.tulisaja.services.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String konfirmasiPassword = binding.etKonfrimasiPassword.getTransitionName().toString();

                boolean bolehRegister = true;
                if (TextUtils.isEmpty(username)) {
                    bolehRegister = false;
                    binding.etUsername.setError("Username tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(password)) {
                    bolehRegister = false;
                    binding.etPassword.setError("Password tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(konfirmasiPassword)) {
                    bolehRegister = false;
                    binding.etKonfrimasiPassword.setError("Konfirmasi Password tidak boleh kosong!");
                }
                if (!password.equals(konfirmasiPassword)) {
                    bolehRegister = false;
                    binding.etKonfrimasiPassword.setError("Konfirmasi password tidak sama dengan password!");
                }
                if (password.length() < 6) {
                    bolehRegister = false;
                    binding.etPassword.setError("Password minimal 6 karakter!");
                }
                if (bolehRegister){
                    register(username, password);
                }
            }
        });

    }

    private void register(String username, String password) {
        showProgressBar();
        APIService api = Utilities.getRetrofit().create(APIService.class);
        api.registerUser(Utilities.TULIS_AJA_API_KEY,username,password).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this,
                            "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(RegisterActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

}