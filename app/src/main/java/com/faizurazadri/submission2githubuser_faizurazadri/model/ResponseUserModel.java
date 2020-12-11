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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isIncompleteresults() {
        return incompleteresults;
    }

    public void setIncompleteresults(boolean incompleteresults) {
        this.incompleteresults = incompleteresults;
    }

    public List<UserModel> getItems() {
        return items;
    }

    public void setItems(List<UserModel> items) {
        this.items = items;
    }
}
