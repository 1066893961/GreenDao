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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.children.greendaoapp.R;
import com.children.greendaoapp.event.LogoutEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 我 页面模块
 */

public class MyFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private TextView name, size_tv;
    private Button logout;
    private RelativeLayout account_rel, info_rel, update_rel, cache_rel, agree_rel, change_rel;
    private LinearLayout all_ll;
    private boolean flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, null);

        name = mView.findViewById(R.id.name);
        size_tv = mView.findViewById(R.id.size_tv);
        logout = mView.findViewById(R.id.logout);
        account_rel = mView.findViewById(R.id.account_rel);
        info_rel = mView.findViewById(R.id.info_rel);
        update_rel = mView.findViewById(R.id.update_rel);
        cache_rel = mView.findViewById(R.id.cache_rel);
        agree_rel = mView.findViewById(R.id.agree_rel);
        change_rel = mView.findViewById(R.id.change_rel);
        all_ll = mView.findViewById(R.id.all_ll);

        //先获取对应的Share
        SharedPreferences sp = getContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        //根据key取出对应的值
        String phone = sp.getString("phone", "");//第二个参数为默认值，即当从Share中取不到时，返回这个值
        name.setText("登录账号：" + phone);

        logout.setOnClickListener(view -> {
            logolOut();
            Intent intent = new Intent();
            intent.setClass(getActivity(), LoginActivity.class);
            startActivity(intent);
        });
        account_rel.setOnClickListener(this);
        info_rel.setOnClickListener(this);
        update_rel.setOnClickListener(this);
        cache_rel.setOnClickListener(this);
        agree_rel.setOnClickListener(this);
        change_rel.setOnClickListener(this);
        return mView;
    }

    /**
     * 退出登录  清空缓存的登录账号  发退出登录广播
     */
    private void logolOut(){
        EventBus.getDefault().post(new LogoutEvent());
        SharedPreferences sp = getContext().getSharedPreferences("MY_SHARE", Context.MODE_PRIVATE);
        //获取Editor对象，这个对象用于写入，可理解为编辑
        SharedPreferences.Editor editor = sp.edit();
        //Editor对象有几个方法需要注：clear()，commit()，putXXX(),clear()为清空Share文件中的内容，
        //commit()为提交，editor在put值以后，需要调用commit方法才能被真正写入到Share文件中
        editor.putString("phone", "").commit();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.account_rel:
                //账号管理

                break;
            case R.id.info_rel:
                //完善个人资料

                break;
            case R.id.update_rel:
                //版本更新
                Toast.makeText(getContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cache_rel:
                //缓存清理
                Toast.makeText(getContext(), "清理完成", Toast.LENGTH_SHORT).show();
                size_tv.setText("清理缓存（0kb）");
                break;
            case R.id.agree_rel:
                Toast.makeText(getContext(), "已发送", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_rel:
                if (!flag) {
                    all_ll.setBackgroundColor(getActivity().getResources().getColor(R.color.grey));
                    flag = true;
                } else {
                    all_ll.setBackgroundColor(getActivity().getResources().getColor(R.color.color_FFF1EBEB));
                    flag = false;
                }
                break;
        }
    }
}
