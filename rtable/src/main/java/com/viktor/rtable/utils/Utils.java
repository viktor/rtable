package com.viktor.rtable.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class Utils {

    /**
     * Convertion to DP units.
     */
    public static class Conversion {

        public static int dp(float value, Resources res){
            return (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    value,
                    res.getDisplayMetrics());
        }

    }

    /**
     * Random int between 1-999999
     * @return
     */
    public static int randomInt() {
        String intSection = String.valueOf(Math.random()).split("\\.")[1];
        return Integer.valueOf(intSection.substring(0,6));
    }

}
