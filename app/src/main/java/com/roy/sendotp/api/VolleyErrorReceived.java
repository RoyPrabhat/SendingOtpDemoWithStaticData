package com.roy.sendotp.api;


import com.android.volley.VolleyError;

import java.lang.reflect.Type;

/**
 * @author prabhat.roy
 */
public class VolleyErrorReceived {

    public VolleyError error;
    public String callType;
    public Type typeOfT;

    public VolleyErrorReceived(VolleyError error, Type typeOfT) {
        this.error = error;
        this.typeOfT = typeOfT;
        this.callType = typeOfT.toString();
    }

}
