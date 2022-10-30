package com.if5a.booksdictionary.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;

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
                ArrayList<BooksDictionary> kamusEnglishIndonesia = preLoadRawBooks();
                booksHelper.open();

                double progressMaxInsert = 80.0;
                double progressDiff = (progressMaxInsert - progress) / kamusEnglishIndonesia.size();
                progress = 30;
                publishProgress((int) progress);

                for (BooksDictionary books : kamusEnglishIndonesia) {
                    booksHelper.insertDataBooksDictionary(books);
                    progress += progressDiff;
                    publishProgress((int) progress);
                }

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
                InputStream raw_dictionary = resources.openRawResource(R.raw.Books);

                bufferedReader = new BufferedReader(new InputStreamReader(raw_dictionary));

                int count = 0;
                do {
                    line = bufferedReader.readLine();
                    String[] splitted = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                    BooksDictionary books = new BooksDictionary();
                    books.setISBN(splitted[0]);
                    books.setBook_title(splitted[1]);
                    books.setBook_Author(splitted[2]);
                    books.setYear_of_Publish(splitted[3]);
                    books.setPublisher(splitted[4]);
                    books.setImage_url_s(splitted[5]);
                    books.setImage_url_m(splitted[6]);
                    books.setImage_url_l(splitted[7]);

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