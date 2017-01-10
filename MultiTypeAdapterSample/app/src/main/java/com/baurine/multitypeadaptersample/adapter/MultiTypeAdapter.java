package com.baurine.multitypeadaptersample.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.baurine.multitypeadaptersample.BR;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.DataBoundViewHolder> {

    protected List<IItemType> items = new ArrayList<>();

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

    public void setItem(IItemType item) {
        clearItems();
        addItem(item);
    }

    public void setItems(List<IItemType> items) {
        clearItems();
        addItems(items);
    }

    public void addItem(IItemType item) {
        items.add(item);
    }

    public void addItem(IItemType item, int index) {
        items.add(index, item);
    }

    public void addItems(List<IItemType> items) {
        this.items.addAll(items);
    }

    // we don't need updateItem() method
    // public void updateItem(item) {
    // }

    public void removeItem(IItemType item) {
        items.remove(item);
    }

    public void clearItems() {
        this.items.clear();
    }

    ////////////////////////////////////////////////////////
    public interface IItemType {
        // should directly return layout id
        int getType();

        // if you want to the variable name in xml configurable, define following method
        // int getVariableId();
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

        void bindTo(Object obj) {
            binding.setVariable(BR.item, obj);
            binding.executePendingBindings();
        }

        // if you make the variable name in xml configurable, use following `bindTo()` method
        // void bindTo(IItemType item) {
        //     binding.setVariable(item.getVariableId(), item);
        //     binding.executePendingBindings();
        // }
    }

}
