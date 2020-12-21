package com.faizurazadri.submission2githubuser_faizurazadri.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.faizurazadri.submission2githubuser_faizurazadri.db.UserHelper;

import java.util.Objects;

import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.AUTHORITY;
import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.CONTENT_URL;
import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.TABLE_USER_NAME;

public class MyContentProvider extends ContentProvider {

    private static final int USER = 0;
    private static final int USER_ID = 1;
    UserHelper userHelper;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
        uriMatcher.addURI(AUTHORITY, TABLE_USER_NAME, USER);
        uriMatcher.addURI(AUTHORITY, TABLE_USER_NAME + "/#",USER_ID);
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }



    @Override
    public boolean onCreate() {
        userHelper = UserHelper.getInstance(getContext());
        userHelper.open();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case USER:
                cursor = userHelper.queryAll();
                break;

            case USER_ID:
                cursor = userHelper.queryByid(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        if (cursor!=null){
            cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long added;
        Uri contentUri = null;
        switch (uriMatcher.match(uri)){
            case USER:
                added = userHelper.InsertProvider(values);
                if (added > 0){
                    contentUri = ContentUris.withAppendedId(CONTENT_URL, added);
                }
                break;
            default:
                added = 0;
                break;
        }

        if (added>0){
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return contentUri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int update;
        switch (uriMatcher.match(uri)){
            case USER_ID:
                update = userHelper.UpdateProvider(uri.getLastPathSegment(), values);
                break;
            default:
                update = 0;
                break;
        }
        if (update>0){
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return update;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int delete;
        switch (uriMatcher.match(uri)){
            case USER_ID:
                delete = userHelper.DeleteProvider(uri.getLastPathSegment());
                break;
            default:
                delete = 0;
                break;
        }

        if (delete>0){
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        }
        return delete;
    }
}