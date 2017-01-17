package com.baurine.multitypeadaptertutorial.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.databinding.ActivityMainBinding;
import com.baurine.multitypeadaptertutorial.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements RefreshingView {
    private MainPresenter mainPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainPresenter = new MainPresenter(this);
        swipeRefreshLayout = binding.swipeRefreshLayout;
        recyclerView = binding.recyclerView;
        initViews();
    }

    private void initViews() {
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mainPresenter.refreshData();
                    }
                });

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        llm.findLastVisibleItemPosition() >=
                                mainPresenter.getAdapter().getItemCount() - 1) {
                    mainPresenter.loadMoreData();
                }
            }
        });
        recyclerView.setAdapter(mainPresenter.getAdapter());
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
