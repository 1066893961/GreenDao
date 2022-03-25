package com.children.greendaoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2022/3/24 14:39
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();

    public StudentListAdapter(List<Student> studentList){
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textView.setText("姓名：" + student.getName());
        holder.textView2.setText("年龄：" + student.getAge());
        holder.textView3.setText("学校名：" + student.getSchoolName());
        holder.textView4.setText("学号：" + student.getStudentNo());
        holder.textView5.setText("id：" + student.getId());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    static class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView textView,textView2,textView3,textView4,textView5;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView5 = itemView.findViewById(R.id.textView5);
        }
    }

    public void setStudentList(List<Student> studentList){
        this.studentList = studentList;
        notifyDataSetChanged();
    }

}

