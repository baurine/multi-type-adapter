package com.baurine.multitypeadaptertutorial.item;

import android.view.View;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;

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

    public TextItem(final MultiTypeAdapter adapter) {
        content = new Date().toString();
        liked = new Random().nextBoolean();

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_like:
                        toggleLiked();
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.tv_hide:
                        adapter.removeItem(TextItem.this);
                        adapter.notifyDataSetChanged();
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
