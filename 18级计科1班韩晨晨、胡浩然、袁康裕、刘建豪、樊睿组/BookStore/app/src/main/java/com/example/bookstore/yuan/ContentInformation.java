package com.example.bookstore.yuan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContentInformation extends AppCompatActivity {

    private TextView bookid;
    private TextView bookname;
    private TextView bookwriter;
    private TextView bookdetail;
    private TextView bookvalue;
    private TextView bookxiao;
    private TextView booktype;
    private Button addShopcar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_content_information);
//        //接收MainActivity传送过来的书的信息
//        Intent intent=getIntent();
//        //将书的信息显示出来
//        //bookid=(TextView)findViewById(R.id.book_id);
//        //bookid.setText(intent.getStringExtra("bookid"));
//        bookname=(TextView)findViewById(R.id.book_name);
//        bookname.setText("名称："+intent.getStringExtra("bookname"));
//        bookwriter=(TextView)findViewById(R.id.book_wirter);
//        bookwriter.setText("作者："+intent.getStringExtra("bookwriter"));
//        //bookdetail=(TextView)findViewById(R.id.book_detail);
//        //bookdetail.setText(intent.getStringExtra("bookdetail"));
//        bookvalue=(TextView)findViewById(R.id.book_value);
//        bookvalue.setText("¥"+intent.getStringExtra("bookvalue"));
//        bookxiao=(TextView)findViewById(R.id.book_xiao);
//        bookxiao.setText("销量："+intent.getStringExtra("bookxiao"));
//        booktype=(TextView)findViewById(R.id.book_type);
//        booktype.setText("类别："+intent.getStringExtra("booktype"));
//
//        addShopcar=(Button)findViewById(R.id.add_shopcars);
    }
}