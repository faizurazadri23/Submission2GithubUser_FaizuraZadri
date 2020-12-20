package com.faizurazadri.submission2githubuser_faizurazadri.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.TABLE_USER_NAME;
import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.USERNAME;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String USER_DB_NAME = "userdbGithub";
    private static final int USER_DB_VERSION = 1;

    private static String SQL_CREATE_TABLE_USER = String.format(

            "CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            TABLE_USER_NAME,
            UserDBContract.UserColumns.ID,
            UserDBContract.UserColumns.USERNAME,
            UserDBContract.UserColumns.AVATAR
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER_NAME);
        onCreate(db);
    }

    public UserDBHelper(Context context){
        super(context, USER_DB_NAME, null, USER_DB_VERSION);
    }
}
