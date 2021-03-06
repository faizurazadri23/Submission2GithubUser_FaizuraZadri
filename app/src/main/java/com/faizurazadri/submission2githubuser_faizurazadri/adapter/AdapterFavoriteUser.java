package com.faizurazadri.submission2githubuser_faizurazadri.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class AdapterFavoriteUser extends RecyclerView.Adapter<AdapterFavoriteUser.ViewHolderAdapterFavorite> {


    List<UserModel> userModels;
    Context context;

    public AdapterFavoriteUser(Context context) {
        this.context = context;
    }

    public void setUserModels(ArrayList<UserModel> detailUserModelArrayList){
        this.userModels = detailUserModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolderAdapterFavorite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolderAdapterFavorite(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapterFavorite holder, int position) {
        holder.username.setText(userModels.get(position).getMasuk());
        Glide.with(context)
                .load(userModels.get(position).getUrl())
                .into(holder.avatar);

        holder.itemView.setOnClickListener(v -> {
            UserModel detailUserModel = userModels.get(position);
            Intent intent =new Intent(context, DetailUserActivity.class);
            intent.putExtra("DATA_USER", detailUserModel);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class ViewHolderAdapterFavorite extends RecyclerView.ViewHolder {
        private TextView username;
        private ImageView avatar;

        public ViewHolderAdapterFavorite(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_favorite);
            avatar = itemView.findViewById(R.id.avatar_favorite);

        }
    }
}
