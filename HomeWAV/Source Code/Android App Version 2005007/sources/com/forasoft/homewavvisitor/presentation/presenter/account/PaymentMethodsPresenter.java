package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.navigation.ResultListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.PaymentMethodsView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Cards;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B5\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0014\b\u0001\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0006\u0010\u0011\u001a\u00020\u0010J\u0006\u0010\u0012\u001a\u00020\u0010J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0010H\u0014J\u0006\u0010\u0015\u001a\u00020\u0010J\u0006\u0010\u0016\u001a\u00020\u0010J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/PaymentMethodsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/PaymentMethodsView;", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "cardsSubject", "Lio/reactivex/subjects/BehaviorSubject;", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lio/reactivex/subjects/BehaviorSubject;)V", "fetchCards", "", "getNotificationsCount", "onAddCardClicked", "onBackPressed", "onFirstViewAttach", "onManageCardsClicked", "onNotificationsClicked", "onResult", "resultData", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: PaymentMethodsPresenter.kt */
public final class PaymentMethodsPresenter extends BasePresenter<PaymentMethodsView> implements ResultListener {
    /* access modifiers changed from: private */
    public final BehaviorSubject<List<Card>> cardsSubject;
    private final NotificationDao notificationDao;
    private final PaymentGateway paymentGateway;
    private final HomewavRouter router;

    @Inject
    public PaymentMethodsPresenter(HomewavRouter homewavRouter, PaymentGateway paymentGateway2, NotificationDao notificationDao2, @Cards BehaviorSubject<List<Card>> behaviorSubject) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(behaviorSubject, "cardsSubject");
        this.router = homewavRouter;
        this.paymentGateway = paymentGateway2;
        this.notificationDao = notificationDao2;
        this.cardsSubject = behaviorSubject;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        this.paymentGateway.definePaymentGatewayType();
        fetchCards();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.cardsSubject.subscribe(new PaymentMethodsPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "cardsSubject.subscribe {…wState.displayCards(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public void onResult(Object obj) {
        this.router.removeResultListener(-1);
        this.router.removeResultListener(0);
        fetchCards();
    }

    private final void fetchCards() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.getCards().subscribe(new PaymentMethodsPresenter$fetchCards$1(this), new PaymentMethodsPresenter$fetchCards$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.getCards(…?: \"\")\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new PaymentMethodsPresenter$getNotificationsCount$1(this), PaymentMethodsPresenter$getNotificationsCount$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…tificationMenu(it) }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onManageCardsClicked() {
        this.router.navigateTo(Screens.ManageCardsScreen.INSTANCE);
    }

    public final void onAddCardClicked() {
        ResultListener resultListener = this;
        this.router.setResultListener(-1, resultListener);
        this.router.setResultListener(0, resultListener);
        this.router.navigateTo(new Screens.AddCardScreen((String) null, 1, (DefaultConstructorMarker) null));
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
