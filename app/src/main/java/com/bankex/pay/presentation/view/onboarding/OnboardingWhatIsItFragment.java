package com.bankex.pay.presentation.view.onboarding;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.di.onboarding.OnboardingInjector;
import com.bankex.pay.presentation.view.base.BaseFragment;

/**
 * фрагмент онбординга
 *
 * @author Gevork Safaryan on 11.09.2018.
 */
public class OnboardingWhatIsItFragment extends BaseFragment {


    /**
     * Возвращает новый OnboardingWhatIsItFragment
     *
     * @return OnboardingWhatIsItFragment
     */
    public static OnboardingWhatIsItFragment newInstance() {
        return new OnboardingWhatIsItFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        OnboardingInjector.getOnboardingComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.onboarding_custom_network, container, false);
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
       /* TextView title = view.findViewById(R.id.onboarding_what_is_it_title_text_view);
        TextView text = view.findViewById(R.id.onboarding_what_is_it_body_text_view);
        ImageView icon = view.findViewById(R.id.onboarding_what_is_it_image_view);*/
    }
}
