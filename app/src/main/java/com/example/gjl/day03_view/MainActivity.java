package com.example.gjl.day03_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //获取输入框的内容
        final MySearchView mySearchView = findViewById(R.id.mySearchView);
        TextView search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mySearchView.getContent();
                Toast.makeText(MainActivity.this, content + "***", Toast.LENGTH_SHORT).show();
            }
        });

        //
//        MyViewPager myViewPager = findViewById(R.id.myViewPager);

        //初始化数据
        List<ImageView> list = initDatas();

//        github提交测试


        MyViewPager.MyAdapter myAdapter = new MyViewPager.MyAdapter(list);

//        myViewPager.setAdapter(myAdapter);

//        myViewPager.autoPlay();

    }

    private List<ImageView> initDatas() {

        List<ImageView> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.drawable.hello);
            list.add(imageView);
        }

        return list;
    }
}
