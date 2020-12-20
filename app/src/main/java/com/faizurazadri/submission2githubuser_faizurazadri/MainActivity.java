package com.faizurazadri.submission2githubuser_faizurazadri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterUsers;
import com.faizurazadri.submission2githubuser_faizurazadri.model.ResponseUserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<UserModel> data = new ArrayList<>();
    private RecyclerView recyclerViewberanda;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.search_user));
        }

        progressBar = findViewById(R.id.progressbard_beranda);
        SearchView searchView = findViewById(R.id.search_user);

        recyclerViewberanda = findViewById(R.id.recyc_searchuser);

        recyclerViewberanda.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager!= null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.user));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    tampilProgressbar(true);
                    if (query != null){
                        getData(query);
                    }else{
                        Toast.makeText(getApplicationContext(), "Silahkan Masukkan Username...", Toast.LENGTH_LONG).show();
                    }

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return true;
                }
            });

        }
    }

    private void getData(final String query) {
        Call<ResponseUserModel> responseUserModelCall = ApiClient.getApiService().getCariUser(query);
        responseUserModelCall.enqueue(new Callback<ResponseUserModel>() {
            @Override
            public void onResponse(Call<ResponseUserModel> call, Response<ResponseUserModel> response) {
                if (response.isSuccessful()){
                    data = response.body().getItems();
                    recyclerViewberanda.setAdapter(new AdapterUsers(MainActivity.this, data));
                    tampilProgressbar(false);
                }else{
                    Toast.makeText(getApplicationContext(), "Tidak Berhasil Mengambil Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUserModel> call, Throwable t) {
                tampilProgressbar(false);
                Toast.makeText(getApplicationContext(), "Permintaan Gagal " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void tampilProgressbar(boolean b) {
        if (b){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_utama, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.gantibahasa){
            Intent pengaturan = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(pengaturan);
        }else if (item.getItemId() == R.id.exit){
            moveTaskToBack(true);
            finish();
            System.exit(0);
        }else if (item.getItemId() == R.id.favorite){
            Intent favorite = new Intent(MainActivity.this, FavoriteActivity.class);
            startActivity(favorite);
        }else if(item.getItemId() == R.id.pengaturan){
            Intent settings = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(settings);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}