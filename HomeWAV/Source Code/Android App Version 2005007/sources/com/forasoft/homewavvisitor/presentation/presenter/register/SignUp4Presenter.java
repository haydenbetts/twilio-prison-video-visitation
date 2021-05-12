package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.register.SignUp4View;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0006\u0010\u0014\u001a\u00020\u0015J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u0013J\u0006\u0010\u001d\u001a\u00020\u0015J\u0006\u0010\u001e\u001a\u00020\u0015J\u0006\u0010\u001f\u001a\u00020\u0015J\u0016\u0010 \u001a\u00020\u00152\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\"H\u0002J\u0006\u0010#\u001a\u00020\u0015J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp4Presenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp4View;", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "chosenConnection", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "getNotificationsCount", "", "isAnyPaymentOptionAvailable", "", "facility", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "onBackPressed", "onItemClicked", "inmate", "onNotificationsClicked", "onSkipClicked", "onTransferClicked", "processInmates", "inmates", "", "refresh", "subscribe", "isMainActivity", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: SignUp4Presenter.kt */
public final class SignUp4Presenter extends BasePresenter<SignUp4View> {
    private final SignUpApi api;
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public InmateByVisitor chosenConnection;
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    private final InmateDao inmateDao;
    private final NotificationDao notificationDao;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    /* access modifiers changed from: private */
    public Router router;

    @Inject
    public SignUp4Presenter(Router router2, SignUpApi signUpApi, AuthHolder authHolder2, InmateDao inmateDao2, PaymentGateway paymentGateway2, NotificationDao notificationDao2, HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(signUpApi, "api");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.router = router2;
        this.api = signUpApi;
        this.authHolder = authHolder2;
        this.inmateDao = inmateDao2;
        this.paymentGateway = paymentGateway2;
        this.notificationDao = notificationDao2;
        this.heartbeatRepository = heartbeatRepository2;
    }

    public final void subscribe(boolean z) {
        CompositeDisposable disposables = getDisposables();
        InmateDao inmateDao2 = this.inmateDao;
        User user = this.authHolder.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(inmateDao2.getInmates(visitor_id)).filter(SignUp4Presenter$subscribe$1.INSTANCE).subscribe(new SignUp4Presenter$subscribe$2(this, z), SignUp4Presenter$subscribe$3.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmateDao.getInmates(aut… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new SignUp4Presenter$getNotificationsCount$1(this), SignUp4Presenter$getNotificationsCount$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…tificationMenu(it) }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onSkipClicked() {
        try {
            Toothpick.closeScope(DI.ADD_CONNECTION_SCOPE);
        } catch (Exception unused) {
        }
        this.router.newRootScreen(Screens.VerifyMethodScreen.INSTANCE);
    }

    public final void onItemClicked(InmateByVisitor inmateByVisitor) {
        Intrinsics.checkParameterIsNotNull(inmateByVisitor, "inmate");
        this.chosenConnection = inmateByVisitor;
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.loadFacilities()).doOnSubscribe(new SignUp4Presenter$onItemClicked$1(this)).doOnSuccess(new SignUp4Presenter$onItemClicked$2(this)).doOnError(new SignUp4Presenter$onItemClicked$3(this)).subscribe(new SignUp4Presenter$onItemClicked$4(this), SignUp4Presenter$onItemClicked$5.INSTANCE);
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

    public final void onTransferClicked() {
        this.router.navigateTo(Screens.TransferFundsScreen.INSTANCE);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    /* access modifiers changed from: private */
    public final void processInmates(List<InmateByVisitor> list) {
        double d = 0.0d;
        for (InmateByVisitor credit_balance : list) {
            d += Double.parseDouble(credit_balance.getCredit_balance());
        }
        ((SignUp4View) getViewState()).showTotalBalance(d);
        ((SignUp4View) getViewState()).showConnections(list);
    }

    public final void refresh() {
        this.heartbeatRepository.doHeartbeat();
    }
}
