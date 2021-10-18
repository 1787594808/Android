package cn.net.sdkd.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Second2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second2_layout);
        //1.先获得FirstActivity发送来的data
        Intent receiveIntent = getIntent();
        String receiveData = receiveIntent.getStringExtra("sendData");
        Toast.makeText(Second2Activity.this, receiveData, Toast.LENGTH_LONG).show();

        Button button2 = findViewById(R.id.button2_3_5);
        button2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //返回data给FirstActivity
                Intent returnIntent = new Intent();//用于返回数据的Intent，不需要指定Intent的目标Activity
                returnIntent.putExtra("returnData", "Hello, First Activity, I am the second activity");
                setResult(RESULT_OK, returnIntent);//返回：1）处理结果；2）intent及其携带的数据
                finish();//销毁当前的Activity（Second2Activity）
            }
        });

    }

}