package com.baurine.multitypeadaptersample.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.databinding.ActivityMainBinding;
import com.baurine.multitypeadaptersample.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements RefreshingView {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainPresenter = new MainPresenter(this);

        recyclerView = binding.recyclerView;
        swipeRefreshLayout = binding.swipeRefreshLayout;
        initViews();
    }

    private void initViews() {
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        llm.findLastVisibleItemPosition() >= mainPresenter.getAdapter().getItemCount() - 1) {
                    mainPresenter.loadMoreData();
                }
            }
        });
        recyclerView.setAdapter(mainPresenter.getAdapter());

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mainPresenter.refreshData();
                    }
                });
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
