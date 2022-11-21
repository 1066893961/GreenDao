package com.children.greendaoapp.ui;

import static com.children.greendaoapp.MyApplication.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.event.LogoutEvent;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.DaoUtilsStore2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * 修改密码
 */
public class ModifyPwdActivity extends AppCompatActivity implements View.OnClickListener {
    private Button modify;
    private EditText name_et, study_no_et, class_et;
    private ImageView back;
    private long mId;
    private User mUser;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Object myEvent) {
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);
        //初始化各个view
        modify = findViewById(R.id.modify);
        name_et = findViewById(R.id.name_et);
        study_no_et = findViewById(R.id.study_no_et);
        class_et = findViewById(R.id.class_et);
        back = findViewById(R.id.back);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }


        //先获取对应的Share
        SharedPreferences sp = getContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        mId = sp.getLong("id", 0);
        mUser = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryById(mId);
        name_et.setText(mUser.getPhone());

        modify.setOnClickListener(this);
        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.modify:
                //查询所有注册账号列表
                List<User> sLists = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll();
                //是否能登录
                boolean canInsert = false;
                //判断 是否可插入
                for (int i = 0; i < sLists.size(); i++) {
                    if (sLists.get(i).getPhone().equals(mUser.getPhone()) && sLists.get(i).getPassword().equals(mUser.getPassword())) {
                        canInsert = true;
                    }
                }
                if (canInsert) {
                    //修改密码
                    mUser.setPhone(name_et.getText().toString());
                    mUser.setPassword(class_et.getText().toString());
                    if (DaoUtilsStore2.getInstance().getmUserDaoUtils().update(mUser)) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                        logolOut();
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "修改失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "手机号或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                finish();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    /**
     * 退出登录  清空缓存的登录账号  发退出登录广播
     */
    private void logolOut() {
        EventBus.getDefault().post(new LogoutEvent());
        SharedPreferences sp = getContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        //获取Editor对象，这个对象用于写入，可理解为编辑
        SharedPreferences.Editor editor = sp.edit();
        //Editor对象有几个方法需要注：clear()，commit()，putXXX(),clear()为清空Share文件中的内容，
        //commit()为提交，editor在put值以后，需要调用commit方法才能被真正写入到Share文件中
        editor.putString("phone", "").commit();
        editor.putLong("id", 0).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}