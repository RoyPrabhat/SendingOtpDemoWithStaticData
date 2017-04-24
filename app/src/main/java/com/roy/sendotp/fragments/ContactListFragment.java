package com.roy.sendotp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roy.sendotp.R;
import com.roy.sendotp.activity.ContactInfoActivity;
import com.roy.sendotp.adapters.ContactListAdapter;
import com.roy.sendotp.adapters.OnRecyclerItemClickListener;
import com.roy.sendotp.constants.Constants;
import com.roy.sendotp.model.Contact;
import com.roy.sendotp.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prabhat.roy
 */
public class ContactListFragment extends Fragment {

    // This is our place holder for recycler view
    private RecyclerView mRecyclerView;
    // This is a placeholder for our contact list
    private List<Contact> mContactList;
    // This is our place holder for the adapter
    ContactListAdapter mContactListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);
        // initialisation of views
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
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.contacts_list);
        mContactList = new ArrayList<>();
        mContactList = getContactList();
    }

    /**
     * This methods helps us in getting the data i.e. that list of contacts
     * in this scenario to be displayed in our recycler view
     *
     * @return the list of contacts
     */
    private List<Contact> getContactList() {

        JSONObject jsonObject = new JSONObject();
        String json = StringUtil.rawToString(R.raw.raw_user_list, getContext());
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Contact.parseJSONList(jsonObject);
    }

    /**
     * This method helps us in setting up our recycler view
     **/
    private void setUpRecyclerView() {
        mContactListAdapter = new ContactListAdapter(getContext(), mContactList, new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View contactItem) {
                Intent contactInfoIntent = new Intent(getActivity(), ContactInfoActivity.class);
                contactInfoIntent.putExtra(Constants.CONTACT, ((Contact) contactItem.getTag()));
                getActivity().startActivity(contactInfoIntent);
            }
        });
        mRecyclerView.setAdapter(mContactListAdapter);
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(l);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
