package com.roy.sendotp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * @author prabhat.roy
 */
public class GsonUtil {

    /**
     * Provides an instantiation of the GSON Class
     *
     * @return
     */
    public Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }

    /**
     * All the following methods helps in parsing from json to class and vice versa
     */
    public static <T> T parseJson(JSONObject json, Class<T> classOfT) {
        return parseJson(json.toString(), classOfT);
    }

    public static <T> T parseJson(String json, Class<T> classOfT) {
        return new GsonUtil().getGson().fromJson(json, classOfT);
    }

    public static String toJson(Object src) {
        return new GsonUtil().getGson().toJson(src);
    }
}
