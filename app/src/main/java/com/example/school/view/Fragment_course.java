package com.example.school.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.Adapter.CourseReclAdapter;
import com.example.school.R;
import com.example.school.RecycleItemDecoration.MyDecoration;
import com.example.school.Tool.Course;
import com.example.school.model.SetOkHttp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by 泡泡 on 2018/2/26.
 */

/**
 * 成绩
 */
public class Fragment_course extends Fragment {

    private RecyclerView recyclerView;
    private CourseReclAdapter courseReclAdapter;
    private List<Course> courses;
    private String coures_url="http://222.24.62.120/pyjh.aspx?xh=04161134&xm=%B8%DF%C5%E5&gnmkdm=N121607";
    private SharedPreferences sharedPreferences;
    private String cookie;
    private String enter_url;
    private String responses;
    private TextView course_text;
    private String enter_year="";//入学年份
    private String name__VIEWSTATE="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_course,container,false);

        recyclerView= (RecyclerView) view.findViewById(R.id.course_recycle);
        //  recyclerView.setHasFixedSize(true);
        //   recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        course_text=(TextView)view.findViewById(R.id.course_text);
        courses=new ArrayList<>();
        courseReclAdapter=new CourseReclAdapter(courses);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
//        recyclerView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(courseReclAdapter);

        sharedPreferences = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie", "");
        enter_url = sharedPreferences.getString("Enter_url", "");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        course_text.setText("培养计划");

        send();
    }

    private void send() {

        SetOkHttp.sendCourseUrl(coures_url, cookie, enter_url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("course","respon1");
                responses = response.body().string();
                Document document = Jsoup.parse(responses);

                Elements names = document.select("[name=__VIEWSTATE]");

                name__VIEWSTATE = names.val();

               // Log.d("namesm",names.toString());


                Log.d("namename",name__VIEWSTATE);

                send1("1");
                send1("2");
                send1("3");
                send1("4");
                send1("5");
                send1("6");
                send1("7");
                send1("8");
            }
        });
    }

    private void send1(final String pager) {
        SetOkHttp.postCourseUrl(coures_url,cookie, pager,name__VIEWSTATE, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responses=response.body().string();
                Document document = Jsoup.parse(responses);

                Elements names = document.select("[name=__VIEWSTATE]");

                name__VIEWSTATE = names.val();

                Log.d("CourseResponse"+pager,responses);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseCourse(responses);
                        courseReclAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

    }

    private void parseCourse(String responses) {

        Document document = Jsoup.parse(responses);

        Elements table = document.getElementsByClass("datelist");
        Log.d("trs1", String.valueOf(table.size()));

        Element tables=table.get(0);

        //然后获取table中的td节点
        Elements trs = tables.select("tr");
        //移除不需要的参数，这里表示移除前两个数值。
        trs.remove(0);
        Log.d("trs", String.valueOf(trs.size()));
        for (int i = 0; i < trs.size(); i++) {
            final Course course = new Course();

            Element tr = trs.get(i);
            //获取tr下的td节点，要求
            Elements tds = tr.select("td");

            Log.d("tdlen", String.valueOf(tds.size()));

            String trr;

            course.setCourse_code(tds.get(0).text());
            course.setCourse_name(tds.get(1).text());
            course.setCourse_credit(tds.get(2).text());
            course.setCourse_hour(tds.get(3).text());
            course.setCourse_way(tds.get(4).text());
            course.setCourse_quality(tds.get(5).text());


            courses.add(course);
            courseReclAdapter.notifyItemInserted(courses.size() - 1);

            Log.d("size", String.valueOf(courses.size()));
            Log.d("sizename", courses.get(courses.size() - 1).getCourse_name());
            Log.d("sizenamestart", courses.get(0).getCourse_name());
            Log.d("aex", "aex");



        }
    }


}
