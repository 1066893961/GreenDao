package com.children.greendaoapp.ui;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.children.greendaoapp.R;
import com.children.greendaoapp.adapter.StudentListAdapter;
import com.children.greendaoapp.utils.DaoUtilsStore;
import com.children.greendaoapp.utils.DaoUtilsStore2;

/**
 * 首页  学生信息列表
 */

public class HomePageFragment extends Fragment {
    private View mView;

    private TextView add;
    private TextView empty_tv;
    private RecyclerView rv;
    private StudentListAdapter studentListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        add = mView.findViewById(R.id.add);
        empty_tv = mView.findViewById(R.id.empty_tv);
        rv = mView.findViewById(R.id.rv);

        if (DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll().size() == 0){
            empty_tv.setVisibility(View.VISIBLE);
        }else{
            empty_tv.setVisibility(View.GONE);
        }
        studentListAdapter = new StudentListAdapter(getActivity(), DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//            rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        rv.setAdapter(studentListAdapter);



        add.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(getActivity(), AddUserActivity.class);
            getActivity().startActivityForResult(intent, 1000);
        });

        return mView;
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case 1000:
//                case 2000:
//                    notifyList();
//                    break;
//            }
//        }
//    }

    public  void notifyChannelChange() {
        if (DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll().size() == 0){
            empty_tv.setVisibility(View.VISIBLE);
        }else {
            empty_tv.setVisibility(View.GONE);
        }
        studentListAdapter.setStudentList(DaoUtilsStore.getInstance().getmStudentDaoUtils().queryAll());
    }

}
