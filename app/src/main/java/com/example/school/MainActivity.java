package com.example.school;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.RippleDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.school.model.LocalCookieJar;
import com.example.school.model.SetOkHttp;
import com.example.school.view.Main2Activity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    private CheckBox  eye_enter;
    private CheckBox  rember_Password;
    private String textName="04161134";
    private String passWord="970716zuiaibl";
    private String code = "";
    private Button enter;
    private EditText id_code;
    private TextView textView;
    private String cookieStr;
    private String enter_url;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Boolean ok=true;
    private String responses;
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().cookieJar(new LocalCookieJar()).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        sharedPreferences= getSharedPreferences("name",MainActivity.MODE_APPEND);
        eye_enter=(CheckBox)findViewById(R.id.eye_enter);
        rember_Password=(CheckBox)findViewById(R.id.rember_passWord);
        textNames=(EditText)findViewById(R.id.textName);
        passWords=(EditText)findViewById(R.id.passWord);
        textView = (TextView) findViewById(R.id.text);
        enter = (Button) findViewById(R.id.enter_a);
        id_code = (EditText) findViewById(R.id.id_code);
        imageView = (ImageView) findViewById(R.id.imageCode);
        final Boolean rember=sharedPreferences.getBoolean("记住密码",false);
        Log.d("aaaaaaa", "aaaaaaaa");
        if(rember){
            String name=sharedPreferences.getString("学号","");
            String password=sharedPreferences.getString("密码","");
            rember_Password.setChecked(true);
            textNames.setText(name);
            passWords.setText(password);
        }
        eye_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eye_enter.isChecked()){
                    passWords.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eye_enter.setButtonDrawable(R.drawable.eye_open);
                }else {
                    passWords.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eye_enter.setButtonDrawable(R.drawable.eye_close);
                }
            }
        });
        sendHttp();

        /**
         * 测试使用
         */
        textNames.setText(textName);
        passWords.setText(passWord);

        enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    textName=textNames.getText().toString();
//                    Log.d("Nmae",textName);
//                    passWord=passWords.getText().toString();
//                    Log.d("word",passWord);
                    code = id_code.getText().toString();
                    Log.d("code", code);
                    send();
                }
            });
    }

    private void sendHttp(){
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
                enter_url=response.request().url().toString();
                cookieStr = session.substring(0, session.indexOf(";"));
                editor=sharedPreferences.edit();
                editor.putString("Enter_url",enter_url);
                editor.putString("Cookie",cookieStr);
                editor.apply();
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
    }

    private void sendHttp2(){
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
//                Headers headers = response.headers();
//                Log.d("info_headers", "header " + headers);
//                List<String> cookies = headers.values("Set-Cookie");
//                String session = cookies.get(0);
//                Log.d("info_cookies", "onResponse-size: " + cookies);
//                cookieStr = session.substring(0, session.indexOf(";"));
//                Log.i("info_s", "session is  :" + cookieStr);
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
                        responses=response.body().string();
                        Log.d("respon",responses);
                        Document doc= Jsoup.parse(responses);
                        Elements links=doc.select("a[href]");
                        Element nameElement = doc.getElementById("xhxm");
                        Elements alerts=doc.select("script[language]");

                        for(Element alert:alerts){
                            if(alert.data().contains("用户名不存在或未按照要求参加教学活动")){
                               Log.d("用户名","用户名");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"用户名不存在或未按照要求参加教学活动",Toast.LENGTH_LONG).show();
                                        textNames.setText("");
                                        passWords.setText("");
                                        id_code.setText("");
                                    }
                                });
                                sendHttp2();
                            }
                            else if(alert.data().contains("密码错误")){
                                Log.d("密码错误","密码错误");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                                        passWords.setText("");
                                        textNames.setText(textName);
                                        id_code.setText("");
                                    }
                                });
                                sendHttp2();
                            }
                            else if(alert.data().contains("验证码不正确")){
                                Log.d("验证码不正确","验证码不正确");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"验证码不正确",Toast.LENGTH_LONG).show();
                                        textNames.setText(textName);
                                        passWords.setText(passWord);
                                        id_code.setText("");
                                    }
                                });
                                sendHttp2();
                            }
                            else if(alert.data().contains("用户名不能为空")){
                                Log.d("用户名不能为空","用户名不能为空");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"用户名不能为空",Toast.LENGTH_LONG).show();
                                        textNames.setText("");
                                        passWords.setText("");
                                        id_code.setText("");
                                    }
                                });
                                sendHttp2();
                            }
                            else if(alert.data().contains("密码不能为空")){
                                Log.d("密码不能为空","密码不能为空");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_LONG).show();
                                        textNames.setText(textName);
                                        passWords.setText("");
                                        id_code.setText("");
                                    }
                                });
                                sendHttp2();
                            }
                            else if(alert.data().contains("验证码不能为空，如看不清请刷新")){
                                Log.d("验证码不能为空","验证码不能为空");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"验证码不能为空",Toast.LENGTH_LONG).show();
                                        textNames.setText(textName);
                                        passWords.setText(passWord);
                                        id_code.setText("");
                                    }
                                });
                                sendHttp2();
                            }else if(nameElement!=null){
                                String studentName = nameElement.html();
                                Log.d("nameEle",studentName);
                                if(rember_Password.isChecked()){
                                    editor.putString("学号",textNames.getText().toString());
                                    editor.putString("密码",passWords.getText().toString());
                                    editor.putBoolean("记住密码",true);
                                }
                                if(!rember_Password.isChecked()){
                                    editor=sharedPreferences.edit();
                                    editor.putString("学号","");
                                    editor.putString("密码","");
                                    editor.putBoolean("记住密码",false);
                                }
                                editor.apply();
                                Intent intent=new Intent(MainActivity.this, Main2Activity.class);
                                intent.putExtra("Cookie",cookieStr);
                                intent.putExtra("Response",responses);
                                intent.putExtra("enter_url",response.request().url().toString());
                                startActivity(intent);
                            }
                        }


                    }

                });
    }
}
