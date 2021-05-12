package com.forasoft.homewavvisitor.ui.fragment.payment;

import ru.terrakok.cicerone.Router;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class PayNearMeViewerFragment__MemberInjector implements MemberInjector<PayNearMeViewerFragment> {
    public void inject(PayNearMeViewerFragment payNearMeViewerFragment, Scope scope) {
        payNearMeViewerFragment.router = (Router) scope.getInstance(Router.class);
        payNearMeViewerFragment.trackingUrl = (String) scope.getInstance(String.class, "com.forasoft.homewavvisitor.toothpick.qualifier.PaynearmeOrderUrl");
    }
}
