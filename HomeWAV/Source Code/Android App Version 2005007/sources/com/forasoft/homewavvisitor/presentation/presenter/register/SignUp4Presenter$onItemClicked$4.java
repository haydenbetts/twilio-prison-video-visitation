package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp4Presenter.kt */
final class SignUp4Presenter$onItemClicked$4<T> implements Consumer<ApiResponse<? extends List<? extends Facility>>> {
    final /* synthetic */ SignUp4Presenter this$0;

    SignUp4Presenter$onItemClicked$4(SignUp4Presenter signUp4Presenter) {
        this.this$0 = signUp4Presenter;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.forasoft.homewavvisitor.model.data.Facility} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void accept(com.forasoft.homewavvisitor.model.server.response.ApiResponse<? extends java.util.List<com.forasoft.homewavvisitor.model.data.Facility>> r5) {
        /*
            r4 = this;
            boolean r0 = r5.getOk()
            if (r0 == 0) goto L_0x007a
            java.lang.Object r5 = r5.getBody()
            java.util.List r5 = (java.util.List) r5
            r0 = 0
            if (r5 == 0) goto L_0x003e
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0015:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r5.next()
            r2 = r1
            com.forasoft.homewavvisitor.model.data.Facility r2 = (com.forasoft.homewavvisitor.model.data.Facility) r2
            java.lang.String r2 = r2.getId()
            com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter r3 = r4.this$0
            com.forasoft.homewavvisitor.model.data.register.InmateByVisitor r3 = r3.chosenConnection
            if (r3 != 0) goto L_0x0031
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0031:
            java.lang.String r3 = r3.getPrison_id()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x0015
            r0 = r1
        L_0x003c:
            com.forasoft.homewavvisitor.model.data.Facility r0 = (com.forasoft.homewavvisitor.model.data.Facility) r0
        L_0x003e:
            com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter r5 = r4.this$0
            boolean r5 = r5.isAnyPaymentOptionAvailable(r0)
            if (r5 == 0) goto L_0x006c
            com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter r5 = r4.this$0
            com.forasoft.homewavvisitor.model.interactor.PaymentGateway r5 = r5.paymentGateway
            io.reactivex.subjects.BehaviorSubject r5 = r5.getSelectedConnectionSubject()
            com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter r0 = r4.this$0
            com.forasoft.homewavvisitor.model.data.register.InmateByVisitor r0 = r0.chosenConnection
            if (r0 != 0) goto L_0x005b
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x005b:
            r5.onNext(r0)
            com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter r5 = r4.this$0
            ru.terrakok.cicerone.Router r5 = r5.router
            com.forasoft.homewavvisitor.navigation.Screens$ChooseFundsScreen r0 = com.forasoft.homewavvisitor.navigation.Screens.ChooseFundsScreen.INSTANCE
            ru.terrakok.cicerone.Screen r0 = (ru.terrakok.cicerone.Screen) r0
            r5.navigateTo(r0)
            goto L_0x007a
        L_0x006c:
            com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter r5 = r4.this$0
            moxy.MvpView r5 = r5.getViewState()
            com.forasoft.homewavvisitor.presentation.view.register.SignUp4View r5 = (com.forasoft.homewavvisitor.presentation.view.register.SignUp4View) r5
            r0 = 2131821022(0x7f1101de, float:1.9274775E38)
            r5.showMessage((int) r0)
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter$onItemClicked$4.accept(com.forasoft.homewavvisitor.model.server.response.ApiResponse):void");
    }
}
