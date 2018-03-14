package com.example.school.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.R;
import com.example.school.Tool.Exam;

import java.util.List;


/**
 * Created by 泡泡 on 2018/2/27.
 */

public class ExamReclAdapter extends RecyclerView.Adapter<ExamReclAdapter.ViewHolder>{

    private List<Exam>  exams;

    public ExamReclAdapter(List<Exam>  exams){
        this.exams=exams;
//        Log.d("examrel",exams.get(0).getExam_name());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_year;
        TextView textView_semes;
        TextView textView_code;
        TextView textView_name;
        TextView textView_lesson;
        TextView textView_credit;
        TextView textView_grade;
        TextView textView_score;
        TextView textView_academy;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_year=(TextView)itemView.findViewById(R.id.exam_card_year);
            textView_semes=(TextView)itemView.findViewById(R.id.exam_card_semes);
            textView_code=(TextView)itemView.findViewById(R.id.exam_card_code);
            textView_name=(TextView)itemView.findViewById(R.id.exam_card_name);
            textView_lesson=(TextView)itemView.findViewById(R.id.exam_card_lesson);
            textView_credit=(TextView)itemView.findViewById(R.id.exam_card_credit);
            textView_grade=(TextView)itemView.findViewById(R.id.exam_card_grade);
            textView_score=(TextView)itemView.findViewById(R.id.exam_card_score);
            textView_academy=(TextView)itemView.findViewById(R.id.exam_card_academy);

        }
    }

    @Override
    public ExamReclAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_recycle_cardview,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ExamReclAdapter.ViewHolder holder, int position) {
        Exam exam=exams.get(position);
        holder.textView_year.setText(exam.getExam_year());
        holder.textView_semes.setText(exam.getExam_semes());
        holder.textView_code.setText(exam.getExam_code());
        holder.textView_name.setText(exam.getExam_name());
  //      Log.d("nemwEel",exam.getExam_name());
        holder.textView_lesson.setText(exam.getExam_lesson());
        holder.textView_credit.setText(exam.getExam_credit());
        holder.textView_grade.setText(exam.getExam_grade());
        holder.textView_score.setText(exam.getExam_score());
        holder.textView_academy.setText(exam.getExam_academy());
    }

    @Override
    public int getItemCount() {
        //Log.d("recyAdapter", String.valueOf(exams.size()));
        return exams.size();
    }

}
