package com.faizurazadri.submission2githubuser_faizurazadri.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import retrofit2.http.GET;

@Dao
public interface FavoriteDao {
    @Query("Select * from Favorite ORDER BY id DESC")
    List<FavoriteModel> getAllData();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteModel favoriteModel);

    @Update
    void updateFavorite(FavoriteModel favoriteModel);

    @Delete
    void deleteFavorite(FavoriteModel favoriteModel);
}
