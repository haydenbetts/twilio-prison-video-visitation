package moxy.viewstate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import moxy.MvpView;
import moxy.locators.StrategyLocator;
import moxy.viewstate.strategy.StateStrategy;

public class ViewCommands<View extends MvpView> {
    private List<ViewCommand<View>> state = new ArrayList();
    private Map<Class<? extends StateStrategy>, StateStrategy> strategies = new HashMap();

    public void beforeApply(ViewCommand<View> viewCommand) {
        getStateStrategy(viewCommand).beforeApply(this.state, viewCommand);
    }

    public void afterApply(ViewCommand<View> viewCommand) {
        getStateStrategy(viewCommand).afterApply(this.state, viewCommand);
    }

    private StateStrategy getStateStrategy(ViewCommand<View> viewCommand) {
        StateStrategy strategy = StrategyLocator.getStrategy(viewCommand.getStrategyType());
        if (strategy == null) {
            try {
                strategy = (StateStrategy) viewCommand.getStrategyType().newInstance();
                this.strategies.put(viewCommand.getStrategyType(), strategy);
            } catch (InstantiationException unused) {
                throw new IllegalArgumentException("Unable to create state strategy: " + viewCommand.toString());
            } catch (IllegalAccessException unused2) {
                throw new IllegalArgumentException("Unable to create state strategy: " + viewCommand.toString());
            }
        }
        return strategy;
    }

    public boolean isEmpty() {
        return this.state.isEmpty();
    }

    public void reapply(View view, Set<ViewCommand<View>> set) {
        Iterator it = new ArrayList(this.state).iterator();
        while (it.hasNext()) {
            ViewCommand viewCommand = (ViewCommand) it.next();
            if (!set.contains(viewCommand)) {
                viewCommand.apply(view);
                afterApply(viewCommand);
            }
        }
    }

    public List<ViewCommand<View>> getCurrentState() {
        return this.state;
    }
}
