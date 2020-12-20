package com.faizurazadri.submission2githubuser_faizurazadri.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class UserModel implements Parcelable {
    @SerializedName("login")
    public String masuk;

    @SerializedName("avatar_url")
    public String url;

    @SerializedName("id")
    public int id;

    public UserModel(){

    }

    protected UserModel(android.os.Parcel in) {
        masuk = in.readString();
        url = in.readString();
        id = in.readInt();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(android.os.Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getMasuk() {
        return masuk;
    }

    public void setMasuk(String masuk) {
        this.masuk = masuk;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(masuk);
        dest.writeString(url);
        dest.writeInt(id);
    }
}
