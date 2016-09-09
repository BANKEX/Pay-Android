package com.elegion.android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.elegion.android.R;
import com.elegion.android.presenter.MainPresenter;
import com.elegion.android.view.MainView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.tatarka.rxloader.RxLoaderManager;
import me.tatarka.rxloader.RxLoaderObserver;
import rx.Observable;

/**
 * @author Daniel Serdyukov
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.mainText)
    protected TextView mMainText;
    private MainPresenter mPresenter;
    private RxLoaderManager loaderManager;

    String tag = "MY_LOADER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this, this, getSupportLoaderManager());

        mPresenter.loadContent();

        loaderManager = RxLoaderManager.get(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.dispatchStart();

        loaderManager.create(tag,
                Observable.interval(2, TimeUnit.SECONDS),
                new RxLoaderObserver<Long>() {
                    @Override
                    public void onStarted() {
                        Log.d("***", "onStarted");
                        // Show your progress indicator.
                    }

                    @Override
                    public void onNext(Long result) {
                        Log.d("***", "onNext "+result);
                        mMainText.setText(""+result);
                        // Hide your progress indicator and show the result.
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d("***", "onError "+error);
                        // Hide your progress indicator and show that there was an error.
                    }
                }
        ).start(); // Make sure you call this to kick things off.
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
