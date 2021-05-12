package moxy.viewstate;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import moxy.MvpView;

public abstract class MvpViewState<View extends MvpView> {
    protected Set<View> inRestoreState = Collections.newSetFromMap(new WeakHashMap());
    protected ViewCommands<View> viewCommands = new ViewCommands<>();
    protected Map<View, Set<ViewCommand<View>>> viewStates = new WeakHashMap();
    protected Set<View> views = Collections.newSetFromMap(new WeakHashMap());

    /* access modifiers changed from: protected */
    public void restoreState(View view, Set<ViewCommand<View>> set) {
        if (!this.viewCommands.isEmpty()) {
            this.viewCommands.reapply(view, set);
        }
    }

    /* access modifiers changed from: protected */
    public Boolean hasNotView() {
        Set<View> set = this.views;
        return Boolean.valueOf(set == null || set.isEmpty());
    }

    public void attachView(View view) {
        if (view == null) {
            throw new IllegalArgumentException("Mvp view must be not null");
        } else if (this.views.add(view)) {
            this.inRestoreState.add(view);
            Set set = this.viewStates.get(view);
            if (set == null) {
                set = Collections.emptySet();
            }
            restoreState(view, set);
            this.viewStates.remove(view);
            this.inRestoreState.remove(view);
        }
    }

    public void detachView(View view) {
        this.views.remove(view);
        this.inRestoreState.remove(view);
        Set newSetFromMap = Collections.newSetFromMap(new WeakHashMap());
        newSetFromMap.addAll(this.viewCommands.getCurrentState());
        this.viewStates.put(view, newSetFromMap);
    }

    public void destroyView(View view) {
        this.viewStates.remove(view);
    }

    public Set<View> getViews() {
        return this.views;
    }

    public boolean isInRestoreState(View view) {
        return this.inRestoreState.contains(view);
    }
}
