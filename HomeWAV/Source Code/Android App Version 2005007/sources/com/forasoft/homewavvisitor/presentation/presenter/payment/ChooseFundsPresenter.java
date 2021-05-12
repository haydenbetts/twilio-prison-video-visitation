package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.braintreepayments.api.models.PaymentMethodNonce;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.payment.Handling;
import com.forasoft.homewavvisitor.model.data.payment.PaymentState;
import com.forasoft.homewavvisitor.model.data.payment.PaymentStateHolder;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.ResultListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B7\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0012\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020\u001bH\u0002J\u0006\u0010%\u001a\u00020&J\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020\u0017H\u0002J\u0016\u0010+\u001a\u00020#2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u001bJ\u0016\u0010.\u001a\u00020#2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u001bJ\u0006\u0010/\u001a\u00020(J\u0006\u00100\u001a\u00020#J\u000e\u00101\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001bJ\u0006\u00102\u001a\u00020#J\b\u00103\u001a\u00020#H\u0014J\u0018\u00104\u001a\u00020#2\u000e\u00105\u001a\n\u0012\u0004\u0012\u000207\u0018\u000106H\u0002J\u001e\u00108\u001a\u00020#2\u0006\u0010,\u001a\u00020\u00132\u0006\u0010-\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\u0013J\u0010\u0010:\u001a\u00020#2\u0006\u0010;\u001a\u00020<H\u0002J\u000e\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020\u001bJ\u0014\u0010?\u001a\u00020#2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020B0AJ\u0012\u0010C\u001a\u00020#2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\u0018\u0010F\u001a\u00020#2\u000e\u00105\u001a\n\u0012\u0004\u0012\u00020G\u0018\u000106H\u0002J\u001f\u0010H\u001a\u0004\u0018\u00010 2\u0006\u0010-\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u0013H\u0002¢\u0006\u0002\u0010IJ\u0006\u0010J\u001a\u00020#J\b\u0010K\u001a\u00020#H\u0002J\u0006\u0010L\u001a\u00020#J\u0006\u0010M\u001a\u00020#J\b\u0010N\u001a\u00020#H\u0002J\u000e\u0010O\u001a\u00020#2\u0006\u0010P\u001a\u00020&R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/payment/ChooseFundsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/payment/ChooseFundsView;", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "paymentStateHolder", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentStateHolder;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "homewavApi", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lcom/forasoft/homewavvisitor/model/data/payment/PaymentStateHolder;Lcom/forasoft/homewavvisitor/model/Analytics;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "amounts", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "cards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "currentConnection", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "currentPaymentScope", "", "currentSelectedFacility", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "idempotencyKey", "lastTransactionAmount", "", "lastTransactionCard", "fetchHandlingFee", "", "scope", "getPaymentState", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentState;", "isTransactionDuplicated", "", "amount", "card", "navigateToPaynearme", "indexOfSelected", "otherAmount", "onAddCardClicked", "onBackPressed", "onBraintreeError", "onChoosePaymentScope", "onExecutePayment", "onFirstViewAttach", "onHandlingObtained", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/Handling;", "onNextClicked", "indexOfCard", "onObtainFailed", "error", "", "onReceivePaymentMethod", "nonce", "onReceivePaymentMethods", "paymentMethods", "", "Lcom/braintreepayments/api/models/PaymentMethodNonce;", "onResult", "resultData", "", "onTokenObtained", "Lcom/forasoft/homewavvisitor/model/data/payment/BraintreeToken;", "parseRealAmount", "(Ljava/lang/String;I)Ljava/lang/Float;", "refresh", "refreshToken", "resetPaymentScope", "setGlobalRouter", "setPaymentScope", "setPaymentState", "paymentState", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: ChooseFundsPresenter.kt */
public final class ChooseFundsPresenter extends BasePresenter<ChooseFundsView> implements ResultListener {
    private final ArrayList<Integer> amounts = CollectionsKt.arrayListOf(5, 10, 20);
    /* access modifiers changed from: private */
    public final Analytics analytics;
    /* access modifiers changed from: private */
    public final List<Card> cards = new ArrayList();
    /* access modifiers changed from: private */
    public InmateByVisitor currentConnection;
    /* access modifiers changed from: private */
    public String currentPaymentScope;
    /* access modifiers changed from: private */
    public Facility currentSelectedFacility;
    private final HeartbeatRepository heartbeatRepository;
    private final HomewavApi homewavApi;
    /* access modifiers changed from: private */
    public String idempotencyKey = "";
    private float lastTransactionAmount;
    private Card lastTransactionCard;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    private final PaymentStateHolder paymentStateHolder;
    /* access modifiers changed from: private */
    public HomewavRouter router;

