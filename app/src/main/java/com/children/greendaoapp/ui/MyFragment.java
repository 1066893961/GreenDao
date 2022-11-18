package com.children.greendaoapp.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.children.greendaoapp.R;

/**
 * 我 页面模块
 */

public class MyFragment extends Fragment {
    private View mView;
    private TextView name;
    private Button logout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);

        name = mView.findViewById(R.id.name);
        logout = mView.findViewById(R.id.logout);

        //先获取对应的Share
        SharedPreferences sp = getContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        //根据key取出对应的值
        String phone = sp.getString("phone", "");//第二个参数为默认值，即当从Share中取不到时，返回这个值
        name.setText(phone);

        logout.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        return mView;
    }


}
