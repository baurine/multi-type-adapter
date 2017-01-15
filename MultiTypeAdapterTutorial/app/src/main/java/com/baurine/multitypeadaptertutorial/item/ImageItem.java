package com.baurine.multitypeadaptertutorial.item;

import android.view.View;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;

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

    public ImageItem(final MultiTypeAdapter adapter) {
        url = "https://unsplash.it/200/200?random&" + new Random().nextInt(40);
        liked = new Random().nextBoolean();

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_like:
                        toggleLiked();
                        adapter.notifyItemChanged(adapter.findPos(ImageItem.this));
                        break;
                    case R.id.tv_hide:
                        adapter.notifyItemRemoved(adapter.removeItem(ImageItem.this));
                        break;
                    case R.id.tv_comment:
                        // TODO: jump to another activity
                        break;
                }
            }
        });
    }

    public boolean isLiked() {
        return liked;
    }

    public void toggleLiked() {
        liked = !liked;
    }
}
