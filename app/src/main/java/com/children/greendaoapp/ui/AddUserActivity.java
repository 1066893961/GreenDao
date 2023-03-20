package com.children.greendaoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.children.greendaoapp.R;
import com.children.greendaoapp.adapter.StudentListAdapter;
import com.children.greendaoapp.entity.Student;
import com.children.greendaoapp.entity.User;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.DaoUtilsStore2;
import com.children.greendaoapp.utils.RandomValue;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private EditText title, content;
    private ImageView back;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add = findViewById(R.id.add);
        title = findViewById(R.id.title_et);
        content = findViewById(R.id.content_et);
        back = findViewById(R.id.back);
        spinner = findViewById(R.id.spinner);

        add.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add:
                Student user = new Student();
                user.setName(title.getText().toString());
                user.setStudyNo(content.getText().toString());
                user.setClassNo(spinner.getSelectedItem().toString());

                List<Student> sLists = DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll();
                boolean canInsert = true;
//                for (int i = 0; i < sLists.size(); i++) {
//                    if (sLists.get(i).getStudyNo().equals(user.getStudyNo())) {
//                        canInsert = false;
//                    }
//                }
                if (canInsert) {
                    DaoUtilsStore.getInstance().getmStudentDaoUtils().insert(user);
                    Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "学号" + user.getStudyNo() + "已存在 不可重复添加", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    // public byte[] UriToByte(Uri uri){
    //    Bitmap bitmap1 = null;
    //    try {
    //        bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //
    //    int size = bitmap1.getWidth() * bitmap1.getHeight() * 4;
    //    ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
    //    bitmap1.compress(Bitmap.CompressFormat.PNG, 100, baos);
    //    byte[] imagedata1 = baos.toByteArray();
    //
    //    return  imagedata1;
    //}
}