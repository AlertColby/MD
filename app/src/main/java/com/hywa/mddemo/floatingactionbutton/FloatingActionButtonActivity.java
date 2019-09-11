package com.hywa.mddemo.floatingactionbutton;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hywa.mddemo.R;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
    }

    private boolean reverse = false;

    public void onClickFAB(View view){
        float rotateAngle = reverse ? 90 : -90;
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view , "rotation" ,0.0f, rotateAngle);
        objectAnimator.setDuration(500);
        objectAnimator.start();
        reverse = !reverse;
    }
}
