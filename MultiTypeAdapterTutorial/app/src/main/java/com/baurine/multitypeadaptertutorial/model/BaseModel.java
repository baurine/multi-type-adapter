package com.baurine.multitypeadaptertutorial.model;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class BaseModel {

    public int id;
    public Date createdAt;
    public Date updatedAt;

    public BaseModel() {
        id = (new Random()).nextInt(10000);
        createdAt = new Date();
        updatedAt = new Date();
    }
}
