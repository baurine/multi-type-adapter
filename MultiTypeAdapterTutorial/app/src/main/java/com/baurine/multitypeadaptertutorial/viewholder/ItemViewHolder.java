package com.baurine.multitypeadaptertutorial.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;

/**
 * Created by baurine on 1/14/17.
 */

public abstract class ItemViewHolder extends RecyclerView.ViewHolder {
    public ItemViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindTo(MulitTypeAdapter.IItem item);
}
