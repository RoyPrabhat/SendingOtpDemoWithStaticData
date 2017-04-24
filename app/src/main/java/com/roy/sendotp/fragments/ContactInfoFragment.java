package com.roy.sendotp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.roy.sendotp.R;
import com.roy.sendotp.constants.Constants;
import com.roy.sendotp.model.Contact;

/**
 * @author prabhat.roy
 */
public class ContactInfoFragment extends Fragment {


    //This view is to display contact name of the desired person
    private TextView mNameView;

    //This view is to display desired person's contact number
    private TextView mContactView;

    //This Button will open a new screen using which we can initiate sending of and OTP
    private Button mSendOTP;

    // This variable is to hold a desired person's contact
    private Contact mContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_info, container, false);
        // initialisation of UI elements
        initViews(rootView);
        // setting up of event listeners
        setupListeners();
        return rootView;
    }

    /**
     * This method helps to initialise our ui elements
     *
     * @param rootView is the Root node of the view hierarchy og this activity
     */
    private void initViews(View rootView) {
        mNameView = (TextView) rootView.findViewById(R.id.contact_ful_name);
        mContactView = (TextView) rootView.findViewById(R.id.contact_number);
        mContact = getActivity().getIntent().getParcelableExtra(Constants.CONTACT);
        mNameView.setText(mContact.getDisplayName());
        mContactView.setText(String.valueOf(mContact.getPhoneNumber()));
        mSendOTP = (Button) rootView.findViewById(R.id.send_otp);
    }

    /**
     * This method helps up in setting up listener to desired events in our Fragment
     */
    private void setupListeners() {
        mSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendOTPFragment sendOTPFragment = new SendOTPFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.info_container, sendOTPFragment, sendOTPFragment.getClass().getName());
                transaction.addToBackStack(SendOTPFragment.class.getName());
                transaction.commit();
            }
        });

    }

}