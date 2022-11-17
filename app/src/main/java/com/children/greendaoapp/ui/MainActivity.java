package com.children.greendaoapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import com.children.greendaoapp.R;
import com.children.greendaoapp.entity.BottomTab;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();


    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<BottomTab> mBottomTabs = new ArrayList<>(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();

    }

    // 初始化底部标签栏
    private void initTab() {
        // 首页标签
        BottomTab bottomTab_news = new BottomTab(HomePageFragment.class,R.string.news_fragment,R.drawable.select_icon_news);
        // 我 标签
        BottomTab bottomTab_about = new BottomTab(MyFragment.class,R.string.about_fragment,R.drawable.select_icon_about);


        mBottomTabs.add(bottomTab_news);
        mBottomTabs.add(bottomTab_about);


        // 设置FragmentTab
        mInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);


        for (BottomTab bottomTab : mBottomTabs){

            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(bottomTab.getTitle()));

            tabSpec.setIndicator(buildIndicator(bottomTab));

            mTabHost.addTab(tabSpec, bottomTab.getFragment(),null);

        }

        mTabHost.setOnTabChangedListener(tabId -> {

        });

        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTab(0);

    }

    // 设置底部tab的图片和文字
    private View buildIndicator(BottomTab bottomTab){

        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);

        img.setBackgroundResource(bottomTab.getIcon());
        text.setText(bottomTab.getTitle());

        return  view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String tag =  mTabHost.getCurrentTabTag();
        if (resultCode == 789){
//            Bundle bundle = data.getExtras();
//            int tabPosition = bundle.getInt("NewTabPostion");
//            NewsFragment newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag(tag);
//            newsFragment.setCurrentChannel(tabPosition);
//            newsFragment.notifyChannelChange();
        }
    }
}
