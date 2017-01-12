package com.baurine.multitypeadaptersample.item;

import com.baurine.multitypeadaptersample.R;

/**
 * Created by baurine on 1/10/17.
 */

public class ErrorItem extends BaseItem {
    @Override
    public int getType() {
        return R.layout.item_error;
    }

    /////////////////////////////////////////////////
    public ErrorItem() {
        content = "Error happened! please try it later!";
    }

    /////////////////////////////////////////////////
    // data part
    private String content;

    public String getContent() {
        return content;
    }
}
