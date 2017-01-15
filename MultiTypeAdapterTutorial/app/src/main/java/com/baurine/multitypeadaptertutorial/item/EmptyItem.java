package com.baurine.multitypeadaptertutorial.item;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public class EmptyItem implements MultiTypeAdapter.IItem {
    @Override
    public int getType() {
        return R.layout.item_empty;
    }

    /////////////////////////////////////////////////
    public EmptyItem() {
        content = "Currently has no items, please refresh it later!";
    }

    /////////////////////////////////////////////////
    // data part
    private final String content;

    public String getContent() {
        return content;
    }
}
