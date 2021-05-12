package com.forasoft.homewavvisitor.ui.fragment.payment;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class PayNearMeViewerFragment__Factory implements Factory<PayNearMeViewerFragment> {
    private MemberInjector<PayNearMeViewerFragment> memberInjector = new PayNearMeViewerFragment__MemberInjector();

    public Scope getTargetScope(Scope scope) {
        return scope;
    }

    public boolean hasProvidesReleasableAnnotation() {
        return false;
    }

    public boolean hasProvidesSingletonAnnotation() {
        return false;
    }

    public boolean hasReleasableAnnotation() {
        return false;
    }

    public boolean hasScopeAnnotation() {
        return false;
    }

    public boolean hasSingletonAnnotation() {
        return false;
    }

    public PayNearMeViewerFragment createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        PayNearMeViewerFragment payNearMeViewerFragment = new PayNearMeViewerFragment();
        this.memberInjector.inject(payNearMeViewerFragment, targetScope);
        return payNearMeViewerFragment;
    }
}
