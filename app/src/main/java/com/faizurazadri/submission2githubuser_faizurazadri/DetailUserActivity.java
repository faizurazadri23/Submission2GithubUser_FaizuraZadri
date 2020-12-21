package com.faizurazadri.submission2githubuser_faizurazadri;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterPage;
import com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract;
import com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBHelper;
import com.faizurazadri.submission2githubuser_faizurazadri.db.UserHelper;
import com.faizurazadri.submission2githubuser_faizurazadri.model.DetailUserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.retrofit.ApiClient;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.faizurazadri.submission2githubuser_faizurazadri.db.UserDBContract.UserColumns.TABLE_USER_NAME;

public class DetailUserActivity extends AppCompatActivity {

    DetailUserModel detailUserModel;
    UserModel userModel;
    private UserHelper userHelper;
    private ArrayList<UserModel> detailUserModels = new ArrayList<>();
    UserModel detailUserModel1 = new UserModel();

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

        userModel = getIntent().getParcelableExtra("DATA_USER");

        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Detail "+ userModel.getMasuk());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

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

        userHelper = UserHelper.getInstance(getApplicationContext());

        setFavoriteDao();
    }

    public void setFavoriteDao() {
        MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.fab_favorite);

        if (EXIST(userModel.getMasuk())) {
            materialFavoriteButton.setFavorite(true);
            materialFavoriteButton.setOnFavoriteChangeListener((buttonView, favorite) -> {
                if (favorite) {
                    detailUserModels = userHelper.getDataUser();
                    userHelper.userInsert(detailUserModel);
                    Toast.makeText(DetailUserActivity.this, "Berhasil Menambahkan Ke Daftar Favorit", Toast.LENGTH_LONG).show();
                } else {
                    detailUserModels = userHelper.getDataUser();
                    userHelper.userDelete(String.valueOf(detailUserModel.getId()));
                    Toast.makeText(DetailUserActivity.this, "Berhasil Menghapus Dari Data Favorit", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            materialFavoriteButton.setOnFavoriteChangeListener(((buttonView, favorite) -> {
                if (favorite) {
                    detailUserModels = userHelper.getDataUser();
                    userHelper.userInsert(detailUserModel);
                    Toast.makeText(DetailUserActivity.this, "Berhasil Menambahkan Ke Daftar Favorit", Toast.LENGTH_LONG).show();
                } else {
                    detailUserModels = userHelper.getDataUser();
                    userHelper.userDelete(String.valueOf(detailUserModel.getId()));
                    Toast.makeText(DetailUserActivity.this, "Berhasil Menghapus Dari Data Favorit", Toast.LENGTH_LONG).show();
                }
            }));


        }


    }

    private boolean EXIST (String username){

        String change = UserDBContract.UserColumns.USERNAME + "=?";
        String[] changeArg = {username};
        String limit = "1";
        userHelper = new UserHelper(this);
        userHelper.open();
        userModel = getIntent().getParcelableExtra("DATA_USER");

        UserDBHelper userDBHelper = new UserDBHelper(getApplicationContext());
        SQLiteDatabase database = userDBHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = database.query(TABLE_USER_NAME, null, change, changeArg, null, null, null, limit);
        boolean exist = (cursor.getCount() > 0);
        cursor.close();
        return exist;
    }
}