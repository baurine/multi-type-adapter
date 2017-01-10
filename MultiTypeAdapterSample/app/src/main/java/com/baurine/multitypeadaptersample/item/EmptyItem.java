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

    private View.OnClickListener btnListener;

    public void setBtnListener(View.OnClickListener btnListener) {
        this.btnListener = btnListener;
    }

    public void onBtnClick(View btn) {
        if (btnListener != null) {
            btnListener.onClick(btn);
        }
    }

}
