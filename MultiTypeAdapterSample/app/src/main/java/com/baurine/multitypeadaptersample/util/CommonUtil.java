package com.baurine.multitypeadaptersample.util;

import android.content.res.Resources;

public class CommonUtil {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
