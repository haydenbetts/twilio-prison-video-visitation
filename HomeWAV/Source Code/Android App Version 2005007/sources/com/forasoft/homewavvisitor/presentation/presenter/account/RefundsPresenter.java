package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.RefundsView;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0013J\b\u0010\u0017\u001a\u00020\u0013H\u0014J\u000e\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u0016\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0015R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/RefundsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/RefundsView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "selectedInmate", "totalAmount", "", "onAmountChanged", "", "amount", "", "onBackPressed", "onFirstViewAttach", "onInmateSelected", "position", "", "onRequestClicked", "comments", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: RefundsPresenter.kt */
public final class RefundsPresenter extends BasePresenter<RefundsView> {
    private final HomewavApi api;
    private final AuthHolder authHolder;
    private final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public List<Inmate> inmates = CollectionsKt.emptyList();
    /* access modifiers changed from: private */
    public final HomewavRouter router;
    private Inmate selectedInmate;
    private float totalAmount = -7.5f;

    @Inject
    public RefundsPresenter(HomewavRouter homewavRouter, AuthHolder authHolder2, InmateDao inmateDao2, HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.router = homewavRouter;
        this.authHolder = authHolder2;
        this.inmateDao = inmateDao2;
        this.api = homewavApi;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        ((RefundsView) getViewState()).updateTotalAmount(-7.5f);
        CompositeDisposable disposables = getDisposables();
        InmateDao inmateDao2 = this.inmateDao;
        User user = this.authHolder.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        Flowable<R> map = inmateDao2.getApprovedInmates(visitor_id).doOnNext(new RefundsPresenter$onFirstViewAttach$1(this)).map(RefundsPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "inmateDao.getApprovedInm….map { it.full_name!! } }");
        Disposable subscribe = CommonKt.applyAsync(map).subscribe(new RefundsPresenter$onFirstViewAttach$3(this), RefundsPresenter$onFirstViewAttach$4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmateDao.getApprovedInm…te.showInmates(it) }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onInmateSelected(int i) {
        this.selectedInmate = this.inmates.get(i);
    }

    public final void onAmountChanged(String str) {
        Intrinsics.checkParameterIsNotNull(str, "amount");
        try {
            this.totalAmount = Float.parseFloat(str) - 7.5f;
            ((RefundsView) getViewState()).updateTotalAmount(this.totalAmount);
        } catch (NumberFormatException unused) {
            this.totalAmount = -7.5f;
            ((RefundsView) getViewState()).updateTotalAmount(this.totalAmount);
        }
    }

    public final void onRequestClicked(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "amount");
        Intrinsics.checkParameterIsNotNull(str2, "comments");
        if (this.selectedInmate == null) {
            ((RefundsView) getViewState()).showMessage("Select inmate");
        } else if (this.totalAmount <= ((float) 0)) {
            ((RefundsView) getViewState()).showMessage("You don't have enough funds");
        } else {
            CompositeDisposable disposables = getDisposables();
            HomewavApi homewavApi = this.api;
            User user = this.authHolder.getUser();
            String visitor_id = user != null ? user.getVisitor_id() : null;
            if (visitor_id == null) {
                Intrinsics.throwNpe();
            }
            Inmate inmate = this.selectedInmate;
            if (inmate == null) {
                Intrinsics.throwNpe();
            }
            Disposable subscribe = CommonKt.applyAsync(homewavApi.requestRefund(visitor_id, inmate.getId(), str, str2)).subscribe(new RefundsPresenter$onRequestClicked$1(this), new RefundsPresenter$onRequestClicked$2(this));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.requestRefund(authHo…sent\")\n                })");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
