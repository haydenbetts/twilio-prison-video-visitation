package moxy;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import moxy.MvpView;
import moxy.locators.ViewStateLocator;
import moxy.viewstate.MvpViewState;

public abstract class MvpPresenter<View extends MvpView> {
    private boolean isFirstLaunch = true;
    private Class<? extends MvpPresenter> presenterClass;
    private String tag;
    /* access modifiers changed from: private */
    public MvpViewState<View> viewState;
    /* access modifiers changed from: private */
    public View viewStateAsView;
    private Set<View> views;

    public void onDestroy() {
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
    }

    public MvpPresenter() {
        Binder.bind(this);
        this.views = Collections.newSetFromMap(new WeakHashMap());
    }

    public void attachView(View view) {
        MvpViewState<View> mvpViewState = this.viewState;
        if (mvpViewState != null) {
            mvpViewState.attachView(view);
        } else {
            this.views.add(view);
        }
        if (this.isFirstLaunch) {
            this.isFirstLaunch = false;
            onFirstViewAttach();
        }
    }

    public void detachView(View view) {
        MvpViewState<View> mvpViewState = this.viewState;
        if (mvpViewState != null) {
            mvpViewState.detachView(view);
        } else {
            this.views.remove(view);
        }
    }

    public void destroyView(View view) {
        MvpViewState<View> mvpViewState = this.viewState;
        if (mvpViewState != null) {
            mvpViewState.destroyView(view);
        }
    }

    public Set<View> getAttachedViews() {
        MvpViewState<View> mvpViewState = this.viewState;
        if (mvpViewState != null) {
            return mvpViewState.getViews();
        }
        return this.views;
    }

    public View getViewState() {
        return this.viewStateAsView;
    }

    public void setViewState(MvpViewState<View> mvpViewState) {
        this.viewStateAsView = (MvpView) mvpViewState;
        this.viewState = mvpViewState;
    }

    public boolean isInRestoreState(View view) {
        MvpViewState<View> mvpViewState = this.viewState;
        if (mvpViewState != null) {
            return mvpViewState.isInRestoreState(view);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public String getTag() {
        return this.tag;
    }

    /* access modifiers changed from: package-private */
    public void setTag(String str) {
        this.tag = str;
    }

    /* access modifiers changed from: package-private */
    public Class<? extends MvpPresenter> getPresenterClass() {
        return this.presenterClass;
    }

    /* access modifiers changed from: package-private */
    public void setPresenterClass(Class<? extends MvpPresenter> cls) {
        this.presenterClass = cls;
    }

    private static class Binder {
        private Binder() {
        }

        static void bind(MvpPresenter mvpPresenter) {
            MvpViewState viewState = ViewStateLocator.getViewState(mvpPresenter.getClass());
            MvpView unused = mvpPresenter.viewStateAsView = (MvpView) viewState;
            MvpViewState unused2 = mvpPresenter.viewState = viewState;
        }
    }
}
