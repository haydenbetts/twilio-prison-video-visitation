package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.view.account.AccountHistoryActivityView;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/AccountHistoryActivityPresenter;", "Lmoxy/MvpPresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/AccountHistoryActivityView;", "router", "Lru/terrakok/cicerone/Router;", "(Lru/terrakok/cicerone/Router;)V", "onFirstViewAttach", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: AccountHistoryActivityPresenter.kt */
public final class AccountHistoryActivityPresenter extends MvpPresenter<AccountHistoryActivityView> {
    private final Router router;

    @Inject
    public AccountHistoryActivityPresenter(Router router2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        this.router = router2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        this.router.replaceScreen(Screens.AccountHistoryScreen.INSTANCE);
    }
}
