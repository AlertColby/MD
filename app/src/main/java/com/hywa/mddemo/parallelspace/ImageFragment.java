package com.hywa.mddemo.parallelspace;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hywa.mddemo.R;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\17 0017 09:37
 * 邮箱:chen510470614@163.com
 */
public class ImageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resId = getArguments().getInt("resId");
        View view = inflater.inflate(R.layout.fragment_image , container , false);
        ImageView image = view.findViewById(R.id.image);
        image.setImageResource(resId);
        return view;
    }
}
