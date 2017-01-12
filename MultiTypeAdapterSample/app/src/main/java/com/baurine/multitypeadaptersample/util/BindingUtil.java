package com.baurine.multitypeadaptersample.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingUtil {
    @BindingAdapter({"imageUrl", "error", "placeholder"})
    public static void loadImage(ImageView imgView,
                                 String url,
                                 Drawable error,
                                 Drawable placeholder) {
        Glide.with(imgView.getContext())
                .load(url)
                .error(error)
                .placeholder(placeholder)
                .into(imgView);
    }

    @BindingAdapter("android:paddingVertical")
    public static void setPaddingVertical(View view, int paddingVertical) {
        int px = CommonUtil.dpToPx(paddingVertical);
        view.setPadding(view.getPaddingLeft(), px, view.getPaddingRight(), px);
    }
}
