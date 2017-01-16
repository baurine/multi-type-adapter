package com.baurine.multitypeadaptertutorial.model;

import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptertutorial.item.BaseItem;
import com.baurine.multitypeadaptertutorial.item.ImageItem;

import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class ImageModel extends BaseModel {
    @Override
    public BaseItem createItem(MultiTypeAdapter adapter) {
        return new ImageItem(adapter, this);
    }

    ////////////////////////////////////////////////////
    public String url;
    public boolean liked;

    public ImageModel() {
        super();
        url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
        liked = new Random().nextBoolean();
    }
}
