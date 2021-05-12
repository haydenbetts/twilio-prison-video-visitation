package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0006\u0010\u0015\u001a\u00020\u0014J\b\u0010\u0016\u001a\u00020\u0014H\u0014J\u0006\u0010\u0017\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0014J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bR\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/TransferFundsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/TransferFundsView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "homewavApi", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "signUpApi", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "facilitiesSubject", "Lio/reactivex/subjects/BehaviorSubject;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "inmateFrom", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "inmateTo", "clearResultListener", "", "onBackPressed", "onFirstViewAttach", "onSelectFromClicked", "onSelectToClicked", "onTransferClicked", "amount", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: TransferFundsPresenter.kt */
public final class TransferFundsPresenter extends BasePresenter<TransferFundsView> {
    /* access modifiers changed from: private */
    public final BehaviorSubject<List<Facility>> facilitiesSubject;
    private final HeartbeatRepository heartbeatRepository;
    private final HomewavApi homewavApi;
    /* access modifiers changed from: private */
    public Inmate inmateFrom;
    /* access modifiers changed from: private */
    public Inmate inmateTo;
    /* access modifiers changed from: private */
    public final HomewavRouter router;
    private final SignUpApi signUpApi;

    @Inject
    public TransferFundsPresenter(HomewavRouter homewavRouter, HomewavApi homewavApi2, SignUpApi signUpApi2, HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi2, "homewavApi");
        Intrinsics.checkParameterIsNotNull(signUpApi2, "signUpApi");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.router = homewavRouter;
        this.homewavApi = homewavApi2;
        this.signUpApi = signUpApi2;
        this.heartbeatRepository = heartbeatRepository2;
        BehaviorSubject<List<Facility>> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.facilitiesSubject = create;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.signUpApi.loadFacilities()).subscribe(new TransferFundsPresenter$onFirstViewAttach$1(this), TransferFundsPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "signUpApi.loadFacilities… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = this.heartbeatRepository.getHeartbeatState().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TransferFundsPresenter$onFirstViewAttach$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "heartbeatRepository.hear…      }\n                }");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    public final void onSelectFromClicked() {
        this.router.setResultListener(-1, new TransferFundsPresenter$onSelectFromClicked$1(this));
        this.router.setResultListener(0, new TransferFundsPresenter$onSelectFromClicked$2(this));
        this.router.navigateTo(new Screens.InmateChooseAccountScreen("Transfer From", this.inmateTo));
    }

    public final void onSelectToClicked() {
        this.router.setResultListener(-1, new TransferFundsPresenter$onSelectToClicked$1(this));
        this.router.setResultListener(0, new TransferFundsPresenter$onSelectToClicked$2(this));
        this.router.navigateTo(new Screens.InmateChooseAccountScreen("Transfer To", this.inmateFrom));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onTransferClicked(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "amount"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            io.reactivex.subjects.BehaviorSubject<java.util.List<com.forasoft.homewavvisitor.model.data.Facility>> r1 = r7.facilitiesSubject
            java.lang.Object r1 = r1.getValue()
            java.util.List r1 = (java.util.List) r1
            r2 = 0
            if (r1 == 0) goto L_0x003a
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0016:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0038
            java.lang.Object r3 = r1.next()
            r4 = r3
            com.forasoft.homewavvisitor.model.data.Facility r4 = (com.forasoft.homewavvisitor.model.data.Facility) r4
            java.lang.String r4 = r4.getId()
            com.forasoft.homewavvisitor.model.data.Inmate r5 = r7.inmateTo
            if (r5 == 0) goto L_0x0030
            java.lang.String r5 = r5.getPrison_id()
            goto L_0x0031
        L_0x0030:
            r5 = r2
        L_0x0031:
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0016
            r2 = r3
        L_0x0038:
            com.forasoft.homewavvisitor.model.data.Facility r2 = (com.forasoft.homewavvisitor.model.data.Facility) r2
        L_0x003a:
            com.forasoft.homewavvisitor.model.data.Inmate r1 = r7.inmateTo
            if (r1 != 0) goto L_0x0041
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0041:
            java.lang.String r1 = r1.getId()
            com.forasoft.homewavvisitor.model.data.Inmate r3 = r7.inmateFrom
            if (r3 != 0) goto L_0x004c
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x004c:
            java.lang.String r3 = r3.getId()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)
            if (r1 == 0) goto L_0x0063
            moxy.MvpView r8 = r7.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView r8 = (com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView) r8
            r0 = 2131820734(0x7f1100be, float:1.9274191E38)
            r8.showMessage(r0)
            return
        L_0x0063:
            com.forasoft.homewavvisitor.model.data.Inmate r1 = r7.inmateFrom
            if (r1 != 0) goto L_0x006a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x006a:
            java.lang.String r1 = r1.getCredit_balance()
            if (r1 == 0) goto L_0x0075
            double r3 = java.lang.Double.parseDouble(r1)
            goto L_0x0077
        L_0x0075:
            r3 = 0
        L_0x0077:
            double r5 = java.lang.Double.parseDouble(r8)
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x008c
            moxy.MvpView r8 = r7.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView r8 = (com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView) r8
            r0 = 2131820725(0x7f1100b5, float:1.9274173E38)
            r8.showMessage(r0)
            return
        L_0x008c:
            float r1 = java.lang.Float.parseFloat(r8)
            if (r2 == 0) goto L_0x009d
            java.lang.String r2 = r2.getTransfer_fee()
            if (r2 == 0) goto L_0x009d
            float r2 = java.lang.Float.parseFloat(r2)
            goto L_0x009e
        L_0x009d:
            r2 = 0
        L_0x009e:
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x00af
            moxy.MvpView r8 = r7.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView r8 = (com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView) r8
            r0 = 2131820735(0x7f1100bf, float:1.9274193E38)
            r8.showMessage(r0)
            return
        L_0x00af:
            moxy.MvpView r1 = r7.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView r1 = (com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView) r1
            r1.showProgressDialog()
            io.reactivex.disposables.CompositeDisposable r1 = r7.getDisposables()
            com.forasoft.homewavvisitor.model.server.apis.HomewavApi r2 = r7.homewavApi
            okhttp3.MultipartBody$Part$Companion r3 = okhttp3.MultipartBody.Part.Companion
            com.forasoft.homewavvisitor.model.data.Inmate r4 = r7.inmateFrom
            if (r4 != 0) goto L_0x00c7
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00c7:
            java.lang.String r4 = r4.getId()
            java.lang.String r5 = "inmate_id"
            okhttp3.MultipartBody$Part r3 = r3.createFormData(r5, r4)
            okhttp3.MultipartBody$Part$Companion r4 = okhttp3.MultipartBody.Part.Companion
            com.forasoft.homewavvisitor.model.data.Inmate r5 = r7.inmateTo
            if (r5 != 0) goto L_0x00da
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00da:
            java.lang.String r5 = r5.getId()
            java.lang.String r6 = "target_inmate_id"
            okhttp3.MultipartBody$Part r4 = r4.createFormData(r6, r5)
            okhttp3.MultipartBody$Part$Companion r5 = okhttp3.MultipartBody.Part.Companion
            okhttp3.MultipartBody$Part r8 = r5.createFormData(r0, r8)
            io.reactivex.Single r8 = r2.transferFunds(r3, r4, r8)
            io.reactivex.Single r8 = com.forasoft.homewavvisitor.extension.CommonKt.applyAsync(r8)
            com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter$onTransferClicked$1 r0 = new com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter$onTransferClicked$1
            r0.<init>(r7)
            io.reactivex.functions.Consumer r0 = (io.reactivex.functions.Consumer) r0
            com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter$onTransferClicked$2 r2 = new com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter$onTransferClicked$2
            r2.<init>(r7)
            io.reactivex.functions.Consumer r2 = (io.reactivex.functions.Consumer) r2
            io.reactivex.disposables.Disposable r8 = r8.subscribe(r0, r2)
            java.lang.String r0 = "homewavApi.transferFunds…alog()\n                })"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            io.reactivex.rxkotlin.DisposableKt.plusAssign(r1, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter.onTransferClicked(java.lang.String):void");
    }

    public final void onBackPressed() {
        this.router.exit();
    }

    /* access modifiers changed from: private */
    public final void clearResultListener() {
        this.router.removeResultListener(-1);
        this.router.removeResultListener(0);
    }
}
