package com.hywa.mddemo.tab;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.hywa.mddemo.R;

public class TabActivity extends AppCompatActivity {

    private TabLayout tab_layout;
    private ViewPager vp;

    private String titles[] = new String[]{
//      "新闻",
//      "杂志",
      "汽车",
      "招聘",
      "NBA"
    };
    private TabFragment fragments[];
    private TabViewPagerAdapter tabViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tab_layout = findViewById(R.id.tab_layout);
        vp = findViewById(R.id.vp);
        init();
    }

    private void init(){
        fragments = new TabFragment[titles.length];
        for (int i = 0; i < titles.length; i++) {
            fragments[i] = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title" , titles[i]);
            fragments[i].setArguments(bundle);
        }
        tabViewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager() , fragments);
        vp.setAdapter(tabViewPagerAdapter);
        tab_layout.setupWithViewPager(vp);
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = tab_layout.getTabAt(i);
//            tab.setIcon(R.mipmap.ic_launcher);
            tab.setCustomView(R.layout.item_tab);
        }
    }

}
