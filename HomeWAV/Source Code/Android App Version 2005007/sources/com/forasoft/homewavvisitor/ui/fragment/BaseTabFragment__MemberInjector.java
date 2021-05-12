package com.forasoft.homewavvisitor.ui.fragment;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.navigation.CiceroneHolder;
import ru.terrakok.cicerone.NavigatorHolder;
import toothpick.MemberInjector;
import toothpick.Scope;

public final class BaseTabFragment__MemberInjector implements MemberInjector<BaseTabFragment> {
    public void inject(BaseTabFragment baseTabFragment, Scope scope) {
        baseTabFragment.ciceroneHolder = (CiceroneHolder) scope.getInstance(CiceroneHolder.class);
        baseTabFragment.router = (HomewavRouter) scope.getInstance(HomewavRouter.class);
        baseTabFragment.navigatorHolder = (NavigatorHolder) scope.getInstance(NavigatorHolder.class);
    }
}
