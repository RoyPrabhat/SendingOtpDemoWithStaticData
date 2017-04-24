package com.roy.sendotp.activity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.roy.sendotp.R;
import com.roy.sendotp.adapters.ViewPagerAdapter;
import com.roy.sendotp.fragments.ContactListFragment;
import com.roy.sendotp.fragments.SentOTPFragment;
import com.roy.sendotp.util.APISupportUtil;
import com.roy.sendotp.util.ErrorUtil;
import com.roy.sendotp.widget.ToolbarBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prabhat.roy
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    //This is our tab layout
    private TabLayout mTabLayout;

    //This is our list containing fragment details required for tab layout
    private List<Fragment> mFragmentList;

    //This is our tab title list
    private List<String> mTitleList;

    //This is our mViewPager
    private ViewPager mViewPager;

    //This is rrquest code to request send permission
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Adding toolbar to the activity
        // made use of composition pattern to make code more modular as well as testable
        setUpToolBar();

        //initialization of views
        initViews();

        //setting up of tab layout
        setUpTabLayout();

        // Requesting for Send SMS permission ataPi level 23 or above
        APISupportUtil.getPermission(this, MY_PERMISSIONS_REQUEST_SEND_SMS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "WE HAVE THE PERMISSION NOW");
                } else {
                    //TODO gracefully handle permission being denied by the user
                    // instead of null show a dialogue on click of it
                    ErrorUtil.buildAndDisplayErrorMessage(this, getString(R.string.permision_error_title),
                            getString(R.string.permission_error_message));
                }
                return;
            }
        }
    }

    /**
     * This methods helps in adding a toolbar to an activity
     */
    private void setUpToolBar() {
        new ToolbarBuilder(R.id.main_toolbar)
                .title(R.string.main_activity_toolbar_title)
                .build(this);
    }

    /**
     * This methods helps in initialising UI elements of our activity
     */
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    /**
     * This method helps us in setting up our Tab Layout
     */
    private void setUpTabLayout() {

        initializeTabDataSource();

        //Adding the tabs using addTab() method
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList, mTitleList);

        //Adding adapter to pager
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * This methods helps up with setting up al the data required for building our tab layout
     */
    private void initializeTabDataSource() {
        mFragmentList = new ArrayList<>();
        mTitleList = new ArrayList<>();
        setFragmentList();
        setTitleList();
    }

    /**
     * This method sets up our fragmnets that are to be displayed in our tab layout
     */
    private void setFragmentList() {
        mFragmentList.add(new ContactListFragment());
        mFragmentList.add(new SentOTPFragment());
    }

    /**
     * This method sets up titles of our tab layouts
     */
    private void setTitleList() {
        mTitleList.add(getString(R.string.contact_list));
        mTitleList.add(getString(R.string.otp_history));
    }

}
