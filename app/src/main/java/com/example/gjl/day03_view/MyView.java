package com.example.gjl.day03_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义View
 * 新的控件
 * <p>
 * 绘制简单图形，圆，线，点，
 * <p>
 * 处理触摸事件，多动
 */

public class MyView extends View {

    //创建画笔
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化东西
//        画笔画布，颜色，粗细。。。
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        //设置颜色  默认黑色
        mPaint.setColor(Color.RED);
        //粗细  默认14px
        mPaint.setStrokeWidth(5);
    }

    //绘制
    //Canvas  画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //比如绘制圆形
//        1.圆心的x轴坐标   2.圆心的y轴坐标  3.半径  4.画笔

        int cx = mWidth / 2;
        int cy = mHeight / 2;

        int radius = mWidth / 2;

        canvas.drawCircle(cx, cy, radius, mPaint);
//        canvas.drawLine(200,200,400,400,mPaint);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawText("赵俊超", 0, "赵俊超".length(), 300, 600, mPaint);
    }

    //测量的方法

    //    MeasureSpec----MeasureMode  MeasureSize
//    MeasureMode   3 种
//    MeasureSize   测量后的尺寸
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

    }

    //处理事件
//    拖动事件的思路：
//    1.记录点击时的坐标
//    2.拖动，计算手指一动额距离
//    3.将距离重新给加到原来的坐标上
//    4.将新的坐标给view
//    触摸事件：1.按下，2.滑动 3.抬起 4.取消

    int x ;
    int y ;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
//            按下
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getRawX();//绝对坐标
                y = (int) event.getRawY();
                break;
//                滑动
            case MotionEvent.ACTION_MOVE:
                int x1 = (int) event.getRawX();
                int y1 = (int) event.getRawY();
                //得到距离差
                int dx = x1-x;
                int dy = y1 -y;
                //从新赋值
                int nL = getLeft()+dx;
                int t = getTop()+dy;
                int r = getRight()+dx;
                int b = getBottom()+dy;
                //确定位置
                layout(nL,t,r,b);
                //给x y 重新赋值
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
//                抬起
            case MotionEvent.ACTION_UP:
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
        }
        return true;//自己消费了这个事件
    }
}
