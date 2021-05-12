package moxy.viewstate.strategy;

import java.util.List;
import moxy.MvpView;
import moxy.viewstate.ViewCommand;

public class OneExecutionStateStrategy implements StateStrategy {
    public <View extends MvpView> void beforeApply(List<ViewCommand<View>> list, ViewCommand<View> viewCommand) {
        list.add(viewCommand);
    }

    public <View extends MvpView> void afterApply(List<ViewCommand<View>> list, ViewCommand<View> viewCommand) {
        list.remove(viewCommand);
    }
}
