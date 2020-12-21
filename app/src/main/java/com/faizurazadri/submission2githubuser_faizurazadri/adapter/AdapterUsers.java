package com.faizurazadri.submission2githubuser_faizurazadri.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.faizurazadri.submission2githubuser_faizurazadri.DetailUserActivity;
import com.faizurazadri.submission2githubuser_faizurazadri.R;
import com.faizurazadri.submission2githubuser_faizurazadri.model.UserModel;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.ViewHolderUser> {

    public static final String USER_DATA  = "datauser";
    public static final String EXTRA_DATA = "dataextra";
    private Context context;
    private List<UserModel> userModels = new ArrayList<>();

    public AdapterUsers(Context context, List<UserModel> userModels) {
        this.context = context;
        this.userModels = userModels;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_users, parent, false);
        return new ViewHolderUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, int position) {
        holder.username.setText(userModels.get(position).getMasuk());
        Glide.with(context)
                .load(userModels.get(position).getUrl())
                .into(holder.avatar);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel1 = userModels.get(position);
                Intent moveDetail= new Intent(context, DetailUserActivity.class);
                moveDetail.putExtra("DATA_USER", userModel1);
                v.getContext().startActivity(moveDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public static class ViewHolderUser extends RecyclerView.ViewHolder {
        private TextView username;
        private ImageView avatar;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            avatar = itemView.findViewById(R.id.avataruser);
        }
    }
}
