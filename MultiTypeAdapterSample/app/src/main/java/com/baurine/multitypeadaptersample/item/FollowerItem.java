package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.model.FollowerModel;

/**
 * Created by baurine on 1/10/17.
 */

public class FollowerItem extends BaseItem {
    @Override
    public int getType() {
        return R.layout.item_follower;
    }

    ////////////////////////////////////////////////
    // data model part
    private FollowerModel followerModel;

    public FollowerItem(FollowerModel followerModel) {
        this.followerModel = followerModel;
    }

    public String getContent() {
        return followerModel.follower + " 关注了你！";
    }

    public int getAvatarResId() {
        return followerModel.avatarResId;
    }

    public boolean isFollowed() {
        return followerModel.followed;
    }

    public void toggleFollowed() {
        followerModel.followed = !followerModel.followed;
    }
}
