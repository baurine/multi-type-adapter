package com.baurine.multitypeadaptertutorial.item;

import android.view.View;

import com.baurine.multitypeadaptertutorial.R;
import com.baurine.multitypeadaptertutorial.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptertutorial.model.TextModel;

/**
 * Created by baurine on 1/14/17.
 */

public class TextItem extends BaseItem {
    @Override
    public int getType() {
        return R.layout.item_text;
    }

    ///////////////////////////////////////////
    private final TextModel textModel;

    public TextItem(final MultiTypeAdapter adapter, TextModel textModel) {
        this.textModel = textModel;

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_like:
                        toggleLiked();
                        adapter.notifyItemChanged(adapter.findPos(TextItem.this));
                        break;
                    case R.id.tv_hide:
                        adapter.notifyItemRemoved(adapter.removeItem(TextItem.this));
                        break;
                    case R.id.tv_comment:
                        // TODO: jump to another activity
                        break;
                }
            }
        });
    }

    public String getContent() {
        return textModel.content;
    }

    public boolean isLiked() {
        return textModel.liked;
    }

    private void toggleLiked() {
        textModel.liked = !textModel.liked;
    }
}
