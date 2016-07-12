package com.lixinxin.recyclerviewloadingmore.ui.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lixinxin.recyclerviewloadingmore.R;
import com.lixinxin.recyclerviewloadingmore.ui.adapter.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> lists;

    private RecyclerviewAdapter adapter;

    int lastVisibleItem;  //记录RecyclerView 显示的最后一个 位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        lists = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            lists.add("普通的数据" + i);
        }
        adapter = new RecyclerviewAdapter(this, lists);
        recyclerView.setAdapter(adapter);


        /**
         * 实现加载更多
         */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == adapter.getItemCount()) {
                    adapter.mIsFooterEnable = true;
                    adapter.notifyItemChanged(lists.size() + 1);
                    LoadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });

    }

    //加载更多的逻辑
    private void LoadMore() {

        //这里实现数据的加载

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 2; i++) {
                    lists.add("加载更多的数据" + i);
                }
                adapter.lists = lists;
                adapter.mIsFooterEnable = false;
                adapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
