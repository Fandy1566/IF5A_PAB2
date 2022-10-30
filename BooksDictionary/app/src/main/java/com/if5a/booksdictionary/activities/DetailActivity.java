package com.if5a.booksdictionary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.if5a.booksdictionary.databinding.ActivityDetailBinding;
import com.if5a.booksdictionary.models.BooksDictionary;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BooksDictionary booksDictionary = getIntent().getParcelableExtra("EXTRA_BOOKS");

        binding.tvDetailTitle.setText(booksDictionary.getBook_title());
        binding.tvDetailPublisher.setText(booksDictionary.getPublisher());
    }
}