package com.faizurazadri.submission2githubuser_faizurazadri.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.faizurazadri.submission2githubuser_faizurazadri.model.DetailUserModel;

import java.util.ArrayList;

import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.AVATAR;
import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.ID;
import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.TABLE_USER_NAME;
import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.USERNAME;

public class UserHelper {
    private static final String DATABASE_TABLE = TABLE_USER_NAME;
    private static UserDBHelper userDBHelper;
    private static UserHelper instance;
    private static SQLiteDatabase database;

    public UserHelper(Context context){
        userDBHelper = new UserDBHelper(context);
    }

    public static UserHelper getInstance(Context context){
        if (instance == null){
            synchronized (SQLiteOpenHelper.class){
                if (instance == null){
                    instance = new UserHelper(context);
                }
            }
        }
        return instance;
    }

    public void open() throws SQLException{
        database = userDBHelper.getWritableDatabase();
    }

    public void close(){
        userDBHelper.close();
        if (database.isOpen())
            database.close();
    }

    public Cursor DataAll(){
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                ID+ " ASC");
    }

    public Cursor queryByid(String id){
        return database.query(DATABASE_TABLE, null
        , ID + " = ?"
        , new String[]{id}
        ,null
        ,null
        ,null
        ,null);
    }

    public ArrayList<DetailUserModel> getDataUser(){
        ArrayList<DetailUserModel> detailUserModelArrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                USERNAME + " ASC",
                null);
        cursor.moveToFirst();
        DetailUserModel detailUserModel;
        if (cursor.getCount() > 0){
            do {
                detailUserModel = new DetailUserModel();
                detailUserModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                detailUserModel.setMasuk(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
                detailUserModel.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(AVATAR)));

                detailUserModelArrayList.add(detailUserModel);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }cursor.close();
        return detailUserModelArrayList;
    }

    public long userInsert(DetailUserModel detailUserModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,  detailUserModel.getId());
        contentValues.put(USERNAME, detailUserModel.getMasuk());
        contentValues.put(AVATAR, detailUserModel.getUrl());


        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int userDelete(String id){
        return database.delete(TABLE_USER_NAME, ID+ " = '" + id + "'", null);
    }

    public int DeleteProvider(String id){
        return database.delete(TABLE_USER_NAME, ID+ "=?", new String[]{id});
    }

    public int UpdateProvider(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, ID + " =?", new String[]{id});
    }

    public long InsertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE, null, values);
    }
}
