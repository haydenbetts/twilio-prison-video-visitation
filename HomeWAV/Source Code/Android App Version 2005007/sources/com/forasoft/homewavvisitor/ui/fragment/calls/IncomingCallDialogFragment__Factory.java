package com.forasoft.homewavvisitor.ui.fragment.calls;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class IncomingCallDialogFragment__Factory implements Factory<IncomingCallDialogFragment> {
    private MemberInjector<IncomingCallDialogFragment> memberInjector = new IncomingCallDialogFragment__MemberInjector();

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

    public IncomingCallDialogFragment createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        IncomingCallDialogFragment incomingCallDialogFragment = new IncomingCallDialogFragment();
        this.memberInjector.inject(incomingCallDialogFragment, targetScope);
        return incomingCallDialogFragment;
    }
}
