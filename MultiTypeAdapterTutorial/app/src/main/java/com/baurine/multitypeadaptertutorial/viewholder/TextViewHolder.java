package com.baurine.multitypeadaptertutorial.viewholder;

import android.view.View;

import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;
import com.baurine.multitypeadaptertutorial.item.TextItem;

/**
 * Created by baurine on 1/14/17.
 */

public class TextViewHolder extends ItemViewHolder {
    public TextViewHolder(View itemView) {
        super(itemView);
    }

    public void bindTo(MulitTypeAdapter.IItem item) {
        TextItem textItem = (TextItem) item;
        // then do something
    }
}
