package com.example.toolcabinet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,OnDataCallbackListener {

    private SparseArray<Fragment> fragments;
    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化Fragment
        initFragment();
        //初始化RadioGroup
        initView();
    }
    private void initView() {
        //获取RadioGroup对象，设置监听器
        group = findViewById(R.id.btn_group);
        group.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        replaceFragment(fragments.get(checkedId));//checkedId是RadioButton的id
    }

    private void initFragment() {
        //将创建的fragment放入集合中
        fragments = new SparseArray<>();
        fragments.put(R.id.btn_music,MusicFragment.newInstance());//RadioButton的id
        fragments.put(R.id.btn_count,CountFragment.newInstance());
        fragments.put(R.id.btn_note, NoteFragment.newInstance());
        //初始化、将页面定位为第1个fragment
        replaceFragment(fragments.get(R.id.btn_music));
    }

    //功能：对多个fragment进行管理和替换
    private void replaceFragment(Fragment fragment) {
        //获取FragmentManager对象
        FragmentManager manager = getSupportFragmentManager();
        //开始FragmentTransaction的事务管理
        FragmentTransaction ft = manager.beginTransaction();
        //替换为新的Fragment
        ft.replace(R.id.content_layout, fragment);//activity中的LinearLayout的id
        //事务提交
        ft.commit();
    }
    @Override
    public void setActivityTitle(String data) {
        setTitle(data);
    }
}
