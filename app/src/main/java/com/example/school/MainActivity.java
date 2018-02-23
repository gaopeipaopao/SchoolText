package com.example.school;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.school.model.LocalCookieJar;
import com.example.school.model.SetOkHttp;

import java.io.IOException;
import java.util.List;

import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText textNames;
    private EditText  passWords;
    private String textName="";
    private String passWord="";
    private String code = "";
    private Button enter;
    private EditText id_code;
    private TextView textView;
    private String cookieStr;
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().cookieJar(new LocalCookieJar()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        textNames=(EditText)findViewById(R.id.textName);
        passWords=(EditText)findViewById(R.id.passWord);
        textView = (TextView) findViewById(R.id.text);
        enter = (Button) findViewById(R.id.enter_a);
        id_code = (EditText) findViewById(R.id.id_code);
        imageView = (ImageView) findViewById(R.id.imageCode);
        Log.d("aaaaaaa", "aaaaaaaa");
        SetOkHttp.setHttpGet(okHttpClient, "http://222.24.62.120/CheckCode.aspx", new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_LONG);
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.d("res1",response.toString());
                final byte[] byte_image = response.body().bytes();//当OkHttp成功获取验证码后，会返回的byte[]数据
                Headers headers = response.headers();
                Log.d("info_headers", "header " + headers);
                List<String> cookies = headers.values("Set-Cookie");
                String session = cookies.get(0);
                Log.d("info_cookies", "onResponse-size: " + cookies);
                cookieStr = session.substring(0, session.indexOf(";"));
                Log.i("info_s", "session is  :" + cookieStr);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //加载网络成功进行UI的更新,处理得到的图片资源
                        //通过message，拿到字节数组
                        // byte[] Picture = (byte[]) msg.obj;
                        //使用BitmapFactory工厂，把字节数组转化为bitmap
                        Bitmap bitmap = BitmapFactory.decodeByteArray(byte_image, 0, byte_image.length);
                        //通过imageview，设置图片
                        imageView.setImageBitmap(bitmap);//ImageView 显示验证码的组件
                    }
                });
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textName=textNames.getText().toString();
                Log.d("Nmae",textName);
                passWord=passWords.getText().toString();
                Log.d("word",passWord);
                code = id_code.getText().toString();
                Log.d("code", code);
                send();
                // send2();
            }
        });
    }

    private void send() {
        SetOkHttp.setHttpPost(okHttpClient,"http://222.24.62.120/default2.aspx", textName, passWord, cookieStr, code,
                new okhttp3.Callback() {

                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        Log.d("eeeeee", e.toString());
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, Response response) throws IOException {
                        Log.d("responsePass", response.body().string());
                    }

                });
    }
}