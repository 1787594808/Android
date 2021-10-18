package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KanPing extends AppCompatActivity {
    private List<Remark> remarkList= new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterPing adapterPing;
    int bid;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_ping);
        Intent intent =getIntent();
        bid = intent.getIntExtra("bid",1);
        mContext=this;
        recyclerView = findViewById(R.id.recy_ping);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getPing();
    }
    void getPing()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    remarkList=HttpUtil.getRemark(bid);
                    System.out.println("list++++"+remarkList.size());
                    showResponse();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private  void showResponse()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
               for(int i=0;i<remarkList.size();i++)
                  userList.add(new User());
               getUerName();
            }
        });
    }
    void getUerName()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i=0;i<remarkList.size();i++)
                        userList.get(i).setUname(HttpUtil.getUser(remarkList.get(i).getUid()).getUname());
                    showResponse1();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private  void showResponse1()
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                System.out.println(remarkList.size()+"------"+userList.size());
                adapterPing= new AdapterPing(remarkList,userList);
                recyclerView.setAdapter(adapterPing);
            }
        });
    }

}