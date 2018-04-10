package com.example.gjl.day03_view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gjl on 2018/1/26.
 */

public class MyViewPager extends RelativeLayout {

    private ViewPager viewPager;
    private static LinearLayout buttom;
    private static List<ImageView> imageViews = new ArrayList<>();
    private static List<ImageView> points = new ArrayList<>();
    private static Context mContext;
    private MyHandler myHandler = new MyHandler();

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewPager(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_layout, this);
        viewPager = view.findViewById(R.id.viewpager);
        buttom = view.findViewById(R.id.buttom);

        //滑动事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % imageViews.size();
                for (int i = 0; i < points.size(); i++) {
                    if (i == position) {
                        points.get(position).setSelected(true);
                    } else {
                        points.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //设置适配器
    public void setAdapter(PagerAdapter pagerAdapter) {
        viewPager.setAdapter(pagerAdapter);

    }

    public void autoPlay() {
        myHandler.sendEmptyMessageDelayed(0, 2000);
    }

    public void stopPlay() {
        myHandler.removeCallbacksAndMessages(null);
    }

    //自动轮播
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

            myHandler.sendEmptyMessageDelayed(0, 2000);

        }
    }


    static class MyAdapter extends PagerAdapter {

        public MyAdapter(List<ImageView> list) {
            imageViews = list;
            //进行小圆点的绘制

            for (int i = 0; i < imageViews.size(); i++) {
                ImageView point = new ImageView(mContext);
                point.setImageResource(R.drawable.point);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5, 0, 0, 0);
                point.setLayoutParams(layoutParams);
                //设置间距
                points.add(point);
                buttom.addView(point);
            }
            //默认第一个点选中
            points.get(0).setSelected(true);

        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            position = position % imageViews.size();

            container.addView(imageViews.get(position));

            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
