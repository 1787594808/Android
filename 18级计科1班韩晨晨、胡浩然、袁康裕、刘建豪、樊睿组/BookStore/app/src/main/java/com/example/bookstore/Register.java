package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity {

    EditText username;
    EditText password;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.rusername);
        password = findViewById(R.id.rpassword);
        register = findViewById(R.id.rregister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zhuche(v);
            }
        });
    }
    private void zhuche(View v)
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
                            .url(HttpUtil.IP+"/register")
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
                if(response.equals("1"))
                {
                    finish();
                    Toast.makeText(v.getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(v.getContext(), "该账号已注册", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}