package com.elegion.android.ui.profiles.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elegion.android.R;
import com.elegion.android.data.model.UserProfile;
import com.elegion.android.ui.base.adapter.AbstractPaginationAdapter;
import com.elegion.android.ui.profiles.adapter.holder.ProfileViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikhail.barannikov on 24.07.2017
 */

public class ProfilesAdapter extends AbstractPaginationAdapter<ProfileViewHolder> {
    private final List<UserProfile> mItems = new ArrayList<>();

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_profile, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(List<UserProfile> items, boolean clear) {
        if (clear) {
            mItems.clear();
        }
        mItems.addAll(items);
        notifyDataSetChanged();
    }
}
