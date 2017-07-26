package com.elegion.android.util.moxy.strategy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

import java.util.Iterator;
import java.util.List;

/**
 * Command will not be put in commands queue. If commands queue contains commands with same tag, then existing commands will be removed.
 *
 * @author mikhail.barannikov on 25.07.2017
 */
public class SkipByTagStrategy implements StateStrategy {
    @Override
    public <T extends MvpView> void beforeApply(List<ViewCommand<T>> currentState, ViewCommand<T> incomingCommand) {
        Iterator<ViewCommand<T>> iterator = currentState.iterator();

        while (iterator.hasNext()) {
            ViewCommand<T> entry = iterator.next();

            if (entry.getTag().equals(incomingCommand.getTag())) {
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public <T extends MvpView> void afterApply(List<ViewCommand<T>> currentState, ViewCommand<T> incomingCommand) {
        // pass
    }
}
