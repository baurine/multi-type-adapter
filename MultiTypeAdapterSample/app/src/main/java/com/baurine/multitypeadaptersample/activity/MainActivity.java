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
import com.baurine.multitypeadaptersample.item.ErrorItem;
import com.baurine.multitypeadaptersample.item.FooterItem;
import com.baurine.multitypeadaptersample.item.HeaderItem;
import com.baurine.multitypeadaptersample.item.ImageItem;
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
    private FooterItem footerItem = new FooterItem();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initItems();
        initViews();
    }

    private void initItems() {
        emptyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeItem(emptyItem);
                // adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(adapter.getItemCount());
                refreshData();
            }
        });
        errorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeItem(errorItem);
                // adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(adapter.getItemCount());
                refreshData();
            }
        });
        footerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                footerItem.setState(FooterItem.LOADING);
                // adapter.notifyDataSetChanged();
                adapter.notifyItemChanged(adapter.getItemCount() - 1);
                loading = true;
                fetchData(true);
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
                            // remove all other items, just keep headerItem
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
    private void fetchData(final boolean loadMore) {
        // mock network request
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (refreshing) {
                    refreshing = false;
                    binding.swipeRefreshLayout.setRefreshing(false);
                }

                if (loadMore) {
                    loading = false;
                    adapter.removeItem(footerItem);
                }
                retrieveItems(loadMore);
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    public void retrieveItems(boolean loadMore) {
        // result = 0, network error
        // result = 1, empty or last page data
        // result = 2 and other, normal result
        int resultType = (new Random()).nextInt(100) % 4;
        if (resultType == 0) {
            adapter.addItem(loadMore ? footerItem.setState(FooterItem.ERROR) : errorItem);
        } else if (resultType == 1) {
            if (loadMore) {
                hasMoreData = false;

                for (int i = 0; i < PER_PAGE_COUNT / 2; i++) {
                    adapter.addItem(i % 2 == 0 ? new ImageItem() : new TextItem());
                }
                // here depends whether you want to display no more data state
                // if you don't want to display this state when has no more data
                // then just don't add it back
                adapter.addItem(footerItem.setState(FooterItem.NO_MORE));
            } else {
                adapter.addItem(emptyItem);
            }
        } else {
            for (int i = 0; i < PER_PAGE_COUNT; i++) {
                adapter.addItem(i % 2 == 0 ? new ImageItem() : new TextItem());
            }
            // pre-display loading state to improve user experience
            adapter.addItem(footerItem.setState(FooterItem.LOADING));
        }
    }
}
