package com.elegion.android.presenter;

import com.elegion.android.view.MainView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

/**
 * @author Rovkin Max
 */
@RunWith(JUnit4.class)
public class MainPresenterTest {

    @Test
    public void testLoadContent() throws Exception {
        MainView view = Mockito.mock(MainView.class);
        MainPresenter presenter = new MainPresenter(view);
        presenter.loadContent();
        Mockito.verify(view).showMainText("Hello world");
    }
}