package com.baurine.multitypeadaptertutorial.model;

import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class ImageModel {
    public final String url;
    public boolean liked;

    public ImageModel() {
        url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
        liked = new Random().nextBoolean();
    }
}
