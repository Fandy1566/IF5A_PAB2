package com.if5a.booksdictionary.databases;

import android.provider.BaseColumns;

public class DatabaseConstants {
    static String TABLE_BOOKS_DICTIONARY = "books_dictionary";
    static final class BooksDictionaryColumns implements BaseColumns{
        static String ISBN = "isbn";
        static String Book_title = "book_title";
        static String Book_Author = "book_author";
        static String Year_of_Publish = "year_of_publish";
        static String Publisher = "publisher";
        static String image_url_s = "image_url_s";
        static String image_url_m = "image_url_m";
        static String image_url_l = "image_url_m";
    }
}
