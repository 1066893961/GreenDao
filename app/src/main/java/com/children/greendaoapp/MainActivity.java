package com.children.greendaoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.children.greendaoapp.adapter.StudentListAdapter;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.RandomValue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add, delete, modify, search, add_single;
    private RecyclerView rv;
    private StudentListAdapter studentListAdapter;
    private List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.add);
        delete = findViewById(R.id.delete);
        modify = findViewById(R.id.modify);
        search = findViewById(R.id.search);
        add_single = findViewById(R.id.add_single);
        rv = findViewById(R.id.rv);


        studentListAdapter = new StudentListAdapter(studentList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(studentListAdapter);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);
        modify.setOnClickListener(this);
        search.setOnClickListener(this);
        add_single.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_single:
                Student student2 = new Student();
                student2.setName("王旭成2");
                student2.setAge(30);
                student2.setSchoolName("清华大学");
                student2.setStudentNo(113);
                List<Student> sLists = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll();
                boolean canInsert = true;
                for (int i = 0; i < sLists.size(); i++) {
                    if (sLists.get(i).getStudentNo() == student2.getStudentNo()) {
                        canInsert = false;
                    }
                }
                if (canInsert) {
                    DaoUtilsStore.getInstance().getmStudentDaoUtils().insert(student2);
                    Toast.makeText(getApplicationContext(), "单条插入成功", Toast.LENGTH_SHORT).show();
                    notifyList();
                }else {
                    Toast.makeText(getApplicationContext(), "学号"+student2.getStudentNo() + "已存在 不可插入", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.add:
                List<Student> students = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    Student student = new Student();
                    student.setStudentNo(i);
                    int age = RandomValue.getNum(0, 10) + 10;
                    student.setAge(age);
                    student.setTelPhone(RandomValue.getTel());
                    String chineseName = RandomValue.getChineseName();
                    student.setName(chineseName);
                    if (i % 2 == 0) {
                        student.setSex("男");
                    } else {
                        student.setSex("女");
                    }
                    student.setAddress(RandomValue.getRoad());
                    student.setGrade(String.valueOf(age % 10) + "年纪");
                    student.setSchoolName("清华大学");
                    students.add(student);
                }
                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().insertMultiple(students)){
                    Toast.makeText(getApplicationContext(), "批量插入成功", Toast.LENGTH_SHORT).show();
                    notifyList();
                }
                break;
            case R.id.delete:
                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().delete(DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(72))){
                    Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    notifyList();
                }else {
                    Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.modify:
                Student student4 = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(1);
                student4.setName("王旭成333");
                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().update(student4)){
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    notifyList();
                }else {
                    Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.search:
               notifyList();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void notifyList(){
        studentListAdapter.setStudentList(DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll());
    }
}