package com.faizurazadri.submission2githubuser_faizurazadri.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.faizurazadri.submission2githubuser_faizurazadri.model.FavoriteDao;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FavoriteModel;

@Database(
    entities = FavoriteModel.class,
        version = 1, exportSchema =false
)
public abstract class GithubUserDatabase extends RoomDatabase {

    private static GithubUserDatabase githubUserDatabase = null;

    public abstract FavoriteDao favoriteDao();

    public static synchronized GithubUserDatabase getInstance(Context context){
        if (githubUserDatabase == null){
            githubUserDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    GithubUserDatabase.class,
                    "GithubUser"
            ).allowMainThreadQueries()
                    .build();
        }
        return githubUserDatabase;
    }
}
