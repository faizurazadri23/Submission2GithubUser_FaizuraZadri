package com.faizurazadri.submission2githubuser_faizurazadri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterFavoriteUser;
import com.faizurazadri.submission2githubuser_faizurazadri.db.UserHelper;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    private UserHelper userHelper;
    private ArrayList<UserModel> detailUserModels = new ArrayList<>();
    private AdapterFavoriteUser adapterFavoriteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        userHelper = new UserHelper(getApplicationContext());
        userHelper.open();
        detailUserModels = userHelper.getDataUser();
        setRecyleView();
    }

    private void setRecyleView() {

        RecyclerView recyclerView = findViewById(R.id.rv_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapterFavoriteUser = new AdapterFavoriteUser(getApplicationContext());
        recyclerView.setAdapter(adapterFavoriteUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        detailUserModels = userHelper.getDataUser();
        adapterFavoriteUser.setUserModels(detailUserModels);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userHelper.close();
    }
}