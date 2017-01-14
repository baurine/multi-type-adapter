package com.baurine.multitypeadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baurine on 1/10/17.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.DataBoundViewHolder> {
    public interface IItem {
        // should directly return layout
        int getType();

        // if you want to the variable name in xml configurable, define following method
        int getVariableId();
    }

    private List<IItem> items = new ArrayList<>();

    ////////////////////////////////////////////////////////
    @Override
    public DataBoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DataBoundViewHolder.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(DataBoundViewHolder holder, int position) {
        holder.bindTo(items.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    ////////////////////////////////////////////////////////
    // default items operation
    // you also can inherit MultiTypeAdapter to implement more methods to operate items
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

    // we don't need updateItem() method
    // public void updateItem(item) {
    // }

    public int removeItem(IItem item) {
        int pos = findPos(item);
        items.remove(item);
        return pos;
    }

    public void clearItems() {
        items.clear();
    }

    ////////////////////////////////////////////////////////
    static class DataBoundViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        static DataBoundViewHolder create(ViewGroup parent, int viewType) {
            ViewDataBinding binding =
                    DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                            viewType, parent, false);
            return new DataBoundViewHolder(binding);
        }

        DataBoundViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // this method doesn't work if this file is depended as a lib
        // void bindTo(Object obj) {
        //     binding.setVariable(BR.item, obj);
        //     binding.executePendingBindings();
        // }

        // if you make the variable name in xml configurable, use following `bindTo()` method
        void bindTo(IItem item) {
            binding.setVariable(item.getVariableId(), item);
            binding.executePendingBindings();
        }
    }

}
