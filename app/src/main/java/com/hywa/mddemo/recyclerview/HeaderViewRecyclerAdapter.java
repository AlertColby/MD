package com.hywa.mddemo.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\9\2 0002 16:23
 * 邮箱:chen510470614@163.com
 */
@Deprecated
public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<HeaderRecyclerView.FixedViewInfo> mHeaderViewInfos;
    ArrayList<HeaderRecyclerView.FixedViewInfo> mFooterViewInfos;

    private RecyclerView.Adapter mAdapter;
    private static final ArrayList<HeaderRecyclerView.FixedViewInfo> EMPTY_INFO_LIST =
            new ArrayList<>();

    public HeaderViewRecyclerAdapter(ArrayList<HeaderRecyclerView.FixedViewInfo> headerViewInfos ,
                                     ArrayList<HeaderRecyclerView.FixedViewInfo> footerViewInfos,
                                     RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        if (headerViewInfos == null) {
            mHeaderViewInfos = EMPTY_INFO_LIST;
        } else {
            mHeaderViewInfos = headerViewInfos;
        }

        if (footerViewInfos == null) {
            mFooterViewInfos = EMPTY_INFO_LIST;
        } else {
            mFooterViewInfos = footerViewInfos;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        int numHeaders = getHeadersCount();
        int position = viewType;
        if (position < numHeaders && position >= 0) {//头部
            View v = mHeaderViewInfos.get(position).view;
            return new MyHolder(v);
        }else if ( position >= numHeaders && position < numHeaders + mAdapter.getItemCount()){
            return mAdapter.onCreateViewHolder(viewGroup , viewType);
        }else {
            int footPosition = position - getHeadersCount() - mAdapter.getItemCount();
            View view = mFooterViewInfos.get(footPosition).view;
            return new MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position){
        if ( position >= getHeadersCount() && position < getItemCount() - getFootersCount()){
            int adjPosition = position - getHeadersCount();
            mAdapter.onBindViewHolder(viewHolder , adjPosition);
        }
    }

    @Override
    public int getItemViewType(int position){
//        //如果是条目就直接返回条目的项，否则直接范围位置
//        if (position >= getHeadersCount() && position < getHeadersCount() + mAdapter.getItemCount()){
//            return mAdapter.getItemViewType(position);
//        }
        //直接返回的方式虽然可以实现功能，但是RecyclerView的复用将无效
        return position;
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    public int getHeadersCount() {
        return mHeaderViewInfos.size();
    }

    public int getFootersCount() {
        return mFooterViewInfos.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
