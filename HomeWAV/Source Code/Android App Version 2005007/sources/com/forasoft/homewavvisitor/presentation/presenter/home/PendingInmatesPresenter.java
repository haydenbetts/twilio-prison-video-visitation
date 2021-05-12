package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.home.PendingInmatesView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.Arrays;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0014J\u000e\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0012\u0010 \u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/home/PendingInmatesPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/home/PendingInmatesView;", "router", "Lru/terrakok/cicerone/Router;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "dao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;)V", "chosenConnection", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "idempotencyKey", "", "paymentAmount", "", "Ljava/lang/Float;", "isAnyPaymentOptionAvailable", "", "facility", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "onAddFundsClicked", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "onBackPressed", "onFirstViewAttach", "onInmateClicked", "onNonceObtained", "nonce", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: PendingInmatesPresenter.kt */
public final class PendingInmatesPresenter extends BasePresenter<PendingInmatesView> {
    private final SignUpApi api;
    private final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public InmateByVisitor chosenConnection;
    private final InmateDao dao;
    private final String idempotencyKey;
    /* access modifiers changed from: private */
    public Float paymentAmount;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    /* access modifiers changed from: private */
    public final Router router;

    @Inject
    public PendingInmatesPresenter(Router router2, AuthHolder authHolder2, InmateDao inmateDao, SignUpApi signUpApi, PaymentGateway paymentGateway2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(inmateDao, "dao");
        Intrinsics.checkParameterIsNotNull(signUpApi, "api");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        this.router = router2;
        this.authHolder = authHolder2;
        this.dao = inmateDao;
        this.api = signUpApi;
        this.paymentGateway = paymentGateway2;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.idempotencyKey = uuid;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        InmateDao inmateDao = this.dao;
        User user = this.authHolder.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(inmateDao.getPendingInmates(visitor_id)).subscribe(new PendingInmatesPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "dao.getPendingInmates(au…tate.displayInmates(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = this.paymentGateway.getPaymentAmountSubject().subscribe(new PendingInmatesPresenter$onFirstViewAttach$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "paymentGateway.paymentAm…be { paymentAmount = it }");
        DisposableKt.plusAssign(disposables2, subscribe2);
        CompositeDisposable disposables3 = getDisposables();
        Disposable subscribe3 = this.paymentGateway.getPaymentTokenSubject().subscribe(new PendingInmatesPresenter$sam$io_reactivex_functions_Consumer$0(new PendingInmatesPresenter$onFirstViewAttach$3(this)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe3, "paymentGateway.paymentTo…be(this::onNonceObtained)");
        DisposableKt.plusAssign(disposables3, subscribe3);
    }

    /* access modifiers changed from: private */
    public final void onNonceObtained(String str) {
        if (!Intrinsics.areEqual((Object) str, (Object) PaymentGateway.EMPTY)) {
            this.paymentGateway.getPaymentTokenSubject().onNext(PaymentGateway.EMPTY);
            Timber.d("onNonceObtained: %s", str);
            CompositeDisposable disposables = getDisposables();
            PaymentGateway paymentGateway2 = this.paymentGateway;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%.2f", Arrays.copyOf(new Object[]{this.paymentAmount}, 1));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            String replace$default = StringsKt.replace$default(format, ",", ".", false, 4, (Object) null);
            if (str == null) {
                Intrinsics.throwNpe();
            }
            InmateByVisitor inmateByVisitor = this.chosenConnection;
            if (inmateByVisitor == null) {
                Intrinsics.throwNpe();
            }
            String id = inmateByVisitor.getId();
            InmateByVisitor inmateByVisitor2 = this.chosenConnection;
            if (inmateByVisitor2 == null) {
                Intrinsics.throwNpe();
            }
            Disposable subscribe = paymentGateway2.processPayment("inmate", replace$default, str, id, inmateByVisitor2.getOccupant_id(), this.idempotencyKey, true).subscribe(new PendingInmatesPresenter$onNonceObtained$1(this), PendingInmatesPresenter$onNonceObtained$2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.processPa….e(it)\n                })");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    public final void onAddFundsClicked(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        String approved = inmate.getApproved();
        if (approved == null) {
            Intrinsics.throwNpe();
        }
        String credit_balance = inmate.getCredit_balance();
        if (credit_balance == null) {
            Intrinsics.throwNpe();
        }
        String first_name = inmate.getFirst_name();
        if (first_name == null) {
            Intrinsics.throwNpe();
        }
        String id = inmate.getId();
        String last_name = inmate.getLast_name();
        if (last_name == null) {
            Intrinsics.throwNpe();
        }
        String occupant_id = inmate.getOccupant_id();
        String prison_id = inmate.getPrison_id();
        if (prison_id == null) {
            Intrinsics.throwNpe();
        }
        String prison_payment_gateway = inmate.getPrison_payment_gateway();
        if (prison_payment_gateway == null) {
            Intrinsics.throwNpe();
        }
        boolean isFraud = inmate.isFraud();
        User user = this.authHolder.getUser();
        this.chosenConnection = new InmateByVisitor(approved, credit_balance, first_name, id, last_name, occupant_id, prison_id, prison_payment_gateway, isFraud, user != null ? user.getEmail() : null, (String) null, 1024, (DefaultConstructorMarker) null);
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.loadFacilities()).doOnSubscribe(new PendingInmatesPresenter$onAddFundsClicked$1(this)).doOnSuccess(new PendingInmatesPresenter$onAddFundsClicked$2(this)).doOnError(new PendingInmatesPresenter$onAddFundsClicked$3(this)).subscribe(new PendingInmatesPresenter$onAddFundsClicked$4(this), PendingInmatesPresenter$onAddFundsClicked$5.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.loadFacilities()\n   … }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final boolean isAnyPaymentOptionAvailable(Facility facility) {
        if ((facility != null && !facility.isTalkToMeFundsDisabled()) || (facility != null && !facility.isGeneralFundsDisabled())) {
            InmateByVisitor inmateByVisitor = this.chosenConnection;
            if (inmateByVisitor == null) {
                Intrinsics.throwNpe();
            }
            if (!inmateByVisitor.is_fraud() || !facility.isTalkToMeFundsDisabled()) {
                return true;
            }
        }
        return false;
    }

    public final void onInmateClicked(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        this.router.navigateTo(new Screens.InmateDetailScreen(inmate));
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }
}
