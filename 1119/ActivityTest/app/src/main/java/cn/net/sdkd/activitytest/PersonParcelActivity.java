package cn.net.sdkd.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PersonParcelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_parcel);

        Intent intent = getIntent();
        Person person = intent.getParcelableExtra("data");
        Toast.makeText(PersonParcelActivity.this, person.getName()+","+person.getAge(), Toast.LENGTH_LONG).show();

        /*TextView tv = (TextView)findViewById(R.id.person_activity_textview);
        tv.setText(person.getName()+","+person.getAge());*/
    }
}