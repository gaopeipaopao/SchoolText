package com.example.school.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.school.R;
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
 * 课表
 */
public class Fragment_Lesson extends Fragment {

    private AddLessonActivity addLesson;
    private View view;
    private String enter_year="";//入学年份
  //  private FloatingActionButton floatingActionButton;
    private Button lesson_add;
    private List<String> names;
    private boolean isGetData = false;
    private int maxClassNumber = 0;//课程表左侧的最大节数
    private int number = 1; //课程表左侧的当前节数
   // private Toolbar toolbar; //工具条
    private TextView toolbal_text;
    private RelativeLayout day_one = null;
    private RelativeLayout day_two = null;
    private RelativeLayout day_three = null;
    private RelativeLayout day_four = null;
    private RelativeLayout day_five = null;
    private RelativeLayout day_six = null;
    private RelativeLayout day_seven = null;//星期几
    private RelativeLayout day = null;
    private LinearLayout classNumberLayout;
    private List<Lesson> lessons;
    private String responses;
    private SharedPreferences sharedPreferences;
    private String cookie;
    private String enter_url;
    private String lesson_url = "http://222.24.62.120/xskbcx.aspx?xh=04161134&xm=%B8%DF%C5%E5&gnmkdm=N121603";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lesson, container, false);
        lesson_add=(Button)view.findViewById(R.id.lesson_add);
       // floatingActionButton=(FloatingActionButton)view.findViewById(R.id.lesson_float);
        classNumberLayout = (LinearLayout) view.findViewById(R.id.class_number_layout);
        day_one = (RelativeLayout) view.findViewById(R.id.monday);
        day_two = (RelativeLayout) view.findViewById(R.id.tuesday);
        day_three = (RelativeLayout) view.findViewById(R.id.wednesday);
        day_four = (RelativeLayout) view.findViewById(R.id.thursday);
        day_five = (RelativeLayout) view.findViewById(R.id.friday);
        day_six = (RelativeLayout) view.findViewById(R.id.saturday);
        day_seven = (RelativeLayout) view.findViewById(R.id.weekday);
