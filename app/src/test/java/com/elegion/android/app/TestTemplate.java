package com.elegion.android.app;

import android.text.TextUtils;

import com.elegion.android.BuildConfig;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * @author Rovkin Max
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = BuildConfig.TEST_SDK_INT)
public class TestTemplate {
    @Test
    public void testFailedMethod() throws Exception {
        Assert.assertThat(TextUtils.isEmpty("  "), IsEqual.equalTo(true));
    }
}
