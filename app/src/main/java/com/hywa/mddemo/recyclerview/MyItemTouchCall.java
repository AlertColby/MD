package com.hywa.mddemo.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\8\30 0030 15:24
 * 邮箱:chen510470614@163.com
 */
public class MyItemTouchCall extends ItemTouchHelper.Callback {

    //判断Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向，（意思是我要监听那个方向的拖动）
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //up,down,left,right
        //常量：ItemTouchHelper.UP,ItemTouchHelper.DOWN,ItemTouchHelper.LEFT,ItemTouchHelper.RIGHT,

        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        //要监听swipe侧滑的方向
//        int swipeFlags = 0;
        int swipeFlags = ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int flags = makeMovementFlags(dragFlags, swipeFlags);
        return flags;
    }

    //是否允许长按拖拽
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    //当移动的时候回调
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder srcViewHolder, @NonNull RecyclerView.ViewHolder targetViewHolder) {

        return true;
    }

    //侧滑的时候回调
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }
}
