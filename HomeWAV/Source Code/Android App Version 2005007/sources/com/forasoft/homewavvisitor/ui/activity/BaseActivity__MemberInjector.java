package com.forasoft.homewavvisitor.ui.activity;

import ru.terrakok.cicerone.NavigatorHolder;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class BaseActivity__MemberInjector implements MemberInjector<BaseActivity> {
    public void inject(BaseActivity baseActivity, Scope scope) {
        baseActivity.navigatorHolder = (NavigatorHolder) scope.getInstance(NavigatorHolder.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
    }
}
