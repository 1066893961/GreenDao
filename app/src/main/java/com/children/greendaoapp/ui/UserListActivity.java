package com.children.greendaoapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.children.greendaoapp.R;
import com.children.greendaoapp.adapter.StudentListAdapter;
import com.children.greendaoapp.utils.DaoUtilsStore2;

public class UserListActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView add;
    private RecyclerView rv;
    private StudentListAdapter studentListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        add = findViewById(R.id.add);
        rv = findViewById(R.id.rv);

        studentListAdapter = new StudentListAdapter(UserListActivity.this, DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(studentListAdapter);

        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                Intent intent = new Intent();
                intent.setClass(UserListActivity.this, AddUserActivity.class);
                startActivityForResult(intent, 1000);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    private void notifyList() {
        studentListAdapter.setStudentList(DaoUtilsStore2.getInstance().getmUserDaoUtils().queryAll());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1000:
            case 2000:
                if (resultCode == Activity.RESULT_OK) {
                    notifyList();
                }
                break;
        }
    }
}