package com.example.school.Tool;

import com.example.school.model.SetOkHttp;

import org.litepal.crud.DataSupport;

/**
 * Created by 泡泡 on 2018/2/28.
 */

public class Lesson extends DataSupport{

    private String lessonName;
    private String classRoom;
    private String lessonTime;
    private String lessonTeacher;

    public void setLessonName(String lessonName){
        this.lessonName=lessonName;
    }
    public String getLessonName(){
        return lessonName;
    }
    public void setClassRoom(String classRoom){
        this.classRoom=classRoom;
    }
    public String getClassRoom(){
        return classRoom;
    }
    public void  setLessonTime(String lessonTime){
        this.lessonTime=lessonTime;
    }
    public String getLessonTime(){
        return lessonTime;
    }
    public void setLessonTeacher(String lessonTeacher){
        this.lessonTeacher=lessonTeacher;
    }
    public String getLessonTeacher(){
        return lessonTeacher;
    }

}
