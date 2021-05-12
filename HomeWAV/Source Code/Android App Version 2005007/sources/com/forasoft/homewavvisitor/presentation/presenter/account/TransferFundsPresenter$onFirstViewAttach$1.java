package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
final class TransferFundsPresenter$onFirstViewAttach$1<T> implements Consumer<ApiResponse<? extends List<? extends Facility>>> {
    final /* synthetic */ TransferFundsPresenter this$0;

    TransferFundsPresenter$onFirstViewAttach$1(TransferFundsPresenter transferFundsPresenter) {
        this.this$0 = transferFundsPresenter;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void accept(com.forasoft.homewavvisitor.model.server.response.ApiResponse<? extends java.util.List<com.forasoft.homewavvisitor.model.data.Facility>> r5) {
        /*
            r4 = this;
            java.lang.Object r5 = r5.getBody()
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L_0x0011
            com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter r0 = r4.this$0
            io.reactivex.subjects.BehaviorSubject r0 = r0.facilitiesSubject
            r0.onNext(r5)
        L_0x0011:
            com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter r0 = r4.this$0
            com.forasoft.homewavvisitor.model.data.Inmate r0 = r0.inmateTo
            if (r0 == 0) goto L_0x005b
            r0 = 0
            if (r5 == 0) goto L_0x004a
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0022:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0048
            java.lang.Object r1 = r5.next()
            r2 = r1
            com.forasoft.homewavvisitor.model.data.Facility r2 = (com.forasoft.homewavvisitor.model.data.Facility) r2
            java.lang.String r2 = r2.getId()
            com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter r3 = r4.this$0
            com.forasoft.homewavvisitor.model.data.Inmate r3 = r3.inmateTo
            if (r3 == 0) goto L_0x0040
            java.lang.String r3 = r3.getPrison_id()
            goto L_0x0041
        L_0x0040:
            r3 = r0
        L_0x0041:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x0022
            r0 = r1
        L_0x0048:
            com.forasoft.homewavvisitor.model.data.Facility r0 = (com.forasoft.homewavvisitor.model.data.Facility) r0
        L_0x004a:
            if (r0 == 0) goto L_0x005b
            com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter r5 = r4.this$0
            moxy.MvpView r5 = r5.getViewState()
            com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView r5 = (com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView) r5
            java.lang.String r0 = r0.getTransfer_fee()
            r5.showTransferFee(r0)
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.account.TransferFundsPresenter$onFirstViewAttach$1.accept(com.forasoft.homewavvisitor.model.server.response.ApiResponse):void");
    }
}
