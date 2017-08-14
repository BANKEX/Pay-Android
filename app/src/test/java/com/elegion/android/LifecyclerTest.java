package com.elegion.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Daniel Serdyukov
 */
@SuppressLint("CommitTransaction")
@RunWith(MockitoJUnitRunner.class)
public class LifecyclerTest {

    @Mock
    private Activity mActivity;

    @Mock
    private Bundle mSavedInstanceState;

    @Mock
    private FragmentManager mFm;

    @Mock
    private FragmentTransaction mFt;

    @Spy
    private Lifecycler mLifecycler = new Lifecycler();

    @Before
    public void setUp() throws Exception {
        Mockito.doReturn(mFm).when(mActivity).getFragmentManager();
        Mockito.doReturn(mFt).when(mFm).beginTransaction();
        Mockito.doReturn(mFt).when(mFt).add(Mockito.<Fragment>any(), Mockito.anyString());
    }

    @Test
    public void testOnActivityStarted() throws Exception {
        mLifecycler.onActivityStarted(mActivity);
        Mockito.verifyZeroInteractions(mActivity);
    }

    @Test
    public void testOnActivityResumed() throws Exception {
        mLifecycler.onActivityResumed(mActivity);
        Mockito.verifyZeroInteractions(mActivity);
    }

    @Test
    public void testOnActivityPaused() throws Exception {
        mLifecycler.onActivityPaused(mActivity);
        Mockito.verifyZeroInteractions(mActivity);
    }

    @Test
    public void testOnActivityStopped() throws Exception {
        mLifecycler.onActivityStopped(mActivity);
        Mockito.verifyZeroInteractions(mActivity);
    }

    @Test
    public void testOnActivitySaveInstanceState() throws Exception {
        mLifecycler.onActivitySaveInstanceState(mActivity, mSavedInstanceState);
        Mockito.verifyZeroInteractions(mActivity);
        Mockito.verifyZeroInteractions(mSavedInstanceState);
    }

    @Test
    public void testOnActivityDestroyed() throws Exception {
        mLifecycler.onActivityDestroyed(mActivity);
        Mockito.verifyZeroInteractions(mActivity);
    }

    @Test
    public void testIsApplicationVisible() throws Exception {
        assertThat(Lifecycler.isApplicationVisible(), is(false));
        mLifecycler.onActivityStarted(mActivity);
        assertThat(Lifecycler.isApplicationVisible(), is(true));
        mLifecycler.onActivityStopped(mActivity);
        assertThat(Lifecycler.isApplicationVisible(), is(false));
    }

    @Test
    public void testOnActivityCreated() throws Exception {
        mLifecycler.onActivityCreated(mActivity, mSavedInstanceState);
        Mockito.verifyZeroInteractions(mActivity);
    }
}