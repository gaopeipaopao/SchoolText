package com.example.school.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.school.R;

/**
 * Created by 泡泡 on 2018/2/25.
 */

/**
 * 我的
 */
public class Fragment_Owns extends Fragment {

    private LinearLayout change_secret;

    private LinearLayout back_enter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owns,container,false);
        change_secret = (LinearLayout)view.findViewById(R.id.change_secret);
        back_enter = (LinearLayout)view.findViewById(R.id.back_enter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        change_secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"修改密码",Toast.LENGTH_LONG);
            }
        });
        back_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"退出登录",Toast.LENGTH_LONG);
            }
        });
    }
}
