package com.faizurazadri.submission2githubuser_faizurazadri;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterPage;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterUsers;
import com.faizurazadri.submission2githubuser_faizurazadri.model.DetailUserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.retrofit.ApiClient;
import com.google.android.material.tabs.TabLayout;

import org.parceler.Parcels;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {

    DetailUserModel detailUserModel;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        TextView name = findViewById(R.id.name_detail);
        TextView username = findViewById(R.id.username_detail);
        TextView Lokasi = findViewById(R.id.location_detail);
        ImageView avatar = findViewById(R.id.avatar_detail_user);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewpager);

        Bundle bundle = getIntent().getBundleExtra(AdapterUsers.EXTRA_DATA);
        userModel = Parcels.unwrap(bundle.getParcelable(AdapterUsers.USER_DATA));

        final ProgressDialog progressDialog = new ProgressDialog(DetailUserActivity.this);
        progressDialog.setMessage(getString(R.string.progress_dialog));
        progressDialog.setCancelable(false);
        progressDialog.show();
        Glide.with(DetailUserActivity.this)
                .load(userModel.getUrl())
                .into(avatar);
        username.setText(userModel.getMasuk());

        Call<DetailUserModel> detailUserModelCall = ApiClient.getApiService().getDetailUser(userModel.getMasuk());
        detailUserModelCall.enqueue(new Callback<DetailUserModel>() {
            @Override
            public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
                detailUserModel =response.body();
                name.setText(detailUserModel.getName());
                Lokasi.setText(detailUserModel.getLocation());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Permintaan Gagal "+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        AdapterPage pagerAdapter = new AdapterPage(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setElevation(0);
    }
}