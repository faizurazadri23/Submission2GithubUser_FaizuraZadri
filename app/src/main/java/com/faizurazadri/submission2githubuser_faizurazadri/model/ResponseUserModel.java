package com.faizurazadri.submission2githubuser_faizurazadri.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUserModel {

    @SerializedName("total_count")
    private int total;

    @SerializedName("incomplete_results")
    private boolean incompleteresults;

    @SerializedName("items")
    private List<UserModel> items;


    public List<UserModel> getItems() {
        return items;
    }


}
