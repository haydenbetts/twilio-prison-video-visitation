package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.navigation.Screens;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AccountPresenter.kt */
final class AccountPresenter$onLogout$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AccountPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AccountPresenter$onLogout$1(AccountPresenter accountPresenter) {
        super(0);
        this.this$0 = accountPresenter;
    }

    public final void invoke() {
        this.this$0.globalRouter.newRootScreen(new Screens.LoginScreen(false, 1, (DefaultConstructorMarker) null));
    }
}
