package com.faizurazadri.submission2githubuser_faizurazadri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterFavoriteUser;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterPage;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterUsers;
import com.faizurazadri.submission2githubuser_faizurazadri.model.DetailUserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FavoriteModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.retrofit.ApiClient;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserGithubActivity extends AppCompatActivity {

    DetailUserModel detailUserModel;
    FavoriteModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_github);

        TextView name = findViewById(R.id.name_detail);
        TextView username = findViewById(R.id.username_detail);
        TextView Lokasi = findViewById(R.id.location_detail);
        ImageView avatar = findViewById(R.id.avatar_detail_user);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewpager);

       /* Bundle bundle = getIntent().getBundleExtra(AdapterFavoriteUser.DATA_EXTRA);
        userModel = Parcels.unwrap(bundle.getParcelable(AdapterFavoriteUser.DATA_USER));*/

        final ProgressDialog progressDialog = new ProgressDialog(DetailUserGithubActivity.this);
        progressDialog.setMessage(getString(R.string.progress_dialog));
        progressDialog.setCancelable(false);
        progressDialog.show();
        Glide.with(DetailUserGithubActivity.this)
                .load(userModel.getAvatar_url())
                .into(avatar);
        username.setText(userModel.getUsername());

        Call<DetailUserModel> detailUserModelCall = ApiClient.getApiService().getDetailUser(userModel.getUsername());
        detailUserModelCall.enqueue(new Callback<DetailUserModel>() {
            @Override
            public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
                detailUserModel = response.body();
                name.setText(detailUserModel.getName());
                Lokasi.setText(detailUserModel.getLocation());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Permintaan Gagal " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        AdapterPage pagerAdapter = new AdapterPage(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setElevation(0);
    }
}