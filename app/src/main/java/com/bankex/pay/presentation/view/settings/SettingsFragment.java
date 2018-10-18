package com.bankex.pay.presentation.view.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.View;
import android.widget.Toast;
import com.bankex.pay.R;
import com.bankex.pay.di.settings.SettingsInjector;
import com.bankex.pay.presentation.navigation.settings.ISettingsRouter;
import com.bankex.pay.utils.dialogs.RateUsDialog;
import javax.inject.Inject;

/**
 * Settings screen fragment
 *
 * @author Pavel Apanovskiy on 12.09.2018.
 */
public class SettingsFragment extends PreferenceFragmentCompat {
	@Inject
	ISettingsRouter mSettingsRouter;

	public static SettingsFragment newInstance() {
		return new SettingsFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		SettingsInjector.getSettingsComponent().inject(this);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		addPreferencesFromResource(R.xml.settings_fragment);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initPreferences();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SettingsInjector.clearSettingsComponent();
	}

	private void initPreferences() {
		Preference network = findPreference(getString(R.string.settings_network_key));
		Preference wallets = findPreference(getString(R.string.settings_wallets_key));
		Preference security = findPreference(getString(R.string.settings_security_key));
		Preference rateUs = findPreference(getString(R.string.settings_rate_us_key));
		Preference writeUs = findPreference(getString(R.string.settings_write_us_key));
		Preference twitter = findPreference(getString(R.string.settings_twitter_key));
		Preference facebook = findPreference(getString(R.string.settings_facebook_key));

		network.setOnPreferenceClickListener(getNetworkOnClickListener());
		wallets.setOnPreferenceClickListener(getWalletsOnClickListener());
		security.setOnPreferenceClickListener(getSecurityOnClickListener());
		rateUs.setOnPreferenceClickListener(getRateUsOnClickListener());
		writeUs.setOnPreferenceClickListener(getWriteUsOnClickListener());
		twitter.setOnPreferenceClickListener(getTwitterOnClickListener());
		facebook.setOnPreferenceClickListener(getFacebookOnClickListener());
	}

	private Preference.OnPreferenceClickListener getNetworkOnClickListener() {
		return preference -> {
			Toast.makeText(getActivity(), "Network", Toast.LENGTH_SHORT).show();
			return true;
		};
	}

	private Preference.OnPreferenceClickListener getWalletsOnClickListener() {
		return preference -> {
			Toast.makeText(getActivity(), "Wallet", Toast.LENGTH_SHORT).show();
			return true;
		};
	}

	private Preference.OnPreferenceClickListener getSecurityOnClickListener() {
		return preference -> {
			Toast.makeText(getActivity(), "Security", Toast.LENGTH_SHORT).show();
			return true;
		};
	}

	private Preference.OnPreferenceClickListener getRateUsOnClickListener() {
		return preference -> {
			RateUsDialog dialog = new RateUsDialog();
			if (getFragmentManager() != null) {
				dialog.show(getFragmentManager(), getTag());
			}
			return true;
		};
	}

	private Preference.OnPreferenceClickListener getWriteUsOnClickListener() {
		return preference -> {
			mSettingsRouter.goToEmail(getContext());
			return true;
		};
	}

	private Preference.OnPreferenceClickListener getTwitterOnClickListener() {
		return preference -> {
			mSettingsRouter.goToTwitter(getContext());
			return true;
		};
	}

	private Preference.OnPreferenceClickListener getFacebookOnClickListener() {
		return preference -> {
			mSettingsRouter.goToFacebook(getContext());
			return true;
		};
	}
}
