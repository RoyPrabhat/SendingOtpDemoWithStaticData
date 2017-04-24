package com.roy.sendotp.api;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;
import com.roy.sendotp.util.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author prabhat.roy
 */
public class SendSMSRequest extends ApiRequest<SendSMSRequest.Response> {

    @SerializedName("To")
    private String to;

    @SerializedName("From")
    private String from;

    @SerializedName("Body")
    private String body;


    public SendSMSRequest(String to, String from, String body) {
        super(Response.class);
        this.to = to;
        this.from = from;
        this.body = body;
    }

    /**
     * Gets the URI for the request.
     */
    @Override
    public Uri getUri() {
        return getAuthenticateUri().build();
    }

    public static Uri.Builder getAuthenticateUri() {
        return getApiEndpointUri().appendEncodedPath(SendOTPApiController.BASE_URL);
    }

    @Override
    public JSONObject getBody() throws JSONException {
        return new JSONObject(GsonUtil.toJson(this));
    }

    /**
     * This is to provide the data model of the response to the request
     * Currently as I was not able to hit the right url
     */
    public static class Response {
    }
}