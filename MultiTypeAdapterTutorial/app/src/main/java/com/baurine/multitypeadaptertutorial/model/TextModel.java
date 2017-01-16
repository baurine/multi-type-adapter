package com.baurine.multitypeadaptertutorial.model;

import java.util.Date;
import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class TextModel {
    public final String content;
    public boolean liked;

    public TextModel() {
        content = new Date().toString();
        liked = new Random().nextBoolean();
    }
}
