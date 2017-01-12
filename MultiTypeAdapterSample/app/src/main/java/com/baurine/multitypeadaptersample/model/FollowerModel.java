package com.baurine.multitypeadaptersample.model;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.item.FollowerItem;

import java.util.Random;

/**
 * Created by baurine on 1/11/17.
 */

public class FollowerModel extends BaseModel {
    @Override
    public MultiTypeAdapter.IItemType createItem(MultiTypeAdapter adapter) {
        return new FollowerItem(this, adapter);
    }

    ///////////////////////////////////////
    private static final String[] FOLLOWERS = new String[]{
            "习大大", "奥巴马", "川普", "普京"
    };
    private static final int[] AVATARS = new int[]{
            R.drawable.ic_xidada,
            R.drawable.ic_obama,
            R.drawable.ic_djtrump,
            R.drawable.ic_dlputin
    };

    ////////////////////////////////////////
    public final String follower;
    public final int avatarResId;
    public boolean followed;

    public FollowerModel() {
        super();
        int index = new Random().nextInt(100) % 4;
        follower = FOLLOWERS[index];
        avatarResId = AVATARS[index];
        followed = new Random().nextBoolean();
    }
}
