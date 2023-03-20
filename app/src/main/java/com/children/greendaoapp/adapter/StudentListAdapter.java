package com.children.greendaoapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.ui.UserInfoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2022/3/24 14:39
 */
public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();
    private Activity mContext;

    public StudentListAdapter(Activity context, List<Student> studentList) {
        this.mContext = context;
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
        holder.name_tv.setText(student.getName());
        if (studentList.get(position).getClassNo().equals("植树")) {
            holder.img.setImageResource(R.mipmap.img_1);
        } else if (studentList.get(position).getClassNo().equals("爱护环境 关爱地球")) {
            holder.img.setImageResource(R.mipmap.img_2);
        } else if (studentList.get(position).getClassNo().equals("敬老爱老 全民行动")) {
            holder.img.setImageResource(R.mipmap.img_3);
        } else if (studentList.get(position).getClassNo().equals("其他")) {
            holder.img.setImageResource(R.mipmap.img_4);
        }


        holder.item.setOnClickListener(view -> {
            Intent intent = new Intent(mContext.getApplicationContext(), UserInfoActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bean", student);
            intent.putExtras(bundle);
            mContext.startActivityForResult(intent, 2000);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }


    static class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView name_tv;
        private ImageView img;
        private RelativeLayout item;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            name_tv = itemView.findViewById(R.id.name_tv);
            img = itemView.findViewById(R.id.img);
            item = itemView.findViewById(R.id.item);
        }
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

}

