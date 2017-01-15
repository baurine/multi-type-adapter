package com.baurine.multitypeadaptertutorial.item;

import com.baurine.multitypeadaptertutorial.R;

import java.util.Random;

/**
 * Created by baurine on 1/14/17.
 */

public class ImageItem extends BaseItem {
    @Override
    public int getType() {
        return R.layout.item_image;
    }

    ////////////////////////////////////////////////
    public final String url;
    private boolean liked;

    public ImageItem() {
        url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
        liked = new Random().nextBoolean();
    }

    public boolean isLiked() {
        return liked;
    }

    public void toggleLiked() {
        liked = !liked;
    }
}
