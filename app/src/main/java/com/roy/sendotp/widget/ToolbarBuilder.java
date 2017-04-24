package com.roy.sendotp.widget;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roy.sendotp.R;
import com.roy.sendotp.util.APISupportUtil;

/**
 * Builds the the primary ActionBar for an Activity.
 *
 * @author prabhat.roy
 */
public class ToolbarBuilder {
    @IdRes
    final int mId;

    @StringRes
    int mTitleResId;
    String mHexColorCode;

    /**
     * sets id of the toolbar
     *
     * @param id The id to be set for the toolbar
     */
    public ToolbarBuilder(@IdRes int id) {
        mId = id;
    }

    /**
     * sets the title of the toolbar
     *
     * @param resId The resource id of the toolbar
     * @return The toolbar is returned
     */
    public ToolbarBuilder title(@StringRes int resId) {
        mTitleResId = resId;
        return this;
    }

    /**
     * Sets the background color of the toolbar
     *
     * @param hexColorCode The color to be set as background color
     * @return
     */
    public ToolbarBuilder backgroundColor(String hexColorCode) {
        mHexColorCode = hexColorCode;
        return this;
    }

    /**
     * Builds the toolbar for the activity
     *
     * @param activity The activity for which the toolbar is required
     * @return The reference of the toolbar that was built
     */
    public Toolbar build(AppCompatActivity activity) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.main_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            if (mTitleResId > 0) {
                actionBar.setTitle(mTitleResId);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        APISupportUtil.setViewElevation(toolbar, activity.getResources().getDimension(R.dimen.toolbar_elevation));
        return toolbar;
    }

}

