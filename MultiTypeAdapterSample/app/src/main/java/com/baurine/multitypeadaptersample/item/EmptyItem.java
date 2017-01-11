package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public class EmptyItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getType() {
        return R.layout.item_empty;
    }

    /////////////////////////////////////////////////
    // data part
    private String content;

    public String getContent() {
        return content;
    }

    public EmptyItem() {
        content = "Currently has no items, please refresh it later!";
    }

    /////////////////////////////////////////////////
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
