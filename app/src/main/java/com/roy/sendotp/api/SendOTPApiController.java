package com.roy.sendotp.api;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * @author prabhat.roy
 */
public class SendOTPApiController extends Application {

    private static SendOTPApiController controller;
    private static Map<String, String> defaultHeaders;

    public static final String ACCOUNT_SID = "AC52b8ef3805ad8dc4adce8ba8ed9699d9";
    public static final String AUTH_TOKEN = "41622eae83e1f4495e3d1b8beef097f1";
    public static final String BASE_URL =
            "https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/SMS/Messages";

    public static final int RETRY_ONCE = 1;
    private static RequestQueue requestQueue;

    /**
     * @return instance of singleton SendOTPApiController object
     */
    public static SendOTPApiController getInstance() {
        if (controller == null) {
            controller = new SendOTPApiController();
        }
        return controller;
    }

    /**
     * Sends the API request using the GET verb.
     *
     * @param request the request to be sent.
     */
    public void get(ApiRequest request, Context context) {
        try {
            addRequestToQueueWithRetry(ASJsonObjectRequest.get(request, request.getResponseClass()), request.getTag(), context);
        } catch (Exception ex) {
            BusProvider.getInstance().post(new VolleyErrorReceived(new VolleyError(ex), request.getResponseClass()));
        }
    }

    /**
     * Sends the API request use the GET verb.
     * NOTE: This method uses a callback in place of firing events on the Otto bus.
     *
     * @param request
     * @param callback the callback that handles onSuccess and onError.
     */
    public void get(ApiRequest request, ApiCallback callback, Context context) {
        try {
            ASJsonObjectRequest restRequest = new ASJsonObjectRequest(Request.Method.GET,
                    request.getUri().toString(),
                    request.getBody(),
                    new ASJsonObjectRequest.CallbackListener<>(request.getResponseClass(), callback),
                    new ASJsonObjectRequest.CallbackErrorListener(callback));
            addRequestToQueueWithRetry(restRequest, request.getTag(), context);
        } catch (Exception ex) {
            callback.onError(ex);
        }
    }


    /**
     * This method makes a post call to the Api path provided to it
     *
     * @param request This is the api request object
     * @param context This the the context from there the api call is being made
     */
    public void post(ApiRequest request, Context context) {
        try {
            addRequestToQueueWithRetry(ASJsonObjectRequest.post(request, request.getResponseClass()), request.getTag(), context);
        } catch (Exception ex) {
            BusProvider.getInstance().post(new VolleyErrorReceived(new VolleyError(ex), request.getResponseClass()));
        }
    }

    /**
     * Initialize the default headers used by all requests.  Must be called before all calls.
     */
    public void initializeHeaders() {

        String base64EncodedCredentials = Base64.encodeToString((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes(), Base64.NO_WRAP);
        defaultHeaders = new HashMap<>();
        defaultHeaders.put("Basic", base64EncodedCredentials);
    }

    /**
     * Get the default headers used by all requests.
     */
    public Map<String, String> getDefaultHeaders() {

        if (defaultHeaders == null) {
            initializeHeaders();
            return defaultHeaders;
        }

        return defaultHeaders;
    }

    /**
     * This is a method to add request to the request queue
     *
     * @param request This the request to be added to the queue
     * @param tag     This is the tag associated with the request
     * @param context This is the context from where the call is being made
     */
    private void addRequestToQueueWithRetry(Request<?> request, String tag, Context context) {
        request.setRetryPolicy(new DefaultRetryPolicy(14000,
                SendOTPApiController.RETRY_ONCE,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        request.setShouldCache(false);

        SendOTPApiController.getInstance().addToRequestQueue(request, tag, context);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag, Context context) {

        req.setTag(TextUtils.isEmpty(tag) ? "ROY" : tag);

        VolleyLog.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue(context).add(req);
    }

    /**
     * This method returns the request queue
     *
     * @param context This context is required to get the request queue
     * @return
     */
    public RequestQueue getRequestQueue(Context context) {

        //we create the request queue if it is null here
        //and then always return it
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }


}