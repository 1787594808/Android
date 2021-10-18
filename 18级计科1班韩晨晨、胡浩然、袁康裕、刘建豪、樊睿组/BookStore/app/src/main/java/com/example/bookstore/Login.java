package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.litepal.LitePal;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LitePal.getDatabase();
        username= findViewById(R.id.username);
        password= findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login.this,YanZheng.class);
//                startActivityForResult(intent,1);
                  sentLoginMessage(v);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }


    private void sentLoginMessage(View v)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    OkHttpClient client  = new OkHttpClient();
                    String uname= username.getText().toString();
                    String upass= password.getText().toString();
                    RequestBody requestBody = new FormBody.Builder().add("username",uname).add("password",upass).build();
                    Request request = new Request.Builder()
                            .url(HttpUtil.IP+"/login")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData,v);
                }
                catch (Exception e)
                {
                    System.out.println("error");
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private  void showResponse(final String response,View v)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(response.equals("")==false)
                {
                    System.out.println(response);
                    Gson gson = new Gson();
                    HttpUtil.MyUser = gson.fromJson(response,User.class);
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    //HttpUtil.MyUser=
                    Toast.makeText(v.getContext(),"登录成功",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(v.getContext(), "密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}