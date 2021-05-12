package moxy;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import moxy.presenter.PresenterField;

public class MvpDelegate<Delegated> {
    private static final Comparator<PresenterField> COMPARE_BY_TAGS = $$Lambda$MvpDelegate$lFXhGTySyisx3X0PPsh8phfAs.INSTANCE;
    private static final String KEY_TAG = "moxy.MvpDelegate.KEY_TAG";
    public static final String MOXY_DELEGATE_TAGS_KEY = "MoxyDelegateBundle";
    private Bundle bundle;
    private List<MvpDelegate> childDelegates;
    private String delegateTag;
    private final Delegated delegated;
    private Set<PresenterField<? super Delegated>> externalPresenterFields = new TreeSet(COMPARE_BY_TAGS);
    private boolean isAttached;
    private String keyTag = KEY_TAG;
    private MvpDelegate parentDelegate;
    private List<MvpPresenter<? super Delegated>> presenters = Collections.emptyList();

    public MvpDelegate(Delegated delegated2) {
        this.delegated = delegated2;
        this.childDelegates = new ArrayList();
    }

    public void setParentDelegate(MvpDelegate mvpDelegate, String str) {
        if (this.bundle == null) {
            List<MvpDelegate> list = this.childDelegates;
            if (list == null || list.size() <= 0) {
                this.parentDelegate = mvpDelegate;
                this.keyTag = this.parentDelegate.keyTag + "$" + str;
                mvpDelegate.addChildDelegate(this);
                return;
            }
            throw new IllegalStateException("You could not set parent delegate when there are some child presenters already");
        }
        throw new IllegalStateException("You should call setParentDelegate() before first call to onCreate()");
    }

    private void addChildDelegate(MvpDelegate mvpDelegate) {
        this.childDelegates.add(mvpDelegate);
    }

    private void removeChildDelegate(MvpDelegate mvpDelegate) {
        this.childDelegates.remove(mvpDelegate);
    }

    public void freeParentDelegate() {
        MvpDelegate mvpDelegate = this.parentDelegate;
        if (mvpDelegate != null) {
            mvpDelegate.removeChildDelegate(this);
            return;
        }
        throw new IllegalStateException("You should call freeParentDelegate() before first call to setParentDelegate()");
    }

    public void removeAllChildDelegates() {
        ArrayList<MvpDelegate> arrayList = new ArrayList<>(this.childDelegates.size());
        arrayList.addAll(this.childDelegates);
        for (MvpDelegate freeParentDelegate : arrayList) {
            freeParentDelegate.freeParentDelegate();
        }
        this.childDelegates = new ArrayList();
    }

    public void onCreate() {
        Bundle bundle2 = new Bundle();
        MvpDelegate mvpDelegate = this.parentDelegate;
        if (mvpDelegate != null) {
            bundle2 = mvpDelegate.bundle;
        }
        onCreate(bundle2);
    }

    public void onCreate(Bundle bundle2) {
        Bundle bundle3;
        if (this.parentDelegate == null && bundle2 != null) {
            bundle2 = bundle2.getBundle(MOXY_DELEGATE_TAGS_KEY);
        }
        this.isAttached = false;
        if (bundle2 != null) {
            bundle3 = bundle2;
        } else {
            bundle3 = new Bundle();
        }
        this.bundle = bundle3;
        if (bundle2 == null || !bundle3.containsKey(this.keyTag)) {
            this.delegateTag = generateTag();
        } else {
            this.delegateTag = bundle2.getString(this.keyTag);
        }
        this.presenters = MvpFacade.getInstance().getMvpProcessor().getMvpPresenters(this.delegated, this.delegateTag, this.externalPresenterFields);
        for (MvpDelegate onCreate : this.childDelegates) {
            onCreate.onCreate(bundle2);
        }
    }

    public void onAttach() {
        for (MvpPresenter next : this.presenters) {
            if (!this.isAttached || !next.getAttachedViews().contains(this.delegated)) {
                next.attachView((MvpView) this.delegated);
            }
        }
        for (MvpDelegate onAttach : this.childDelegates) {
            onAttach.onAttach();
        }
        this.isAttached = true;
    }

    public void onDetach() {
        for (MvpPresenter next : this.presenters) {
            if (this.isAttached || next.getAttachedViews().contains(this.delegated)) {
                next.detachView((MvpView) this.delegated);
            }
        }
        this.isAttached = false;
        for (MvpDelegate onDetach : this.childDelegates) {
            onDetach.onDetach();
        }
    }

    public void onDestroyView() {
        for (MvpPresenter<? super Delegated> destroyView : this.presenters) {
            destroyView.destroyView((MvpView) this.delegated);
        }
        ArrayList<MvpDelegate> arrayList = new ArrayList<>(this.childDelegates.size());
        arrayList.addAll(this.childDelegates);
        for (MvpDelegate onDestroyView : arrayList) {
            onDestroyView.onDestroyView();
        }
        if (this.parentDelegate != null) {
            freeParentDelegate();
        }
    }

    public void onDestroy() {
        PresentersCounter presentersCounter = MvpFacade.getInstance().getPresentersCounter();
        PresenterStore presenterStore = MvpFacade.getInstance().getPresenterStore();
        for (MvpPresenter next : presentersCounter.getAll(this.delegateTag)) {
            if (presentersCounter.rejectPresenter(next, this.delegateTag)) {
                presenterStore.remove(next.getTag());
                next.onDestroy();
            }
        }
    }

    public void onSaveInstanceState() {
        Bundle bundle2;
        Bundle bundle3 = new Bundle();
        MvpDelegate mvpDelegate = this.parentDelegate;
        if (!(mvpDelegate == null || (bundle2 = mvpDelegate.bundle) == null)) {
            bundle3 = bundle2;
        }
        onSaveInstanceState(bundle3);
    }

    public void onSaveInstanceState(Bundle bundle2) {
        if (this.parentDelegate == null) {
            Bundle bundle3 = new Bundle();
            bundle2.putBundle(MOXY_DELEGATE_TAGS_KEY, bundle3);
            bundle2 = bundle3;
        }
        bundle2.putAll(this.bundle);
        bundle2.putString(this.keyTag, this.delegateTag);
        for (MvpDelegate onSaveInstanceState : this.childDelegates) {
            onSaveInstanceState.onSaveInstanceState(bundle2);
        }
    }

    public Bundle getChildrenSaveState() {
        return this.bundle;
    }

    private String generateTag() {
        String str;
        if (this.parentDelegate != null) {
            str = this.parentDelegate.delegateTag + " ";
        } else {
            str = "";
        }
        return str + this.delegated.getClass().getSimpleName() + "$" + getClass().getSimpleName() + toString().replace(getClass().getName(), "");
    }

    public void registerExternalPresenterField(PresenterField<? super Delegated> presenterField) {
        this.externalPresenterFields.add(presenterField);
    }
}
