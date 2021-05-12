package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.payment.BraintreeToken;
import com.forasoft.homewavvisitor.model.data.payment.Handling;
import com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse;
import com.forasoft.homewavvisitor.model.data.payment.PaynearmeResponse;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.repository.PaymentRepository;
import com.forasoft.homewavvisitor.model.server.apis.PaymentApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 >2\u00020\u0001:\u0001>B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0017J&\u0010\u001c\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020 0\u001f0\u001e0\u001d2\u0006\u0010!\u001a\u00020\u0014J\u0018\u0010\"\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\u001e0\u001dJ\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\u001e0\u001dJ*\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u001e0\u001d2\u0006\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u0014JS\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020-0\u001e0\u001d2\u0006\u0010)\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u00142\n\b\u0002\u00101\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u00102Jj\u00103\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u000204 5*\n\u0012\u0004\u0012\u000204\u0018\u00010\u001e0\u001e 5*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u000204 5*\n\u0012\u0004\u0012\u000204\u0018\u00010\u001e0\u001e\u0018\u00010\u001d0\u001d2\u0006\u0010)\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\u00142\u0006\u00106\u001a\u00020\u00142\u0006\u0010*\u001a\u00020\u00142\u0006\u0010+\u001a\u00020\u0014J\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020 082\u0006\u00109\u001a\u00020 J.\u0010:\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020 0\u001f0\u001e0\u001d2\u0006\u0010!\u001a\u00020\u00142\u0006\u0010;\u001a\u00020 J\f\u0010<\u001a\u00020=*\u00020 H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006?"}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "", "paymentRepository", "Lcom/forasoft/homewavvisitor/model/repository/PaymentRepository;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "paymentApi", "Lcom/forasoft/homewavvisitor/model/server/apis/PaymentApi;", "(Lcom/forasoft/homewavvisitor/model/repository/PaymentRepository;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/server/apis/PaymentApi;)V", "gatewayTypeSubject", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "getGatewayTypeSubject", "()Lio/reactivex/subjects/BehaviorSubject;", "paymentAmountSubject", "", "getPaymentAmountSubject", "paymentTokenSubject", "", "getPaymentTokenSubject", "selectedConnectionSubject", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "getSelectedConnectionSubject", "definePaymentGatewayType", "", "inmate", "deleteCard", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "token", "getCards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "getClientToken", "Lcom/forasoft/homewavvisitor/model/data/payment/BraintreeToken;", "getHandlingFee", "Lcom/forasoft/homewavvisitor/model/data/payment/Handling;", "scope", "inmateId", "occupantId", "processPayment", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "amount", "nonce", "idempotencyKey", "useSavedCard", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/Single;", "processWithPayNearMe", "Lcom/forasoft/homewavvisitor/model/data/payment/PaynearmeResponse;", "kotlin.jvm.PlatformType", "email", "saveCard", "Lio/reactivex/Observable;", "makeDefault", "updateCardStatus", "default", "toInt", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
public final class PaymentGateway {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EMPTY = "empty";
    public static final String PAYMENT_STATE = "payment state";
    private final AuthHolder authHolder;
    private final BehaviorSubject<PaymentGatewayType> gatewayTypeSubject;
    private final InmateDao inmateDao;
    private final BehaviorSubject<Float> paymentAmountSubject;
    /* access modifiers changed from: private */
    public final PaymentApi paymentApi;
    /* access modifiers changed from: private */
    public final PaymentRepository paymentRepository;
    private final BehaviorSubject<String> paymentTokenSubject;
    private final BehaviorSubject<InmateByVisitor> selectedConnectionSubject;

