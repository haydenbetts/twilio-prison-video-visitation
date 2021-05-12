package com.forasoft.homewavvisitor.ui.fragment.register;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class AddConnectionSignUpFragment__Factory implements Factory<AddConnectionSignUpFragment> {
    private MemberInjector<AddConnectionSignUpFragment> memberInjector = new AddConnectionSignUpFragment__MemberInjector();

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

    public AddConnectionSignUpFragment createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        AddConnectionSignUpFragment addConnectionSignUpFragment = new AddConnectionSignUpFragment();
        this.memberInjector.inject(addConnectionSignUpFragment, targetScope);
        return addConnectionSignUpFragment;
    }
}
