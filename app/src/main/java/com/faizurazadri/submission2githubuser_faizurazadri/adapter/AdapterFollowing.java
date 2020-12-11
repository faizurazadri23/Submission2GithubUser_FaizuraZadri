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
import com.faizurazadri.submission2githubuser_faizurazadri.model.FollowingModel;

import java.util.ArrayList;

public class AdapterFollowing extends RecyclerView.Adapter<AdapterFollowing.ViewHolderFollowing> {

    private Context context;
    private ArrayList<FollowingModel> dataFollowing = new ArrayList<>();

    public AdapterFollowing(Context context, ArrayList<FollowingModel> dataFollowing) {
        this.context = context;
        this.dataFollowing = dataFollowing;
    }

    @NonNull
    @Override
    public ViewHolderFollowing onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_following, parent, false);
        return new ViewHolderFollowing(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFollowing holder, int position) {
        holder.username.setText(dataFollowing.get(position).getMasuk());
        Glide.with(context)
                .load(dataFollowing.get(position).getUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return dataFollowing.size();
    }

    public class ViewHolderFollowing extends RecyclerView.ViewHolder {
        private TextView username;
        private ImageView avatar;

        public ViewHolderFollowing(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_following);
            avatar = itemView.findViewById(R.id.avatar_following);
        }
    }
}
