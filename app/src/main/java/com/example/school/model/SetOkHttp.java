package com.example.school.model;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 泡泡 on 2018/2/24.
 */

public class SetOkHttp {

    public static void setHttpGet( OkHttpClient okHttpClient,String address, okhttp3.Callback callback){
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public static void setHttpPost( OkHttpClient okHttpClient,String address, String name, String password
            , String cookieStr, String code, final Callback callback){
        FormBody formBody=new FormBody.Builder()
                .add("__VIEWSTATE","dDwxNTMxMDk5Mzc0Ozs+lYSKnsl/mKGQ7CKkWFJpv0btUa8=")
                .add("txtUserName",name)
                .add("Textbox1","")
                .add("TextBox2",password)
                .add("txtSecretCode",code)
                .add("RadioButtonList1","%D1%A7%C9%FA")
                .add("Button1","")
                .add("lbLanguage","")
                .add("hidPdrs","")
                .add("hidsc","")
                .build();
        Request request = new Request.Builder().post(formBody).url(address).addHeader("ASP.NET_SessionId",cookieStr).addHeader("Referer","http://222.24.62.120/").build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public static void sendLessonUrl(String lesson_url,String cookie,String enter_url,Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        Request request=new Request.Builder().url(lesson_url).addHeader("Cookie",cookie).addHeader("Referer",enter_url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void getLesson(String xnd,String xqd,String lesson_url,String cookie,String enter_url,Callback callback){
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        FormBody formBody=new FormBody.Builder()
                .add("xnd",xnd)
                .add("xqd",xqd)
                .build();
        Request request=new Request.Builder().post(formBody).url(lesson_url).addHeader("Cookie",cookie).addHeader("Referer",enter_url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void postExamUrl(String exam_url,String cookie,String enter_url,Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody=new FormBody.Builder()
                .add("__EVENTTARGET","")
                .add("__EVENTARGUMENT","")
                .add("__VIEWSTATE","dDwtMTIxNTQzNzk3NTt0PHA8bDxTb3J0RXhwcmVzO3NmZGNiaztkZzM7ZHlieXNjajtTb3J0RGlyZTt4aDtzdHJfdGFiX2JqZztjamN4X2xzYjt6eGNqY3h4czs+O2w8a2NtYztcZTtiamc7XGU7YXNjOzA0MTYxMTM0O3pmX2N4Y2p0al8wNDE2MTEzNDs7MDs+PjtsPGk8MT47PjtsPHQ8O2w8aTw0PjtpPDEwPjtpPDIzPjtpPDI4PjtpPDM2PjtpPDQwPjtpPDQyPjtpPDQ0PjtpPDQ2PjtpPDQ4PjtpPDUwPjtpPDUyPjtpPDU0PjtpPDU4PjtpPDYwPjtpPDYyPjs+O2w8dDx0PHA8cDxsPERhdGFUZXh0RmllbGQ7RGF0YVZhbHVlRmllbGQ7PjtsPFhOO1hOOz4+Oz47dDxpPDM+O0A8XGU7MjAxNy0yMDE4OzIwMTYtMjAxNzs+O0A8XGU7MjAxNy0yMDE4OzIwMTYtMjAxNzs+Pjs+Ozs+O3Q8dDxwPHA8bDxEYXRhVGV4dEZpZWxkO0RhdGFWYWx1ZUZpZWxkOz47bDxrY3h6bWM7a2N4emRtOz4+Oz47dDxpPDEyPjtAPOW/heS/ruivvjvpmZDpgInor7475Lu76YCJ6K++O+ivvuWkluWunui3teaVmeWtpjvovoXkv67or7476Leo5a2m56eRO+e0oOi0qOaLk+WxlTvlhazlhbHpgInkv67or7475Lq65paH57Sg6LSo6ZmQ6YCJO+mAieS/ruivvjvpgInkv67or74o5bCU6ZuFKTtcZTs+O0A8MDE7MDI7MDM7MDQ7MDU7MDY7MDc7MDg7MDk7MTA7MTE7XGU7Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPFxlOz4+Oz47Oz47dDxwPHA8bDxUZXh0O1Zpc2libGU7PjtsPOWtpuWPt++8mjA0MTYxMTM0O288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85aeT5ZCN77ya6auY5L2pO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85a2m6Zmi77ya6K6h566X5py65a2m6ZmiO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85LiT5Lia77yaO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w86K6h566X5py656eR5a2m5LiO5oqA5pyvO288dD47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOS4k+S4muaWueWQkTo7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w86KGM5pS/54+t77ya6K6h56eRMTYwNTtvPHQ+Oz4+Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Oz47Ozs7Ozs7Ozs7Pjs7Pjt0PDtsPGk8MT47aTwzPjtpPDU+O2k8Nz47aTw5PjtpPDEzPjtpPDE1PjtpPDE3PjtpPDIxPjtpPDIzPjtpPDI0PjtpPDI1PjtpPDI3PjtpPDI5PjtpPDMxPjtpPDMzPjtpPDM1PjtpPDQzPjtpPDQ5PjtpPDUzPjtpPDU0Pjs+O2w8dDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+O3A8bDxzdHlsZTs+O2w8RElTUExBWTpub25lOz4+Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8O2w8aTwxMz47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47dDxwPHA8bDxUZXh0O1Zpc2libGU7PjtsPOiHs+S7iuacqumAmui/h+ivvueoi+aIkOe7qe+8mjtvPHQ+Oz4+Oz47Oz47dDxAMDxwPHA8bDxQYWdlQ291bnQ7XyFJdGVtQ291bnQ7XyFEYXRhU291cmNlSXRlbUNvdW50O0RhdGFLZXlzOz47bDxpPDE+O2k8MT47aTwxPjtsPD47Pj47cDxsPHN0eWxlOz47bDxESVNQTEFZOmJsb2NrOz4+Pjs7Ozs7Ozs7Ozs+O2w8aTwwPjs+O2w8dDw7bDxpPDE+Oz47bDx0PDtsPGk8MD47aTwxPjtpPDI+O2k8Mz47aTw0PjtpPDU+O2k8Nj47PjtsPHQ8cDxwPGw8VGV4dDs+O2w8RFoxMTAyMTE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOaVsOWtl+eUtei3r+S4jumAu+i+keiuvuiuoUE7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOW/heS/ruivvjs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8NC4wOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDw0NTs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w8Jm5ic3BcOzs+Pjs+Ozs+Oz4+Oz4+Oz4+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtwPGw8c3R5bGU7PjtsPERJU1BMQVk6bm9uZTs+Pj47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtwPGw8c3R5bGU7PjtsPERJU1BMQVk6bm9uZTs+Pj47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8cDxwPGw8VmlzaWJsZTs+O2w8bzxmPjs+PjtwPGw8c3R5bGU7PjtsPERJU1BMQVk6bm9uZTs+Pj47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47cDxsPHN0eWxlOz47bDxESVNQTEFZOm5vbmU7Pj4+Ozs7Ozs7Ozs7Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+Oz47Ozs7Ozs7Ozs7Pjs7Pjt0PEAwPHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47cDxsPHN0eWxlOz47bDxESVNQTEFZOm5vbmU7Pj4+Ozs7Ozs7Ozs7Oz47Oz47dDxAMDxwPHA8bDxWaXNpYmxlOz47bDxvPGY+Oz4+O3A8bDxzdHlsZTs+O2w8RElTUExBWTpub25lOz4+Pjs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8O0AwPDs7QDA8cDxsPEhlYWRlclRleHQ7PjtsPOWIm+aWsOWGheWuuTs+Pjs7Ozs+O0AwPHA8bDxIZWFkZXJUZXh0Oz47bDzliJvmlrDlrabliIY7Pj47Ozs7PjtAMDxwPGw8SGVhZGVyVGV4dDs+O2w85Yib5paw5qyh5pWwOz4+Ozs7Oz47Ozs+Ozs7Ozs7Ozs7Pjs7Pjt0PHA8cDxsPFRleHQ7VmlzaWJsZTs+O2w85pys5LiT5Lia5YWxMjQx5Lq6O288Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFZpc2libGU7PjtsPG88Zj47Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPFhJWU9VOz4+Oz47Oz47dDxwPHA8bDxJbWFnZVVybDs+O2w8Li9leGNlbC8wNDE2MTEzNC5qcGc7Pj47Pjs7Pjs+Pjt0PDtsPGk8Mz47PjtsPHQ8QDA8Ozs7Ozs7Ozs7Oz47Oz47Pj47Pj47Pj47PihhNR57YvMIZh983FtbJSymQE7W")
                .add("hidLanguage","")
                .add("ddlXN","2016-2017")
                .add("ddlXQ","2")
                .add("ddl_kcxz","")
                .add("btn_xq","%D1%A7%C6%DA%B3%C9%BC%A8")
                .build();
        Request request = new Request.Builder().post(formBody).addHeader("Host","222.24.62.120").url(exam_url).addHeader("Cookie", cookie).addHeader("Referer", exam_url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void sendExamUrl(String exam_url,String cookie,String enter_url,Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().addHeader("Host","222.24.62.120").url(exam_url).addHeader("Cookie", cookie).addHeader("Referer", enter_url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public static void sendCourseUrl(String course_url,String cookie,String enter_url,Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().addHeader("Host","222.24.62.120").url(course_url).addHeader("Cookie", cookie).addHeader("Referer", enter_url).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public static void postCourseUrl(String course_url,String cookie,String pager,String name__VIEWSTATE,Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder()
                .add("__EVENTTARGET","xq")
                .add("__EVENTARGUMENT","")
                .add("__VIEWSTATE",name__VIEWSTATE)
                .add("xq",pager)
                .add("kcxz","%C8%AB%B2%BF")
                .add("dpDBGrid%3AtxtChoosePage","1")
                .add("dpDBGrid%3AtxtPageSize","10")
                .build();
        Request request = new Request.Builder().post(formBody).addHeader("Host","222.24.62.120").url(course_url).addHeader("Cookie", cookie).addHeader("Referer", "http://222.24.62.120/pyjh.aspx?xh=04161134&xm=%B8%DF%C5%E5&gnmkdm=N121607").build();
        okHttpClient.newCall(request).enqueue(callback);

    }
}
