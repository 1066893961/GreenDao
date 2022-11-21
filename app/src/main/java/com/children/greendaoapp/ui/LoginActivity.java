package com.children.greendaoapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.utils.DaoUtilsStore2;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText name, psd;
    private Button login, regist;
    private long mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.username);
        psd = findViewById(R.id.password);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);

        //先获取对应的Share
        SharedPreferences sp2 = this.getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        //根据key取出对应的值
        String phone = sp2.getString("phone", "");//第二个参数为默认值，即当从Share中取不到时，返回这个值

        /**
         * 判断是否已经登录  已经登录直接跳转首页
         */
        if (!phone.equals("")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


        //跳转注册页面
        regist.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(view -> {
            User user = new User();
            user.setPhone(name.getText().toString());
            user.setPassword(psd.getText().toString());
            //查询所有注册账号列表
            List<User> sLists = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll();
            //是否能登录
            boolean canInsert = false;
            //判断 是否可插入
            for (int i = 0; i < sLists.size(); i++) {
                if (sLists.get(i).getPhone().equals(user.getPhone()) && sLists.get(i).getPassword().equals(user.getPassword())) {
                    canInsert = true;
                    mId = sLists.get(i).getId();
                }
            }
            if (canInsert) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
                //获取Editor对象，这个对象用于写入，可理解为编辑
                SharedPreferences.Editor editor = sp.edit();
                //Editor对象有几个方法需要注：clear()，commit()，putXXX(),clear()为清空Share文件中的内容，
                //commit()为提交，editor在put值以后，需要调用commit方法才能被真正写入到Share文件中
                editor.putString("phone", name.getText().toString()).commit();
                editor.putLong("id", mId).commit();

//                DaoUtilsStore2.getInstance().getmUserDaoUtils().insert(user);
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "手机号或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }

        });


    }

}