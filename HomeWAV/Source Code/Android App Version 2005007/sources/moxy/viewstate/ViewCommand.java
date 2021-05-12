package moxy.viewstate;

import moxy.MvpView;
import moxy.viewstate.strategy.StateStrategy;

public abstract class ViewCommand<View extends MvpView> {
    private final Class<? extends StateStrategy> stateStrategyType;
    private final String tag;

    public abstract void apply(View view);

    protected ViewCommand(String str, Class<? extends StateStrategy> cls) {
        this.tag = str;
        this.stateStrategyType = cls;
    }

    public String getTag() {
        return this.tag;
    }

    public Class<? extends StateStrategy> getStrategyType() {
        return this.stateStrategyType;
    }
}
