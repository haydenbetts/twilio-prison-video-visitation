package moxy.viewstate.strategy;

import java.util.Iterator;
import java.util.List;
import moxy.MvpView;
import moxy.viewstate.ViewCommand;

public class AddToEndSingleStrategy implements StateStrategy {
    public <View extends MvpView> void afterApply(List<ViewCommand<View>> list, ViewCommand<View> viewCommand) {
    }

    public <View extends MvpView> void beforeApply(List<ViewCommand<View>> list, ViewCommand<View> viewCommand) {
        Iterator<ViewCommand<View>> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().getClass() == viewCommand.getClass()) {
                    it.remove();
                    break;
                }
            } else {
                break;
            }
        }
        list.add(viewCommand);
    }
}
