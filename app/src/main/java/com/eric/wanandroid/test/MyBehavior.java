package com.eric.wanandroid.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by eric on 20-10-9
 */
public class MyBehavior extends CoordinatorLayout.Behavior {

    //必须要写构造方法
    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //我们这里监听的是一个RecyclerView，当RecyclerView变化后，捕获
        return dependency instanceof AppBarLayout || super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //偏移量
        int offset = dependency.getBottom() - child.getTop();
        child.setTranslationY(offset);
        return false;
    }
}
