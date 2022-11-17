package com.children.greendaoapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.children.greendaoapp.R;

/**
 * 首页  学生信息列表
 */

public class HomePageFragment extends Fragment {
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        bindData();
        initListener();
        return mView;
    }

    public void initListener() {

    }

    public void bindData() {

    }

//    private class AboutAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return data.length;
//        }
//
//        @Override
//        public String getItem(int position) {
//            return data[position];
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_about, null);
//            TextView tv_about = (TextView) convertView.findViewById(R.id.tv_about);
//            tv_about.setText(data[position]);
//            return convertView;
//        }
//    }
}
