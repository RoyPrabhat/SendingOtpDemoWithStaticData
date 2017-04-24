package com.roy.sendotp.api;

/**
 * Callback interface for API calls.
 *
 * @author prabhat.roy
 */
public interface ApiCallback<T> {
    void onSuccess(T response);

    void onError(Throwable error);
}
