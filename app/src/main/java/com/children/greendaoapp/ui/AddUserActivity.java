package com.children.greendaoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.children.greendaoapp.R;
import com.children.greendaoapp.adapter.StudentListAdapter;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.DaoUtilsStore2;
import com.children.greendaoapp.utils.RandomValue;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private EditText name_et, study_no_et, class_et, math_et, chinese_et, english_et, sum_et, rank_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add = findViewById(R.id.add);
        name_et = findViewById(R.id.name_et);
        study_no_et = findViewById(R.id.study_no_et);
        class_et = findViewById(R.id.class_et);
        math_et = findViewById(R.id.math_et);
        chinese_et = findViewById(R.id.chinese_et);
        english_et = findViewById(R.id.english_et);
        sum_et = findViewById(R.id.sum_et);
        rank_et = findViewById(R.id.rank_et);

        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                User user = new User();
                user.setName(name_et.getText().toString());
                user.setStudyNo(study_no_et.getText().toString());
                user.setClassNo(class_et.getText().toString());
                user.setChinese(chinese_et.getText().toString());
                user.setEnglish(english_et.getText().toString());
                user.setMath(math_et.getText().toString());
                user.setSum(sum_et.getText().toString());
                user.setRank(Integer.parseInt(rank_et.getText().toString()));

                List<User> sLists = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll();
                boolean canInsert = true;
                for (int i = 0; i < sLists.size(); i++) {
                    if (sLists.get(i).getStudyNo().equals(user.getStudyNo())) {
                        canInsert = false;
                    }
                }
                if (canInsert) {
                    DaoUtilsStore2.getInstance().getmUserDaoUtils().insert(user);
                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "??????" + user.getStudyNo() + "????????? ??????????????????", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    // case R.id.add_single:
    //                Student student2 = new Student();
    //                student2.setName("?????????2");
    //                student2.setAge(30);
    //                student2.setSchoolName("????????????");
    //                student2.setStudentNo(113);
    //                List<Student> sLists = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll();
    //                boolean canInsert = true;
    //                for (int i = 0; i < sLists.size(); i++) {
    //                    if (sLists.get(i).getStudentNo() == student2.getStudentNo()) {
    //                        canInsert = false;
    //                    }
    //                }
    //                if (canInsert) {
    //                    DaoUtilsStore.getInstance().getmStudentDaoUtils().insert(student2);
    //                    Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }else {
    //                    Toast.makeText(getApplicationContext(), "??????"+student2.getStudentNo() + "????????? ????????????", Toast.LENGTH_SHORT).show();
    //                }
    //                break;
    //            case R.id.add:
    //                List<Student> students = new ArrayList<>();
    //                for (int i = 0; i < 10; i++) {
    //                    Student student = new Student();
    //                    student.setStudentNo(i);
    //                    int age = RandomValue.getNum(0, 10) + 10;
    //                    student.setAge(age);
    //                    student.setTelPhone(RandomValue.getTel());
    //                    String chineseName = RandomValue.getChineseName();
    //                    student.setName(chineseName);
    //                    if (i % 2 == 0) {
    //                        student.setSex("???");
    //                    } else {
    //                        student.setSex("???");
    //                    }
    //                    student.setAddress(RandomValue.getRoad());
    //                    student.setGrade(String.valueOf(age % 10) + "??????");
    //                    student.setSchoolName("????????????");
    //                    students.add(student);
    //                }
    //                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().insertMultiple(students)){
    //                    Toast.makeText(getApplicationContext(), "??????????????????", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }
    //                break;
    //            case R.id.delete:
    //                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().delete(DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(72))){
    //                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }else {
    //                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
    //                }
    //                break;
    //            case R.id.modify:
    //                Student student4 = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(1);
    //                student4.setName("?????????333");
    //                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().update(student4)){
    //                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }else {
    //                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
    //                }
    //                break;
    //            case R.id.search:
    //               notifyList();
    //                break;
}