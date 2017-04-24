package com.roy.sendotp.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.roy.sendotp.util.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * @author prabhat.roy
 */
public class ASJsonObjectRequest extends JsonObjectRequest {

    public ASJsonObjectRequest(int method, String url, JSONObject jsonRequest,
                               Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    /**
     * Thi method initializes the default headres of any api call
     * that the project might require
     *
     * @return A hashmap of list
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return SendOTPApiController.getInstance().getDefaultHeaders();
    }

    /**
     * This method makes a post request to the API
     *
     * @param request  The request object
     * @param classOfT The return type of the response that will be used to deserialize the data
     * @param <T>      The return type
     * @return returns the response
     * @throws JSONException
     */
    public static <T> ASJsonObjectRequest get(ApiRequest request, final Class<T> classOfT) throws JSONException {
        return new ASJsonObjectRequest(Request.Method.GET,
                request.getUri().toString(),
                request.getBody(),
                new DefaultListener<>(classOfT),
                new DefaultErrorListener<>(classOfT));
    }

    /**
     * This method makes a post request to the API
     *
     * @param request  The request object
     * @param classOfT The return type of the response that will be used to deserialize the data
     * @param <T>      The return type
     * @return returns the response
     * @throws JSONException
     */
    public static <T> ASJsonObjectRequest post(ApiRequest request, final Class<T> classOfT) throws JSONException {
        return new ASJsonObjectRequest(Request.Method.POST,
                request.getUri().toString(),
                request.getBody(),
                new DefaultListener<>(classOfT),
                new DefaultErrorListener<>(classOfT));
    }

    /**
     * The default listener for api responses
     *
     * @param <T> The return type of response
     */
    public static class DefaultListener<T> implements Listener<JSONObject> {
        final Class<T> classOfT;

        public DefaultListener(Class<T> classOfT) {
            this.classOfT = classOfT;
        }

        @Override
        public void onResponse(JSONObject response) {
            T apiResponse = GsonUtil.parseJson(response, classOfT);
            Log.d(apiResponse.getClass().getSimpleName(), response.toString());
            BusProvider.getInstance().post(apiResponse);
        }
    }

    /**
     * The default listener for api responses
     *
     * @param <T> The return type of error response
     */
    public static class DefaultErrorListener<T> implements Response.ErrorListener {
        final Class<T> classOfT;

        public DefaultErrorListener(Class<T> classOfT) {
            this.classOfT = classOfT;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            BusProvider.getInstance().post(new VolleyErrorReceived(error, classOfT));
        }
    }

    /**
     * Listener to be used when ApiCallback is being used to handle API responses.
     *
     * @param <T> of response to passed to ApiCallback.onSuccess()
     */
    public static class CallbackListener<T> implements Listener<JSONObject> {
        final Class<T> classOfT;
        final ApiCallback callback;

        public CallbackListener(Class<T> classOfT, ApiCallback callback) {
            this.classOfT = classOfT;
            this.callback = callback;
        }

        @Override
        public void onResponse(JSONObject response) {
            T apiResponse = GsonUtil.parseJson(response, classOfT);
            Log.d(apiResponse.getClass().getSimpleName(), response.toString());
            callback.onSuccess(apiResponse);
        }
    }

    /**
     * ErrorListener to be used when ApiCallback is being used to handle API responses.
     */
    public static class CallbackErrorListener implements Response.ErrorListener {
        final ApiCallback callback;

        public CallbackErrorListener(ApiCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            callback.onError(error);
        }
    }

}
