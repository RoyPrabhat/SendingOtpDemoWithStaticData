package com.roy.sendotp.util;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;

/**
 * @author prabhat.roy
 */
public class APISupportUtil {

    /**
     * This Util method helps in setting up of elevation on api levels that support the property
     *
     * @param view      The view to which elevation is to be applied
     * @param elevation The elevation value to be applied
     */
    public static void setViewElevation(View view, float elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setElevation(elevation);
        }
    }

    /**
     * This Method requests for permission and api 23 and above
     *
     * @param activity    The activity from where you wanan request permission
     * @param requestCode The request code to be sent along with permission request
     */
    public static void getPermission(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, requestCode);
        }
    }

}
