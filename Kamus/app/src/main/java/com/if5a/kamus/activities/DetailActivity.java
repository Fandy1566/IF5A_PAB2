package com.if5a.kamus.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.if5a.kamus.databinding.ActivityDetailBinding;
import com.if5a.kamus.models.Kamus;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Kamus kamus = getIntent().getParcelableExtra("EXTRA_KAMUS");

        binding.tvTitle.setText(kamus.getTitle());
        binding.tvDesription.setText(kamus.getDescription());
    }

}