package com.elegion.android.template

import android.annotation.SuppressLint
import android.app.Activity
import android.app.FragmentManager
import android.app.FragmentTransaction
import android.os.Bundle
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@SuppressLint("CommitTransaction")
@RunWith(MockitoJUnitRunner.Silent::class)
class LifecyclerTest {

    @Mock
    private val mActivity: Activity? = null

    @Mock
    private val mSavedInstanceState: Bundle? = null

    @Mock
    private val mFm: FragmentManager? = null

    @Mock
    private val mFt: FragmentTransaction? = null

    @Spy
    private val mLifecycler = Lifecycler()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        Mockito.doReturn(mFm).`when`<Activity>(mActivity).fragmentManager
        Mockito.doReturn(mFt).`when`<FragmentManager>(mFm).beginTransaction()
        Mockito.doReturn(mFt).`when`<FragmentTransaction>(mFt).add(Mockito.any(), Mockito.anyString())
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityStarted() {
        mLifecycler.onActivityStarted(mActivity!!)
        Mockito.verifyZeroInteractions(mActivity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityResumed() {
        mLifecycler.onActivityResumed(mActivity!!)
        Mockito.verifyZeroInteractions(mActivity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityPaused() {
        mLifecycler.onActivityPaused(mActivity!!)
        Mockito.verifyZeroInteractions(mActivity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityStopped() {
        mLifecycler.onActivityStopped(mActivity!!)
        Mockito.verifyZeroInteractions(mActivity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivitySaveInstanceState() {
        mLifecycler.onActivitySaveInstanceState(mActivity!!, mSavedInstanceState!!)
        Mockito.verifyZeroInteractions(mActivity)
        Mockito.verifyZeroInteractions(mSavedInstanceState)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityDestroyed() {
        mLifecycler.onActivityDestroyed(mActivity!!)
        Mockito.verifyZeroInteractions(mActivity)
    }

    @Test
    @Throws(Exception::class)
    fun testIsApplicationVisible() {
        assertThat(Lifecycler.isApplicationVisible, `is`(false))
        mLifecycler.onActivityStarted(mActivity!!)
        assertThat(Lifecycler.isApplicationVisible, `is`(true))
        mLifecycler.onActivityStopped(mActivity)
        assertThat(Lifecycler.isApplicationVisible, `is`(false))
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityCreated() {
        mLifecycler.onActivityCreated(mActivity!!, mSavedInstanceState!!)
        Mockito.verifyZeroInteractions(mActivity)
    }
}