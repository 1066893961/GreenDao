package com.children.greendaoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.DaoUtilsStore2;

import java.util.List;

public class RegistActivity extends AppCompatActivity {

    private EditText name, psd;
    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        name = findViewById(R.id.username);
        psd = findViewById(R.id.password);
        regist = findViewById(R.id.regist);

        regist.setOnClickListener(view -> {
            User user = new User();
            user.setStudyNo(name.getText().toString());
            user.setPassword(psd.getText().toString());
            List<User> sLists = DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll();
            boolean canInsert = true;
            for (int i = 0; i < sLists.size(); i++) {
                if (sLists.get(i).getStudyNo().equals(user.getStudyNo())) {
                    canInsert = false;
                }
            }
            if (canInsert) {
                DaoUtilsStore2.getInstance().getmUserDaoUtils().insert(user);
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(getApplicationContext(), "学号"+user.getStudyNo() + "已存在 不可重复注册", Toast.LENGTH_SHORT).show();
            }
        });
    }

}