package com.example.bookstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {

    View view;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    TextView username;
    Button qian;
    Button ding;
    TextView money;

    public MyFragment() {
    }

    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        username = view.findViewById(R.id.m_username);
        qian = view.findViewById(R.id.introduction);
        ding = view.findViewById(R.id.kanding);
        money = view.findViewById(R.id.money);

        username.setText(HttpUtil.MyUser.getUname());

        qian.setText("个性签名:"+HttpUtil.MyUser.getQian());
        double v = new BigDecimal(HttpUtil.MyUser.getUmoney()).setScale(2, RoundingMode.DOWN).doubleValue();
        money.setText(String.valueOf(v));

        qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });
        ding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),Dingdan.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(view.getContext());
        final View dialogView = LayoutInflater.from(view.getContext())
                .inflate(R.layout.dia_qian, null);
        customizeDialog.setTitle("修改签名");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MaterialEditText qian = dialogView.findViewById(R.id.g_qian);
                        updateQian(qian.getText().toString());
                    }
                });
        customizeDialog.show();
    }

    private void updateQian(String qian) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    String url = HttpUtil.IP + "/updateqian";
                    RequestBody requestBody = new FormBody.Builder()
                            .add("uid", String.valueOf(HttpUtil.MyUser.getUid()))
                            .add("qian", qian).build();
                    Request request = new Request.Builder()
                            .post(requestBody)
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    String s=response.body().string();
                    showResponse(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private  void showResponse(String response)
    {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                HttpUtil.MyUser = gson.fromJson(response,User.class);
                qian.setText("个性签名:"+HttpUtil.MyUser.getQian());
            }
        });
    }
}