package com.example.bookstore;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<com.example.bookstore.AdapterProduct.ViewHolder> {
    private List<Book> bookList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView Image;
        TextView Name;
        TextView Value;
        TextView Writer;
        TextView Type;
        TextView Xiao;
        View productView;

        public ViewHolder(View view)
        {
            super(view);
            Image = view.findViewById(R.id.Book_Image);
            Name = view.findViewById(R.id.book_name);
            Value = view.findViewById(R.id.value);
            Writer = view.findViewById(R.id.writer);
            Type = view.findViewById(R.id.type);
            Xiao= view.findViewById(R.id.xiaoliang);
            productView=view;
        }
    }
    public AdapterProduct(List<Book> books)
    {
        bookList=books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.productView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(parent.getContext(),BookDetail.class);
                intent.putExtra("BookId",bookList.get(position).getBid());
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.Name.setText("书名:"+book.getBname());
        holder.Type.setText("类型:"+book.getBtype());
        holder.Writer.setText("作者:"+book.getBwriter());
        //holder.Image.setImageBitmap(book.getPic1());
        holder.Value.setText("¥"+String.valueOf(book.getBvalue()));
        holder.Xiao.setText("销量:"+String.valueOf(book.getXiao()));
        Glide.with(holder.productView).load(HttpUtil.IP+ "/static/image/" + String.valueOf(book.getBid()) + "-1.jpg").into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
