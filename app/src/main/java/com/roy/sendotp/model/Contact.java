package com.roy.sendotp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roy.sendotp.constants.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * made use of builder pattern to construct an immutable data model
 * Though it best to use for complex models which is not the case here
 * but for future purposes
 * <p>
 * Also implemented the parcelable interface to efficiently transfer data in between views
 *
 * @author prabhat.roy
 */
public class Contact implements Parcelable {
    @NonNull
    private final String mFirstName;

    @NonNull
    private final String mLastName;

    @NonNull
    private final String mPhoneNumber;

    protected Contact(Parcel in) {
        mFirstName = in.readString();
        mLastName = in.readString();
        mPhoneNumber = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @NonNull
    public String getFirstName() {
        return mFirstName;
    }

    @NonNull
    public String getLastName() {
        return mLastName;
    }

    @NonNull
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    @NonNull
    public String getDisplayName() {
        return mFirstName + Constants.BLANK_SPACE + mLastName;
    }

    private Contact(Builder builder) {
        mFirstName = builder.mFirstName;
        mLastName = builder.mLastName;
        mPhoneNumber = builder.mPhoneNumber;
    }

    public static ArrayList<Contact> parseJSONList(JSONObject jsonObject) {
        String alertJson = "";
        try {
            JSONArray contactsArray = jsonObject.getJSONArray("ContactList");
            alertJson = contactsArray.toString();
        } catch (JSONException e) {
        }

        Gson gson = new Gson();

        Type collectionType = new TypeToken<ArrayList<Contact>>() {
        }.getType();
        return gson.fromJson(alertJson, collectionType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mFirstName);
        parcel.writeString(mLastName);
        parcel.writeString(mPhoneNumber);
    }

    /**
     * This is our builder class for contacts data model
     */
    public static class Builder {

        private String mFirstName;
        private String mLastName;
        private String mPhoneNumber;

        public Builder(String firstName, String lastName, String phoneNumber) {
            this.mFirstName = firstName;
            this.mLastName = lastName;
            this.mPhoneNumber = phoneNumber;
        }

        public Builder firstName(String firstName) {
            this.mFirstName = firstName;
            return this;
        }

        public Builder LastName(String lastName) {
            this.mLastName = lastName;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.mPhoneNumber = phoneNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }

    }

}
