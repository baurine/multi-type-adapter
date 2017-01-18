package com.baurine.multitypeadaptertutorial.presenter;

import android.os.Handler;
import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptertutorial.activity.RefreshingView;
import com.baurine.multitypeadaptertutorial.item.EmptyItem;
import com.baurine.multitypeadaptertutorial.item.ErrorItem;
import com.baurine.multitypeadaptertutorial.item.FooterItem;
import com.baurine.multitypeadaptertutorial.item.HeaderItem;
import com.baurine.multitypeadaptertutorial.model.ModelFaker;

import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class MainPresenter {
    private RefreshingView refreshingView;

    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private HeaderItem headerItem;
    private EmptyItem emptyItem;
    private ErrorItem errorItem;
    private FooterItem footerItem;

    private boolean refreshing = false;
    private boolean loading = false;
    private static final int PER_PAGE_COUNT = 8;

    public MainPresenter(RefreshingView refreshingView) {
        this.refreshingView = refreshingView;
        initItems();
    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    private void initItems() {
        headerItem = new HeaderItem();
        emptyItem = new EmptyItem();
        errorItem = new ErrorItem();
        footerItem = new FooterItem();

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
                loadMoreData();
            }
        });

        adapter.addItem(headerItem);
        adapter.addItem(emptyItem);
    }

    public void refreshData() {
        if (!refreshing) {
            refreshing = true;
            refreshingView.setRefreshing(true);
            footerItem.setState(FooterItem.LOADING);
            // remove all other items, just keep headerItem
            adapter.setItem(headerItem);
            fetchData(false);
        }
    }

    public void loadMoreData() {
        int footerPos = adapter.findPos(footerItem);
        if (!loading &&
                footerPos >= 0 &&
                !footerItem.isNoMore()) {
            if (!footerItem.isLoading()) {
                footerItem.setState(FooterItem.LOADING);
                adapter.notifyItemChanged(adapter.findPos(footerItem));
            }
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
                    refreshingView.setRefreshing(false);
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
        // result = 1, empty
        // result = 2, last page
        // result = 3 and other, normal result
        int resultType = (new Random()).nextInt(100) % 5;
        if (resultType == 0) {
            adapter.addItem(loadMore ? footerItem.setState(FooterItem.ERROR) : errorItem);
        } else if (resultType == 1) {
            adapter.addItem(loadMore ? footerItem.setState(FooterItem.NO_MORE) : emptyItem);
        } else if (resultType == 2) {
            addDataItems(PER_PAGE_COUNT / 2);
            // here depends whether you want to display no more data state
            // if you don't want to display it when has no more data
            // then just don't add it back,
            // but still need set no more state for footerItem
            adapter.addItem(footerItem.setState(FooterItem.NO_MORE));
        } else {
            addDataItems(PER_PAGE_COUNT);
            // pre-display loading state to improve user experience
            adapter.addItem(footerItem.setState(FooterItem.LOADING));
        }
    }

    private void addDataItems(int count) {
        for (int i = 0; i < count; i++) {
            adapter.addItem(ModelFaker.fake().createItem(adapter));
        }
    }
}
