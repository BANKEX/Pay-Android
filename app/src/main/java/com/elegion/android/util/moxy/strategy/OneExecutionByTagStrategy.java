package com.elegion.android.util.moxy.strategy;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;

import java.util.Iterator;
import java.util.List;

/**
 * Command will be saved in commands queue. And this command will be removed after first execution.
 * If commands queue contains commands with same tag, then existing commands will be removed.
 *
 * @author mikhail.barannikov on 25.07.2017
 */
public class OneExecutionByTagStrategy implements StateStrategy {
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

        currentState.add(incomingCommand);
    }

    @Override
    public <T extends MvpView> void afterApply(List<ViewCommand<T>> currentState, ViewCommand<T> incomingCommand) {
        currentState.remove(incomingCommand);
    }
}
