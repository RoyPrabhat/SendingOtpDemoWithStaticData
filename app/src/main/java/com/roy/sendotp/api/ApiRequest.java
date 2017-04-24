package com.roy.sendotp.api;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The base class for all API requests objects.
 *
 * @author prabhat.roy
 */
public abstract class ApiRequest<TResponse> {
    private transient final Class<TResponse> classOfTResponse;

    /**
     * The tag used to group logging statements.
     */
    private transient String tag = "API_REQUEST";

    private transient Uri.Builder uriBuilder;

    /**
     * Constructs ApiRequest with the type of response object for this request.
     *
     * @param classOfTResponse type of response object for this request.
     */
    public ApiRequest(Class<TResponse> classOfTResponse) {
        this.classOfTResponse = classOfTResponse;
    }

    /**
     * Gets the class type to be used for the response of this request.
     *
     * @return
     */
    public Class<TResponse> getResponseClass() {
        return classOfTResponse;
    }

    /**
     * Gets the URI for the request.
     *
     * @return the URI for the current request.
     */
    public abstract Uri getUri();

    /**
     * Gets the body for this request.
     *
     * @return the JSON to be POST as the body of this request.
     */
    public JSONObject getBody() throws JSONException {
        return null;
    }

    /**
     * Gets the tag for this request.
     * <p>
     * The tag is primarily used to group logging statements together for tracking an event
     * through the call stack.
     *
     * @return tag for the current action being performed.
     */
    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Fluent method for tagging the request
     *
     * @param tag for the operation owning this request.
     */
    public ApiRequest<TResponse> tag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Gets the a {@link Uri.Builder} initialized with the API endpoint root path.
     *
     * @return a builder that is initialized with the API endpoint root path.
     */
    protected Uri.Builder getUriBuilder() {
        if (uriBuilder == null) {
            uriBuilder = getApiEndpointUri();
        }
        return uriBuilder;
    }

    public static Uri.Builder getApiEndpointUri() {
        Uri.Builder builder = new Uri.Builder();
        // TODO append your api path here this code is to mke the api call scalabe for future uses
        return builder;
    }

}
