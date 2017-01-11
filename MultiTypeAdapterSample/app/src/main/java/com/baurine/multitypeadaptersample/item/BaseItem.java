package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public abstract class BaseItem implements MultiTypeAdapter.IItemType {
    ////////////////////////////////////////////
    // handle event
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
