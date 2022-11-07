package com.if5a.booksdictionary.databases;

import static android.provider.BaseColumns._ID;

import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.Book_Author;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.Book_title;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.ISBN;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.Publisher;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.Year_of_Publish;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.image_url_l;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.image_url_m;
import static com.if5a.booksdictionary.databases.DatabaseConstants.BooksDictionaryColumns.image_url_s;
import static com.if5a.booksdictionary.databases.DatabaseConstants.TABLE_BOOKS_DICTIONARY;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "dbBooks";
    private static final int DATABASE_VERSION = 1;
    private static String CREATE_TABLE_BOOKS_DICTIONARY = "CREATE TABLE " +
            TABLE_BOOKS_DICTIONARY + " ("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ISBN + " STRING(9) NOT NULL, "
            + Book_title + " TEXT NOT NULL, "
            + Book_Author+" TEXT NOT NULL,"
            + Year_of_Publish+" STRING(5) NOT NULL,"
            + Publisher+" TEXT NOT NULL,"
            + image_url_s+" TEXT NOT NULL,"
            + image_url_m+" TEXT NOT NULL,"
            + image_url_l+" TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKS_DICTIONARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS_DICTIONARY);
        onCreate(sqLiteDatabase);
    }
}
