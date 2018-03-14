package com.example.school.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.school.R;
import com.example.school.Tool.Lesson;
import com.example.school.model.SetOkHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AddLessonActivity extends AppCompatActivity {

    private String enter_year="";
    private int enter_year_int=0;
    private List<Lesson> lessons;
    private Button add_lessonback;
    private Button lesson_query;
    private EditText addYears;
    private EditText addSemes;
    private String addYear;
    private String addSeme;
    private String xnd="";//学年
    private String xqd="";//学期
    public String responses;
    private SharedPreferences sharedPreferences;
    private String cookie;
    private String enter_url;
    private String lesson_url = "http://222.24.62.120/xskbcx.aspx?xh=04161134&xm=%B8%DF%C5%E5&gnmkdm=N121603";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        lessons=new ArrayList<>();
        sharedPreferences = getSharedPreferences("name", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie", "");
        enter_url = sharedPreferences.getString("Enter_url", "");

        add_lessonback=(Button)findViewById(R.id.lesson_add_back);
        lesson_query=(Button)findViewById(R.id.lesson_query);
        addYears=(EditText)findViewById(R.id.add_year);
        addSemes=(EditText)findViewById(R.id.add_semes);

        Intent intent=getIntent();
        enter_year=intent.getStringExtra("Enter_year");
        enter_year_int=Integer.parseInt(enter_year);
        Log.d("enter",enter_year);

        addYear=addYears.getText().toString();
        addSeme=addSemes.getText().toString();

        int a1=enter_year_int;
        int a2=enter_year_int+1;
        //   Log.d("a2", String.valueOf(a2));
        int a3=enter_year_int+2;
        int a4=enter_year_int+3;
        int a5=enter_year_int+4;


        if(addYear.contains("大一")&&addSeme.contains("1")){
            xnd=""+20+a1+"-"+20+a2;
            xqd="1";
        }
        if(addYear.contains("大一")&&addSeme.contains("2")){
            xnd=""+20+a1+"-"+20+a2;
            xqd="2";
        }
        if(addYear.contains("大二")&&addSeme.contains("1")){
            xnd=""+20+a2+"-"+20+a3;
            xqd="1";
        }
        if(addYear.contains("大二")&&addSeme.contains("2")){
            xnd=""+20+a2+"-"+20+a3;
            xqd="2";
        }
        if(addYear.contains("大三")&&addSeme.contains("1")){
            xnd=""+20+a3+"-"+20+a4;
            xqd="1";
        }
        if(addYear.contains("大三")&&addSeme.contains("2")){
            xnd=""+20+a3+"-"+20+a4;
            xqd="2";
        }
        if(addYear.contains("大四")&&addSeme.contains("1")){
            xnd=""+20+a4+"-"+20+a5;
            xqd="1";
        }
        if(addYear.contains("大四")&&addSeme.contains("2")){
            xnd=""+20+a4+"-"+20+a5;
            xqd="2";
        }
//        else {
//            Toast.makeText(this,"输入有误",Toast.LENGTH_LONG).show();
//            addYears.setText("");
//            addSemes.setText("");
//        }


        Log.d("xnd",xnd);

        add_lessonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lesson_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetOkHttp.getLesson(xnd,xqd,lesson_url, cookie, enter_url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //Log.d("leresponse",response.body().string());
                        responses=response.body().string();
                    }
                });


            }
        });

    }
}
