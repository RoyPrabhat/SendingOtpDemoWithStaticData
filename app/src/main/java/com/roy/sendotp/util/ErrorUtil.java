package com.roy.sendotp.util;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.roy.sendotp.R;

/**
 * @author prabhat.roy
 */
public class ErrorUtil {

    /**
     * Created a generic alert dialogue
     *
     * @param context The context where the dialogue needs to be configured
     * @param title   The tile of the alert dialogue
     * @param message The message of the alert dialogue
     * @return The alert dialogue build
     */
    public static AlertDialog.Builder buildGenericDialog(Context context, String title, String message) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context, R.style.AppTheme_AlertDialog);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNegativeButton(context.getText(R.string.cancel), null);
        return alertDialogBuilder;
    }

    /**
     * Shows a n alert dialogue
     *
     * @param context The context where the dialogue needs to be configured
     * @param title   The tile of the alert dialogue
     * @param message The message of the alert dialogue
     */
    public static void buildAndDisplayErrorMessage(Context context, String title, String message) {
        AlertDialog.Builder alertDialogBuilder = ErrorUtil.buildGenericDialog(context, title, message);
        alertDialogBuilder.show();
    }
}
