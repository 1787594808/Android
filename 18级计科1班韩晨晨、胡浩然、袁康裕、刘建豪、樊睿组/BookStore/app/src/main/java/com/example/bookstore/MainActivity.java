package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);
    }
    private class MainAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new LookFragment());
            fragments.add(new FragmentFenlei());
            fragments.add(new FragmentCart());
            fragments.add(new MyFragment());
        }
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }

//    private void sentLoginMessage(View v)
//    {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try
//                {
//                    OkHttpClient client  = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url(HttpUtil.IP+"/TEST")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    showResponse(responseData,v);
//                }
//                catch (Exception e)
//                {
//                    System.out.println("error");
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//    private  void showResponse(final String response,View v)
//    {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(response);
//            }
//        });
//    }
}