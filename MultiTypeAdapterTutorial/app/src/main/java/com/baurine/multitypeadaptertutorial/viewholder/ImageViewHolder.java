package com.baurine.multitypeadaptertutorial.viewholder;

import android.databinding.ViewDataBinding;

import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;
import com.baurine.multitypeadaptertutorial.databinding.ItemImageBinding;
import com.baurine.multitypeadaptertutorial.item.ImageItem;

/**
 * Created by baurine on 1/14/17.
 */

public class ImageViewHolder extends ItemViewHolder {
    public ImageViewHolder(ViewDataBinding binding) {
        super(binding);
    }

    public void bindTo(MulitTypeAdapter.IItem item) {
        ImageItem imageItem = (ImageItem) item;
        ((ItemImageBinding) binding).setItem(imageItem);
    }
}
