package com.faizurazadri.submission2githubuser_faizurazadri.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.faizurazadri.submission2githubuser_faizurazadri.DetailUserActivity;
import com.faizurazadri.submission2githubuser_faizurazadri.R;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterFollowers;
import com.faizurazadri.submission2githubuser_faizurazadri.adapter.AdapterUsers;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FollowerModel;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;
import com.faizurazadri.submission2githubuser_faizurazadri.retrofit.ApiClient;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowerFragment extends Fragment {

    RecyclerView recyclerViewFollower;
    UserModel datauser;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowerFragment newInstance(String param1, String param2) {
        FollowerFragment fragment = new FollowerFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DetailUserActivity detailUserActivity = (DetailUserActivity) getActivity();
        Bundle bundle = detailUserActivity.getIntent().getBundleExtra(AdapterUsers.EXTRA_DATA);
        datauser = Parcels.unwrap(bundle.getParcelable(AdapterUsers.USER_DATA));

        recyclerViewFollower = view.findViewById(R.id.rv_follower);
        recyclerViewFollower.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Call<List<FollowerModel>> request = ApiClient.getApiService().getDataFollowerUser(datauser.getMasuk());
        request.enqueue(new Callback<List<FollowerModel>>() {
            @Override
            public void onResponse(Call<List<FollowerModel>> call, Response<List<FollowerModel>> response) {
                ArrayList<FollowerModel> followerModelArrayList = new ArrayList<>();
                if (response.isSuccessful()){
                    if (response.body() != null){
                        followerModelArrayList.addAll(response.body());
                        Log.d("TAG RESULT", "onResponse: " + followerModelArrayList.size());
                        recyclerViewFollower.setAdapter(new AdapterFollowers(getContext(), followerModelArrayList));
                    }
                }else{
                    Toast.makeText(getContext(), "Permintaan Tidak Berhasil Dilakukan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<FollowerModel>> call, Throwable t) {
                Toast.makeText(getContext(),"Permintaan Gagal", Toast.LENGTH_LONG).show();
            }
        });

    }
}