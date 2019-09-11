package com.hywa.mddemo.translucent;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\11 0011 10:45
 * 邮箱:chen510470614@163.com
 * 描述：做沉浸式效果需要继承本类并调用setOrChangeTranslucentColor实现
 */
public class BaseTranlucentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     *
     * @param toolbar 顶部放置的toolbar
     * @param bottomNavition 布局底部放置的用来填充的view
     * @param color
     */
    public void setOrChangeTranslucentColor(Toolbar toolbar , View bottomNavition , int color){
        //判断版本，如果[4.4,5.0)就设置状态栏和导航栏为透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            if ( toolbar != null){
                //状态栏
                ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
                layoutParams.height += getStatusBarHeight();
                toolbar.setLayoutParams(layoutParams);
                toolbar.setPadding(toolbar.getPaddingLeft() , toolbar.getPaddingTop() +
                        getStatusBarHeight() ,  toolbar.getPaddingRight() , toolbar.getPaddingBottom());
                toolbar.setBackgroundColor(color);
            }
            if (bottomNavition != null){
                //底部导航栏
                ViewGroup.LayoutParams lp = bottomNavition.getLayoutParams();
                lp.height += getNavigationBarHeight();
                bottomNavition.setLayoutParams(lp);
                bottomNavition.setBackgroundColor(color);
            }
        }else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(color);
            getWindow().setStatusBarColor(color);
            if (toolbar != null){
                toolbar.setBackgroundColor(color);
            }
            if ( bottomNavition != null){
                ViewGroup.LayoutParams lp = bottomNavition.getLayoutParams();
                lp.height = 0;
                bottomNavition.setLayoutParams(lp);
            }
        }else{
            if (toolbar != null){
                toolbar.setBackgroundColor(color);
            }
            if ( bottomNavition != null ){
                ViewGroup.LayoutParams lp = bottomNavition.getLayoutParams();
                lp.height = 0;
                bottomNavition.setLayoutParams(lp);
            }
        }
    }

    /**
     * 获取导航栏的高度
     * @return
     */
    private int getNavigationBarHeight() {
        return getSystemComponentDimen("navigation_bar_height");
    }


    /**
     * 获取状态栏的高度
     * @return
     */
    private int getStatusBarHeight() {
        return getSystemComponentDimen("status_bar_height");
    }

    private int getSystemComponentDimen(String componentName){
        int resultHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            String heightStr = clazz.getField(componentName).get(obj).toString();
            int height = Integer.parseInt(heightStr);//得到dimen的resId
            resultHeight = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultHeight;
    }
}
