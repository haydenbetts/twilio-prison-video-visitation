package moxy.locators;

import java.util.HashMap;
import java.util.Map;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategy;

public class StrategyLocator {
    private static Map<Class, StateStrategy> strategies;

    static {
        HashMap hashMap = new HashMap();
        strategies = hashMap;
        hashMap.put(AddToEndSingleStrategy.class, new AddToEndSingleStrategy());
        strategies.put(AddToEndStrategy.class, new AddToEndStrategy());
        strategies.put(OneExecutionStateStrategy.class, new OneExecutionStateStrategy());
    }

    private StrategyLocator() {
    }

    public static StateStrategy getStrategy(Class cls) {
        try {
            StateStrategy stateStrategy = strategies.get(cls);
            if (stateStrategy != null) {
                return stateStrategy;
            }
            return (StateStrategy) cls.newInstance();
        } catch (Exception unused) {
            throw new RuntimeException("Cannot instantiate " + cls.getName());
        }
    }
}
