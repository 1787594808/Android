package cn.net.sdkd.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent dataIntent = getIntent();
        String data = dataIntent.getStringExtra("sendData");
        Toast.makeText(SecondActivity.this, data, Toast.LENGTH_LONG).show();
    }
}