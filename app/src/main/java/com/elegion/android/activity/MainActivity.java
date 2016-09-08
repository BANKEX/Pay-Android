package com.elegion.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.presenter.MainPresenter;
import com.elegion.android.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Daniel Serdyukov
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
        mPresenter = new MainPresenter(this, this, getSupportLoaderManager());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.loadContent();
    }

    @Override
    public void showMainText(@NonNull String string) {
        mMainText.setText(string);
    }
}
