package com.faizurazadri.submission2githubuser_faizurazadri.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faizurazadri.submission2githubuser_faizurazadri.DetailUserActivity;
import com.faizurazadri.submission2githubuser_faizurazadri.R;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterFollowers;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterFollowing;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterUsers;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FollowingModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.retrofit.ApiClient;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingFragment extends Fragment {
    
    RecyclerView recyclerViewFollowing;
    UserModel userModel;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowingFragment() {

    }

    public static FollowingFragment newInstance(String param1, String param2) {
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_following, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewFollowing =view.findViewById(R.id.rv_following);
        recyclerViewFollowing.setLayoutManager(new LinearLayoutManager(view.getContext()));
        UserModel userModel = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra("DATA_USER");

        Call<List<FollowingModel>> listCall = ApiClient.getApiService().getDataFollowingUser(Objects.requireNonNull(userModel).getMasuk());
        listCall.enqueue(new Callback<List<FollowingModel>>() {
            @Override
            public void onResponse(Call<List<FollowingModel>> call, Response<List<FollowingModel>> response) {
                ArrayList<FollowingModel> followingModels = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        followingModels.addAll(response.body());
                        recyclerViewFollowing.setAdapter(new AdapterFollowing(getContext(), followingModels));
                    }
                }else {
                    Toast.makeText(getContext(), "Permintaan Tidak Berhasil", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowingModel>> call, Throwable t) {
                Toast.makeText(getContext(), "Permintaan Gagal " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}