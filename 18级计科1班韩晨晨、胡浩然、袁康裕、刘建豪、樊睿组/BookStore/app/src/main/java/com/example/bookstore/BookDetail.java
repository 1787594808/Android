package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstore.scoll.AutoScrollViewPager;
import com.example.bookstore.scoll.BannerIndicator;
import com.example.bookstore.scoll.ViewPagerAdapter;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BookDetail extends AppCompatActivity {
    private AutoScrollViewPager autoScrollViewPager;
    private List<Drawable> drawableList = new ArrayList<>();
    private BannerIndicator bannerIndicator;
    ViewPagerAdapter pagerAdapter;
    TextView bookname;
    TextView bookvalue;
    TextView bookdetail;
    TextView bookwriter;
    TextView booktype;
    TextView bookxiao;
    Context mContext;
    Button ping;
    Button kan;
    Button jie;
    Button gou;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Intent intent = getIntent();
        mContext = this;

        int bookid = intent.getIntExtra("BookId", 1);

        bookname = findViewById(R.id.book_name);
        bookvalue = findViewById(R.id.book_value);
        booktype = findViewById(R.id.book_type);
        bookdetail = findViewById(R.id.tit_desc_xiangqing);
        bookxiao = findViewById(R.id.book_xiao);
        bookwriter = findViewById(R.id.book_wirter);
        autoScrollViewPager = findViewById(R.id.book_image);
        bannerIndicator = findViewById(R.id.indicator);
        ping = findViewById(R.id.pinglun);
        kan = findViewById(R.id.kanping);
        jie = findViewById(R.id.jiesuan);
        gou = findViewById(R.id.add_shopcars);

        getBookMessage(bookid);

        ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });

        kan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mContext, KanPing.class);
                intent1.putExtra("bid", book.getBid());
                startActivity(intent1);
            }
        });

        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gou gou = new Gou(bookid, HttpUtil.MyUser.getUid());
                gou.save();
                Toast.makeText(v.getContext(), "加入成功", Toast.LENGTH_SHORT).show();
            }
        });

        jie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
    }

    private void getBookMessage(int bookid) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    book = HttpUtil.getABook(bookid);
                    showResponse();
                } catch (Exception e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bookname.setText("书名:" + book.getBname());
                bookdetail.setText(book.getBdetail());
                booktype.setText("类别:" + book.getBtype());
                bookvalue.setText("¥" + String.valueOf(book.getBvalue()));
                bookwriter.setText("作者:" + book.getBwriter());
                bookxiao.setText("销量:" + String.valueOf(book.getXiao()));

                drawableList.add(new BitmapDrawable(book.getPic1()));
                drawableList.add(new BitmapDrawable(book.getPic2()));
                drawableList.add(new BitmapDrawable(book.getPic3()));
                pagerAdapter = new ViewPagerAdapter(drawableList, mContext);
                autoScrollViewPager.setAdapter(pagerAdapter);
                bannerIndicator.setUpWidthViewPager(autoScrollViewPager);
                autoScrollViewPager.startAutoPlay();
            }
        });
    }

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(mContext);
        final View dialogView = LayoutInflater.from(mContext)
                .inflate(R.layout.dia_ping, null);
        customizeDialog.setTitle("评论");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("发布",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MaterialEditText pp = dialogView.findViewById(R.id.d_ping);
                        postPing(pp.getText().toString());
                    }
                });
        customizeDialog.show();
    }

    private void postPing(String ppp) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Remark remark = new Remark(1, HttpUtil.MyUser.getUid(), book.getBid(), ppp);
                    Gson gson1 = new Gson();
                    String jsons = gson1.toJson(remark);
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("remark", String.valueOf(jsons)).build();
                    Request request = new Request.Builder()
                            .post(requestBody)
                            .url(HttpUtil.IP + "/postremark")
                            .build();
                    Response response = client.newCall(request).execute();
                } catch (Exception e) {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showNormalDialog() {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setTitle("是否支付");
        normalDialog.setMessage("你确定要支付吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Bill order = new Bill();
                        order.setBid(book.getBid());
                        order.setUid(HttpUtil.MyUser.getUid());
                        Date date = new Date();
                        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                        String nowTime = sdf.format(date);
                        order.setTime(nowTime);
                        insertOrder(order);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        normalDialog.show();
    }

    private void insertOrder(Bill order) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.insertBill(order);
                HttpUtil.addxiao(book.getBid());
                HttpUtil.jianqian(HttpUtil.MyUser.getUid(),book.getBvalue());
            }
        }).start();
    }
}