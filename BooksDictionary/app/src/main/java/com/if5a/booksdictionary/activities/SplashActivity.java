package com.if5a.booksdictionary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.if5a.booksdictionary.R;
import com.if5a.booksdictionary.databases.BooksHelper;
import com.if5a.booksdictionary.databinding.ActivitySplashBinding;
import com.if5a.booksdictionary.models.BooksDictionary;
import com.if5a.booksdictionary.utilities.AppPreference;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        final String TAG = LoadData.class.getSimpleName();
        BooksHelper booksHelper;
        AppPreference appPreference;
        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            booksHelper = new BooksHelper(SplashActivity.this);
            appPreference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            binding.progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();

            if (firstRun) {

                ArrayList<BooksDictionary> booksDictionary = preLoadRawBooks();
                booksHelper.open();

                double progressMaxInsert = 80.0;
                double progressDiff = (progressMaxInsert - progress) / booksDictionary.size();
                progress = 30;
                publishProgress((int) progress);

                booksHelper.beginTransaction();

                try {
                    for (BooksDictionary books : booksDictionary) {
                        booksHelper.insertDataBooksDictionary(books);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    booksHelper.setTransactionSuccess();
                } catch (Exception e){
                    e.printStackTrace();
                }

                booksHelper.endTransaction();

                booksHelper.close();
                appPreference.setFirstRun(false);
                publishProgress((int) maxProgress);
            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                        publishProgress(50);

                        this.wait(1000);
                        publishProgress((int) maxProgress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        public ArrayList<BooksDictionary> preLoadRawBooks() {
            ArrayList<BooksDictionary> booksArrayList = new ArrayList<>();
            String line = null;

            BufferedReader bufferedReader;

            try {
                Resources resources = getResources();
                InputStream raw_dictionary = resources.openRawResource(R.raw.books);

                bufferedReader = new BufferedReader(new InputStreamReader(raw_dictionary));

                int count = 0;
                do {
                    line = bufferedReader.readLine();
                    String[] splitted = line.split(",");

                    BooksDictionary books = new BooksDictionary(splitted[0],splitted[1], splitted[2], splitted[3],splitted[4],splitted[5],splitted[6],splitted[7]);

                    booksArrayList.add(books);
                    count++;
                } while (line != null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return booksArrayList;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}