package com.baurine.multitypeadaptertutorial.item;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;

import java.util.Random;

/**
 * Created by baurine on 1/14/17.
 */

public class ImageItem implements MulitTypeAdapter.IItem {
    @Override
    public int getType() {
        return R.layout.item_image;
    }

    ////////////////////////////////////////////////
    public final String url;

    public ImageItem() {
        url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
    }
}
