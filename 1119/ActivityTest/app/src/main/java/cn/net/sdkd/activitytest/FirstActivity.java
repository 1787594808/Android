package cn.net.sdkd.activitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button button1 = this.findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                /** Basic key event handler*/
//                Toast.makeText(FirstActivity.this, "You clicked Button 1", Toast.LENGTH_LONG).show();

                /** Explicit Intent: start a new Activity */
                /*Intent explicitIntent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(explicitIntent);*/

                /** Implicit Intent: specify the Action and Category in the xml of an Activity */
                /*Intent implicitIntent = new Intent("Action_start");
                implicitIntent.addCategory("android.intent.action.MY_Category");
                startActivity(implicitIntent);*/

                /** Implicit Intent to open web page*/
                /*Intent webpageIntent = new Intent(Intent.ACTION_VIEW);
                webpageIntent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(webpageIntent);*/

                /** Implicit Intent to open self-defined activity*/
                /*Intent selfDefinedActivityIntent = new Intent(Intent.ACTION_VIEW);
                selfDefinedActivityIntent.setData(Uri.parse("https://www.baidu.com"));
                startActivity(selfDefinedActivityIntent);*/

                /** Implicit Intent to dial*/
                /*Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:10086"));
                startActivity(dialIntent);*/

                /** ????????????Activity???????????? */
                /*Intent sendDataIntent = new Intent(FirstActivity.this, SecondActivity.class);
                sendDataIntent.putExtra("sendData", "Hello, second activiy, I am the first activity.");
                startActivity(sendDataIntent);*/

                /** ????????????Activity?????????????????????????????????????????????????????????Activity */
                Intent passAndReturnDataIntent = new Intent(FirstActivity.this, Second2Activity.class);
                passAndReturnDataIntent.putExtra("sendData", "Hello, second Activity, I am the First Activity");
                startActivityForResult(passAndReturnDataIntent, 1);
                /**  ??????????????? */
            }
        });

        Button button2 = this.findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, StudentSerActivity.class);
                intent.putExtra("stu", new Student("Tom", 25));
                startActivity(intent);

            }
        });

        Button button3 = this.findViewById(R.id.button_3);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, PersonParcelActivity.class);
                intent.putExtra("data", new Person("Peter", 34));
                startActivity(intent);
            }
        });
    }

    @Override
    //????????????
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;//show the menu
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this, "You clicked Add", Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }

    @Override
    /**
     * ????????????
     * ??????data?????????????????????????????????Intent???????????????????????????data???*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {//FirstActivity??????Second2Activity??????????????????????????????????????????????????????????????????????????????
            case 1:
                if (resultCode == RESULT_OK) {//??????Second2Activity???????????????
                    String returnData = data.getStringExtra("returnData");
                    Toast.makeText(FirstActivity.this, returnData, Toast.LENGTH_LONG).show();
                }
                break;
            default:
        }
    }
}