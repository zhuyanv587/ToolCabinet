package com.example.toolcabinet;


import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;


public class CountFragment extends Fragment implements View.OnClickListener {

    private OnDataCallbackListener listener;
    private Button c,del,slant,ride,reduce,add,drop,zero,one,two,three,four,five,six,seven,eight,nine,amount;
    private TextView count,result;
    String count1;
    boolean isResult;//判断是否按下等号

    public CountFragment() {

    }

    public static CountFragment newInstance(){
        return new CountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count, container, false);
        Context context = getActivity();
        if (context instanceof OnDataCallbackListener) {
            listener = (OnDataCallbackListener) context;
            // 接口对象是activity的接口上下文
            listener.setActivityTitle(getResources().getString(R.string.btn_count));
        }
        c = view.findViewById(R.id.c);
        del = view.findViewById(R.id.del);
        slant = view.findViewById(R.id.slant);
        ride = view.findViewById(R.id.ride);
        reduce = view.findViewById(R.id.reduce);
        add = view.findViewById(R.id.add);
        drop = view.findViewById(R.id.drop);
        zero = view.findViewById(R.id.zero);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);
        count = view.findViewById(R.id.count);
        result = view.findViewById(R.id.result);
        amount = view.findViewById(R.id.amount);
        c.setOnClickListener(this);
        del.setOnClickListener(this);
        slant.setOnClickListener(this);
        ride.setOnClickListener(this);
        reduce.setOnClickListener(this);
        add.setOnClickListener(this);
        drop.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        count.setOnClickListener(this);
        result.setOnClickListener(this);
        amount.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        switch (view.getId()){
            //数字键
            case R.id.one:
            case R.id.two:
            case R.id.three:
            case R.id.four:
            case R.id.five:
            case R.id.six:
            case R.id.seven:
            case R.id.eight:
            case R.id.nine:
            case R.id.zero:
            case R.id.drop:
                //运算键
            case R.id.add:
            case R.id.reduce:
            case R.id.ride:
            case R.id.slant:
                if (isResult){//当为true，清空文本框的值，并设置为false
                    count.setText("");
                    result.setText("");
                    isResult = false;
                }
                //获取count的值
                count1 = count.getText().toString();
                //将点击的数字键的内容加到count中
                count1 += btn.getText().toString();
                //设置count的内容
                count.setText(count1);
                break;

            //功能键
            case R.id.amount://求值并显示
                isResult = true;//当按下等号时，设为true
                //获取count的值
                count1 = count.getText().toString();
                //判断count1是否是合理的表达式，最简洁的方法，使用正则表达式（验证字符串是否符合的规则）
                String reg = "^[0-9]+(.\\d)?([+\\-*/]\\d+(.\\d)?)+";//数字.数字+数字.数字
                if (!count1.isEmpty() && count1.matches(reg)){
                    Symbols symbols = new Symbols();
                    try {
                        double result2 = symbols.eval(count1);
                        //计算结果，并显示到result
                        result.setText(result2 + "");

                    } catch (SyntaxException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getActivity(),"这不是一个合格的表达式",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.c://清空值所有
                result.setText("");
                count.setText("");
                break;
            case R.id.del://清空一个值
                count1=count.getText().toString();
                if (count1.length()>0) {
                    count1=count1.substring(0, count.length() - 1);
                    count.setText(count1);
                }
                break;

        }
    }

}
