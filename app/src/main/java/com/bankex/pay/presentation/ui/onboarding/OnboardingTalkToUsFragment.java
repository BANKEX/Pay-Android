package com.bankex.pay.presentation.ui.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.presentation.ui.view.base.BaseFragment;

/**
 * фрагмент онбординга
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingTalkToUsFragment extends BaseFragment {


    private TextView mText;

    /**
     * Возвращает новый OnboardingTalkToUsFragment
     *
     * @return OnboardingTalkToUsFragment
     */
    public static OnboardingTalkToUsFragment newInstance() {
        return new OnboardingTalkToUsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnboardingInjector.getOnboardingComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.onboarding_erc20_standart, container, false);
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
        mText = view.findViewById(R.id.onboarding_talk_to_us_body_text_view);
    }

}