    @Inject
    public ChooseFundsPresenter(HomewavRouter homewavRouter, PaymentGateway paymentGateway2, PaymentStateHolder paymentStateHolder2, Analytics analytics2, HeartbeatRepository heartbeatRepository2, HomewavApi homewavApi2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(paymentStateHolder2, "paymentStateHolder");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(homewavApi2, "homewavApi");
        this.router = homewavRouter;
        this.paymentGateway = paymentGateway2;
        this.paymentStateHolder = paymentStateHolder2;
        this.analytics = analytics2;
        this.heartbeatRepository = heartbeatRepository2;
        this.homewavApi = homewavApi2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.getSelectedConnectionSubject().subscribe(new ChooseFundsPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.selectedC…State()\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
        PaymentGateway paymentGateway2 = this.paymentGateway;
        InmateByVisitor value = paymentGateway2.getSelectedConnectionSubject().getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "paymentGateway.selectedConnectionSubject.value");
        paymentGateway2.definePaymentGatewayType(value);
        refreshToken();
    }

    public final void setPaymentState(PaymentState paymentState) {
        Intrinsics.checkParameterIsNotNull(paymentState, "paymentState");
        this.paymentStateHolder.setPaymentState(paymentState);
    }

    public final PaymentState getPaymentState() {
        return this.paymentStateHolder.getPaymentState();
    }

    public void onResult(Object obj) {
        this.router.removeResultListener(-1);
        this.router.removeResultListener(0);
        refreshToken();
    }

