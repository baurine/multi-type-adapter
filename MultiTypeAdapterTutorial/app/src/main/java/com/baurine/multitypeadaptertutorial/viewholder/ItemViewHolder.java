package com.baurine.multitypeadaptertutorial.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;

/**
 * Created by baurine on 1/14/17.
 */

public abstract class ItemViewHolder extends RecyclerView.ViewHolder {
    protected final ViewDataBinding binding;

    public ItemViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public abstract void bindTo(MulitTypeAdapter.IItem item);
}
