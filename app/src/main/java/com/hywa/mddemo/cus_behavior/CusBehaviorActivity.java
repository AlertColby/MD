package com.hywa.mddemo.cus_behavior;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hywa.mddemo.R;

public class CusBehaviorActivity extends AppCompatActivity {

    TextView txt1;
    private float lastX;
    private float lastY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_behavior);
        txt1 = findViewById(R.id.txt_be_1);
        txt1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getRawX();
                float y = event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float tranX = x - lastX;
                        float tranY = y - lastY;
                        ViewCompat.offsetTopAndBottom(v , (int)tranY);
                        ViewCompat.offsetLeftAndRight(v , (int)tranX);
                        lastX = x;
                        lastY = y;
                        break;
                }
                return true;
            }
        });
    }
}
