package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadapter.MultiTypeAdapter;
import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.model.FollowerModel;

/**
 * Created by baurine on 1/10/17.
 */

public class FollowerItem extends BaseItem {
    @Override
    public int getLayout() {
        return R.layout.item_follower;
    }

    ////////////////////////////////////////////////
    public FollowerItem(FollowerModel followerModel, final MultiTypeAdapter adapter) {
        this.followerModel = followerModel;

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFollowed();
                adapter.notifyItemChanged(adapter.findPos(FollowerItem.this));
            }
        });
    }

    ////////////////////////////////////////////////
    // data model part
    private final FollowerModel followerModel;

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
