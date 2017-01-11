package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.model.TextModel;

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
    private final TextModel textModel;

    public TextItem(TextModel textModel) {
        this.textModel = textModel;
    }

    public String getText() {
        return textModel.content;
    }
}
