package jafar.if5a.kamusbaru.databases;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_ENGLISH_INDONESIA_NAME = "english_indonesia";

    static final class EnglishIndonesiaColumns implements BaseColumns
    {
        static String ENGLISH_INDONESIA_TITLE = "tittle";
        static String ENGLISH_INDONESIA_DESCRIPTION = "description";
    }

}
