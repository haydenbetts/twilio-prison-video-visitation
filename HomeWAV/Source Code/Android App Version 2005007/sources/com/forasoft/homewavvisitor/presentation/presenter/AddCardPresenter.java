package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.payment.PaymentState;
import com.forasoft.homewavvisitor.model.data.payment.PaymentStateHolder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.interactor.PaymentGatewayType;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.AddCardView;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import moxy.InjectViewState;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"H\u0002J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020\u001fJ\u0006\u0010&\u001a\u00020\u001fJ\u000e\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u000fJ8\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\b\u0010-\u001a\u0004\u0018\u00010\u000f2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/J\b\u00101\u001a\u00020\u001fH\u0014J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000204H\u0002J\u000e\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u000fJ\u0018\u00107\u001a\u00020\u001f2\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020:\u0018\u000109H\u0002J\b\u0010;\u001a\u00020\u001fH\u0002J\u0010\u0010.\u001a\u00020\u001f2\u0006\u00100\u001a\u00020/H\u0002J\u0006\u0010<\u001a\u00020\u001fJ\u000e\u0010=\u001a\u00020\u001f2\u0006\u0010>\u001a\u00020$J*\u0010?\u001a\u00020/2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\b\u0010-\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/AddCardPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/AddCardView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "paymentStateHolder", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentStateHolder;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lcom/forasoft/homewavvisitor/model/data/payment/PaymentStateHolder;)V", "amexRegexp", "Lkotlin/text/Regex;", "dinersRegexp", "discoverRegexp", "idempotencyKey", "", "jcbRegexp", "maestroRegexp", "mastercardRegexp", "paymentAmount", "", "Ljava/lang/Float;", "paymentScope", "getPaymentScope", "()Ljava/lang/String;", "setPaymentScope", "(Ljava/lang/String;)V", "regexs", "", "visaRegexp", "closeScreen", "", "closeScreenWithResult", "result", "", "getPaymentState", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentState;", "initializePayments", "onBackPressed", "onCardNumberChanged", "text", "onContinueClicked", "cardNumber", "cvv", "expires", "zip", "saveCard", "", "makeDefault", "onFirstViewAttach", "onObtainFailed", "error", "", "onReceivePaymentMethod", "nonce", "onTokenObtained", "response", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/BraintreeToken;", "processPayment", "setGlobalRouter", "setPaymentState", "paymentState", "validateInputs", "number", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: AddCardPresenter.kt */
public final class AddCardPresenter extends BasePresenter<AddCardView> {
    private final Regex amexRegexp;
    private final Regex dinersRegexp;
    private final Regex discoverRegexp;
    /* access modifiers changed from: private */
    public String idempotencyKey;
    private final Regex jcbRegexp;
    private final Regex maestroRegexp;
    private final Regex mastercardRegexp;
    /* access modifiers changed from: private */
    public Float paymentAmount;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    private String paymentScope = "inmate";
    private final PaymentStateHolder paymentStateHolder;
    private final List<Regex> regexs;
    private HomewavRouter router;
    private final Regex visaRegexp;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PaymentGatewayType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PaymentGatewayType.BRAINTREE.ordinal()] = 1;
            iArr[PaymentGatewayType.STRIPE.ordinal()] = 2;
        }
    }

    @Inject
    public AddCardPresenter(HomewavRouter homewavRouter, PaymentGateway paymentGateway2, PaymentStateHolder paymentStateHolder2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(paymentStateHolder2, "paymentStateHolder");
        this.router = homewavRouter;
        this.paymentGateway = paymentGateway2;
        this.paymentStateHolder = paymentStateHolder2;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.idempotencyKey = uuid;
        Regex regex = new Regex("^5$|^5[1-5]\\d*$");
        this.mastercardRegexp = regex;
        Regex regex2 = new Regex("^5$|^50\\d*$|^6$|^5[6-9]\\d*$|^6[0-9]\\d*$");
        this.maestroRegexp = regex2;
        Regex regex3 = new Regex("^4\\d*$");
        this.visaRegexp = regex3;
        Regex regex4 = new Regex("^3$|^34\\d*$|^37\\d*$");
        this.amexRegexp = regex4;
        Regex regex5 = new Regex("^3$|^30$|^30[0-5]\\d*$|^36\\d*$");
        this.dinersRegexp = regex5;
        Regex regex6 = new Regex("^6$|^60$|^601$|^6011\\d*$");
        this.discoverRegexp = regex6;
        Regex regex7 = new Regex("^3\\d*$|^2$|^21$|^213$|^2131\\d*$|^1$|^18$|^180$|^1800\\d*$");
        this.jcbRegexp = regex7;
        this.regexs = CollectionsKt.arrayListOf(regex, regex2, regex3, regex4, regex5, regex6, regex7);
    }

    public final String getPaymentScope() {
        return this.paymentScope;
    }

    public final void setPaymentScope(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.paymentScope = str;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.getPaymentAmountSubject().subscribe(new AddCardPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.paymentAm…be { paymentAmount = it }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void setPaymentState(PaymentState paymentState) {
        Intrinsics.checkParameterIsNotNull(paymentState, "paymentState");
        this.paymentStateHolder.setPaymentState(paymentState);
    }

    public final PaymentState getPaymentState() {
        return this.paymentStateHolder.getPaymentState();
    }

    public final void setGlobalRouter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(HomewavRouter.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…thpick.qualifier.Global\")");
        this.router = (HomewavRouter) instance;
    }

    public final void onCardNumberChanged(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        ((AddCardView) getViewState()).clearCardSystems();
        CharSequence charSequence = str;
        if (!(charSequence.length() == 0)) {
            int lastIndex = CollectionsKt.getLastIndex(this.regexs);
            for (int i = 0; i < lastIndex; i++) {
                if (this.regexs.get(i).matches(charSequence)) {
                    ((AddCardView) getViewState()).addCardSystemWithIndex(i);
                }
            }
        }
    }

    public final void onContinueClicked(String str, String str2, String str3, String str4, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(str, "cardNumber");
        Intrinsics.checkParameterIsNotNull(str2, "cvv");
        Intrinsics.checkParameterIsNotNull(str3, "expires");
        if (validateInputs(str, str2, str3, str4)) {
            CompositeDisposable disposables = getDisposables();
            Disposable subscribe = this.paymentGateway.getGatewayTypeSubject().take(1).subscribe(new AddCardPresenter$onContinueClicked$1(this, str, str3, str2, str4));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.gatewayTy…      }\n                }");
            DisposableKt.plusAssign(disposables, subscribe);
            if (z) {
                saveCard(z2);
            } else {
                processPayment();
            }
        }
    }

    public final void initializePayments() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.getGatewayTypeSubject().take(1).subscribe(new AddCardPresenter$initializePayments$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.gatewayTy…      }\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onReceivePaymentMethod(String str) {
        Intrinsics.checkParameterIsNotNull(str, "nonce");
        this.paymentGateway.getPaymentTokenSubject().onNext(str);
    }

    public final void onBackPressed() {
        closeScreenWithResult(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0079 A[Catch:{ all -> 0x0086 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x007a A[Catch:{ all -> 0x0086 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean validateInputs(java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15) {
        /*
            r11 = this;
            java.lang.String r0 = "/"
            r1 = 0
            int r12 = r12.length()     // Catch:{ all -> 0x00fe }
            r2 = 12
            r3 = 1
            if (r12 <= r2) goto L_0x000e
            r12 = 1
            goto L_0x000f
        L_0x000e:
            r12 = 0
        L_0x000f:
            java.lang.String r4 = "Failed requirement."
            if (r12 == 0) goto L_0x00f2
            r12 = r14
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ all -> 0x00e6 }
            r5 = r0
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ all -> 0x00e6 }
            r6 = 2
            r7 = 0
            boolean r12 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r12, (java.lang.CharSequence) r5, (boolean) r1, (int) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x00e6 }
            if (r12 == 0) goto L_0x00da
            r5 = r14
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.String[] r6 = new java.lang.String[]{r0}
            r7 = 0
            r8 = 0
            r9 = 6
            r10 = 0
            java.util.List r12 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r5, (java.lang.String[]) r6, (boolean) r7, (int) r8, (int) r9, (java.lang.Object) r10)
            java.lang.Object r14 = r12.get(r1)     // Catch:{ all -> 0x00ce }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x00ce }
            int r14 = java.lang.Integer.parseInt(r14)     // Catch:{ all -> 0x00ce }
            if (r14 > r2) goto L_0x003e
            r14 = 1
            goto L_0x003f
        L_0x003e:
            r14 = 0
        L_0x003f:
            if (r14 == 0) goto L_0x00c2
            java.lang.Object r12 = r12.get(r3)     // Catch:{ all -> 0x00b6 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x00b6 }
            int r12 = java.lang.Integer.parseInt(r12)     // Catch:{ all -> 0x00b6 }
            r14 = 19
            if (r12 <= r14) goto L_0x0051
            r12 = 1
            goto L_0x0052
        L_0x0051:
            r12 = 0
        L_0x0052:
            if (r12 == 0) goto L_0x00aa
            r12 = 4
            r14 = 3
            int r13 = r13.length()     // Catch:{ all -> 0x009e }
            if (r14 <= r13) goto L_0x005d
            goto L_0x0061
        L_0x005d:
            if (r12 < r13) goto L_0x0061
            r12 = 1
            goto L_0x0062
        L_0x0061:
            r12 = 0
        L_0x0062:
            if (r12 == 0) goto L_0x0092
            if (r15 == 0) goto L_0x0076
            java.lang.CharSequence r15 = (java.lang.CharSequence) r15     // Catch:{ all -> 0x0086 }
            int r12 = r15.length()     // Catch:{ all -> 0x0086 }
            if (r12 <= 0) goto L_0x0070
            r12 = 1
            goto L_0x0071
        L_0x0070:
            r12 = 0
        L_0x0071:
            if (r12 == 0) goto L_0x0074
            goto L_0x0076
        L_0x0074:
            r12 = 0
            goto L_0x0077
        L_0x0076:
            r12 = 1
        L_0x0077:
            if (r12 == 0) goto L_0x007a
            return r3
        L_0x007a:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0086 }
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x0086 }
            r12.<init>(r13)     // Catch:{ all -> 0x0086 }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ all -> 0x0086 }
            throw r12     // Catch:{ all -> 0x0086 }
        L_0x0086:
            moxy.MvpView r12 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.AddCardView r12 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r12
            java.lang.String r13 = "Zip is required"
            r12.showMessage((java.lang.String) r13)
            return r1
        L_0x0092:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x009e }
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x009e }
            r12.<init>(r13)     // Catch:{ all -> 0x009e }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ all -> 0x009e }
            throw r12     // Catch:{ all -> 0x009e }
        L_0x009e:
            moxy.MvpView r12 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.AddCardView r12 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r12
            java.lang.String r13 = "CVV is incorrect"
            r12.showMessage((java.lang.String) r13)
            return r1
        L_0x00aa:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00b6 }
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x00b6 }
            r12.<init>(r13)     // Catch:{ all -> 0x00b6 }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ all -> 0x00b6 }
            throw r12     // Catch:{ all -> 0x00b6 }
        L_0x00b6:
            moxy.MvpView r12 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.AddCardView r12 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r12
            java.lang.String r13 = "Expires year is incorrect"
            r12.showMessage((java.lang.String) r13)
            return r1
        L_0x00c2:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00ce }
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x00ce }
            r12.<init>(r13)     // Catch:{ all -> 0x00ce }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ all -> 0x00ce }
            throw r12     // Catch:{ all -> 0x00ce }
        L_0x00ce:
            moxy.MvpView r12 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.AddCardView r12 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r12
            java.lang.String r13 = "Expires month is incorrect"
            r12.showMessage((java.lang.String) r13)
            return r1
        L_0x00da:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00e6 }
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x00e6 }
            r12.<init>(r13)     // Catch:{ all -> 0x00e6 }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ all -> 0x00e6 }
            throw r12     // Catch:{ all -> 0x00e6 }
        L_0x00e6:
            moxy.MvpView r12 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.AddCardView r12 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r12
            java.lang.String r13 = "Expires date is incorrect"
            r12.showMessage((java.lang.String) r13)
            return r1
        L_0x00f2:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00fe }
            java.lang.String r13 = r4.toString()     // Catch:{ all -> 0x00fe }
            r12.<init>(r13)     // Catch:{ all -> 0x00fe }
            java.lang.Throwable r12 = (java.lang.Throwable) r12     // Catch:{ all -> 0x00fe }
            throw r12     // Catch:{ all -> 0x00fe }
        L_0x00fe:
            moxy.MvpView r12 = r11.getViewState()
            com.forasoft.homewavvisitor.presentation.view.AddCardView r12 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r12
            java.lang.String r13 = "Card number is incorrect"
            r12.showMessage((java.lang.String) r13)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.AddCardPresenter.validateInputs(java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
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
            com.forasoft.homewavvisitor.presentation.view.AddCardView r0 = (com.forasoft.homewavvisitor.presentation.view.AddCardView) r0
            r0.initPayment(r2)
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.AddCardPresenter.onTokenObtained(com.forasoft.homewavvisitor.model.server.response.ApiResponse):void");
    }

    /* access modifiers changed from: private */
    public final void onObtainFailed(Throwable th) {
        Timber.e(th);
    }

    private final void saveCard(boolean z) {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.saveCard(z).doOnSubscribe(new AddCardPresenter$saveCard$1(this)).doOnNext(new AddCardPresenter$saveCard$2(this)).subscribe(new AddCardPresenter$saveCard$3(this), new AddCardPresenter$saveCard$4(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.saveCard(…error)\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final void processPayment() {
        CompositeDisposable disposables = getDisposables();
        Observable<R> flatMapSingle = this.paymentGateway.getPaymentTokenSubject().take(1).flatMapSingle(new AddCardPresenter$processPayment$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMapSingle, "paymentGateway.paymentTo…      )\n                }");
        Disposable subscribe = CommonKt.applyAsync(flatMapSingle).doOnSubscribe(new AddCardPresenter$processPayment$2(this)).doOnNext(new AddCardPresenter$processPayment$3(this)).subscribe(new AddCardPresenter$processPayment$4(this), new AddCardPresenter$processPayment$5(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.paymentTo…reen()\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void closeScreen() {
        this.router.exit();
    }

    /* access modifiers changed from: private */
    public final void closeScreenWithResult(int i) {
        this.router.exitWithResult(Integer.valueOf(i), (Object) null);
    }
}
