package com.faizurazadri.submission2githubuser_faizurazadri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission2githubuser_faizurazadri.R;
import com.faizurazadri.submission2githubuser_faizurazadri.model.FollowerModel;

import java.util.ArrayList;

public class AdapterFollowers extends RecyclerView.Adapter<AdapterFollowers.ViewHolderFollowers> {

    private Context context;
    private ArrayList<FollowerModel> dataFollower = new ArrayList<>();

    public AdapterFollowers(Context context, ArrayList<FollowerModel> dataFollower) {
        this.context = context;
        this.dataFollower = dataFollower;
    }

    @NonNull
    @Override
    public ViewHolderFollowers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_followers, parent, false);
        return new ViewHolderFollowers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFollowers holder, int position) {
        holder.username.setText(dataFollower.get(position).getMasuk());
        Glide.with(context)
                .load(dataFollower.get(position).getUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return dataFollower.size();
    }

    public class ViewHolderFollowers extends RecyclerView.ViewHolder {

        private TextView username;
        private ImageView avatar;

        public ViewHolderFollowers(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_followers);
            avatar = itemView.findViewById(R.id.avatar_follower);
        }
    }
}
