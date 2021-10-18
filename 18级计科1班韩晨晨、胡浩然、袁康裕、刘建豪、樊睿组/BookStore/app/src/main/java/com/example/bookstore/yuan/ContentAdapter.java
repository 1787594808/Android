package com.example.bookstore.yuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookstore.HttpUtil;
import com.example.bookstore.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContentAdapter extends ArrayAdapter<Content> {

private int resourseId;

public ContentAdapter(@NonNull Context context, int resource, List<Content> objects) {
        super(context, resource,objects);
        resourseId=resource;
        }

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    Content content=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourseId,parent,false);
        TextView contentname=(TextView)view.findViewById(R.id.content_bookname);

        contentname.setText(content.getBook().getBname());
        ImageView imageView = view.findViewById(R.id.content_image);
        Glide.with(view.getContext()).load(HttpUtil.IP+ "/static/image/" + String.valueOf(content.getBook().getBid()) + "-1.jpg").into(imageView);

        return view;
        }


}
