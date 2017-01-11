package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.model.ImageModel;

/**
 * Created by baurine on 1/10/17.
 */

public class ImageItem extends BaseItem {
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

    public boolean isLiked() {
        return imageModel.liked;
    }

    public int getId() {
        return imageModel.id;
    }

    public void toggleLiked() {
        imageModel.liked = !imageModel.liked;
    }
}
