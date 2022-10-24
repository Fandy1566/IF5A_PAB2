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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.if5a.booksdictionary.models.BooksDictionary;

import java.util.ArrayList;

public class BooksHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public BooksHelper(Context context) {
        this.context = context;
    }

    public BooksHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<BooksDictionary> getAllDataBooksDictionary(){
        Cursor cursor = database.query(TABLE_BOOKS_DICTIONARY, null, null,
                null,null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<BooksDictionary> arrayList = new ArrayList<>();
        BooksDictionary booksDictionary;
        if(cursor.getCount() > 0){
            do {
                booksDictionary = new BooksDictionary();
                booksDictionary.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                booksDictionary.setISBN(cursor.getString(cursor.getColumnIndexOrThrow(ISBN)));
                booksDictionary.setBook_title(cursor.getString(cursor.getColumnIndexOrThrow(Book_title)));
                booksDictionary.setBook_Author(cursor.getString(cursor.getColumnIndexOrThrow(Book_Author)));
                booksDictionary.setYear_of_Publish(cursor.getInt(cursor.getColumnIndexOrThrow(Year_of_Publish)));
                booksDictionary.setPublisher(cursor.getString(cursor.getColumnIndexOrThrow(Publisher)));
                booksDictionary.setImage_url_s(cursor.getString(cursor.getColumnIndexOrThrow(image_url_s)));
                booksDictionary.setImage_url_m(cursor.getString(cursor.getColumnIndexOrThrow(image_url_m)));
                booksDictionary.setImage_url_l(cursor.getString(cursor.getColumnIndexOrThrow(image_url_l)));

                arrayList.add(booksDictionary);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<BooksDictionary> getAllDataEnglishIndonesiaByTitle(String title){
        Cursor cursor = database.query(TABLE_BOOKS_DICTIONARY, null, Book_title + " LIKE ?",
                new String[]{"%"+ title + "%"},null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<BooksDictionary> arrayList = new ArrayList<>();
        BooksDictionary booksDictionary;
        if(cursor.getCount() > 0){
            do {
                booksDictionary = new BooksDictionary();
                booksDictionary.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                booksDictionary.setISBN(cursor.getString(cursor.getColumnIndexOrThrow(ISBN)));
                booksDictionary.setBook_title(cursor.getString(cursor.getColumnIndexOrThrow(Book_title)));
                booksDictionary.setBook_Author(cursor.getString(cursor.getColumnIndexOrThrow(Book_Author)));
                booksDictionary.setYear_of_Publish(cursor.getInt(cursor.getColumnIndexOrThrow(Year_of_Publish)));
                booksDictionary.setPublisher(cursor.getString(cursor.getColumnIndexOrThrow(Publisher)));
                booksDictionary.setImage_url_s(cursor.getString(cursor.getColumnIndexOrThrow(image_url_s)));
                booksDictionary.setImage_url_m(cursor.getString(cursor.getColumnIndexOrThrow(image_url_m)));
                booksDictionary.setImage_url_l(cursor.getString(cursor.getColumnIndexOrThrow(image_url_l)));

                arrayList.add(booksDictionary);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

}
