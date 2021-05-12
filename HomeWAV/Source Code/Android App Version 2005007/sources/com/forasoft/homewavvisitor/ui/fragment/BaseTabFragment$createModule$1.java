package com.forasoft.homewavvisitor.ui.fragment;

import com.forasoft.homewavvisitor.HomewavRouter;
import kotlin.Metadata;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/BaseTabFragment$createModule$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BaseTabFragment.kt */
public final class BaseTabFragment$createModule$1 extends Module {
    final /* synthetic */ Cicerone $cicerone;

    BaseTabFragment$createModule$1(Cicerone cicerone) {
        this.$cicerone = cicerone;
        bind(Cicerone.class).toInstance(cicerone);
        NavigatorHolder navigatorHolder = null;
        bind(Router.class).toInstance(cicerone != null ? (HomewavRouter) cicerone.getRouter() : null);
        bind(HomewavRouter.class).toInstance(cicerone != null ? (HomewavRouter) cicerone.getRouter() : null);
        bind(NavigatorHolder.class).toInstance(cicerone != null ? cicerone.getNavigatorHolder() : navigatorHolder);
    }
}
