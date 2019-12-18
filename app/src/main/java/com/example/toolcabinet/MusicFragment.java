package com.example.toolcabinet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MusicFragment extends Fragment {

    private OnDataCallbackListener listener;

    public MusicFragment() {

    }

   public static MusicFragment newInstance(){
        return new MusicFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music,container,false);
        Context context = getActivity();
        if (context instanceof OnDataCallbackListener) {
            listener = (OnDataCallbackListener) context;
            // 接口对象是activity的接口上下文
            listener.setActivityTitle(getResources().getString(R.string.btn_music));
        }
        initData();
        initListView(view);
        return view;
    }
    private ListView lvMusic;
    private void initListView(View view){
        lvMusic = view.findViewById(R.id.lv_music);
        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(),//上下文
                headerMap,//数据
                R.layout.item_music,//ListView的Item的layout布局
                new String[]{"img","name1","name2"},//hashmap对应的key
                new int[] {R.id.iv_header,R.id.tv_name1,R.id.tv_name2}//item的layout布局中的控件id
        );
        lvMusic.setAdapter(adapter);
        lvMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> data = (HashMap<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(), "Music{name='"+(String) data.get("name1")+"',singer='"+(String) data.get("name2")+"'}",Toast.LENGTH_LONG).show();
            }
        });
    }

    private List<Music> musics;
    private List<HashMap<String,Object>> headerMap;
    private void initData(){
        musics = new ArrayList<>();
        musics.add(new Music(R.mipmap.music3,"心如止水","潘悦晨"));
        musics.add(new Music(R.mipmap.music4,"追光者","岑宁儿"));
        musics.add(new Music(R.mipmap.music5,"光年之外","邓紫棋"));
        musics.add(new Music(R.mipmap.music6,"最美的期待","周笔畅"));

        headerMap = new ArrayList<>();
        for (Music music:musics) {
            HashMap<String,Object> map1 = new HashMap<>();
            map1.put("img",music.getImg());
            map1.put("name1",music.getName1());
            map1.put("name2",music.getName2());
            headerMap.add(map1);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
