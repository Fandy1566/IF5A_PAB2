package com.if5a.pab2.contact.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if5a.pab2.contact.R;
import com.if5a.pab2.contact.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserViewAdapter extends RecyclerView.Adapter<UserViewAdapter.ViewHolder> {
    private List<User> data = new ArrayList<>();
    private onClickListener listener;

    public void setData(List<User> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnClickListener(onClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewAdapter.ViewHolder holder, int position) {
        User user = data.get(position); // bisa juga tanpa menggunakan user ini seperti data.get(position).getblabla()
        holder.tvName.setText("Name : " + user.getName());
        holder.tvEmail.setText("Name : " + user.getEmail());
        holder.tvPhone.setText("Name : " + user.getPhone_number());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onEditClicked(user);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null)
                    listener.onDeleteClicked(user.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvEmail, tvPhone;
        private ImageView ivEdit,ivDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            ivEdit = itemView.findViewById(R.id.iv_edit);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }

    public interface onClickListener {
        void onEditClicked(User user);
        void onDeleteClicked(int userId);
    }
}
