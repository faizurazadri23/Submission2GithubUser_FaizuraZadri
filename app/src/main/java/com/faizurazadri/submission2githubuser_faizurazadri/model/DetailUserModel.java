package com.faizurazadri.submission2githubuser_faizurazadri.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DetailUserModel{

    @SerializedName("login")
    private String masuk;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private Object email;

    @SerializedName("followers")
    private int followers;

    @SerializedName("avatar_url")
    private String url;

    @SerializedName("following")
    private int following;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;



    public String getMasuk() {
        return masuk;
    }

    public void setMasuk(String masuk) {
        this.masuk = masuk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
