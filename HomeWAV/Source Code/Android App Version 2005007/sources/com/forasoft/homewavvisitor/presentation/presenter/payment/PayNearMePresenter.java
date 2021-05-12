package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.PaymentRequestData;
import com.forasoft.homewavvisitor.model.data.payment.PaynearmeResponse;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.payment.PayNearMeView;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.Arrays;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u00020\rH\u0014J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/payment/PayNearMePresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/payment/PayNearMeView;", "router", "Lru/terrakok/cicerone/Router;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "paymentRequestData", "Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;)V", "onBackPressed", "", "onFirstViewAttach", "", "requestPaynearmeOrder", "email", "", "setGlobalRouter", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: PayNearMePresenter.kt */
public final class PayNearMePresenter extends BasePresenter<PayNearMeView> {
    private final PaymentGateway paymentGateway;
    private final PaymentRequestData paymentRequestData;
    /* access modifiers changed from: private */
    public Router router;

    @Inject
    public PayNearMePresenter(Router router2, PaymentGateway paymentGateway2, PaymentRequestData paymentRequestData2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(paymentRequestData2, "paymentRequestData");
        this.router = router2;
        this.paymentGateway = paymentGateway2;
        this.paymentRequestData = paymentRequestData2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        ((PayNearMeView) getViewState()).setInitialEmail(this.paymentRequestData.getVisitorEmail());
    }

    public final void setGlobalRouter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…thpick.qualifier.Global\")");
        this.router = (Router) instance;
    }

    public final void requestPaynearmeOrder(String str) {
        Intrinsics.checkParameterIsNotNull(str, "email");
        CompositeDisposable disposables = getDisposables();
        PaymentGateway paymentGateway2 = this.paymentGateway;
        String paymentScope = this.paymentRequestData.getPaymentScope();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.2f", Arrays.copyOf(new Object[]{this.paymentRequestData.getAmount()}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        Single<ApiResponse<PaynearmeResponse>> processWithPayNearMe = paymentGateway2.processWithPayNearMe(paymentScope, StringsKt.replace$default(format, ",", ".", false, 4, (Object) null), str, this.paymentRequestData.getInmateId(), this.paymentRequestData.getOccupantId());
        Intrinsics.checkExpressionValueIsNotNull(processWithPayNearMe, "paymentGateway.processWi…Data.occupantId\n        )");
        Disposable subscribe = CommonKt.applyAsync(processWithPayNearMe).doOnSubscribe(new PayNearMePresenter$requestPaynearmeOrder$1(this)).subscribe(new PayNearMePresenter$requestPaynearmeOrder$2(this), new PayNearMePresenter$requestPaynearmeOrder$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.processWi…sage()\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }
}
