package com.children.greendaoapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.username);
        psd = findViewById(R.id.password);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);

        regist.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(view -> {
            User user = new User();
            user.setStudyNo(name.getText().toString());
            user.setPassword(psd.getText().toString());
            //查询所有注册账号列表
            List<User> sLists = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll();
            //是否能登录
            boolean canInsert = false;
            for (int i = 0; i < sLists.size(); i++) {
                if (sLists.get(i).getStudyNo().equals(user.getStudyNo()) && sLists.get(i).getPassword().equals(sLists.get(i).getPassword())) {
                    canInsert = true;
                }
            }
            if (canInsert) {
//                DaoUtilsStore2.getInstance().getmUserDaoUtils().insert(user);
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "学号或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
            }

        });


    }

}