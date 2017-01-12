package com.baurine.multitypeadaptersample.util;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class CommonUtil {

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static void showToast(Context cxt, String content) {
        Toast.makeText(cxt, content, Toast.LENGTH_SHORT).show();
    }

}
