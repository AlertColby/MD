package com.hywa.mddemo.translucent;

import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hywa.mddemo.R;

public class TranclucentActivity extends BaseTranlucentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranclucent);
        Toolbar toolbar = findViewById(R.id.toolbar);
        View nav = findViewById(R.id.nav);
        setOrChangeTranslucentColor(toolbar , nav , ContextCompat.getColor(this , R.color.colorPrimary));
    }
}
