package com.baurine.multitypeadaptersample.presenter;

import android.os.Handler;
import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.activity.MainView;
import com.baurine.multitypeadaptersample.item.EmptyItem;
import com.baurine.multitypeadaptersample.item.ErrorItem;
import com.baurine.multitypeadaptersample.item.FooterItem;
import com.baurine.multitypeadaptersample.item.HeaderItem;
import com.baurine.multitypeadaptersample.model.ModelFaker;

import java.util.Random;

public class MainPresenter {
    private MainView mainView;

    private boolean refreshing = false;
    private boolean loading = false;
    private boolean hasMoreData = true;
    private static final int PER_PAGE_COUNT = 8;

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private HeaderItem headerItem = new HeaderItem();
    private EmptyItem emptyItem = new EmptyItem();
    private ErrorItem errorItem = new ErrorItem();
    private FooterItem footerItem = new FooterItem();

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        initItems();
    }

    ////////////////////////////////////////////////////////
    private void initItems() {
        emptyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyItemRemoved(adapter.removeItem(emptyItem));
                refreshData();
            }
        });
        errorItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyItemRemoved(adapter.removeItem(errorItem));
                refreshData();
            }
        });
        footerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                footerItem.setState(FooterItem.LOADING);
                adapter.notifyItemChanged(adapter.findPos(footerItem));
                loading = true;
                fetchData(true);
            }
        });
        adapter.addItem(headerItem);
        adapter.addItem(emptyItem);
    }

    ////////////////////////////////////////////////////////
    // public methods for MainView

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public void refreshData() {
        if (!refreshing) {
            refreshing = true;
            hasMoreData = true;
            mainView.showRefreshing();
            // remove all other items, just keep headerItem
            adapter.setItem(headerItem);
            fetchData(false);
        }
    }

    public void loadMore() {
        if (hasMoreData &&
                !loading &&
                // here the threshold depends on your actual situation
                // adapter.getItemCount() > PER_PAGE_COUNT
                adapter.getItemCount() > 2) {
            loading = true;
            fetchData(true);
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
                    mainView.hideRefreshing();
                }

                if (loadMore) {
                    loading = false;
                    adapter.removeItem(footerItem);
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
        int resultType = (new Random()).nextInt(100) % 6;
        if (resultType == 0) {
            adapter.addItem(loadMore ? footerItem.setState(FooterItem.ERROR) : errorItem);
        } else if (resultType == 1) {
            if (loadMore) {
                hasMoreData = false;
                addDataItems(PER_PAGE_COUNT / 2);
                // here depends whether you want to display no more data state
                // if you don't want to display this state when has no more data
                // then just don't add it back
                adapter.addItem(footerItem.setState(FooterItem.NO_MORE));
            } else {
                adapter.addItem(emptyItem);
            }
        } else {
            addDataItems(PER_PAGE_COUNT);
            // pre-display loading state to improve user experience
            adapter.addItem(footerItem.setState(FooterItem.LOADING));
        }
    }

    private void addDataItems(int count) {
        for (int i = 0; i < count; i++) {
            adapter.addItem(ModelFaker.fakeModel(i % 3).createItem(adapter));
        }
    }
}
