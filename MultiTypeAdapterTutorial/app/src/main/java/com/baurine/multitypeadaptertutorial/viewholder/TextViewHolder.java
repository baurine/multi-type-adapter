package com.baurine.multitypeadaptertutorial.viewholder;

import android.databinding.ViewDataBinding;

import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;
import com.baurine.multitypeadaptertutorial.databinding.ItemTextBinding;
import com.baurine.multitypeadaptertutorial.item.TextItem;

/**
 * Created by baurine on 1/14/17.
 */

public class TextViewHolder extends ItemViewHolder {
    public TextViewHolder(ViewDataBinding binding) {
        super(binding);
    }

    public void bindTo(MulitTypeAdapter.IItem item) {
        TextItem textItem = (TextItem) item;
        ((ItemTextBinding) binding).setItem(textItem);
    }
}
