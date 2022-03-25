package com.children.greendaoapp;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.children.greendaoapp.gen.DaoManager;
import com.children.greendaoapp.gen.DaoMaster;
import com.children.greendaoapp.gen.DaoSession;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2022/3/24 11:38
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        DaoManager.getInstance().init(this);
    }

    public static Context getContext() {
        return mContext;
    }
}
