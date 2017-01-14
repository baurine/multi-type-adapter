package com.baurine.multitypeadaptertutorial.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by baurine on 1/14/17.
 */

public class ViewHolderFactory {
    public static ItemViewHolder create(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType, parent, false);
        return new ItemViewHolder(binding);
    }
}
