package com.example.school.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.R;
import com.example.school.Tool.Course;

import java.util.List;

/**
 * Created by 泡泡 on 2018/3/6.
 */

public class CourseReclAdapter extends RecyclerView.Adapter<CourseReclAdapter.ViewHolder> {

    List<Course>  courses;

    public CourseReclAdapter(List<Course> courses){
        this.courses=courses;
    }


    @Override
    public CourseReclAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.course_recl_cardview,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CourseReclAdapter.ViewHolder holder, int position) {

        Course course=courses.get(position);
        holder.course_code.setText(course.getCourse_code());
        holder.course_name.setText(course.getCourse_name());
        holder.course_credit.setText(course.getCourse_credit());
        holder.course_hour.setText(course.getCourse_hour());
        holder.course_way.setText(course.getCourse_way());
        holder.course_quality.setText(course.getCourse_quality());

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView course_code;
        TextView course_name;
        TextView course_credit;
        TextView course_hour;
        TextView course_way;
        TextView course_quality;

        public ViewHolder(View itemView) {
            super(itemView);

            course_code=(TextView)itemView.findViewById(R.id.course_card_code);
            course_name=(TextView)itemView.findViewById(R.id.course_card_name);
            course_credit=(TextView)itemView.findViewById(R.id.course_card_credit);
            course_hour=(TextView)itemView.findViewById(R.id.course_card_hour);
            course_way=(TextView)itemView.findViewById(R.id.course_card_way);
            course_quality=(TextView)itemView.findViewById(R.id.course_card_quality);

        }
    }
}
