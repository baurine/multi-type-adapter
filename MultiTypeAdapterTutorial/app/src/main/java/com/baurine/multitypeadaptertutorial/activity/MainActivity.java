package com.baurine.multitypeadaptertutorial.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptertutorial.databinding.ActivityMainBinding;
import com.baurine.multitypeadaptertutorial.item.EmptyItem;
import com.baurine.multitypeadaptertutorial.item.ErrorItem;
import com.baurine.multitypeadaptertutorial.item.FooterItem;
import com.baurine.multitypeadaptertutorial.item.HeaderItem;

public class MainActivity extends AppCompatActivity {

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private HeaderItem headerItem;
    private EmptyItem emptyItem;
    private ErrorItem errorItem;
    private FooterItem footerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        swipeRefreshLayout = binding.swipeRefreshLayout;
        recyclerView = binding.recyclerView;
        initViews();
        initItems();
    }

    private void initViews() {
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshData();
                    }
                });

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        llm.findLastVisibleItemPosition() >= adapter.getItemCount() - 1) {
                    loadMore();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initItems() {
        headerItem = new HeaderItem();
        emptyItem = new EmptyItem();
        errorItem = new ErrorItem();
        footerItem = new FooterItem();
        adapter.addItem(headerItem);
        adapter.addItem(emptyItem);
    }

    private void loadMore() {
        // TODO
    }

    private void refreshData() {
        // TODO
    }
}
