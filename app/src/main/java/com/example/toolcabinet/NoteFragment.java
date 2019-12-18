package com.example.toolcabinet;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NoteFragment extends Fragment {

    private OnDataCallbackListener listener;
    private View view;
    private ListView lvNote;

    public NoteFragment() {
    }

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_note, container, false);
        Context context = getActivity();
        if (context instanceof OnDataCallbackListener) {
            listener = (OnDataCallbackListener) context;
            // 接口对象是activity的接口上下文
            listener.setActivityTitle(getResources().getString(R.string.btn_book));
        }
        //获取数据
        initData();
        //初始化控件
        lvNote = view.findViewById(R.id.lv_note);
        //设置Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),//上下文
                android.R.layout.simple_list_item_1,//布局
                notes//数据
        );
        //给ListView设置Adapter
        lvNote.setAdapter(adapter);
        //给Item设置监听事件
        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = (String) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(),data,Toast.LENGTH_LONG).show();
            }
        });
        lvNote.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final String data = (String) parent.getItemAtPosition(position);
                new AlertDialog.Builder(getActivity())
                        .setTitle("删除记录")
                        .setMessage("确认删除:"+data+"？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(data);
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                return true;
            }
        });
        return view;
    }

    private List<String> notes;
    private void initData() {
        notes = new ArrayList<>();
        notes.add("Java作业");
        notes.add("英语单词");
        notes.add("周末电影");
        notes.add("复习Android");
        notes.add("数学公式");
    }



}
