package com.example.school.model;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 泡泡 on 2018/2/24.
 */

public class SetOkHttp {

    public static void setHttpGet(OkHttpClient okHttpClient, String address, okhttp3.Callback callback){
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
    public static void setHttpPost(OkHttpClient okHttpClient, String address, String name, String password
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
}
