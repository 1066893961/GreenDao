package com.children.greendaoapp.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.children.greendaoapp.gen.DaoSession;
import com.children.greendaoapp.gen.IdCardDao;
import com.children.greendaoapp.gen.StudentDao;

import java.io.Serializable;

/**
 *
 */
@Entity
public class Student implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id(autoincrement = true)
    Long id;
    @Unique
    String phone;//手机号
    String name;//姓名
    String password;
    String classNo;//班级
    String studyNo;//学号
    String math;//数学成绩
    String chinese;//语文成绩
    String english;//英语成绩
    String sum;//总成绩
    int rank;//排名
    @Generated(hash = 103863350)
    public Student(Long id, String phone, String name, String password,
            String classNo, String studyNo, String math, String chinese,
            String english, String sum, int rank) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.classNo = classNo;
        this.studyNo = studyNo;
        this.math = math;
        this.chinese = chinese;
        this.english = english;
        this.sum = sum;
        this.rank = rank;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getClassNo() {
        return this.classNo;
    }
    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
    public String getStudyNo() {
        return this.studyNo;
    }
    public void setStudyNo(String studyNo) {
        this.studyNo = studyNo;
    }
    public String getMath() {
        return this.math;
    }
    public void setMath(String math) {
        this.math = math;
    }
    public String getChinese() {
        return this.chinese;
    }
    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
    public String getEnglish() {
        return this.english;
    }
    public void setEnglish(String english) {
        this.english = english;
    }
    public String getSum() {
        return this.sum;
    }
    public void setSum(String sum) {
        this.sum = sum;
    }
    public int getRank() {
        return this.rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

}
