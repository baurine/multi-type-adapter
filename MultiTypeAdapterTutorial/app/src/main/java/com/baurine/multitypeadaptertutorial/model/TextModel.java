package com.baurine.multitypeadaptertutorial.model;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class TextModel extends BaseModel {
    public String content;
    public boolean liked;

    public TextModel() {
        super();
        content = new Date().toString();
        liked = new Random().nextBoolean();
    }
}
