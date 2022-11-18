package com.children.greendaoapp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.utils.DaoUtilsStore;

/**
 * 学生信息展示页  可以编辑 删除
 */
public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button modify, delete;
    private EditText name_et, study_no_et, class_et, math_et, chinese_et, english_et, sum_et, rank_et;
    private Student user;
    private long id;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        //初始化各个view
        modify = findViewById(R.id.modify);
        delete = findViewById(R.id.delete);
        name_et = findViewById(R.id.name_et);
        study_no_et = findViewById(R.id.study_no_et);
        class_et = findViewById(R.id.class_et);
        math_et = findViewById(R.id.math_et);
        chinese_et = findViewById(R.id.chinese_et);
        english_et = findViewById(R.id.english_et);
        sum_et = findViewById(R.id.sum_et);
        rank_et = findViewById(R.id.rank_et);
        back = findViewById(R.id.back);


        user = (Student) getIntent().getExtras().getSerializable("bean");
        id = user.getId();

        if (user != null) {
            if (!TextUtils.isEmpty(user.getName())) {
                name_et.setText(user.getName());
            }
            if (!TextUtils.isEmpty(user.getStudyNo())) {
                study_no_et.setText(user.getStudyNo());
            }

            if (!TextUtils.isEmpty(user.getClassNo())) {
                class_et.setText(user.getClassNo());
            }

            if (!TextUtils.isEmpty(user.getClassNo())) {
                math_et.setText(user.getClassNo());
            }
            if (!TextUtils.isEmpty(user.getChinese())) {
                chinese_et.setText(user.getChinese());
            }
            if (!TextUtils.isEmpty(user.getEnglish())) {
                english_et.setText(user.getEnglish());
            }
            if (!TextUtils.isEmpty(user.getSum())) {
                sum_et.setText(user.getSum());
            }
            if (user.getRank() != 0) {
                rank_et.setText(String.valueOf(user.getRank()));
            }

        }

        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify:
                //修改学生信息
                Student user = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(id);
                user.setName(name_et.getText().toString());
                user.setStudyNo(study_no_et.getText().toString());
                user.setClassNo(class_et.getText().toString());
                user.setChinese(chinese_et.getText().toString());
                user.setEnglish(english_et.getText().toString());
                user.setMath(math_et.getText().toString());
                user.setSum(sum_et.getText().toString());
                user.setRank(Integer.parseInt(rank_et.getText().toString()));

                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().update(user)) {
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete:
                //删除学生信息
                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().delete(DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(id))) {
                    Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    // case R.id.add_single:
    //                Student student2 = new Student();
    //                student2.setName("王旭成2");
    //                student2.setAge(30);
    //                student2.setSchoolName("清华大学");
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
    //                    Toast.makeText(getApplicationContext(), "单条插入成功", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }else {
    //                    Toast.makeText(getApplicationContext(), "学号"+student2.getStudentNo() + "已存在 不可插入", Toast.LENGTH_SHORT).show();
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
    //                        student.setSex("男");
    //                    } else {
    //                        student.setSex("女");
    //                    }
    //                    student.setAddress(RandomValue.getRoad());
    //                    student.setGrade(String.valueOf(age % 10) + "年纪");
    //                    student.setSchoolName("清华大学");
    //                    students.add(student);
    //                }
    //                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().insertMultiple(students)){
    //                    Toast.makeText(getApplicationContext(), "批量插入成功", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }
    //                break;
    //            case R.id.delete:
    //                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().delete(DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(72))){
    //                    Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }else {
    //                    Toast.makeText(getApplicationContext(), "删除失败", Toast.LENGTH_SHORT).show();
    //                }
    //                break;
    //            case R.id.modify:
    //                Student student4 = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryById(1);
    //                student4.setName("王旭成333");
    //                if (DaoUtilsStore.getInstance().getmStudentDaoUtils().update(student4)){
    //                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
    //                    notifyList();
    //                }else {
    //                    Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
    //                }
    //                break;
    //            case R.id.search:
    //               notifyList();
    //                break;
}