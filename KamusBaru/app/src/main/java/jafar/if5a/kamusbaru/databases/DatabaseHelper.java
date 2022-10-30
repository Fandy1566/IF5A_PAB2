package jafar.if5a.kamusbaru.databases;

import static android.provider.BaseColumns._ID;
import static jafar.if5a.kamusbaru.databases.DatabaseContract.EnglishIndonesiaColumns.ENGLISH_INDONESIA_DESCRIPTION;
import static jafar.if5a.kamusbaru.databases.DatabaseContract.EnglishIndonesiaColumns.ENGLISH_INDONESIA_TITLE;
import static jafar.if5a.kamusbaru.databases.DatabaseContract.TABLE_ENGLISH_INDONESIA_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";
    private static final int DATABASE_VERSION = 1;
    private static String CREATE_TABLE_ENGLISH_INDONESIA = "CREATE TABLE " +
            TABLE_ENGLISH_INDONESIA_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ENGLISH_INDONESIA_TITLE + " TEXT NOT NULL, " +
            ENGLISH_INDONESIA_DESCRIPTION + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ENGLISH_INDONESIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH_INDONESIA_NAME);
        onCreate(sqLiteDatabase);
    }
}