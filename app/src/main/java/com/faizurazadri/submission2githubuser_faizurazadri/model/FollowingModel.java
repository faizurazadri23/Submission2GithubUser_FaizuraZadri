package com.faizurazadri.submission2githubuser_faizurazadri.model;

import com.google.gson.annotations.SerializedName;

public class FollowingModel {
    @SerializedName("login")
    public String masuk;

    @SerializedName("avatar_url")
    public String url;

    @SerializedName("id")
    public int id;

    public String getMasuk() {
        return masuk;
    }


    public String getUrl() {
        return url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
