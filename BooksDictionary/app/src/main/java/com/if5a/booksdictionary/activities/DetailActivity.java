package com.if5a.booksdictionary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.if5a.booksdictionary.R;
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
        binding.tvDetailPublisher.setText("Publisher : "+booksDictionary.getPublisher());
        Glide.with(DetailActivity.this)
                .load(booksDictionary.getImage_url_l())
                .placeholder(R.drawable.ic_baseline_do_not_disturb_24)
                .into(binding.ivDetailImage);
        binding.tvYear.setText("Year : "+booksDictionary.getYear_of_Publish());
        binding.tvIsbn.setText("ISBN : "+booksDictionary.getISBN());
        binding.tvAuthor.setText("Author : "+booksDictionary.getBook_Author());

    }
}