package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;

/**
 * Created by baurine on 1/10/17.
 */

public class EmptyItem extends BaseItem {
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
}
