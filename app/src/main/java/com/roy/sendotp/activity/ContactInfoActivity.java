package com.roy.sendotp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.roy.sendotp.R;
import com.roy.sendotp.fragments.ContactInfoFragment;
import com.roy.sendotp.fragments.SentOTPFragment;
import com.roy.sendotp.util.ActivityUtils;
import com.roy.sendotp.widget.ToolbarBuilder;

/**
 * @author prabhat.roy
 */
public class ContactInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        //Adding toolbar to the activity
        // made use of composition pattern to make code more modular as well as testable
        setUpToolBar();

        //Adding fragment to the activity
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.info_container);
        if (fragment == null) {
            ContactInfoFragment communicationSentListFragment = new ContactInfoFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    communicationSentListFragment, R.id.info_container);
        }
    }

    /**
     * This methods helps in adding a toolbar to an activity
     */
    private void setUpToolBar() {
        new ToolbarBuilder(R.id.main_toolbar)
                .title(R.string.info_activity_toolbar_title)
                .build(this);
    }

    /**
     * This method is responds to clicks on menu items
     * It helps in proper navigation between fragments in the same activity
     *
     * @param item The item that has been clicked
     * @return a boolean to show whether the click event has been handled properly or not
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is invoked when the user presses the back button in the toolbar
     * or
     * when the user presses the back button on his mobile device
     */
    @Override
    public void onBackPressed() {
        // The following condition is to check which activity i am presently viewing
        // and follow the proper order of navigation accordingly
        if (getSupportFragmentManager().findFragmentByTag(SentOTPFragment.class.getName()) != null) {
            getSupportFragmentManager().popBackStack(ContactInfoFragment.class.getName(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

}
