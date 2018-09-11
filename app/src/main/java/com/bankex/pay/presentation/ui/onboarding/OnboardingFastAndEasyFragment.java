package com.bankex.pay.presentation.ui.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.presentation.ui.base.BaseFragment;

/**
 * фрагмент онбординга
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingFastAndEasyFragment extends BaseFragment {


    /**
     * Возвращает новый OnboardingFastAndEasyFragment
     *
     * @return OnboardingFastAndEasyFragment
     */
    public static OnboardingFastAndEasyFragment newInstance() {
        return new OnboardingFastAndEasyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnboardingInjector.getOnboardingComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.onboarding_favorite_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OnboardingInjector.clearOnboardingComponent();
    }

    private void initViews(View view) {

    }
}
