package moxy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import moxy.locators.PresenterBinderLocator;
import moxy.presenter.PresenterField;

public class MvpProcessor {
    public static final String PRESENTER_BINDER_INNER_SUFFIX = "Binder";
    public static final String PRESENTER_BINDER_SUFFIX = "$$PresentersBinder";
    public static final String VIEW_STATE_PROVIDER_SUFFIX = "$$ViewStateProvider";
    public static final String VIEW_STATE_SUFFIX = "$$State";

    private <Delegated> MvpPresenter<? super Delegated> getMvpPresenter(Delegated delegated, PresenterField<Delegated> presenterField, String str) {
        Class<? extends MvpPresenter> presenterClass = presenterField.getPresenterClass();
        PresenterStore presenterStore = MvpFacade.getInstance().getPresenterStore();
        String str2 = str + "$" + presenterField.getTag(delegated);
        MvpPresenter<? super Delegated> mvpPresenter = presenterStore.get(str2);
        if (mvpPresenter != null) {
            return mvpPresenter;
        }
        MvpPresenter<?> providePresenter = presenterField.providePresenter(delegated);
        if (providePresenter == null) {
            return null;
        }
        providePresenter.setTag(str2);
        providePresenter.setPresenterClass(presenterClass);
        presenterStore.add(str2, providePresenter);
        return providePresenter;
    }

    /* access modifiers changed from: package-private */
    public <Delegated> List<MvpPresenter<? super Delegated>> getMvpPresenters(Delegated delegated, String str, Set<PresenterField<? super Delegated>> set) {
        List<PresenterField<? super Delegated>> combinePresenterFields = combinePresenterFields(PresenterBinderLocator.getPresenterBinders(delegated.getClass()), set);
        if (combinePresenterFields.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        PresentersCounter presentersCounter = MvpFacade.getInstance().getPresentersCounter();
        for (PresenterField next : combinePresenterFields) {
            MvpPresenter mvpPresenter = getMvpPresenter(delegated, next, str);
            if (mvpPresenter != null) {
                presentersCounter.injectPresenter(mvpPresenter, str);
                arrayList.add(mvpPresenter);
                next.bind(delegated, mvpPresenter);
            }
        }
        return arrayList;
    }

    private <Delegated> List<PresenterField<? super Delegated>> combinePresenterFields(PresenterBinder<Delegated> presenterBinder, Set<PresenterField<? super Delegated>> set) {
        ArrayList arrayList = new ArrayList();
        if (presenterBinder != null) {
            arrayList.addAll(presenterBinder.getPresenterFields());
        }
        arrayList.addAll(set);
        return arrayList;
    }
}
