package com.roy.sendotp.util;

import java.util.Random;

/**
 * @author prabhat.roy
 */
public class RandomNumberUtil {

    /**
     * Helps generate a 6 digit random number
     *
     * @return A 6 digit random number
     */
    public static int getOTP() {
        final Random randomNumber = new Random();
        return 100000 + randomNumber.nextInt(900000);
    }
}
