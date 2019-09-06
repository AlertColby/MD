package com.hywa.mddemo.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\2 0002 09:06
 * 邮箱:chen510470614@163.com
 */

//定义出现问题，实现起来遇到很多问题，最终没有实现
@Deprecated
public class HeaderRecyclerView extends RecyclerView {
    private static final String TAG = "HeaderRecyclerView";

    ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>();
    ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;

    public HeaderRecyclerView(@NonNull Context context) {
        super(context);
    }

    public HeaderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View v) {
        addHeaderView(v, null);
    }

    public void addHeaderView(View v ,Object data ){
        if (v.getParent() != null && v.getParent() != this) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, "The specified child already has a parent. "
                        + "You must call removeView() on the child's parent first.");
            }
        }
        final FixedViewInfo info = new FixedViewInfo();
        info.view = v;
        info.data = data;
        mHeaderViewInfos.add(info);
        // Wrap the adapter if it wasn't already wrapped.
        mAdapter = getAdapter();
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                wrapHeaderRecyclerAdapterInternal();
            }
        }
    }

    public void addFooterView(View v) {
        addFooterView(v, null);
    }

    public void addFooterView(View v ,Object data ){
        if (v.getParent() != null && v.getParent() != this) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, "The specified child already has a parent. "
                        + "You must call removeView() on the child's parent first.");
            }
        }
        final FixedViewInfo info = new FixedViewInfo();
        info.view = v;
        info.data = data;
        mFooterViewInfos.add(info);
        // Wrap the adapter if it wasn't already wrapped.
        mAdapter = getAdapter();
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                wrapHeaderRecyclerAdapterInternal();
            }
        }
    }

    private void wrapHeaderRecyclerAdapterInternal() {
        mAdapter = wrapHeaderRecyclerAdapterInternal(mHeaderViewInfos , mFooterViewInfos , mAdapter);
    }

    protected HeaderViewRecyclerAdapter wrapHeaderRecyclerAdapterInternal(
            ArrayList<FixedViewInfo> headerViewInfos,
            ArrayList<FixedViewInfo> footerViewInfos,
            RecyclerView.Adapter adapter) {
        return new HeaderViewRecyclerAdapter(headerViewInfos, footerViewInfos, adapter);
    }

    @Override
    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = wrapHeaderRecyclerAdapterInternal(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }

    /**
     * A class that represents a fixed view in a list, for example a header at the top
     * or a footer at the bottom.
     */
    public class FixedViewInfo {
        /** The view to add to the list */
        public View view;
        public Object data;
    }
}
