package com.baurine.multitypeadaptersample.model;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.item.ImageItem;

import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class ImageModel extends BaseModel {
    @Override
    public MultiTypeAdapter.IItemType createItem(MultiTypeAdapter adapter) {
        return new ImageItem(this, adapter);
    }

    ////////////////////////////////////////
    public final String url;
    public boolean liked;

    public ImageModel() {
        super();
        url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
        liked = (new Random()).nextBoolean();
    }

}