    public final void setGlobalRouter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(HomewavRouter.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…thpick.qualifier.Global\")");
        this.router = (HomewavRouter) instance;
    }

    /* access modifiers changed from: private */
    public final void setPaymentScope() {
        CompositeDisposable disposables = getDisposables();
        HomewavApi homewavApi2 = this.homewavApi;
        InmateByVisitor inmateByVisitor = this.currentConnection;
        if (inmateByVisitor == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(homewavApi2.getFacility(inmateByVisitor.getPrison_id())).subscribe(new ChooseFundsPresenter$setPaymentScope$1(this), ChooseFundsPresenter$setPaymentScope$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "homewavApi.getFacility(c…cility\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void resetPaymentScope() {
        String str = this.currentPaymentScope;
        if (str != null) {
            ((ChooseFundsView) getViewState()).setPaymentScope(str);
        }
    }

    static /* synthetic */ void fetchHandlingFee$default(ChooseFundsPresenter chooseFundsPresenter, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "inmate";
        }
        chooseFundsPresenter.fetchHandlingFee(str);
    }

    /* access modifiers changed from: private */
    public final void fetchHandlingFee(String str) {
        InmateByVisitor inmateByVisitor = this.currentConnection;
        if (inmateByVisitor != null) {
            CompositeDisposable disposables = getDisposables();
            ChooseFundsPresenter chooseFundsPresenter = this;
            Disposable subscribe = this.paymentGateway.getHandlingFee(str, inmateByVisitor.getId(), inmateByVisitor.getOccupant_id()).subscribe(new ChooseFundsPresenter$sam$i$io_reactivex_functions_Consumer$0(new ChooseFundsPresenter$fetchHandlingFee$1$1(chooseFundsPresenter)), new ChooseFundsPresenter$sam$i$io_reactivex_functions_Consumer$0(new ChooseFundsPresenter$fetchHandlingFee$1$2(chooseFundsPresenter)));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.getHandli…ed, this::onObtainFailed)");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    public final void onChoosePaymentScope(String str) {
        Intrinsics.checkParameterIsNotNull(str, "scope");
        this.currentPaymentScope = str;
        if (Intrinsics.areEqual((Object) str, (Object) "inmate")) {
            fetchHandlingFee$default(this, (String) null, 1, (Object) null);
        } else {
            fetchHandlingFee("occupant");
        }
    }

    public final void onReceivePaymentMethod(String str) {
        Intrinsics.checkParameterIsNotNull(str, "nonce");
        this.paymentGateway.getPaymentTokenSubject().onNext(str);
    }

    public final void onReceivePaymentMethods(List<? extends PaymentMethodNonce> list) {
        Intrinsics.checkParameterIsNotNull(list, "paymentMethods");
        List<Card> list2 = this.cards;
        list2.clear();
        Iterable<PaymentMethodNonce> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (PaymentMethodNonce paymentMethodNonce : iterable) {
            String nonce = paymentMethodNonce.getNonce();
            Intrinsics.checkExpressionValueIsNotNull(nonce, "it.nonce");
            String typeLabel = paymentMethodNonce.getTypeLabel();
            Intrinsics.checkExpressionValueIsNotNull(typeLabel, "it.typeLabel");
            String description = paymentMethodNonce.getDescription();
            Intrinsics.checkExpressionValueIsNotNull(description, "it.description");
            arrayList.add(new Card(nonce, typeLabel, description, "", paymentMethodNonce.isDefault()));
        }
        list2.addAll((List) arrayList);
        CollectionsKt.sortedWith(list2, new ChooseFundsPresenter$$special$$inlined$sortedByDescending$1());
        ((ChooseFundsView) getViewState()).displayCards(this.cards);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r2 = r2.getBody();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onTokenObtained(com.forasoft.homewavvisitor.model.server.response.ApiResponse<com.forasoft.homewavvisitor.model.data.payment.BraintreeToken> r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x000f
            java.lang.Object r2 = r2.getBody()
            com.forasoft.homewavvisitor.model.data.payment.BraintreeToken r2 = (com.forasoft.homewavvisitor.model.data.payment.BraintreeToken) r2
            if (r2 == 0) goto L_0x000f
            java.lang.String r2 = r2.getToken()
            goto L_0x0010
        L_0x000f:
            r2 = 0
        L_0x0010:
            if (r2 == 0) goto L_0x001b
            moxy.MvpView r0 = r1.getViewState()
            com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView r0 = (com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView) r0
            r0.initPayment(r2)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.payment.ChooseFundsPresenter.onTokenObtained(com.forasoft.homewavvisitor.model.server.response.ApiResponse):void");
    }

    /* access modifiers changed from: private */
    public final void onObtainFailed(Throwable th) {
        Timber.e(th);
    }

    public final void onAddCardClicked(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, "otherAmount");
        Float parseRealAmount = parseRealAmount(str, i);
        if (parseRealAmount != null) {
            parseRealAmount.floatValue();
            this.paymentGateway.getPaymentAmountSubject().onNext(parseRealAmount);
            ResultListener resultListener = this;
            this.router.setResultListener(-1, resultListener);
            this.router.setResultListener(0, resultListener);
            this.router.navigateTo(new Screens.AddCardScreen(this.currentPaymentScope));
        }
    }

    /* access modifiers changed from: private */
    public final void onHandlingObtained(ApiResponse<Handling> apiResponse) {
        Handling body;
        if (apiResponse != null && (body = apiResponse.getBody()) != null) {
            ((ChooseFundsView) getViewState()).showHandling(body.getHandling());
        }
    }

    public final void onBraintreeError() {
        ((ChooseFundsView) getViewState()).showBraintreeError();
        this.router.exit();
    }

    public final void onNextClicked(int i, String str, int i2) {
        Intrinsics.checkParameterIsNotNull(str, "otherAmount");
        Float parseRealAmount = parseRealAmount(str, i);
        if (parseRealAmount != null) {
            float floatValue = parseRealAmount.floatValue();
            List<Card> list = this.cards;
            if (i2 >= 0 && i2 <= CollectionsKt.getLastIndex(list)) {
                Card card = list.get(i2);
                if (isTransactionDuplicated(floatValue, card)) {
                    ((ChooseFundsView) getViewState()).showErrorMessage();
                    return;
                }
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
                this.idempotencyKey = uuid;
                this.lastTransactionAmount = floatValue;
                this.lastTransactionCard = card;
                CompositeDisposable disposables = getDisposables();
                Observable<R> flatMapSingle = this.paymentGateway.getSelectedConnectionSubject().take(1).flatMapSingle(new ChooseFundsPresenter$onNextClicked$1(this, floatValue, card));
                Intrinsics.checkExpressionValueIsNotNull(flatMapSingle, "paymentGateway.selectedC…      )\n                }");
                Disposable subscribe = CommonKt.applyAsync(flatMapSingle).doOnSubscribe(new ChooseFundsPresenter$onNextClicked$2(this)).doOnNext(new ChooseFundsPresenter$onNextClicked$3(this)).subscribe(new ChooseFundsPresenter$onNextClicked$4(this), new ChooseFundsPresenter$onNextClicked$5(this));
                Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.selectedC…sage()\n                })");
                DisposableKt.plusAssign(disposables, subscribe);
                this.paymentGateway.getPaymentAmountSubject().onNext(Float.valueOf(floatValue));
            }
        }
    }

    private final boolean isTransactionDuplicated(float f, Card card) {
        return f == this.lastTransactionAmount && Intrinsics.areEqual((Object) card, (Object) this.lastTransactionCard);
    }

    public final void navigateToPaynearme(int i, String str) {
        Intrinsics.checkParameterIsNotNull(str, "otherAmount");
        Float parseRealAmount = parseRealAmount(str, i);
        if (parseRealAmount != null) {
            parseRealAmount.floatValue();
            CompositeDisposable disposables = getDisposables();
            Observable<InmateByVisitor> take = this.paymentGateway.getSelectedConnectionSubject().take(1);
            Intrinsics.checkExpressionValueIsNotNull(take, "paymentGateway.selectedC…\n                .take(1)");
            Disposable subscribe = CommonKt.applyAsync(take).subscribe(new ChooseFundsPresenter$navigateToPaynearme$1(this, parseRealAmount), new ChooseFundsPresenter$navigateToPaynearme$2(this));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.selectedC…sage()\n                })");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    /* access modifiers changed from: private */
    public final void refreshToken() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.getGatewayTypeSubject().take(1).subscribe(new ChooseFundsPresenter$refreshToken$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.gatewayTy…      }\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final Float parseRealAmount(String str, int i) {
        if (i != 3) {
            return Float.valueOf((float) this.amounts.get(i).intValue());
        }
        if (str != null) {
            String substring = str.substring(1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            try {
                float parseFloat = Float.parseFloat(substring);
                if (parseFloat >= 0.01f) {
                    return Float.valueOf(parseFloat);
                }
                throw new NumberFormatException();
            } catch (NumberFormatException unused) {
                ((ChooseFundsView) getViewState()).showMessage("Amount is incorrect");
                return null;
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    public final void refresh() {
        this.heartbeatRepository.doHeartbeat();
    }

    public final void onExecutePayment() {
        if (Intrinsics.areEqual((Object) this.currentPaymentScope, (Object) "occupant")) {
            ((ChooseFundsView) getViewState()).showGeneralFundsConfirmation();
        } else {
            ((ChooseFundsView) getViewState()).executePayment();
        }
    }
}
