package com.roy.sendotp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.roy.sendotp.R;
import com.roy.sendotp.api.BusProvider;
import com.roy.sendotp.api.VolleyErrorReceived;
import com.roy.sendotp.constants.Constants;
import com.roy.sendotp.model.Contact;
import com.roy.sendotp.util.ErrorUtil;
import com.roy.sendotp.util.MessagingUtil;
import com.roy.sendotp.util.RandomNumberUtil;
import com.roy.sendotp.util.ViewUtil;
import com.roy.sendotp.widget.TextProgressBar;
import com.squareup.otto.Subscribe;

/**
 * @author prabhat.roy
 */
public class SendOTPFragment extends Fragment {


    //This view is to display the mMessage to be sent
    private TextView mMessageView;

    //This button is to send the message
    private Button mSendButton;

    //This variable keeps track of whether the fragment is registered to otto bus or not
    private boolean isRegisteredToOttoBus = false;

    // This variable keeps track of api calls made
    private int mBusyTaskCount;

    // This is the progress bar to be displayed when we are waiting for the server response
    private TextProgressBar mProgressBar;

    // This is a placeholder for a mMessage that is to be delivered
    private String mMessage;

    // This is a placeholder for our OTP
    private String mOTP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_otp, container, false);
        // Initialise the views
        initViews(rootView);
        // display the text to be send
        displayMessage();
        // initialisation of our Event listeners
        setUpListeners();
        return rootView;
    }

    /**
     * This method helps to initialise our ui elements
     *
     * @param rootView is the Root node of the view hierarchy og this activity
     */
    private void initViews(View rootView) {
        mMessageView = (TextView) rootView.findViewById(R.id.message);
        mProgressBar = (TextProgressBar) rootView.findViewById(R.id.text_progress_bar);
        mProgressBar.setText(getString(R.string.please_wait));
        mSendButton = (Button) rootView.findViewById(R.id.send);
    }

    /**
     * This method helps us in setting up listeners to our desired events
     **/
    private void setUpListeners() {
        final Contact contact = getActivity().getIntent().getParcelableExtra(Constants.CONTACT);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessagingUtil util = new MessagingUtil();
                util.sendMessage(getActivity().getApplicationContext(), contact, mOTP, mMessage, getContext());
                // Commented out code to disable progressbar functionality
                //mMessageView.setVisibility(View.GONE);
                //mSendButton.setVisibility(View.GONE);
                //asyncTaskStart();
            }
        });
    }

    /**
     * Displays the message to be sent
     */
    private void displayMessage() {
        mMessage = getMessage();
        mMessageView.setText(mMessage);
    }


    /**
     * Constructs the message abut append the string value with the 6 digit randon number generator
     *
     * @return This the message to be sent
     */
    private String getMessage() {
        mOTP = String.valueOf(RandomNumberUtil.getOTP());
        return getString(R.string.otp_message) + mOTP;
    }

    /**
     * Thi method is invoked when an error occurs
     *
     * @param event This event is generated when an error occurs while sending a message
     */
    @Subscribe
    public void onVolleyErrorReceived(VolleyErrorReceived event) {
        String title = getString(R.string.error_title);
        String message = getString(R.string.error_message);
        showError(title, message);
        mMessageView.setVisibility(View.VISIBLE);
        mSendButton.setVisibility(View.VISIBLE);
        // commented out code to disable commented out code
        //asyncTaskComplete();
    }

    /**
     * displays a dialogue box in case of an error
     *
     * @param title   This is title of the dialogue box
     * @param message This is the message of the dialogue box
     */
    protected void showError(String title, String message) {
        ErrorUtil.buildAndDisplayErrorMessage(getContext(), title, message);
    }


    @Override
    public void onStart() {
        super.onStart();
        // register to the bus on start of the activity
        BusProvider.getInstance().register(this);
        isRegisteredToOttoBus = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        // unregister from the bus on stop of the activity
        unregisterFromOttoBus();
    }

    /**
     * Unregisters from the bus
     */
    private void unregisterFromOttoBus() {
        if (isRegisteredToOttoBus) {
            BusProvider.getInstance().unregister(this);
            isRegisteredToOttoBus = false;
        }
    }

    /**
     * Tracks the completion of an asynchronous task and calls the OnAsyncTaskCompleted when the
     * last calls completes.
     */
    protected void asyncTaskComplete() {
        mBusyTaskCount--;
        if (mBusyTaskCount == 0) {
            onAllAsyncTasksCompleted();
        }
    }

    /**
     * Tracks the start of asynchronous tasks and calls onAsyncTaskStarted when the first call is
     * started.
     * This is a good place to hide progress bars.
     */
    protected void asyncTaskStart() {
        if (mBusyTaskCount == 0) {
            onAsyncTasksStarted();
        }
        mBusyTaskCount++;
    }

    /**
     * Called when the last asynchronous task completes.
     */
    protected void onAllAsyncTasksCompleted() {
        showBusy(false);
    }

    /**
     * Called when the first asynchronous task starts.
     * This is a good place to show progress bars.
     */
    protected void onAsyncTasksStarted() {
        showBusy(true);
    }

    protected void showBusy(boolean isBusy) {
        if (mProgressBar != null) {
            ViewUtil.setVisibility(mProgressBar, isBusy);
        }
    }

}
