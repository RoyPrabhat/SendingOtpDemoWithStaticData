package com.roy.sendotp.util;

import com.roy.sendotp.constants.Constants;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author prabhat.roy
 */
public abstract class DateUtil {

    /**
     * This gives date in string format
     *
     * @param date The date to be converted to string
     * @return The string to be returned
     */
    public static String longToReadableDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        return sdf.format(date);
    }

    /**
     * This gives date in string format
     *
     * @param date The date to be converted to string
     * @return The string to be returned
     */
    public static String longToReadableTime(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
        return sdf.format(date);
    }

    /**
     * Returns a string to be displayed in the sent time view
     *
     * @param time This is time in mili seconds
     * @return The string to be displayed
     */
    public static String getDisplayDate(long time) {
        String displayDate = longToReadableDate(time) +
                Constants.BLANK_SPACE +
                longToReadableTime(time);
        return displayDate;
    }


}
