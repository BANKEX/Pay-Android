package com.elegion.android.ui.profiles;

import com.arellomobile.mvp.MvpView;
import com.elegion.android.data.model.UserProfile;

import java.util.List;

/**
 * @author mikhail.barannikov on 24.07.2017
 */

interface ProfilesView extends MvpView {
    void showProfiles(List<UserProfile> profiles, boolean clear);
}
