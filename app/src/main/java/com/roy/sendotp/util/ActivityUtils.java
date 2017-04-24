package com.roy.sendotp.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


/**
 * This provides methods to help Activities load their UI.
 *
 * @author prabhat.roy
 */
public class ActivityUtils {

    /**
     * this method is a helper method to add a fragment to an activity
     *
     * @param fragmentManager The process is carried out via the help of fragment manager
     * @param fragment        This is the fragment being added
     * @param frameId         This is the id of the fragment
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

}