package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dingdan extends AppCompatActivity {
    private List<Book> bookList;
    private List<Bill> billList;

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterDing adapterDing;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        mContext=this;
        bookList= new LinkedList<>();
        recyclerView = findViewById(R.id.recy_d);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getDing();
    }
    private void getDing()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    billList=HttpUtil.getBill(HttpUtil.MyUser.getUid());
                    showResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                getBooks();
            }
        });
    }
    private void getBooks()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    for(int i=0;i<billList.size();i++)
                    {
                       bookList.add(HttpUtil.getOnlyBook(billList.get(i).getBid()));
                    }
                    showResponse1();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showResponse1()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                adapterDing= new AdapterDing(billList,bookList);
                recyclerView.setAdapter(adapterDing);
            }
        });
    }

}