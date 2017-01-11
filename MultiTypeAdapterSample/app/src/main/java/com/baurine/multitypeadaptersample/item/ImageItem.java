package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.model.ImageModel;

/**
 * Created by baurine on 1/10/17.
 */

public class ImageItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getType() {
        return R.layout.item_image;
    }

    ///////////////////////////////////////
    // data model part
    private ImageModel imageModel;

    public ImageItem(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public String getUrl() {
        return imageModel.url;
    }
}
