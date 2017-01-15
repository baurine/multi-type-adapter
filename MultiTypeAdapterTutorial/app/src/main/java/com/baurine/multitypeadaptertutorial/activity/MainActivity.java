package com.baurine.multitypeadaptertutorial.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
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
import com.baurine.multitypeadaptertutorial.item.ImageItem;
import com.baurine.multitypeadaptertutorial.item.TextItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private HeaderItem headerItem;
    private EmptyItem emptyItem;
    private ErrorItem errorItem;
    private FooterItem footerItem;

    private static final int PER_PAGE_COUNT = 8;
    private boolean refreshing = false;

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
                    loadMoreData();
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

    private void loadMoreData() {
        // TODO
    }

    private void refreshData() {
        if (!refreshing) {
            refreshing = true;
            swipeRefreshLayout.setRefreshing(true);
            // remove all other items, just keep headerItem
            adapter.setItem(headerItem);
            fetchData(false);
        }
    }

    ////////////////////////////////////////////////////////
    // fetch data from server, for refreshing or load more
    private void fetchData(final boolean loadMore) {
        // mock network request
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshing) {
                    refreshing = false;
                    swipeRefreshLayout.setRefreshing(false);
                }

                retrieveItems(loadMore);
                adapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    private void retrieveItems(boolean loadMore) {
        // result = 0, network error
        // result = 1, empty or last page data
        // result = 2 and other, normal result
        int resultType = (new Random()).nextInt(100) % 4;
        if (resultType == 0) {
            adapter.addItem(errorItem);
        } else if (resultType == 1) {
            adapter.addItem(emptyItem);
        } else {
            addDataItems(PER_PAGE_COUNT);
            // pre-display loading state to improve user experience
            adapter.addItem(footerItem.setState(FooterItem.LOADING));
        }
    }

    private void addDataItems(int count) {
        for (int i = 0; i < count; i++) {
            adapter.addItem(i % 2 == 0 ? new ImageItem() : new TextItem());
        }
    }
}
