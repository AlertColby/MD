package com.hywa.mddemo.palette;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.hywa.mddemo.R;

public class PaletteActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ImageView iv = findViewById(R.id.iv);
        tv = findViewById(R.id.tv);
        BitmapDrawable drawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
//        Palette palette = Palette.generate(bitmap);
//        tv.setBackgroundColor( palette.getDarkMutedColor(Color.BLACK));
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                Palette.Swatch swatch = palette.getLightMutedSwatch();
//                tv.setBackgroundColor( palette.getDarkMutedColor(Color.BLACK));
                tv.setBackgroundColor(getTranslucentColor(0.4f , swatch.getRgb()));
                tv.setTextColor( swatch.getTitleTextColor() );
            }
        });
    }

    /**
     * int 颜色为32位，从右到左每八位为一组，分别表示blue,breen,red,alpha
     * @param f
     * @param rgb
     * @return
     */
    private int getTranslucentColor(float f , int rgb){
        int blue = rgb & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int red = rgb >> 16 & 0xFF;
        int alpha = rgb >>> 24 ;//>>>不带符号位移，即高位补0 ， >> 带符号位移即如果为负数高位补1，正数补0
        //可查看源码Color.alpha(rgb)的实现原理
        alpha = Math.round(alpha * f);
        return Color.argb(alpha , red ,green , blue);
    }
}
