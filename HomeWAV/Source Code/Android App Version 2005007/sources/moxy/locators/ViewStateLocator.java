package moxy.locators;

import moxy.MvpProcessor;
import moxy.ViewStateProvider;
import moxy.viewstate.MvpViewState;

public class ViewStateLocator {
    private ViewStateLocator() {
    }

    public static MvpViewState getViewState(Class<?> cls) {
        try {
            return ((ViewStateProvider) Class.forName(cls.getName() + MvpProcessor.VIEW_STATE_PROVIDER_SUFFIX).newInstance()).getViewState();
        } catch (Exception unused) {
            return null;
        }
    }
}
