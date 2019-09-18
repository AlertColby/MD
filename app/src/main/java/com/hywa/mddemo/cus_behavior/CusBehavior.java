package com.hywa.mddemo.cus_behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.hywa.mddemo.R;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\18 0018 16:11
 * 邮箱:chen510470614@163.com
 */
public class CusBehavior extends CoordinatorLayout.Behavior {

    //在布局中使用必须要写两个参数的构造方法
    public CusBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        return dependency.getId() == R.id.txt_be_1||super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child , offset);
        return true;
    }
}