    /* access modifiers changed from: private */
    public final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    @Inject
    public PaymentGateway(PaymentRepository paymentRepository2, InmateDao inmateDao2, AuthHolder authHolder2, PaymentApi paymentApi2) {
        Intrinsics.checkParameterIsNotNull(paymentRepository2, "paymentRepository");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(paymentApi2, "paymentApi");
        this.paymentRepository = paymentRepository2;
        this.inmateDao = inmateDao2;
        this.authHolder = authHolder2;
        this.paymentApi = paymentApi2;
        BehaviorSubject<PaymentGatewayType> create = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "BehaviorSubject.create()");
        this.gatewayTypeSubject = create;
        BehaviorSubject<String> create2 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create2, "BehaviorSubject.create()");
        this.paymentTokenSubject = create2;
        BehaviorSubject<InmateByVisitor> create3 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create3, "BehaviorSubject.create()");
        this.selectedConnectionSubject = create3;
        BehaviorSubject<Float> create4 = BehaviorSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create4, "BehaviorSubject.create()");
        this.paymentAmountSubject = create4;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway$Companion;", "", "()V", "EMPTY", "", "PAYMENT_STATE", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: PaymentGateway.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final BehaviorSubject<PaymentGatewayType> getGatewayTypeSubject() {
        return this.gatewayTypeSubject;
    }

    public final BehaviorSubject<String> getPaymentTokenSubject() {
        return this.paymentTokenSubject;
    }

    public final BehaviorSubject<InmateByVisitor> getSelectedConnectionSubject() {
        return this.selectedConnectionSubject;
    }

    public final BehaviorSubject<Float> getPaymentAmountSubject() {
        return this.paymentAmountSubject;
    }

    public final void definePaymentGatewayType() {
        InmateDao inmateDao2 = this.inmateDao;
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        inmateDao2.getInmates(user.getVisitor_id()).subscribe(new PaymentGateway$definePaymentGatewayType$1(this));
    }

    public final void definePaymentGatewayType(InmateByVisitor inmateByVisitor) {
        Intrinsics.checkParameterIsNotNull(inmateByVisitor, "inmate");
        if (inmateByVisitor.getPrison_payment_gateway().equals("stripe")) {
            this.gatewayTypeSubject.onNext(PaymentGatewayType.STRIPE);
        } else {
            this.gatewayTypeSubject.onNext(PaymentGatewayType.BRAINTREE);
        }
    }

    public final Single<ApiResponse<BraintreeToken>> getClientToken() {
        return this.paymentRepository.obtainToken();
    }

    public final Single<ApiResponse<List<Card>>> getCards() {
        Single<R> flatMap = this.gatewayTypeSubject.take(1).singleOrError().flatMap(new PaymentGateway$getCards$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "gatewayTypeSubject\n     …      }\n                }");
        Single<ApiResponse<List<Card>>> applyAsync = CommonKt.applyAsync(flatMap);
        Intrinsics.checkExpressionValueIsNotNull(applyAsync, "gatewayTypeSubject\n     …            .applyAsync()");
        return applyAsync;
    }

    public final Single<ApiResponse<Map<String, Boolean>>> deleteCard(String str) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        Single<R> flatMap = this.gatewayTypeSubject.take(1).singleOrError().flatMap(new PaymentGateway$deleteCard$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "gatewayTypeSubject\n     …      }\n                }");
        return flatMap;
    }

    public final Single<ApiResponse<Map<String, Boolean>>> updateCardStatus(String str, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        Single<R> flatMap = this.gatewayTypeSubject.take(1).singleOrError().flatMap(new PaymentGateway$updateCardStatus$1(this, str, z));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "gatewayTypeSubject\n     …      }\n                }");
        return flatMap;
    }

    public final Observable<Boolean> saveCard(boolean z) {
        Observable<R> observable = this.gatewayTypeSubject.take(1).singleOrError().flatMap(new PaymentGateway$saveCard$1(this, z)).toObservable();
        Intrinsics.checkExpressionValueIsNotNull(observable, "gatewayTypeSubject\n     …          .toObservable()");
        return observable;
    }

    public static /* synthetic */ Single processPayment$default(PaymentGateway paymentGateway, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, int i, Object obj) {
        return paymentGateway.processPayment(str, str2, str3, str4, str5, str6, (i & 64) != 0 ? null : bool);
    }

    public final Single<ApiResponse<PaymentProcessedResponse>> processPayment(String str, String str2, String str3, String str4, String str5, String str6, Boolean bool) {
        Intrinsics.checkParameterIsNotNull(str, "scope");
        Intrinsics.checkParameterIsNotNull(str2, "amount");
        Intrinsics.checkParameterIsNotNull(str3, "nonce");
        String str7 = str4;
        Intrinsics.checkParameterIsNotNull(str7, "inmateId");
        String str8 = str5;
        Intrinsics.checkParameterIsNotNull(str8, "occupantId");
        String str9 = str6;
        Intrinsics.checkParameterIsNotNull(str9, "idempotencyKey");
        Single<R> flatMap = this.gatewayTypeSubject.take(1).singleOrError().flatMap(new PaymentGateway$processPayment$1(this, str, str2, str3, str7, str8, str9, bool)).flatMap(new PaymentGateway$processPayment$2(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "gatewayTypeSubject\n     …      }\n                }");
        Single<ApiResponse<PaymentProcessedResponse>> applyAsync = CommonKt.applyAsync(flatMap);
        Intrinsics.checkExpressionValueIsNotNull(applyAsync, "gatewayTypeSubject\n     …            .applyAsync()");
        return applyAsync;
    }

    public final Single<ApiResponse<PaynearmeResponse>> processWithPayNearMe(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkParameterIsNotNull(str, "scope");
        Intrinsics.checkParameterIsNotNull(str2, "amount");
        Intrinsics.checkParameterIsNotNull(str3, "email");
        Intrinsics.checkParameterIsNotNull(str4, "inmateId");
        Intrinsics.checkParameterIsNotNull(str5, "occupantId");
        return CommonKt.applyAsync(this.paymentApi.processWithPaynearme(str, str2, str3, str4, str5));
    }

    public final Single<ApiResponse<Handling>> getHandlingFee(String str, String str2, String str3) {
        Intrinsics.checkParameterIsNotNull(str, "scope");
        Intrinsics.checkParameterIsNotNull(str2, "inmateId");
        Intrinsics.checkParameterIsNotNull(str3, "occupantId");
        Single<ApiResponse<Handling>> applyAsync = CommonKt.applyAsync(this.paymentApi.getHandlings(str, str2, str3));
        Intrinsics.checkExpressionValueIsNotNull(applyAsync, "paymentApi.getHandlings(…            .applyAsync()");
        return applyAsync;
    }
}
