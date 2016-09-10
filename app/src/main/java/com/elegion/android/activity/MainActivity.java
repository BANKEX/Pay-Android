package com.elegion.android.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.presenter.MainPresenter;
import com.elegion.android.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nikita Bumakov
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.mainText)
    protected TextView mMainText;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, this, getLoaderManager());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.dispatchStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.dispatchStop();
    }

    @Override
    public void showMainText(@NonNull String string) {
        mMainText.setText(string);
    }
}
