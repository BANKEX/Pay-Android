package com.bankex.pay.presentation.ui.importwallet.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bankex.pay.R;
import com.bankex.pay.presentation.ui.importwallet.passphrase.ImportPassPhraseFragment;
import com.bankex.pay.presentation.ui.importwallet.privatekey.ImportPrivateKeyFragment;

/**
 * Адаптер фрагментов экрана импорта кошелька
 *
 * @author Gevork Safaryan on 22.09.2018
 */
public class ImportWalletFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 2;

    private final Context mContext;

    public ImportWalletFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = ImportPrivateKeyFragment.newInstance();
                break;
            case 1:
                fragment = ImportPassPhraseFragment.newInstance();
                break;
            default:
                fragment = ImportPrivateKeyFragment.newInstance();
                break;


        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = mContext.getString(R.string.import_private_key);
                break;
            case 1:
                title = mContext.getString(R.string.import_passphrase);
                break;
            default:
                title = mContext.getString(R.string.import_private_key);
                break;
        }
        return title;
    }
}
