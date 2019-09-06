package com.hywa.mddemo.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.hywa.mddemo.R;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\8\29 0029 16:17
 * 邮箱:chen510470614@163.com
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable mDivider;

    public DividerGridItemDecoration(Context context) {
        this.mContext = context;
        mDivider = ContextCompat.getDrawable( context , R.drawable.gradient_divider);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //RecyclerView会回调绘制方法，需要自己去实现条目的分割线
        super.onDraw(c, parent, state);
        drawVertical(c , parent );
        drawHorizontal(c , parent);
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //画水平线
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (isLastRow(parent , child)){
                continue;
            }
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getLeft() + lp.leftMargin;
            int top = child.getBottom() - lp.bottomMargin;
            int right = left + child.getWidth();
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left , top , right , bottom);
            mDivider.draw( c );
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //画竖直线
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if ( isLastColumn( parent , child)){
                continue;
            }
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + lp.rightMargin;
            int top = child.getTop() + lp.topMargin;
            int right = left + mDivider.getIntrinsicWidth();
            int bottom = top + child.getHeight() + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left , top , right , bottom);
            mDivider.draw( c );
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //1.调用该方法（首先会获取条目之间的间隙宽度）
        // 获取条目的偏移量,如果不设置，图片将会画在条目View之上
        super.getItemOffsets(outRect, view, parent, state);
        if (isLastRow(parent,view)){
            if ( isLastColumn(parent, view)){
                outRect.set( 0 , 0 , 0 , 0);
            }else {
                outRect.set( 0 , 0 , mDivider.getIntrinsicWidth() , 0);
            }
        }else if (isLastColumn(parent ,view)){
            outRect.set( 0 , 0 , 0 , mDivider.getIntrinsicHeight());
        }else{
            outRect.set( 0 , 0 , mDivider.getIntrinsicWidth() , mDivider.getIntrinsicHeight());
        }
    }

    private boolean isLastColumn( RecyclerView parent , View view){
        GridLayoutManager GridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = GridLayoutManager.getSpanCount();
        int position = parent.getChildAdapterPosition(view);
        if ( (position + 1) % spanCount == 0){
            return true;
        }else{
            return false;
        }
    }

    private boolean isLastRow(RecyclerView parent , View view){
        GridLayoutManager GridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        int spanCount = GridLayoutManager.getSpanCount();
        int position = parent.getChildAdapterPosition(view);
        int totalCount = parent.getAdapter().getItemCount();
        if ((totalCount % spanCount == 0 && totalCount - position <= spanCount) ||totalCount % spanCount >= totalCount - position ){
            return true;
        }else{
            return false;
        }
    }
}
