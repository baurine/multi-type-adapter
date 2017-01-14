package com.baurine.multitypeadaptersample.model;

import com.baurine.multitypeadapter.MultiTypeAdapter;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public abstract class BaseModel {

    public abstract MultiTypeAdapter.IItem createItem(MultiTypeAdapter adapter);

    /////////////////////////////////////////////////////////////////////
    public final int id;
    public final Date createdAt;
    public final Date updatedAt;

    public BaseModel() {
        id = (new Random()).nextInt(10000);
        createdAt = new Date();
        updatedAt = new Date();
    }
}
