package com.example.school.view;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.school.Adapter.ViewPagerAdapter;
import com.example.school.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private String responses;
    private String cookie;
    private OkHttpClient okHttpClient;
    private String enter_url;
    private String lesson_url="http://222.24.62.120/xskbcx.aspx?xh=04161134&xm=%B8%DF%C5%E5&gnmkdm=N121603";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private List<String> tabIndicators;
    List<Fragment> mfragment;
    private String[]  titles=new String[]{"课表","成绩","课程","个人"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        // Log.d("aaaaaaa", "onCreate: ");
        initContent();
        initTab();


        okHttpClient=new OkHttpClient.Builder().build();
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        Intent intent=getIntent();
        responses=intent.getStringExtra("Response");
        cookie=intent.getStringExtra("Cookie");
        enter_url=intent.getStringExtra("enter_url");
        Log.d("enter_url",enter_url);
        Log.d("res",responses);
        Document document= Jsoup.parse(responses);
        send();
    }
    private void send(){
        final Request request=new Request.Builder().url(lesson_url).addHeader("Cookie",cookie).addHeader("Referer",enter_url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("exam",response.body().string());
            }
        });
    }
    private void document(){
        Document document= Jsoup.parse(responses);
        Elements elements=document.select("a[href]");
        for(Element element:elements){
            if(element.data().contains("学生个人课表")){
                lesson_url=element.html();

            }
        }
    }

    private void initTab() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);//TabLayout.MODE_FIXED为tabLayout的模式
        tabLayout.setSelectedTabIndicatorHeight(0);//高度为0，就隐藏了indication
        ViewCompat.setElevation(tabLayout, 10);//设置tab之间的间距
        for (int i = 0; i < 4; i++) {
            TabLayout.Tab tab = tabLayout.newTab();

            View view = LinearLayout.inflate(this,R.layout.tablayout_image_text,null);
            tab.setCustomView(view);

            TextView text = (TextView) view.findViewById(R.id.tab_text);
            text.setText(titles[i]);
            ImageView imageView=(ImageView)view.findViewById(R.id.tab_image);
            if (i==0){
                imageView.setImageResource(R.drawable.selsec_lesson);
            }
            if(i==1){
                imageView.setImageResource(R.drawable.select_exam);
            }
            if(i==2){
                imageView.setImageResource(R.drawable.select_course);
            }
            if(i==3){
                imageView.setImageResource(R.drawable.select_owns);
            }

            tabLayout.addTab(tab);
        }
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }



    private void initContent(){
        mfragment = new ArrayList<>();
        mfragment.add(new Fragment_Lesson());
        mfragment.add(new Fragment_Exam());
        mfragment.add(new Fragment_course());
        mfragment.add(new Fragment_Owns());
        viewPagerAdapter = new ViewPagerAdapter(mfragment, getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
    }
}
