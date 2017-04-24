package com.roy.sendotp.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author prabhat.roy
 */
public class StringUtil {

    /**
     * Converts a raw json file to a string
     *
     * @param rawId   The id of the raw json file
     * @param context A context get resources from
     * @return a string representation of the json
     */
    public static String rawToString(int rawId, Context context) {
        try {
            InputStream is = context.getResources().openRawResource(rawId);
            Writer writer = new StringWriter();
            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }

            return writer.toString();
        } catch (IOException e) {
            return "";
        }
    }

}
