package com.baurine.multitypeadaptertutorial.item;

import android.view.View;

import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public abstract class BaseItem implements MultiTypeAdapter.IItem {
    ////////////////////////////////////////////
    // handle event
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
