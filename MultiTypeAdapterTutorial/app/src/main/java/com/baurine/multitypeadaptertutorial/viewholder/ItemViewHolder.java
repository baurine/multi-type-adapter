package com.baurine.multitypeadaptertutorial.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.baurine.multitypeadaptertutorial.adapter.MulitTypeAdapter;

/**
 * Created by baurine on 1/14/17.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public ItemViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindTo(MulitTypeAdapter.IItem item) {
        binding.setVariable(BR.item, item);
    }
}
