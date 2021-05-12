package com.forasoft.homewavvisitor.ui.activity.welcome;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\tH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomePresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeView;", "router", "Lru/terrakok/cicerone/Router;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "finish", "", "onFirstViewAttach", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WelcomePresenter.kt */
public final class WelcomePresenter extends BasePresenter<WelcomeView> {
    private final AuthHolder authHolder;
    private final Router router;

    @Inject
    public WelcomePresenter(@Global Router router2, AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.router = router2;
        this.authHolder = authHolder2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        this.authHolder.setWelcomeScreensShown(true);
    }

    public final void finish() {
        this.router.newRootScreen(new Screens.MainScreen((String) null, 1, (DefaultConstructorMarker) null));
    }
}
