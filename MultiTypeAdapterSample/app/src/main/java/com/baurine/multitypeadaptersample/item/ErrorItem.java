package com.baurine.multitypeadaptersample.item;

import android.view.View;

import com.baurine.multitypeadaptersample.R;
import com.baurine.multitypeadaptersample.adapter.MultiTypeAdapter;

/**
 * Created by baurine on 1/10/17.
 */

public class ErrorItem implements MultiTypeAdapter.IItemType {
    @Override
    public int getType() {
        return R.layout.item_error;
    }

    /////////////////////////////////////////////////
    // data part
    private String content;

    public String getContent() {
        return content;
    }

    public ErrorItem() {
        content = "Error happened! please try it later!";
    }

    /////////////////////////////////////////////////
    // handle event

    private View.OnClickListener btnListener;

    public void setBtnListener(View.OnClickListener btnListener) {
        this.btnListener = btnListener;
    }

    public void onBtnClick(View btn) {
        if (btnListener != null) {
            btnListener.onClick(btn);
        }
    }
}
