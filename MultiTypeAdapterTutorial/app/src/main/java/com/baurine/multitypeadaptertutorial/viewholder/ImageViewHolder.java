package com.baurine.multitypeadaptertutorial.viewholder;

import android.view.View;

import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;
import com.baurine.multitypeadaptertutorial.item.ImageItem;

/**
 * Created by baurine on 1/14/17.
 */

public class ImageViewHolder extends ItemViewHolder {
    public ImageViewHolder(View itemView) {
        super(itemView);
    }

    public void bindTo(MulitTypeAdapter.IItem item) {
        ImageItem imageItem = (ImageItem) item;
        // then do something
    }
}
