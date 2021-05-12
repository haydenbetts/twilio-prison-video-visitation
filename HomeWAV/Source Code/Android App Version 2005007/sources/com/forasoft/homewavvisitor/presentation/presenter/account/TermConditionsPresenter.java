package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.TermConditionsView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.fragment.account.TermConditionsFragment;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/TermConditionsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/TermConditionsView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "homewavApi", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "showTermsDialog", "", "getShowTermsDialog", "()Z", "setShowTermsDialog", "(Z)V", "getTerms", "", "onBackPressed", "setGlobalRouter", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: TermConditionsPresenter.kt */
public final class TermConditionsPresenter extends BasePresenter<TermConditionsView> {
    private final HomewavApi homewavApi;
    private HomewavRouter router;
    private boolean showTermsDialog;

    @Inject
    public TermConditionsPresenter(HomewavRouter homewavRouter, HomewavApi homewavApi2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi2, "homewavApi");
        this.router = homewavRouter;
        this.homewavApi = homewavApi2;
    }

    public final boolean getShowTermsDialog() {
        return this.showTermsDialog;
    }

    public final void setShowTermsDialog(boolean z) {
        this.showTermsDialog = z;
    }

    public final void setGlobalRouter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(HomewavRouter.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…thpick.qualifier.Global\")");
        this.router = (HomewavRouter) instance;
    }

    public final void getTerms() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.homewavApi.getTerms()).subscribe(new TermConditionsPresenter$getTerms$1(this), TermConditionsPresenter$getTerms$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "homewavApi.getTerms()\n  …  }, {}\n                )");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final boolean onBackPressed() {
        if (this.showTermsDialog) {
            setGlobalRouter();
            this.router.exitWithResult(Integer.valueOf(TermConditionsFragment.RESULT_SHOW_TERMS_DIALOG), (Object) null);
            return true;
        }
        this.router.exit();
        return true;
    }
}
