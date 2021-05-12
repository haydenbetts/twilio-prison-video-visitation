package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.NotificationsView;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.rxkotlin.Observables;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\u0006\u0010\u0015\u001a\u00020\u0011J\b\u0010\u0016\u001a\u00020\u0011H\u0014J\u000e\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/NotificationsView;", "router", "Lru/terrakok/cicerone/Router;", "gson", "Lcom/google/gson/Gson;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "(Lru/terrakok/cicerone/Router;Lcom/google/gson/Gson;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;)V", "onAddFundsClicked", "", "inmateId", "", "onBackPressed", "onDismissAll", "onFirstViewAttach", "onNotificationDismissed", "notification", "Lcom/forasoft/homewavvisitor/model/data/Notification;", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: NotificationsPresenter.kt */
public final class NotificationsPresenter extends BasePresenter<NotificationsView> {
    /* access modifiers changed from: private */
    public final Gson gson;
    private final HeartbeatRepository heartbeatRepository;
    private final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final NotificationDao notificationDao;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    /* access modifiers changed from: private */
    public final Router router;

    @Inject
    public NotificationsPresenter(Router router2, Gson gson2, NotificationDao notificationDao2, InmateDao inmateDao2, HeartbeatRepository heartbeatRepository2, PaymentGateway paymentGateway2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(gson2, "gson");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        this.router = router2;
        this.gson = gson2;
        this.notificationDao = notificationDao2;
        this.inmateDao = inmateDao2;
        this.heartbeatRepository = heartbeatRepository2;
        this.paymentGateway = paymentGateway2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Observables observables = Observables.INSTANCE;
        Observable<List<Notification>> observable = this.notificationDao.getAll().toObservable();
        Intrinsics.checkExpressionValueIsNotNull(observable, "notificationDao.getAll().toObservable()");
        Observable combineLatest = Observable.combineLatest(this.heartbeatRepository.getHeartbeatState(), observable, new NotificationsPresenter$onFirstViewAttach$$inlined$combineLatest$1(this));
        if (combineLatest == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(combineLatest).subscribe(new NotificationsPresenter$onFirstViewAttach$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observables.combineLates…ons(it)\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onAddFundsClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "inmateId");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.inmateDao.getInmate(str)).subscribe(new NotificationsPresenter$onAddFundsClicked$1(this), NotificationsPresenter$onAddFundsClicked$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmateDao.getInmate(inma….e(it)\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationDismissed(Notification notification) {
        Intrinsics.checkParameterIsNotNull(notification, "notification");
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new NotificationsPresenter$onNotificationDismissed$1(this, notification), 31, (Object) null);
    }

    public final void onDismissAll() {
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new NotificationsPresenter$onDismissAll$1(this), 31, (Object) null);
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
