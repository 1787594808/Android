package cn.net.sdkd.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StudentSerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_ser);

        Intent intent = getIntent();
        Student stu = (Student)intent.getSerializableExtra("stu");

        //动态添加TextView控件
        Toast.makeText(StudentSerActivity.this, stu.getName()+","+stu.getAge(),Toast.LENGTH_LONG).show();
        /*LinearLayout linearLayout = (LinearLayout)findViewById(R.id.student_ser_activity_linear);
        TextView tv = new TextView(this);
        tv.setText(stu.getName()+","+stu.getAge());//显示接收的对象的内容
        linearLayout.addView(tv);*/
    }
}