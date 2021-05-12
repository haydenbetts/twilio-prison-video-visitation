package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.Empty;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/Empty;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TimeChooserPresenter.kt */
final class TimeChooserPresenter$onInviteConfirmed$1<T> implements Consumer<ApiResponse<? extends Empty>> {
    final /* synthetic */ TimeChooserPresenter this$0;

    TimeChooserPresenter$onInviteConfirmed$1(TimeChooserPresenter timeChooserPresenter) {
        this.this$0 = timeChooserPresenter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x002f, code lost:
        r3 = r3.getMessage();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void accept(com.forasoft.homewavvisitor.model.server.response.ApiResponse<com.forasoft.homewavvisitor.model.data.Empty> r3) {
        /*
            r2 = this;
            com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter r0 = r2.this$0
            ru.terrakok.cicerone.Router r0 = r0.router
            com.forasoft.homewavvisitor.navigation.Screens$VisitsScreen r1 = com.forasoft.homewavvisitor.navigation.Screens.VisitsScreen.INSTANCE
            ru.terrakok.cicerone.Screen r1 = (ru.terrakok.cicerone.Screen) r1
            r0.backTo(r1)
            boolean r0 = r3.getOk()
            if (r0 == 0) goto L_0x0021
            com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter r3 = r2.this$0
            moxy.MvpView r3 = r3.getViewState()
            com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView r3 = (com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView) r3
            java.lang.String r0 = "Visit request sent"
            r3.showMessage((java.lang.String) r0)
            goto L_0x0047
        L_0x0021:
            com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter r0 = r2.this$0
            moxy.MvpView r0 = r0.getViewState()
            com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView r0 = (com.forasoft.homewavvisitor.presentation.view.visits.TimeChooserView) r0
            com.forasoft.homewavvisitor.model.data.ErrorCause r3 = r3.getErrorCause()
            if (r3 == 0) goto L_0x003e
            java.lang.String r3 = r3.getMessage()
            if (r3 == 0) goto L_0x003e
            java.lang.String r1 = "\""
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            java.lang.String r3 = kotlin.text.StringsKt.removeSurrounding((java.lang.String) r3, (java.lang.CharSequence) r1)
            goto L_0x003f
        L_0x003e:
            r3 = 0
        L_0x003f:
            if (r3 != 0) goto L_0x0044
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0044:
            r0.showMessage((java.lang.String) r3)
        L_0x0047:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter$onInviteConfirmed$1.accept(com.forasoft.homewavvisitor.model.server.response.ApiResponse):void");
    }
}
