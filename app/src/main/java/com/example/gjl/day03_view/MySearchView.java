package com.example.gjl.day03_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 组合控件
 * 1.布局文件----
 * 2.将布局文件转成--view
 * 总布局是什么，类就要继承什么
 *  初始化一些数据，参数
 * 3。处理逻辑
 */

public class MySearchView extends LinearLayout {

    private EditText ev_content;

    //直接new处理
    public MySearchView(Context context) {
        this(context,null);
    }

    //在布局文件里面可用
//    AttributeSet  属性集
    public MySearchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    //手动调用
//    defStyleAttr默认属性
    public MySearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化一些东西
        View view = View.inflate(context,R.layout.layout_mysearchview,this);
        //找到控件
        ev_content = view.findViewById(R.id.content);
    }
    //提供一个外部访问的方法，将输入的内容传出去。


    public String getContent(){
        return ev_content.getText().toString();
    }

}
