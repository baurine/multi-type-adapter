package com.baurine.multitypeadaptertutorial.item;

import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.baurine.multitypeadapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public abstract class BaseItem implements MultiTypeAdapter.IItem {
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
