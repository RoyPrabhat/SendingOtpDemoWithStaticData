package com.roy.sendotp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roy.sendotp.constants.Constants;
import com.roy.sendotp.model.OTP;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * @author prabhat.roy
 */
public class SharedPrefUtil {

    private static ArrayList<OTP> mOTP = new ArrayList<>();

    /**
     * This method returns the Shared Preferences attached to The application
     *
     * @param context A context is required to access the shared preferences in an application
     * @return returns the shared preferences
     */
    private static SharedPreferences getSettings(Context context) {
        return context.getSharedPreferences(Constants.SEND_OTP_PREFS, Context.MODE_PRIVATE);
    }

    /**
     * Adds an OTP data to the Shared preferences data
     *
     * @param otp     The OTP to be added
     * @param context The context used to access shared preferences
     */
    public static void addOTP(OTP otp, Context context) {

        SharedPreferences sharedPrefs = getSettings(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        mOTP = retrieveOTPList(context);
        if (mOTP != null) {
            mOTP.add(otp);
        } else {
            mOTP = new ArrayList<>();
            mOTP.add(otp);
        }
        Gson gson = new Gson();
        String json = gson.toJson(mOTP);
        editor.putString(Constants.OTP_LIST, json);
        editor.commit();
    }

    /**
     * This method retrieves the OTP list from Shared preferences data
     *
     * @param context The context used to access shared preferences
     */
    public static ArrayList<OTP> retrieveOTPList(Context context) {
        SharedPreferences sharedPrefs = getSettings(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(Constants.OTP_LIST, null);
        Type type = new TypeToken<ArrayList<OTP>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
