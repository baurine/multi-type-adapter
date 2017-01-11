package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public class FooterItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getType() {
        return R.layout.item_footer;
    }

    ///////////////////////////////////////////////
    // data part
    // FooterItem has 3 states: Loading, Error, NoMore
    public final static int LOADING = 0;
    public final static int ERROR = 1;
    public final static int NO_MORE = 2;
    private int state = LOADING;

    public FooterItem setState(int state) {
        this.state = state;
        return this;
    }

    public boolean isLoading() {
        return state == LOADING;
    }

    public boolean isError() {
        return state == ERROR;
    }

    public boolean isNoMore() {
        return state == NO_MORE;
    }

    ////////////////////////////////////////////
    // handle event
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
