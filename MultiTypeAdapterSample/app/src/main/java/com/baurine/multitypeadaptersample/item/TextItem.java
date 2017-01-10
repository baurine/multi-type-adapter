package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

import java.util.Date;

/**
 * Created by baurine on 1/10/17.
 */

public class TextItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getType() {
        return R.layout.item_text;
    }

    ////////////////////////////////////////////////
    // data model part
    private String text;

    public TextItem() {
        text = (new Date()).toString();
    }

    public String getText() {
        return text;
    }
}
