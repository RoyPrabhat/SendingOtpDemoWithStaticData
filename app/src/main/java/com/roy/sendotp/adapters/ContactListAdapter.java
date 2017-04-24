package com.roy.sendotp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roy.sendotp.R;
import com.roy.sendotp.model.Contact;

import java.util.List;

/**
 * @author prabhat.roy
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {

    private List<Contact> mContactList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    // Our custom defined Item click listener that we will use in order to respond to clicks
    // on a single item of our list
    // This was required because Recycler view by default do not provide us with
    // item click listeners like list views
    private final OnRecyclerItemClickListener listener;

    public ContactListAdapter(Context context, List<Contact> contactList, OnRecyclerItemClickListener listener) {
        this.mContactList = contactList;
        this.mContext = context;
        this.listener = listener;
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
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_contact_list_item, parent, false);
        ContactViewHolder viewHolder = new ContactViewHolder(view);
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
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact currentContact = mContactList.get(position);
        holder.setData(currentContact, position, listener);
    }

    /**
     * This class is our view holder class
     */
    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        int position;
        Contact contact;

        public ContactViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contact_name);
        }

        public void setData(Contact currentContact, int position, final OnRecyclerItemClickListener listener) {
            this.name.setText(currentContact.getDisplayName());
            this.contact = currentContact;
            this.position = position;
            itemView.setTag(this.contact);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemView);
                }
            });

        }
    }

    /**
     * This method returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
