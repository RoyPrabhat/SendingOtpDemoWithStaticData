package com.roy.sendotp.util;

import android.view.View;

/**
 * @author prabhat.roy
 */
public abstract class ViewUtil {

    /**
     * Sets the visibility of a view
     *
     * @param view  The view whose visibility is to be set
     * @param value The boolean value to be used to set it's appropriate visibility
     */
    public static void setVisibility(View view, boolean value) {
        setVisibility(view, value, false);
    }

    public static void setVisibility(View view, boolean value, boolean useInvisible) {
        view.setVisibility(value
                ? View.VISIBLE
                : useInvisible ? View.INVISIBLE : View.GONE);
    }

}
