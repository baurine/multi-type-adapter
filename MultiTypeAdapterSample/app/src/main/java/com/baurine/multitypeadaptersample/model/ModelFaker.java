package com.baurine.multitypeadaptersample.model;

import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class ModelFaker {
    public static BaseModel fake() {
        int type = new Random().nextInt() % 3;
        if (type == 0) {
            return new ImageModel();
        } else if (type == 1) {
            return new TextModel();
        } else {
            return new FollowerModel();
        }
    }
}
