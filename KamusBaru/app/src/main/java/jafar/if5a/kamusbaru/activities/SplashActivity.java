package jafar.if5a.kamusbaru.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import jafar.if5a.kamusbaru.R;
import jafar.if5a.kamusbaru.databases.KamusHelper;
import jafar.if5a.kamusbaru.databinding.ActivitySplashBinding;
import jafar.if5a.kamusbaru.models.Kamus;
import jafar.if5a.kamusbaru.utilities.AppPreference;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void>
    {
        final String TAG = LoadData.class.getSimpleName();
        KamusHelper kamusHelper;
        AppPreference appPreference;
        double progress;
        double maxProgress = 100;

        @Override
        protected void onPreExecute() {
            kamusHelper = new KamusHelper(SplashActivity.this);
            appPreference = new AppPreference(SplashActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {
                ArrayList<Kamus> kamusEnglishIndonesia = preLoadRawEnglishIndonesia();

                kamusHelper.open();

                progress = 30;
                publishProgress((int) progress);
                Double progressMaxInsert = 80.0;
                Double progressDiff = (progressMaxInsert - progress) / (kamusEnglishIndonesia.size());

//                for(Kamus kamus : kamusEnglishIndonesia)
//                {
//                    kamusHelper.insertDataEnglishIndonesia(kamus);
//                    progress += progressDiff;
//                    publishProgress((int) progress);
//                }
                kamusHelper.beginTransaction();

                try {
                    for(Kamus kamus : kamusEnglishIndonesia)
                    {
                        kamusHelper.insertDataEnglishIndonesia(kamus);
                        progress += progressDiff;
                        publishProgress((int) progress);
                    }
                    kamusHelper.setTransactionSuccess();
                } catch (Exception e) {
                    Log.e(TAG, "doInBackground: Exception " + e.getMessage());
                }

                kamusHelper.endTranscation();

                kamusHelper.close();
                appPreference.setFirstRun(false);
                publishProgress((int) maxProgress);

            } else {
                try {
                     synchronized (this){
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

        @Override
        protected void onProgressUpdate(Integer... values) { // ... adalah array
            binding.progressBar.setProgress(values[0]);
            binding.tvLoading.setText("Loading " + values[0] + "% ...");
        }

        @Override
        protected void onPostExecute(Void unused) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public ArrayList<Kamus> preLoadRawEnglishIndonesia() {
        ArrayList<Kamus> kamusArrayList = new ArrayList<>();
        String line = null;
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(R.raw.english_indonesia);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            int count = 0;
            do {
                line = reader.readLine();
                String[] splistr = line.split("\t");

                Kamus kamus;
                kamus = new Kamus(splistr[0], splistr[1]);
                kamusArrayList.add(kamus);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kamusArrayList;
    }
}