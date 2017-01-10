package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public class LoadingItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getType() {
        return R.layout.item_loading;
    }
}
