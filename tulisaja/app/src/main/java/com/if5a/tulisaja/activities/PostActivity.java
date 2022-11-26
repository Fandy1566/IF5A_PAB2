package com.if5a.tulisaja.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.if5a.tulisaja.databinding.ActivityPostBinding;

public class PostActivity extends AppCompatActivity {
    private ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }
}