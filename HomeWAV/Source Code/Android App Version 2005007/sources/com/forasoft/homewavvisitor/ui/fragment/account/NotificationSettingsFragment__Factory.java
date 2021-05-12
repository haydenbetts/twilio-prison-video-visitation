package com.forasoft.homewavvisitor.ui.fragment.account;

import toothpick.Factory;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class NotificationSettingsFragment__Factory implements Factory<NotificationSettingsFragment> {
    private MemberInjector<NotificationSettingsFragment> memberInjector = new NotificationSettingsFragment__MemberInjector();

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

    public NotificationSettingsFragment createInstance(Scope scope) {
        Scope targetScope = getTargetScope(scope);
        NotificationSettingsFragment notificationSettingsFragment = new NotificationSettingsFragment();
        this.memberInjector.inject(notificationSettingsFragment, targetScope);
        return notificationSettingsFragment;
    }
}
