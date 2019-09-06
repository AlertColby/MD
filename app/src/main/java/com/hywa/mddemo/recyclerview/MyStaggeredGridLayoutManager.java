package com.hywa.mddemo.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\8\29 0029 14:55
 * 邮箱:chen510470614@163.com
 */
public class MyStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    public MyStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MyStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try{
            super.onLayoutChildren(recycler, state);
        }catch ( IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
}
