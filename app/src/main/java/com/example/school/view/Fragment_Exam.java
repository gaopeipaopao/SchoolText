package com.example.school.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.Adapter.ExamReclAdapter;
import com.example.school.R;
import com.example.school.RecycleItemDecoration.MyDecoration;
import com.example.school.Tool.Exam;
import com.example.school.Tool.Lesson;
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
 * Created by 泡泡 on 2018/2/25.
 */

/**
 * 成绩
 */
public class Fragment_Exam extends Fragment {

    private RecyclerView recyclerView;
    private ExamReclAdapter examReclAdapter;
    private List<Exam> exams;
    private String exam_url="http://222.24.62.120/xscjcx.aspx?xh=04161134&xm=%B8%DF%C5%E5&gnmkdm=N121605";
    private SharedPreferences sharedPreferences;
    private String cookie;
    private String enter_url;
    private String responses;
    private TextView exam_text;
    private String enter_year="";//入学年份

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie", "");
        enter_url = sharedPreferences.getString("Enter_url", "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_exam,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.exam_recycle);
      //  recyclerView.setHasFixedSize(true);
     //   recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layout);
      //  recyclerView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        exam_text=(TextView)view.findViewById(R.id.exam_text);
        exams=new ArrayList<>();
        examReclAdapter=new ExamReclAdapter(exams);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
//        recyclerView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(examReclAdapter);
//        sharedPreferences = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
//        cookie = sharedPreferences.getString("Cookie", "");
//        enter_url = sharedPreferences.getString("Enter_url", "");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        send();
    }

    private void send(){
        SetOkHttp.sendExamUrl(exam_url, cookie, enter_url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("rrrrsss",response.body().string());
                send1();


                //  responses=response.body().string();
                // parseExam(responses);
            }
        });
    }

    private void send1(){
        SetOkHttp.postExamUrl(exam_url, cookie, enter_url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("rrrrsss",response.body().string());
                responses=response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseExam(responses);
                        examReclAdapter.notifyDataSetChanged();
//                        examReclAdapter=new ExamReclAdapter(exams);


//                            Log.d("eeename", exams.get(0).getExam_name());


//                        recyclerView.setAdapter(examReclAdapter);
//                        recyclerView.addItemDecoration(new MyDecoration(getActivity(),MyDecoration.VERTICAL_LIST));
                    }
                });
            }
        });
    }

    private void parseExam(String responses){


        Document document = Jsoup.parse(responses);

        //得到学年和学期
        Elements semesters = document.getElementsByTag("option");
        Elements selects=semesters.select("[selected=selected]");
//        Log.d("sesize", String.valueOf(semesters.size()));
        String years=selects.get(0).text();
        String semes=selects.get(1).text();

        //得到入学年份
        Elements spans = document.getElementsByTag("span");
        String span=spans.get(11).text();
        int enter_year_int=0;
//        Log.d("spanss",span);
        for(int i=0;i<span.length();i++){
            if(span.charAt(i)>='0'&&span.charAt(i)<='9'){
                enter_year=enter_year+span.charAt(i);
                enter_year=enter_year+span.charAt(i+1);
                break;
            }
        }
        enter_year_int=Integer.parseInt(enter_year);
//        Log.d("ene", String.valueOf(enter_year_int));


        String text="";
        int a1=enter_year_int;
        int a2=enter_year_int+1;
        Log.d("a2", String.valueOf(a2));
        int a3=enter_year_int+2;
        int a4=enter_year_int+3;
        int a5=enter_year_int+4;
        if(years.contains(""+20+a1+"-"+20+a2)){
            text=text+"大一";
        }
//           Log.d("ddd",""+20+a1+"-"+20+a2);
        if(years.contains(""+20+a2+"-"+20+a3)){
            text=text+"大二";
        }
        if(years.contains(""+20+a3+"-"+20+a4)){
            text=text+"大三";
        }
        if(years.contains(""+20+a4+"-"+20+a5)){
            text=text+"大四";
        }
        if(semes.contains("1")){
            text=text+"第一学期";
        }
        if(semes.contains("2")){
            text=text+"第二学期";
        }
        final String finalText = text;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                exam_text.setText(finalText);
            }
        });

        Elements table = document.getElementsByClass("datelist");
        //然后获取table中的td节点
        Elements trs = table.select("tr");
        //移除不需要的参数，这里表示移除前两个数值。
        trs.remove(0);
        trs.remove(0);
  //      Log.d("trs", String.valueOf(trs.size()));
        //遍历td节点
        for (int i = 0; i < trs.size(); i++) {
            final Exam exam=new Exam();

            Element tr = trs.get(i);
            //获取tr下的td节点，要求
            Elements tds = tr.select("td");
            String trr;

            exam.setExam_year(tds.get(0).text());
            exam.setExam_semes(tds.get(1).text());
            exam.setExam_code(tds.get(2).text());
            exam.setExam_name(tds.get(3).text());
            exam.setExam_lesson(tds.get(4).text());
            exam.setExam_credit(tds.get(6).text());
            exam.setExam_grade(tds.get(7).text());
            exam.setExam_score(tds.get(8).text());
            exam.setExam_academy(tds.get(12).text());

            exams.add(exam);

//         //   Log.d("size", String.valueOf(exams.size()));
//            Log.d("sizename",exams.get(exams.size()-1).getExam_name());
//            Log.d("sizenamestart",exams.get(0).getExam_name());
//            Log.d("aex","aex");





//            Log.d("name",exam.exam_name);
            examReclAdapter.notifyItemInserted(exams.size()-1);
//            recyclerView.scrollToPosition(exams.size()-1);
         //   examReclAdapter.notifyItemInserted(0);
         //   examReclAdapter.notifyDataSetChanged();
         //   Log.d("strss", tds.get(3).text());
         //   Log.d("strss", tds.get(6).text());
        }

    }
}
