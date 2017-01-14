package com.baurine.multitypeadaptertutorial.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baurine.multitypeadaptertutorial.R;

/**
 * Created by baurine on 1/14/17.
 */

public class ViewHolderFactory {
    public static ItemViewHolder create(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType, parent, false);
        switch (viewType) {
            case R.layout.item_image:
                return new ImageViewHolder(binding);
            case R.layout.item_text:
                return new TextViewHolder(binding);
            default:
                return null;
        }
    }
}