//        day_one.removeAllViews();
//        day_two.removeAllViews();
//        day_three.removeAllViews();
//        day_four.removeAllViews();
//        day_five.removeAllViews();
//        day_six.removeAllViews();
//        day_seven.removeAllViews();
        toolbal_text=(TextView)view.findViewById(R.id.toolbar_text);
      //  toolbal_text.setText("课程表");
     //   ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
     //   setHasOptionsMenu(true);
        sharedPreferences = getActivity().getSharedPreferences("name", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie", "");
        enter_url = sharedPreferences.getString("Enter_url", "");
   //     lessons = DataSupport.findAll(Lesson.class);
   //     lessons.clear();
        lessons=new ArrayList<>();
        names = new ArrayList<>();
        names.add("aaa");
        Log.d("lessons", String.valueOf(lessons.size()));
        Log.d("cc", cookie);
        Log.d("creat", "craet");
        return view;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        Log.d("creat","cccc");
//        String resp = null;
//        addLesson=(AddLessonActivity)getActivity();
//        resp=addLesson.responses;
//        Log.d("ccc",resp);
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("onAct", "onAct");

//        lesson_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getContext().getApplicationContext(),AddLessonActivity.class);
//                intent.putExtra("Enter_year",enter_year);
//                startActivity(intent);
//            }
//        });


//        if (lessons.size() > 0) {
//            for (Lesson lesson : lessons) {
//                createLeftView(lesson);
//                createView(lesson);
//            }
//        } else {
//            send();
//            lessons = DataSupport.findAll(Lesson.class);
//            for (Lesson lesson : lessons) {
//                createLeftView(lesson);
//                createView(lesson);
//            }
//        }
        send();

    }


    public void send() {
        SetOkHttp.sendLessonUrl(lesson_url, cookie, enter_url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responses = response.body().string();
                parseLesson(responses);
                for (Lesson lesson : lessons) {
                createLeftView(lesson);
                createView(lesson);
            }
         //       Log.d("respon", "respon");
            }
        });
    }

    public  void createLeftView(Lesson lesson) {
        //动态生成课程表左侧的节数视图
        String lesson_Time = lesson.getLessonTime();
        int j = 0;
        for (int i = 0; i < lesson_Time.length(); i++) {
            if (lesson_Time.charAt(i) == ',') {
                j = i;
                break;
            }
        }
        j = j + 1;
        int len = Integer.parseInt(String.valueOf(lesson_Time.charAt(j)));
        if (len > maxClassNumber) {
            View view;
            TextView text;
            for (int i = 0; i < len - maxClassNumber; i++) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.lesson_class_number, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110, 180);
                view.setLayoutParams(params);
                text = (TextView) view.findViewById(R.id.class_number_text);
                text.setText("" + number++);
                final View finalView = view;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classNumberLayout.addView(finalView);
                    }
                });
            }
            maxClassNumber = len;
        }
        if(maxClassNumber==6){
            View view;
            TextView text;
            for (int i = 0; i < 8 - maxClassNumber; i++) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.lesson_class_number, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110, 180);
                view.setLayoutParams(params);
                text = (TextView) view.findViewById(R.id.class_number_text);
                text.setText("" + number++);
                final View finalView = view;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        classNumberLayout.addView(finalView);
                    }
                });
            }
        }
    }

    public  void createView(Lesson lesson) {
        int name_loct = -1;
        int i = 0;
        String lessonName = lesson.getLessonName();
    //    Log.d("lessonmane", lessonName);
        // Log.d("name_size", String.valueOf(names.size()));
        for (i = 0; i < names.size(); i++) {
            //    Log.d("nnnnnn", String.valueOf(i));
            if (names.get(i).contains(lessonName)) {
                name_loct = i;
                break;
            }
        }
        if (name_loct != i) {
            names.add(lessonName);
            name_loct = names.size() - 1;
        }
     //   Log.d("name_loc", String.valueOf(name_loct));
        String lessonTime = lesson.getLessonTime();
        int j1 = 0;
        int start = 0;
        int end = 0;
        for (int i2 = 0; i2 < lessonTime.length(); i2++) {
            if (lessonTime.charAt(i2) == ',') {
                j1 = i2;
                break;
            }
        }
        start = Integer.parseInt(String.valueOf(lessonTime.charAt(j1 - 1)));//start
        end = Integer.parseInt(String.valueOf(lessonTime.charAt(j1 + 1)));//end
        //    Log.d("jjjjjjj",Integer.toString(start));
        //   Log.d("kkkkkkkkk",Integer.toString(end));
        int integer = 0;
        switch (String.valueOf(lessonTime.charAt(1))) {
            case "一":
                integer = 1;
                break;
            case "二":
                integer = 2;
                break;
            case "三":
                integer = 3;
                break;
            case "四":
                integer = 4;
                break;
            case "五":
                integer = 5;
                break;
            case "六":
                integer = 6;
                break;
            case "七":
                integer = 7;
                break;
        }
        //    Log.d("int",Integer.toString(integer));
        if ((integer < 1 && integer > 7)) {
            //Toast.makeText(getContext(), "星期几没写对,或课程结束时间比开始时间还早~~", Toast.LENGTH_LONG).show();
        } else {
            //       Log.d("llllll","llllllll");
            final View view = LayoutInflater.from(getContext()).inflate(R.layout.lesson_card, null); //加载单个课程布局
            view.setY(180 * (start - 1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (end - start + 1) * 180); //设置布局高度,即跨多少节课
            view.setLayoutParams(params);
            CardView cardView = (CardView) view.findViewById(R.id.lesson_card);
            TextView text = (TextView) view.findViewById(R.id.text_view);
         //   Log.d("text", String.valueOf(text.getText()));
            text.setText(lesson.getLessonName() + "\n" + lesson.getClassRoom()); //显示课程名

            switch (name_loct) {
                case 1:
                    cardView.setCardBackgroundColor(Color.parseColor("#FFC0CB"));
                    break;
                case 2:
                    cardView.setCardBackgroundColor(Color.parseColor("#FFDAB9"));
                    break;
                case 3:
                    cardView.setCardBackgroundColor(Color.parseColor("#B2DFEE"));
                    break;
                case 4:
                    cardView.setCardBackgroundColor(Color.parseColor("#87CEFF"));
                    break;
                case 5:
                    cardView.setCardBackgroundColor(Color.parseColor("#7CCD7C"));
                    break;
                case 6:
                    cardView.setCardBackgroundColor(Color.parseColor("#B0E2FF"));
                    break;
                case 7:
                    cardView.setCardBackgroundColor(Color.parseColor("#B2DFEE"));
                    break;
                case 8:
                    cardView.setCardBackgroundColor(Color.parseColor("#f4ea2a"));
                    break;
            }
            switch (integer) {
                case 1:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_one.addView(view);
                        }
                    });
                    break;
                case 2:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_two.addView(view);
                        }
                    });
                    break;
                case 3:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_three.addView(view);
                        }
                    });
                    break;
                case 4:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_four.addView(view);
                        }
                    });
                    break;
                case 5:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_five.addView(view);
                        }
                    });
                    break;
                case 6:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_six.addView(view);
                        }
                    });
                    break;
                case 7:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            day_seven.addView(view);
                        }
                    });
                    break;
            }
        }
    }

    public  void parseLesson(String responses) {
        Document document = Jsoup.parse(responses);

        //得到学年和学期
        Elements semesters = document.getElementsByTag("option");
        Elements selects=semesters.select("[selected=selected]");
        String years=selects.get(0).text();
        String semes=selects.get(1).text();

        //得到入学年份
        Elements spans = document.getElementsByTag("span");
        String span=spans.get(7).text();
        int enter_year_int=0;
        Log.d("span",span);
        for(int i=0;i<span.length();i++){
            if(span.charAt(i)>='0'&&span.charAt(i)<='9'){
                enter_year=enter_year+span.charAt(i);
                enter_year=enter_year+span.charAt(i+1);
                break;
            }
        }
        enter_year_int=Integer.parseInt(enter_year);
     //   Log.d("ene", String.valueOf(enter_year_int));


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
     //   Log.d("ddd",""+20+a1+"-"+20+a2);
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
                toolbal_text.setText(finalText);
            }
        });



        //
        Element table = document.getElementById("Table1");
        //然后获取table中的td节点
        Elements trs = table.select("tr");
   //     Log.d("trs", String.valueOf(trs.size()));
        //移除不需要的参数，这里表示移除前两个数值。
        trs.remove(0);
        trs.remove(0);
        //遍历td节点
        for (int i = 0; i < trs.size(); i++) {
            Element tr = trs.get(i);
            //获取tr下的td节点，要求
            Elements tds = tr.select("td[align]");
            //遍历td节点
            for (int j = 0; j < tds.size(); ++j) {
                Element td = tds.get(j);
                String str = td.text();
                Log.d("str",str);
                String lessonName = "";
                String classRoom = "";
                String lessonTime = "";
                String lessonTeacher = "";
                int k1 = 0;
                int k2 = 0;
                int k3 = 0;
                int teacher_i = 0;
                Log.d("str", str);
                //如果数值为空则不计算。
                if (str.length() != 1) {
                    //解析文本数据
                    Lesson lesson = new Lesson();
                    for (int i1 = 0; i1 < str.length(); i1++) {
                        if (str.charAt(i1) == ' ') {
                            k1 = i1;
                            break;
                        } else {
                            lessonName = lessonName + str.charAt(i1);
                        }
                    }
                //    Log.d("iii", str.charAt(k1 + 1) + "");
                    for (int i1 = k1 + 1; i1 < str.length(); i1++) {
                        if (str.charAt(i1) == ' ') {
                            k2 = i1;
                            break;
                        } else {
                            lessonTime = lessonTime + str.charAt(i1);
                        }
                    }
                 //   Log.d("iii", str.charAt(k2 + 1) + "");
                    for (int i1 = k2 + 1; i1 < str.length(); i1++) {
                        if (str.charAt(i1) == ' ') {
                            k3 = i1;
                            break;
                        } else {
                            lessonTeacher = lessonTeacher + str.charAt(i1);
                        }
                    }
                //    Log.d("iii", str.charAt(k3 + 1) + "");
                    for (int i1 = k3 + 1; i1 < str.length(); i1++) {
                        if (str.charAt(i1) == ' ') {
                            break;
                        } else {
                            classRoom = classRoom + str.charAt(i1);
                        }
                    }
                //    Log.d("ssssssss", String.valueOf(lessonTime.charAt(1)));
                //    Log.d("Name", lessonName);
                //    Log.d("Time", lessonTime);
                //    Log.d("Teacher", lessonTeacher);
                //    Log.d("Room", classRoom);
                    lesson.setLessonName(lessonName);
                    lesson.setLessonTime(lessonTime);
                    lesson.setLessonTeacher(lessonTeacher);
                    lesson.setClassRoom(classRoom);
                    lessons.add(lesson);
                }
            }
        }
    }



}
