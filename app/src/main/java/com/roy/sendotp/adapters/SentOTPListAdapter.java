package com.roy.sendotp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roy.sendotp.R;
import com.roy.sendotp.model.OTP;
import com.roy.sendotp.util.DateUtil;

import java.util.List;

/**
 * @author prabhat.roy
 */
public class SentOTPListAdapter extends RecyclerView.Adapter<SentOTPListAdapter.OTPViewHolder> {

    private List<OTP> mOTPList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public SentOTPListAdapter(Context context, List<OTP> oTPList) {
        this.mOTPList = oTPList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * This method initiates the creation of our view holder as
     * Recycler view by default makes use of view holder pattern
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @Override
    public OTPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_sent_otp_item, parent, false);
        OTPViewHolder viewHolder = new OTPViewHolder(view);
        return viewHolder;
    }

    /**
     * This methods bind the view and data together
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(OTPViewHolder holder, int position) {
        OTP currentOTP = mOTPList.get(position);
        holder.setData(currentOTP, position);

    }

    /**
     * This method returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (mOTPList != null)
            return mOTPList.size();
        else return 0;
    }

    public void setData(List<OTP> motpList) {
        this.mOTPList = motpList;
        notifyDataSetChanged();
    }

    /**
     * This class is our view holder class
     */
    public class OTPViewHolder extends RecyclerView.ViewHolder {

        TextView mContactName;
        TextView mOTP;
        TextView mSentTime;

        public OTPViewHolder(View itemView) {
            super(itemView);
            mContactName = (TextView) itemView.findViewById(R.id.sent_to);
            mOTP = (TextView) itemView.findViewById(R.id.otp);
            mSentTime = (TextView) itemView.findViewById(R.id.sent_at);
        }

        public void setData(OTP currentOTP, int position) {
            this.mContactName.setText(currentOTP.getContactName());
            this.mOTP.setText(currentOTP.getOTP());
            this.mSentTime.setText(DateUtil.getDisplayDate(currentOTP.getSentTime()));
            itemView.setTag(currentOTP);
        }
    }
}
