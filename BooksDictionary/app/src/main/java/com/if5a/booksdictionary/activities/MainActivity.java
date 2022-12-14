package com.if5a.booksdictionary.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.if5a.booksdictionary.adapters.BooksViewAdapter;
import com.if5a.booksdictionary.databases.BooksHelper;
import com.if5a.booksdictionary.databinding.ActivityMainBinding;
import com.if5a.booksdictionary.models.BooksDictionary;
import com.if5a.booksdictionary.utilities.OnItemClickListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BooksViewAdapter booksViewAdapter;
    private BooksHelper booksHelper;
    private OnItemClickListener<BooksDictionary> itemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        itemClickListener = new OnItemClickListener<BooksDictionary>() {
            @Override
            public void onItemClick(BooksDictionary data, int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("EXTRA_BOOKS", data);
                startActivity(intent);
            }
        };

        booksHelper = new BooksHelper(this);
        booksViewAdapter = new BooksViewAdapter(itemClickListener);
        binding.rvBooks.setLayoutManager(new LinearLayoutManager(this));
        binding.rvBooks.setAdapter(booksViewAdapter);

        getAllData();



        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSearch = binding.etSearch.getText().toString();

                if(TextUtils.isEmpty(strSearch)){
                    getAllData();
                } else {
                    booksHelper.open();
                    ArrayList<BooksDictionary> books = booksHelper.getAllDataBooksDictionaryByTitle(strSearch);
                    booksHelper.close();
                    booksViewAdapter.setData(books);
                }
            }
        });
    }

    private void getAllData() {
        booksHelper.open();
        ArrayList<BooksDictionary> booksDictionary = booksHelper.getAllDataBooksDictionary();
        booksHelper.close();
        booksViewAdapter.setData(booksDictionary);
    }

}