package com.children.greendaoapp.ui;

import static com.children.greendaoapp.MyApplication.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.event.LogoutEvent;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.DaoUtilsStore2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 登录账号 个人信息管理页面
 */
public class InfoManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button modify;
    private EditText study_no_et, class_et, math_et;
    private TextView name_et;
    private ImageView back;
    private long mId;
    private User mUser;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object myEvent) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_manager);
        //初始化各个view
        modify = findViewById(R.id.modify);
        name_et = findViewById(R.id.name_et);
        study_no_et = findViewById(R.id.study_no_et);
        class_et = findViewById(R.id.class_et);
        math_et = findViewById(R.id.math_et);
        back = findViewById(R.id.back);


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        //先获取对应的Share
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        mId = sp.getLong("id", 0);

        mUser = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryById(mId);
        name_et.setText(mUser.getPhone());
        study_no_et.setText(mUser.getSex());
        class_et.setText(mUser.getAge());
        math_et.setText(mUser.getAddress());

        modify.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify:
                //修改学生信息
                mUser.setPhone(name_et.getText().toString());
                mUser.setSex(study_no_et.getText().toString());
                mUser.setAge(class_et.getText().toString());
                mUser.setAddress(math_et.getText().toString());

                if (DaoUtilsStore2.getInstance().getmUserDaoUtils().update(mUser)) {
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}