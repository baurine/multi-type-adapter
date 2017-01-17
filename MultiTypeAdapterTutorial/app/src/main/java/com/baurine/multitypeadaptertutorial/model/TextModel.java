package com.baurine.multitypeadaptertutorial.model;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptertutorial.item.BaseItem;
import com.baurine.multitypeadaptertutorial.item.TextItem;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class TextModel extends BaseModel {
    @Override
    public BaseItem createItem(MultiTypeAdapter adapter) {
        return new TextItem(adapter, this);
    }

    ////////////////////////////////////////////////////
    public String content;
    public boolean liked;

    public TextModel() {
        super();
        content = new Date().toString();
        liked = new Random().nextBoolean();
    }
}
