package com.roy.sendotp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roy.sendotp.R;
import com.roy.sendotp.adapters.SentOTPListAdapter;
import com.roy.sendotp.model.OTP;
import com.roy.sendotp.util.SharedPrefUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author prabhat.roy
 */
public class SentOTPFragment extends Fragment {

    // This is our place holder for recycler view
    private RecyclerView mRecyclerView;
    // This is a placeholder for our contact list
    private List<OTP> motpList;

    // This is our adapter that is to be used to display data in our recycler view
    private SentOTPListAdapter mSentOTPListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sent_otp, container, false);
        // initialisation of UI elements
        initViews(rootView);
        // initialisation of our recycler view
        setUpRecyclerView();
        return rootView;
    }

    /**
     * This method helps to initialise our ui elements
     *
     * @param rootView is the Root node of the view hierarchy og this activity
     */
    private void initViews(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.sent_otp_list);
        motpList = new ArrayList<>();
        motpList = SharedPrefUtil.retrieveOTPList(getContext());
    }

    /**
     * This method helps us in setting up our recycler view
     **/
    private void setUpRecyclerView() {
        reverseOTPList();
        mSentOTPListAdapter = new SentOTPListAdapter(getContext(), motpList);
        mRecyclerView.setAdapter(mSentOTPListAdapter);
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(l);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * This function is to properly order the our OTP list so that it is displayed in the right order
     */
    private void reverseOTPList() {
        if (motpList != null) {
            Collections.reverse(motpList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        motpList = SharedPrefUtil.retrieveOTPList(getContext());
        reverseOTPList();
        mSentOTPListAdapter.setData(motpList);
    }
}
