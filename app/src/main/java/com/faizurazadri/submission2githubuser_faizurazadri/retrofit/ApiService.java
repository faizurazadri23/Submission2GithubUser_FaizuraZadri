package com.faizurazadri.submission2githubuser_faizurazadri.retrofit;

import com.faizurazadri.submission2githubuser_faizurazadri.BuildConfig;
import com.faizurazadri.submission2githubuser_faizurazadri.model.DetailUserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FollowerModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FollowingModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.ResponseUserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/users")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<ResponseUserModel> getCariUser(@Query("q") String username);

    @GET("users/{username}")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<DetailUserModel> getDetailUser(@Path("username") String username);

    @GET("users/{username}/followers")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<List<FollowerModel>> getDataFollowerUser(@Path("username") String username);

    @GET("users/{username}/following")
    @Headers(BuildConfig.GITHUB_TOKEN)
    Call<List<FollowingModel>> getDataFollowingUser(@Path("username") String username);

}
