package com.baurine.multitypeadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by baurine on 1/14/17.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.ItemViewHolder> {

    public interface IItem {
        // should directly return layout
        int getType();

        // make variable id in xml configurable
        int getVariableId();
    }

    private List<IItem> items = new ArrayList<>();

    ///////////////////////////////////////////////////////

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindTo(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    ///////////////////////////////////////////////////////
    // operate items

    public List<IItem> getItems() {
        return items;
    }

    public int findPos(IItem item) {
        return items.indexOf(item);
    }

    public void setItem(IItem item) {
        clearItems();
        addItem(item);
    }

    public void setItems(List<IItem> items) {
        clearItems();
        addItems(items);
    }

    public void addItem(IItem item) {
        items.add(item);
    }

    public void addItem(IItem item, int index) {
        items.add(index, item);
    }

    public void addItems(List<IItem> items) {
        this.items.addAll(items);
    }

    public int removeItem(IItem item) {
        int pos = findPos(item);
        items.remove(item);
        return pos;
    }

    public void clearItems() {
        items.clear();
    }

    ///////////////////////////////////////////////////

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        static ItemViewHolder create(ViewGroup parent, int viewType) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    viewType, parent, false);
            return new ItemViewHolder(binding);
        }

        ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(MultiTypeAdapter.IItem item) {
            binding.setVariable(item.getVariableId(), item);
            binding.executePendingBindings();
        }
    }
}
