package com.baurine.multitypeadaptertutorial.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baurine.multitypeadaptertutorial.R;

/**
 * Created by baurine on 1/14/17.
 */

public class ViewHolderFactory {
    public static ItemViewHolder create(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.item_image:
                return new ImageViewHolder(itemView);
            case R.layout.item_text:
                return new TextViewHolder(itemView);
            default:
                return null;
        }
    }
}
