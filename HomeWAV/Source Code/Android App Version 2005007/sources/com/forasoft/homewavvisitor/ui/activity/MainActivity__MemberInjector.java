package com.forasoft.homewavvisitor.ui.activity;

import com.forasoft.homewavvisitor.navigation.BusNotifier;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class MainActivity__MemberInjector implements MemberInjector<MainActivity> {
    private MemberInjector<BaseActivity> superMemberInjector = new BaseActivity__MemberInjector();

    public void inject(MainActivity mainActivity, Scope scope) {
        this.superMemberInjector.inject(mainActivity, scope);
        mainActivity.notifier = (BusNotifier) scope.getInstance(BusNotifier.class);
    }
}
