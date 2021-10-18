package com.example.bookstore;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static String IP = "http://47.110.134.38:8080/SSM_Demo_war/";
    public static User MyUser;
    public static Book getABook(int bookid) throws IOException
    {
        OkHttpClient client  = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("uid",String.valueOf(bookid)).build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HttpUtil.IP+"/getabook")
                .build();

        Response response = client.newCall(request).execute();
        String s = response.body().string();
        System.out.println(s);
        Gson gson = new Gson();
        Book book = gson.fromJson(s,Book.class);

        String url1 =HttpUtil.IP+ "/static/image/" + String.valueOf(book.getBid()) + "-1.jpg";
        OkHttpClient client1  = new OkHttpClient();
        Request request1 = new Request.Builder()
                .url(url1)
                .build();
        Response response1 = client1.newCall(request1).execute();
        InputStream inputStream1 = response1.body().byteStream();//得到图片的流
        Bitmap bitmap1 = BitmapFactory.decodeStream(inputStream1);
        book.setPic1(bitmap1);
        String url2=HttpUtil.IP+ "/static/image/" + String.valueOf(book.getBid()) + "-2.jpg";
        OkHttpClient client2  = new OkHttpClient();
        Request request2 = new Request.Builder()
                .url(url2)
                .build();
        Response response2 = client2.newCall(request2).execute();
        InputStream inputStream2 = response2.body().byteStream();//得到图片的流
        Bitmap bitmap2 = BitmapFactory.decodeStream(inputStream2);
        book.setPic2(bitmap2);

        String url3=HttpUtil.IP+ "/static/image/" + String.valueOf(book.getBid()) + "-3.jpg";
        OkHttpClient client3 = new OkHttpClient();
        Request request3 = new Request.Builder()
                .url(url3)
                .build();
        Response response3 = client3.newCall(request3).execute();
        InputStream inputStream3 = response3.body().byteStream();//得到图片的流
        Bitmap bitmap3 = BitmapFactory.decodeStream(inputStream3);
        book.setPic3(bitmap3);
        return book;
    }
    public static User getUser(int uid) throws IOException {

        OkHttpClient client  = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("uid",String.valueOf(uid)).build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HttpUtil.IP+"/getuser")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        System.out.println(s);
        Gson gson = new Gson();
        User user = gson.fromJson(s,User.class);
        return user;
    }
    public static List<Remark> getRemark(int bid) throws IOException {
        OkHttpClient client  = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("bid",String.valueOf(bid)).build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HttpUtil.IP+"/getping")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        Gson gson = new Gson();
        List<Remark> list;
        list = gson.fromJson(s,new TypeToken<List<Remark>>(){}.getType());
        return list;
    }
    public static List<Bill> getBill(int uid) throws IOException
    {
        OkHttpClient client  = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("uid",String.valueOf(uid)).build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HttpUtil.IP+"/getding")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        Gson gson = new Gson();
        List<Bill> list;
        list = gson.fromJson(s,new TypeToken<List<Bill>>(){}.getType());
        return list;
    }

    public static List<Book> getBooks(SerchItem serchItem) throws Exception
    {
        List<Book> bookList = new LinkedList<>();
        OkHttpClient client  = new OkHttpClient();
        Gson gson1 = new Gson();
        String jsons= gson1.toJson(serchItem);
        RequestBody requestBody = new FormBody.Builder()
                .add("serchItem",jsons).build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HttpUtil.IP+"/getbook")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        Gson gson = new Gson();
        System.out.println(s);
        bookList.clear();
        bookList = gson.fromJson(s,new TypeToken<List<Book>>(){}.getType());
        return bookList;

    }
    public static void insertBill(Bill bill)
    {
        Gson gson = new Gson();
        String jsons = gson.toJson(bill);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("bill", jsons).build();
        Request request = new Request.Builder()
                .url(HttpUtil.IP + "/insertbill")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addxiao(int bid)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("bid",String.valueOf(bid)).build();
        Request request = new Request.Builder()
                .url(HttpUtil.IP + "/addxiao")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void jianqian(int uid,double money)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("uid",String.valueOf(uid))
                .add("money",String.valueOf(money))
                .build();
        Request request = new Request.Builder()
                .url(HttpUtil.IP + "/jianqian")
                .post(requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Book getOnlyBook(int bookid) throws IOException {
        OkHttpClient client  = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("uid" ,String.valueOf(bookid)).build();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(HttpUtil.IP+"/getabook")
                .build();
        Response response = client.newCall(request).execute();
        String s = response.body().string();
        Gson gson = new Gson();
        Book book = gson.fromJson(s,Book.class);
        return book;
    }
}
