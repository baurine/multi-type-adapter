package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.BR;

/**
 * Created by baurine on 1/10/17.
 */

public abstract class BaseItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getVariableId() {
        return BR.item;
    }

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
