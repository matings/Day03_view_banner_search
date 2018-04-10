package com.example.gjl.day03_view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义ViewPager
 * ViewGroup
 * <p>
 * 组合控件
 * <p>
 * 1.展示图片
 * 2.小圆点---两种状态，选中，未选中
 * 3.无限轮播
 * 4.停止轮播
 * <p>
 * 普遍性使用
 */

public class MyBanner extends RelativeLayout {


    private static ViewPager myViewPager;//viewapger
    private static LinearLayout ll_points;//小圆点的线性布局
    private static List<ImageView> imageViews = new ArrayList<>();//图片的集合
    private static List<ImageView> points = new ArrayList<>();//小圆点的集合
    private static Context mContext;
    private MyHandler myHandler = new MyHandler();//handler  用户发送消息

    public MyBanner(Context context) {
        this(context, null);
    }

    public MyBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化一些东西
        mContext = context;
        //layout---view
        View view = View.inflate(context, R.layout.layout_banner, this);
        //找控件
        myViewPager = view.findViewById(R.id.viewpager);
        ll_points = view.findViewById(R.id.ll_points);
        //处理逻辑
    }

    //开启自动轮播
    public void autoPlay() {
        myHandler.sendEmptyMessageDelayed(0, 2000);
    }

    //停止自动轮播
    public void stopPlay() {
        myHandler.removeCallbacksAndMessages(null);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            myViewPager.setCurrentItem(myViewPager.getCurrentItem() + 1);
            //再次发送消息
            myHandler.sendEmptyMessageDelayed(0, 2000);
        }
    }

    //设置适配器的方法
    public void setAdapter(PagerAdapter pagerAdapter) {
        myViewPager.setAdapter(pagerAdapter);
        //设置滑动事件
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //当页面滑动的时候的回到方法
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //当松开的时候的回调函数
            @Override
            public void onPageSelected(int position) {
                position = position % points.size();
                for (int i = 0; i < points.size(); i++) {
                    if (position == i) {
                        points.get(position).setSelected(true);
                    } else {
                        points.get(i).setSelected(false);
                    }
                }
            }

            //状态改变
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //适配器
    public static class MyPagerAdapter extends PagerAdapter {

        public MyPagerAdapter(List<ImageView> list) {
            imageViews = list;
            //添加小圆点
            for (int i = 0; i < imageViews.size(); i++) {
                ImageView p = new ImageView(mContext);
                p.setImageResource(R.drawable.banner_point_selector);
                //设置远点之间的间隔
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 0, 0, 0);
                p.setLayoutParams(params);
                //添加到points集合里面
                points.add(p);
                //添加到线性布局里面
                ll_points.addView(p);
            }
            //设置默认选中第一个图片，也就是第一原点的状态为选中
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
