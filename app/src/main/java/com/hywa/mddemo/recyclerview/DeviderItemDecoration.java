package com.hywa.mddemo.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hywa.mddemo.R;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\8\29 0029 10:48
 * 邮箱:chen510470614@163.com
 */
public class DeviderItemDecoration extends RecyclerView.ItemDecoration {

    private int mOrientation;
    private Drawable mDivider;

    private int []attrs = new int[]{
            android.R.attr.listDivider
    };

    public DeviderItemDecoration(Context context ,int orientation) {
//        TypedArray ta = context.obtainStyledAttributes( attrs );
//        mDivider = ta.getDrawable(0);//得到需要画的图片
//        ta.recycle();
        mDivider = ContextCompat.getDrawable(context , R.drawable.gradient_divider);
        setOrientation(orientation);
    }

    public void setOrientation(int orientation){
        if ( orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("orientation必须是LinearLayoutManager.HORIZONTAL或LinearLayoutManager.VERTICAL");
        }
        this.mOrientation = orientation;
    }

  @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //RecyclerView会回调绘制方法，需要自己去实现条目的分割线
        super.onDraw(c, parent, state);
        if ( mOrientation == LinearLayoutManager.VERTICAL){
            drawVertical(c , parent );
        }else{
            drawHorizontal(c , parent);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        //画水平线
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + lp.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left , top , right , bottom);
            mDivider.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //画水平线
        int left = parent.getPaddingLeft();//RecyclerView左边除去边距
        int right = parent.getWidth() - parent.getPaddingRight();//RecyclerView右边除去边距
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);//获取条目的View
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();//获取条目的布局参数
            int top = child.getBottom() + lp.bottomMargin + Math.round(ViewCompat.getTranslationY(child));//条目的底部+底部外边距+Y方向上的平移量=分割线的顶部
            int bottom = top + mDivider.getIntrinsicHeight();//分割线的底部= 顶部 + 分割线的高
            mDivider.setBounds(left , top , right , bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        //1.调用该方法（首先会获取条目之间的间隙宽度）
        // 获取条目的偏移量,如果不设置，图片将会画在条目View之上
        super.getItemOffsets(outRect, view, parent, state);
        if ( mOrientation == LinearLayoutManager.VERTICAL){
            //设置左上右下的偏移量，这里会导致item的下部留出一块空白区域给分割线使用，这里的设置会影响item的展示
            outRect.set( 0 , 0 , 0 , mDivider.getIntrinsicHeight());
        }else {
            outRect.set(0 , 0 , mDivider.getIntrinsicWidth() , 0);
        }
    }
}
