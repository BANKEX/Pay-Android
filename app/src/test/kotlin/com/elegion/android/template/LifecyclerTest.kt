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
    private val activity: Activity? = null

    @Mock
    private val savedInstanceState: Bundle? = null

    @Mock
    private val fragmentManager: FragmentManager? = null

    @Mock
    private val fragmentTransaction: FragmentTransaction? = null

    @Spy
    private val lifecycler = Lifecycler()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        Mockito.doReturn(fragmentManager).`when`<Activity>(activity).fragmentManager
        Mockito.doReturn(fragmentTransaction).`when`<FragmentManager>(fragmentManager).beginTransaction()
        Mockito.doReturn(fragmentTransaction).`when`<FragmentTransaction>(fragmentTransaction).add(Mockito.any(), Mockito.anyString())
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityStarted() {
        lifecycler.onActivityStarted(activity!!)
        Mockito.verifyZeroInteractions(activity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityResumed() {
        lifecycler.onActivityResumed(activity!!)
        Mockito.verifyZeroInteractions(activity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityPaused() {
        lifecycler.onActivityPaused(activity!!)
        Mockito.verifyZeroInteractions(activity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityStopped() {
        lifecycler.onActivityStopped(activity!!)
        Mockito.verifyZeroInteractions(activity)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivitySaveInstanceState() {
        lifecycler.onActivitySaveInstanceState(activity!!, savedInstanceState!!)
        Mockito.verifyZeroInteractions(activity)
        Mockito.verifyZeroInteractions(savedInstanceState)
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityDestroyed() {
        lifecycler.onActivityDestroyed(activity!!)
        Mockito.verifyZeroInteractions(activity)
    }

    @Test
    @Throws(Exception::class)
    fun testIsApplicationVisible() {
        assertThat(Lifecycler.isApplicationVisible, `is`(false))
        lifecycler.onActivityStarted(activity!!)
        assertThat(Lifecycler.isApplicationVisible, `is`(true))
        lifecycler.onActivityStopped(activity)
        assertThat(Lifecycler.isApplicationVisible, `is`(false))
    }

    @Test
    @Throws(Exception::class)
    fun testOnActivityCreated() {
        lifecycler.onActivityCreated(activity!!, savedInstanceState!!)
        Mockito.verifyZeroInteractions(activity)
    }
}