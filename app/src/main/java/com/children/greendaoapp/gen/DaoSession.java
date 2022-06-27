package com.children.greendaoapp.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.children.greendaoapp.entity.IdCard;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.entity.User;

import com.children.greendaoapp.gen.IdCardDao;
import com.children.greendaoapp.gen.StudentDao;
import com.children.greendaoapp.gen.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig idCardDaoConfig;
    private final DaoConfig studentDaoConfig;
    private final DaoConfig userDaoConfig;

    private final IdCardDao idCardDao;
    private final StudentDao studentDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        idCardDaoConfig = daoConfigMap.get(IdCardDao.class).clone();
        idCardDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        idCardDao = new IdCardDao(idCardDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(IdCard.class, idCardDao);
        registerDao(Student.class, studentDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        idCardDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public IdCardDao getIdCardDao() {
        return idCardDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}
