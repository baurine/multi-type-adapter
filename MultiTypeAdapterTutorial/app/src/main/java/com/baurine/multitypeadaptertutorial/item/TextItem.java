package com.baurine.multitypeadaptertutorial.item;

import com.baurine.multitypeadaptertutorial.R;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/14/17.
 */

public class TextItem extends BaseItem {
    @Override
    public int getType() {
        return R.layout.item_text;
    }

    ///////////////////////////////////////////
    public final String content;
    private boolean liked;

    public TextItem() {
        content = new Date().toString();
        liked = new Random().nextBoolean();
    }

    public boolean isLiked() {
        return liked;
    }

    public void toggleLiked() {
        liked = !liked;
    }
}
