package com.hywa.mddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import com.hywa.mddemo.recyclerview.DragListener;
import com.hywa.mddemo.recyclerview.HeaderRecyclerView;
import com.hywa.mddemo.recyclerview.MyItemTouchCall;
import com.hywa.mddemo.recyclerview.RecAdapter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DragListener {

    private HeaderRecyclerView recycler_view;
    private RecAdapter adapter;
    private ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_recycler);
        recycler_view = findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//        recycler_view.setLayoutManager(new MyGridLayoutManager( this , 3));
//        recycler_view.setLayoutManager(new MyStaggeredGridLayoutManager(3 , StaggeredGridLayoutManager.VERTICAL));
//        DeviderItemDecoration dividerItemDecoration = new DeviderItemDecoration(this , StaggeredGridLayoutManager.VERTICAL);

//        DividerGridItemDecoration dividerItemDecoration = new DividerGridItemDecoration(this);
//        recycler_view.addItemDecoration(dividerItemDecoration);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("条目" + i);
        }
        View view = LayoutInflater.from(this).inflate(R.layout.layout_header , recycler_view , false);
        recycler_view.addHeaderView(view);
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_header , recycler_view , false);
        recycler_view.addFooterView(view1);
        adapter = new RecAdapter(list , this);
        recycler_view.setAdapter(adapter);
        MyItemTouchCall call = new MyItemTouchCall();
        itemTouchHelper = new ItemTouchHelper(call);
        itemTouchHelper.attachToRecyclerView( recycler_view );
    }

    public void onAdd(View view){
        adapter.addItem("添加的Item" , 0 );
    }

    public void onDelete(View view){
        adapter.removeItem( 0 );
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
