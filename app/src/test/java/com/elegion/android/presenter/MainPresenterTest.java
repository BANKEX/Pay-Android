package com.elegion.android.presenter;

import android.content.Context;

import com.elegion.android.R;
import com.elegion.android.view.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 * @author Rovkin Max
 */
@RunWith(JUnit4.class)
public class MainPresenterTest {

    @Mock
    Context mContext;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                return String.valueOf(invocation.getArguments()[0]);
            }
        }).when(mContext).getString(Mockito.anyInt());
    }

    @Test
    public void testLoadContent() throws Exception {
        MainView view = Mockito.mock(MainView.class);
        MainPresenter presenter = new MainPresenter(mContext, view);
        presenter.loadContent();
        Mockito.verify(view).showMainText(String.valueOf(R.string.hello_world));
    }
}