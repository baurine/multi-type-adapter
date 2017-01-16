package com.baurine.multitypeadaptertutorial.model;

import java.util.Random;

/**
 * Created by baurine on 1/16/17.
 */

public class ModelFaker {
    public static BaseModel fake() {
        String type = new Random().nextBoolean() ? "image" : "text";
        if (type.equals("image")) {
            return new ImageModel();
        } else {
            return new TextModel();
        }
    }
}
