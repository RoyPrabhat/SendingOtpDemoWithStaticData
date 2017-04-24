package com.roy.sendotp.model;

import android.support.annotation.NonNull;

/**
 * made use of builder pattern to construct an immutable data model
 * Though it best to use for complex models which is not the case here
 * but for future purposes
 *
 * @author prabhat.roy
 */
public class OTP {
    @NonNull
    private final String mContactName;

    @NonNull
    private final String mOtp;

    @NonNull
    private final long mSentTime;

    @NonNull
    public String getContactName() {
        return mContactName;
    }

    @NonNull
    public String getOTP() {
        return mOtp;
    }

    @NonNull
    public long getSentTime() {
        return mSentTime;
    }

    public OTP(Builder builder) {
        mContactName = builder.mContactName;
        mOtp = builder.mOTP;
        mSentTime = builder.mSentTime;
    }


    /**
     * This is our builder class for contacts data model
     */
    public static class Builder {

        private String mContactName;
        private String mOTP;
        private long mSentTime;

        public Builder(String contactName, String oTP, long sentTime) {
            this.mContactName = contactName;
            this.mOTP = oTP;
            this.mSentTime = sentTime;
        }

        public OTP.Builder contactName(String contactName) {
            this.mContactName = contactName;
            return this;
        }

        public OTP.Builder OTP(String lastName) {
            this.mOTP = lastName;
            return this;
        }

        public OTP.Builder sentTime(long sentTime) {
            this.mSentTime = sentTime;
            return this;
        }

        public OTP build() {
            return new OTP(this);
        }

    }

}
