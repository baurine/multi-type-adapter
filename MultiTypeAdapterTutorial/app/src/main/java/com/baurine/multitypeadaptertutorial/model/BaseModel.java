package com.baurine.multitypeadaptertutorial.model;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptertutorial.item.BaseItem;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class BaseModel {
    public BaseItem createItem(MultiTypeAdapter adapter) {
        return null;
    }

    ////////////////////////////////////////////////////////
    public int id;
    public Date createdAt;
    public Date updatedAt;

    public BaseModel() {
        id = (new Random()).nextInt(10000);
        createdAt = new Date();
        updatedAt = new Date();
    }
}
