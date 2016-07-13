package com.elegion.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.presenter.MainPresenter;
import com.elegion.android.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Daniel Serdyukov
 */
public class MainActivity extends Activity implements MainView {

    @BindView(R.id.mainText)
    protected TextView mMainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        final MainPresenter presenter = new MainPresenter(this, this);
        presenter.loadContent();
    }

    @Override
    public void showMainText(@NonNull String string) {
        mMainText.setText(string);
    }
}
