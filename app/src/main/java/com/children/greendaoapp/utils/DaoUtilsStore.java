package com.children.greendaoapp.utils;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.gen.DaoManager;
import com.children.greendaoapp.gen.StudentDao;

/**
 * @Description: 初始化、存放及获取DaoUtils
 * @Author: leo.li
 * @CreateDate: 2022/3/24 14:24
 */
public class DaoUtilsStore {
    private volatile static DaoUtilsStore instance = new DaoUtilsStore();

    private CommonDaoUtils<Student> mStudentDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        DaoManager mManager = DaoManager.getInstance();
        StudentDao studentDao = mManager.getDaoSession().getStudentDao();
        mStudentDaoUtils = new CommonDaoUtils<>(Student.class, studentDao);
    }

    public CommonDaoUtils<Student> getmStudentDaoUtils() {
        return mStudentDaoUtils;
    }

}