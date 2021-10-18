package com.example.bookstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookstore.Util.BookSortForJia;
import com.example.bookstore.Util.BookSortForJiaF;
import com.example.bookstore.Util.BookSortForXiao;
import com.example.bookstore.Util.BookSortForXiaoF;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LookFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LookFragment extends Fragment {

    private List<Book> bookList = new ArrayList<>();
    private View view;
    private String s;
    private Button sousuo;
    private Button sai;
    private Button sxiao;
    private Button sjia;
    private EditText suosouText;
    private SerchItem serchItem;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterProduct adapterProduct;
    boolean isxiao=false;
    boolean isjia=false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LookFragment() {
    }


    public static LookFragment newInstance(String param1, String param2) {
        LookFragment fragment = new LookFragment();
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
        view = inflater.inflate(R.layout.fragment_look, container, false);
        sousuo = view.findViewById(R.id.sousuo);
        sai = view.findViewById(R.id.sai);
        sxiao = view.findViewById(R.id.xiao);
        sjia = view.findViewById(R.id.jia);
        serchItem = new SerchItem();
        serchItem.Init();
        suosouText = view.findViewById(R.id.sousuotext);
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serchItem.Init();
                serchItem.setBookName(suosouText.getText().toString());
                System.out.println(serchItem.getBookName());
                initBook(view);
            }
        });
        sai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });
        sxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isxiao)
                {
                    Collections.sort(bookList, new BookSortForXiao());
                    isxiao=false;
                }
                else
                {
                    Collections.sort(bookList, new BookSortForXiaoF());
                    isxiao=true;
                }
                adapterProduct.notifyDataSetChanged();
            }
        });
        sjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isjia)
                {
                    Collections.sort(bookList, new BookSortForJia());
                    isjia=false;
                }
                else
                {
                    Collections.sort(bookList, new BookSortForJiaF());
                    isjia=true;
                }
                adapterProduct.notifyDataSetChanged();
            }
        });
        initBook(view);
        return view;
    }
    void initBook(View view)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    bookList= HttpUtil.getBooks(serchItem);
                    showResponse(s,view);
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerView = v.findViewById(R.id.recylerview_f);
                layoutManager = new LinearLayoutManager(v.getContext());
                recyclerView.setLayoutManager(layoutManager);
                adapterProduct = new AdapterProduct(bookList);
                recyclerView.setAdapter(adapterProduct);
                adapterProduct.notifyDataSetChanged();
            }
        });
    }

    private void showCustomizeDialog() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(view.getContext());
        final View dialogView = LayoutInflater.from(view.getContext())
                .inflate(R.layout.dia_sai,null);
        customizeDialog.setTitle("筛选");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MaterialEditText writer = dialogView.findViewById(R.id.writer1);
                        MaterialEditText type = dialogView.findViewById(R.id.type1);
                        MaterialEditText min = dialogView.findViewById(R.id.min1);
                        MaterialEditText max = dialogView.findViewById(R.id.max1);
                        serchItem.setWriter(writer.getText().toString());
                        serchItem.setType(type.getText().toString());
                        if(min.getText().toString().equals("")==false)
                        {
                            serchItem.setMinValue(Integer.parseInt(min.getText().toString()));
                        }
                        if(max.getText().toString().equals("")==false)
                        {
                            serchItem.setMaxValue(Integer.parseInt(max.getText().toString()));
                        }
                        initBook(view);
                    }
                });
        customizeDialog.show();
    }
}