package com.roy.sendotp.util;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.roy.sendotp.model.Contact;
import com.roy.sendotp.model.OTP;

/**
 * @author prabhat.roy
 */
public class MessagingUtil {


    public void sendMessage(Context applicationContext, Contact contact, String mOTP, String message, Context context) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(contact.getPhoneNumber(), null, message, null, null);
            SharedPrefUtil.addOTP(new OTP.Builder(contact.getDisplayName(), mOTP, System.currentTimeMillis()).build(), context);
            Toast.makeText(applicationContext, "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(applicationContext, ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

        // Commented out code that would have been used to send sms through third party api
        // This feature is not working currently but we comment out the above code and uncomment this pieces of code
        // to test how error handling is happening with the help of otto event bus
  /*
       SendSMSRequest chatAuthRequest = new SendSMSRequest(to, Constants.FROM, message);
        SendOTPApiController.getInstance().post(chatAuthRequest, context);
    */
    }

}
