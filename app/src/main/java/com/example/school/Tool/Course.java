package com.example.school.Tool;

import com.example.school.model.SetOkHttp;

/**
 * Created by 泡泡 on 2018/3/5.
 */

public class Course {

    public String course_code;//课程代码

    public String course_name;

    public String course_credit;//学分

    public String course_hour;//周学时

    public String course_way;//考核方式

    public String course_quality;//课程性质


    public void setCourse_code(String course_code){
        this.course_code=course_code;
    }
    public String getCourse_code(){
        return course_code;
    }

    public void setCourse_name(String course_name){
        this.course_name=course_name;
    }
    public String getCourse_name(){
        return course_name;
    }

    public void setCourse_credit(String course_credit){
        this.course_credit=course_credit;
    }
    public String getCourse_credit(){
        return course_credit;
    }

    public void setCourse_hour(String course_hour){
        this.course_hour=course_hour;
    }
    public String getCourse_hour(){
        return course_hour;
    }

    public void setCourse_way(String course_way){
        this.course_way=course_way;
    }
    public String getCourse_way(){
        return course_way;
    }

    public void setCourse_quality(String course_quality){
        this.course_quality=course_quality;
    }
    public String getCourse_quality(){
        return course_quality;
    }


}
