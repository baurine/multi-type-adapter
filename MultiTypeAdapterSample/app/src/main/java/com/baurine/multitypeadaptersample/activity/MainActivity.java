package com.baurine.multitypeadaptersample.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.databinding.ActivityMainBinding;
import com.baurine.multitypeadaptersample.item.EmptyItem;
import com.baurine.multitypeadaptersample.item.EndItem;
import com.baurine.multitypeadaptersample.item.ErrorItem;
import com.baurine.multitypeadaptersample.item.HeaderItem;
import com.baurine.multitypeadaptersample.item.ImageItem;
import com.baurine.multitypeadaptersample.item.LoadingItem;
import com.baurine.multitypeadaptersample.item.TextItem;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private boolean refreshing = false;
    private boolean loading = false;
    private boolean hasMoreData = true;
    private final int PER_PAGE_COUNT = 8;

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private HeaderItem headerItem = new HeaderItem();
    private EmptyItem emptyItem = new EmptyItem();
    private ErrorItem errorItem = new ErrorItem();
    private LoadingItem loadingItem = new LoadingItem();
    private EndItem endItem = new EndItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initItems();
        initViews();
    }

    private void initItems() {
        emptyItem.setBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeItem(emptyItem);
                adapter.notifyDataSetChanged();
                refreshData();
            }
        });
        errorItem.setBtnListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeItem(errorItem);
                adapter.notifyDataSetChanged();
                refreshData();
            }
        });
        adapter.addItem(headerItem);
        adapter.addItem(emptyItem);
    }

    private void initViews() {
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (hasMoreData &&
                        !loading &&
                        newState == RecyclerView.SCROLL_STATE_IDLE &&
                        adapter.getItemCount() > PER_PAGE_COUNT &&
                        llm.findLastVisibleItemPosition() >= adapter.getItemCount() - 1) {
                    loading = true;
                    fetchData(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);

        binding.swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        if (!refreshing) {
                            refreshing = true;
                            hasMoreData = true;
                            // just keep headerItem
                            adapter.setItem(headerItem);
                            fetchData(false);
                        }
                    }
                });
    }

    private void refreshData() {
        if (!refreshing) {
            refreshing = true;
            hasMoreData = true;
            binding.swipeRefreshLayout.setRefreshing(true);
            fetchData(false);
        }
    }

    // fetch data from server, for refreshing or load more
    private void fetchData(final boolean fetchMore) {
        // mock network request
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshing) {
                    refreshing = false;
                    binding.swipeRefreshLayout.setRefreshing(false);
                }

                if (fetchMore) {
                    loading = false;
                    adapter.removeItem(loadingItem);
                    retrieveItems();
                } else {
                    int result = (new Random()).nextInt(100) % 5;
                    // result = 0, empty result
                    // result = 1, network error
                    // result = 2, normal result
                    if (result == 0) {
                        adapter.addItem(emptyItem);
                    } else if (result == 1) {
                        adapter.addItem(errorItem);
                    } else {
                        retrieveItems();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    public void retrieveItems() {
        int resultCnt = adapter.getItemCount() > 20 ? 4 : PER_PAGE_COUNT;

        for (int i = 0; i < resultCnt; i++) {
            adapter.addItem(i % 2 == 0 ? new ImageItem() : new TextItem());
        }

        if (resultCnt < PER_PAGE_COUNT) {
            hasMoreData = false;
            adapter.addItem(endItem);
        } else {
            adapter.addItem(loadingItem);
        }
    }
}
