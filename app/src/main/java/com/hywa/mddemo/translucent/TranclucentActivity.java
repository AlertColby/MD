package com.hywa.mddemo.translucent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.hywa.mddemo.R;

public class TranclucentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_tranclucent);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
        layoutParams.height += getStatusBarHeight();
        toolbar.setLayoutParams(layoutParams);
        toolbar.setPadding(toolbar.getPaddingLeft() , toolbar.getPaddingTop() +
                getStatusBarHeight() ,  toolbar.getPaddingRight() , toolbar.getPaddingBottom());
    }

    private int getStatusBarHeight() {
        int resultHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            String heightStr = clazz.getField("status_bar_height").get(obj).toString();
            int height = Integer.parseInt(heightStr);//得到dimen的resId
            resultHeight = getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("----------->"  + resultHeight);
        return resultHeight;
    }
}
