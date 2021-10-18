package com.example.bookstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDing extends RecyclerView.Adapter<AdapterDing.ViewHolder>{

    List<Bill> billList;
    List<Book> bookList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookname;
        TextView bookvalue;
        TextView time;
        View dingView;

        public ViewHolder(View view) {
            super(view);
            bookname  = view.findViewById(R.id.d_bookname);
            bookvalue = view.findViewById(R.id.d_bookvalue);
            time      = view.findViewById(R.id.d_time);
            dingView = view;
        }
    }

    public AdapterDing(List<Bill> billList, List<Book> bookList) {
        this.billList = billList;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public AdapterDing.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false);
        final AdapterDing.ViewHolder holder = new AdapterDing.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDing.ViewHolder holder, int position) {
        Bill bill = billList.get(position);
        Book book = bookList.get(position);
        holder.bookname.setText(book.getBname());
        holder.bookvalue.setText("¥"+String.valueOf(book.getBvalue()));
        holder.time.setText(bill.getTime());
    }
    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
