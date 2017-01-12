package com.baurine.multitypeadaptersample.model;

/**
 * Created by baurine on 1/11/17.
 */

public class ModelFaker {
    public static BaseModel fakeModel(int type) {
        if (type == 0) {
            return new ImageModel();
        } else if (type == 1) {
            return new TextModel();
        } else {
            return new FollowerModel();
        }
    }
}
