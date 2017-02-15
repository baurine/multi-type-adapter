package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.model.TextModel;
import com.baurine.multitypeadaptersample.util.CommonUtil;
import com.baurine.multitypeadaptersample.util.DateFormatUtil;

/**
 * Created by baurine on 1/10/17.
 */

public class TextItem extends BaseItem {
    @Override
    public int getLayout() {
        return R.layout.item_text;
    }

    ////////////////////////////////////////////////
    public TextItem(TextModel textModel, final MultiTypeAdapter adapter) {
        this.textModel = textModel;

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_like:
                        toggleLiked();
                        // adapter.notifyDataSetChanged();
                        adapter.notifyItemChanged(adapter.findPos(TextItem.this));
                        break;
                    case R.id.tv_hide:
                        // adapter.removeItem(item);
                        // adapter.notifyDataSetChanged();
                        adapter.notifyItemRemoved(adapter.removeItem(TextItem.this));
                        break;
                    case R.id.tv_comment:
                        CommonUtil.showToast(view.getContext(),
                                "TODO: comment text, id: " +
                                        String.valueOf(getId()));
                        break;
                }
            }
        });
    }

    ////////////////////////////////////////////////
    // data model part
    private final TextModel textModel;

    public String getText() {
        return DateFormatUtil.formatTime(textModel.createdAt) + " - " + textModel.content;
    }

    public boolean isLiked() {
        return textModel.liked;
    }

    public int getId() {
        return textModel.id;
    }

    public void toggleLiked() {
        textModel.liked = !textModel.liked;
    }
}
