package com.example.bookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPing extends RecyclerView.Adapter<AdapterPing.ViewHolder> {
    List<Remark> remarkList;
    List<User> userList;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView comment;
        View pingView;

        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.p_user);
            comment = view.findViewById(R.id.p_ping);
            pingView = view;
        }
    }

    public AdapterPing(List<Remark> remarks,List<User> users) {
        remarkList = remarks;
        userList=users;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ping_item, parent, false);
        final AdapterPing.ViewHolder holder = new AdapterPing.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPing.ViewHolder holder, int position) {
        Remark remark = remarkList.get(position);
        User user = userList.get(position);
        holder.comment.setText("评论详情:"+remark.getComment());
        holder.username.setText("用户名:"+user.getUname());
    }
    @Override
    public int getItemCount() {
        return remarkList.size();
    }



}
