package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.bookstore.yuan.Classify;
import com.example.bookstore.yuan.ClassifyAdapter;
import com.example.bookstore.yuan.Content;
import com.example.bookstore.yuan.ContentAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FragmentFenlei extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private List<Book> bookList;
    private List<Classify> classifyList;
    private ListView Tiltelistview;//左侧listview
    private ListView Contentlistview;//右侧listview;
    View view;

    public FragmentFenlei() {

    }


    public static FragmentFenlei newInstance(String param1, String param2) {
        FragmentFenlei fragment = new FragmentFenlei();
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
        view= inflater.inflate(R.layout.fragment_fenlei, container, false);
        classifyList=new ArrayList<>();
        Tiltelistview =view.findViewById(R.id.classify_list_view);
        Contentlistview=(ListView)view.findViewById(R.id.content_list_view);
        getbooks();
        return view;
    }
    private void getbooks()
    {
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     SerchItem serchItem = new SerchItem();
                     serchItem.Init();
                     bookList=HttpUtil.getBooks(serchItem);
                     initClassify();
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }).start();
    }
    private void initClassify() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                Set<String> strings= new HashSet<>();
                for(int i=0;i<bookList.size();i++)
                    strings.add(bookList.get(i).getBtype());
                for(String string:strings)
                {
                    List<Content>contentList=new ArrayList<>();
                    for(int i=0;i<bookList.size();i++)
                    {
                        if(bookList.get(i).getBtype().equals(string))
                            contentList.add(new Content(string,bookList.get(i)));
                    }
                    classifyList.add(new Classify(string,contentList));
                }
                ClassifyAdapter classifyAdapter=new ClassifyAdapter(view.getContext(),R.layout.classify_item,classifyList);
                Tiltelistview.setAdapter(classifyAdapter);
                Tiltelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position1, long id) {
                        Classify classify=classifyList.get(position1);//获取classify的位置
                        ContentAdapter contentAdapter = new ContentAdapter(view.getContext(),R.layout.content_item,classify.getContent());
                        Contentlistview.setAdapter(contentAdapter);
                        Contentlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Content content=classify.getContent().get(position);//获取content的位置
                                Intent intent=new Intent(view.getContext(),BookDetail.class);
                                intent.putExtra("BookId",content.getBook().getBid());
                                startActivity(intent);
                            }
                        });
                    }
                });

            }
        });
    }
}