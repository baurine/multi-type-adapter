package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

import java.util.Random;

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
    private final String url;

    public ImageItem() {
        url = "https://unsplash.it/100/100?random&r=" + new Random().nextInt();
    }

    public String getUrl() {
        return url;
    }
}
