package com.baurine.multitypeadaptersample.model;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.item.FollowerItem;

import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class FollowerModel extends BaseModel {
    @Override
    public MultiTypeAdapter.IItemType createItem() {
        return new FollowerItem(this);
    }

    ///////////////////////////////////////
    private static final String[] FOLLOWERS = new String[]{
            "习大大", "奥巴马", "川普", "普京"
    };

    ////////////////////////////////////////
    public final String follower;
    public final int avatarResId;
    public boolean followed;

    public FollowerModel() {
        super();
        int index = new Random().nextInt(100) % 4;
        follower = FOLLOWERS[index];
        avatarResId = R.drawable.ic_launcher;
        followed = new Random().nextBoolean();
    }
}
