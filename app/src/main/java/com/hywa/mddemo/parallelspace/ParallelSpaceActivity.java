package com.hywa.mddemo.parallelspace;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hywa.mddemo.R;
import java.util.ArrayList;
import java.util.List;

public class ParallelSpaceActivity extends AppCompatActivity {

    private ViewPager vp_container;
    private ViewPager vp_kuang;

    private List<Fragment> fragments;

    private VPAdapter vpAdapter_Container;
    private int resIds[] = new int[]{R.drawable.a , R.drawable.a1 , R.drawable.a2 , R.drawable.a3 , R.drawable.a4};

    private VPAdapter vpAdapter_Kuang;
    private List<Fragment> fragments_kuang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallel_space);
        vp_container = findViewById(R.id.vp_container);
        vp_kuang = findViewById(R.id.vp_kuang);
        fragments = new ArrayList<>();
        fragments_kuang = new ArrayList<>();
        for (int i = 0; i < resIds.length; i++) {
            ImageFragment imageFragment = new ImageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("resId" , resIds[i]);
            imageFragment.setArguments(bundle);
            fragments.add(imageFragment);
            ImageFragment imageFragment1 = new ImageFragment();
            Bundle bundle1 = new Bundle();
            bundle1.putInt("resId" , resIds[i]);
            imageFragment1.setArguments(bundle1);
            fragments_kuang.add(imageFragment1);
        }
        vpAdapter_Container = new VPAdapter(getSupportFragmentManager() , fragments);
        vp_container.setAdapter(vpAdapter_Container);
        vpAdapter_Kuang = new VPAdapter(getSupportFragmentManager() , fragments_kuang);
        vp_kuang.setAdapter(vpAdapter_Kuang);
        vp_container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                vp_kuang.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        vp_kuang.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                vp_container.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
