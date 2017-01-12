package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.model.ImageModel;
import com.baurine.multitypeadaptersample.util.CommonUtil;

/**
 * Created by baurine on 1/10/17.
 */

public class ImageItem extends BaseItem {
    @Override
    public int getType() {
        return R.layout.item_image;
    }

    ///////////////////////////////////////
    public ImageItem(ImageModel imageModel, final MultiTypeAdapter adapter) {
        this.imageModel = imageModel;

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_like:
                        toggleLiked();
                        // adapter.notifyDataSetChanged();
                        adapter.notifyItemChanged(adapter.findPos(ImageItem.this));
                        break;
                    case R.id.tv_hide:
                        // adapter.removeItem(item);
                        // adapter.notifyDataSetChanged();
                        adapter.notifyItemRemoved(adapter.removeItem(ImageItem.this));
                        break;
                    case R.id.tv_comment:
                        CommonUtil.showToast(view.getContext(),
                                "TODO: comment image, id: " +
                                        String.valueOf(getId()));
                        break;
                }
            }
        });
    }

    ///////////////////////////////////////
    // data model part
    private final ImageModel imageModel;

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
