package com.bwei.hanleixin1502h0213;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import clean.DataCleanManager;
import fragment.MyFragment;
import adapter.MyPageradapter;

public class MainActivity extends AppCompatActivity/* implements View.OnClickListener */{

    private Button toutiao;//头条
    private Button shehui;//社会
    private Button guoji;//国际
    private Button guonei;//国内
    private Button yule;//娱乐
    private Button tiyu;//体育
    private Button junshi;//军事
    private Button keji;//科技
    private Button caijing;//财经
    private Button shishang;//时尚

    private FrameLayout frameLayout;
    private Button deletecache;

    private TextView text;
    private TabLayout tabLayout;

    private String[] titles=new String[]{"头条","社会","国际","国内","娱乐","体育","军事","科技","财经","时尚"};
    private ViewPager viewPager;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取地址的方法
        findId();

        //
        initview();

        //
        initData();

        try {
            String totalCacheSize = DataCleanManager.getTotalCacheSize(this);
            text.setText(totalCacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        deletecache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataCleanManager.cleanInternalCache(MainActivity.this);
                DataCleanManager.cleanDatabases(MainActivity.this);
            }
        });
    }
    //获取ID
    private void findId() {
        deletecache = (Button) findViewById(R.id.deletecache);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        text = (TextView) findViewById(R.id.textview);
    }

    public void initview() {
        list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("num", i);
            MyFragment myFragment = new MyFragment();
            myFragment.setArguments(bundle);
            list.add(myFragment);
        }
    }


    public void initData() {
        MyPageradapter myPageradapter = new MyPageradapter(getSupportFragmentManager(), list, titles);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(myPageradapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);//模式为滚动
    }

    public void setCheck(){
        toutiao.setSelected(false);
        shehui.setSelected(false);
    }
}
