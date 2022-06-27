package com.children.greendaoapp.utils;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.gen.DaoManager;
import com.children.greendaoapp.gen.StudentDao;
import com.children.greendaoapp.gen.UserDao;

/**
 * @Description: 初始化、存放及获取DaoUtils
 * @Author: leo.li
 * @CreateDate: 2022/3/24 14:24
 */
public class DaoUtilsStore2 {
    private volatile static DaoUtilsStore2 instance = new DaoUtilsStore2();

    private CommonDaoUtils<User> mUserDaoUtils;

    public static DaoUtilsStore2 getInstance() {
        return instance;
    }

    private DaoUtilsStore2() {
        DaoManager mManager = DaoManager.getInstance();
        UserDao userDao = mManager.getDaoSession().getUserDao();
        mUserDaoUtils = new CommonDaoUtils<>(User.class, userDao);
    }

    public CommonDaoUtils<User> getmUserDaoUtils() {
        return mUserDaoUtils;
    }

}