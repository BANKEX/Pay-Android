package com.elegion.android.ui.profiles.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.data.model.UserProfile;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author mikhail.barannikov on 24.07.2017
 */

public class ProfileViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.top_layout)
    View mTopLayout;
    @BindView(R.id.first_name)
    TextView mFirstName;
    @BindView(R.id.last_name)
    TextView mLastName;

    private UserProfile mProfile;

    public ProfileViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(UserProfile profile) {
        mProfile = profile;
        mFirstName.setText(mProfile.getFirstName());
        mLastName.setText(mProfile.getLastName());
    }

}