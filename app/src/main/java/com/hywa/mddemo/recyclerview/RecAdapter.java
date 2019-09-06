package com.hywa.mddemo.recyclerview;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hywa.mddemo.R;

import java.util.List;

/**
 * 作者:  陈庆松
 * 创建时间: 2019\8\29 0029 09:18
 * 邮箱:chen510470614@163.com
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

    private List<String> list;
    private  DragListener mDragListener;
    public RecAdapter(List<String> list , DragListener dragListener) {
        this.list = list;
        this.mDragListener = dragListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycler , viewGroup , false);
        return new MyViewHolder(view);
//        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1 , viewGroup , false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv.setText(list.get(i));
        myViewHolder.tv.setBackgroundColor(Color.BLUE);
        myViewHolder.tv.setOnClickListener(new MyOnClickListener(i));
        myViewHolder.tv2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    mDragListener.onStartDrag(myViewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(String value , int position){
        list.add(position , value);
//        notifyItemInserted(position);//只这样通知position不会改变
        notifyItemRangeChanged( position , list.size() - position);
    }

    public void removeItem( int position){
        int preCount = list.size();
        list.remove(position);
//        notifyItemRemoved(position);
//        notifyItemRangeRemoved(position , 1);
        notifyItemRangeChanged(position , preCount - position);
//        notifyDataSetChanged();
    }

    class MyOnClickListener implements View.OnClickListener{

        int position;

        public MyOnClickListener( int position ) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext() , "点击第" + position + "项" , Toast.LENGTH_SHORT).show();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        TextView tv2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.txt_1);
            tv2 = itemView.findViewById(R.id.txt_2);
//            tv = itemView.findViewById(android.R.id.text1);
        }
    }
}
