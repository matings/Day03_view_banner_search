package com.example.gjl.day03_view;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据
 * <p>
 * 大家写的时候，要用真实的数据
 */
public class MyViewPagerActivity extends AppCompatActivity {
    private List<ImageView> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_pager);

        //初始化界面
        MyBanner myBanner = findViewById(R.id.myBanner);
        //初始化数据
        int[] icon_id = new int[]{
                R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.hello
        };
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(icon_id[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            list.add(imageView);
        }
        //逻辑
        MyBanner.MyPagerAdapter myPagerAdapter = new MyBanner.MyPagerAdapter(list);
        myBanner.setAdapter(myPagerAdapter);
        //调用自动轮播
        myBanner.autoPlay();
    }
}
