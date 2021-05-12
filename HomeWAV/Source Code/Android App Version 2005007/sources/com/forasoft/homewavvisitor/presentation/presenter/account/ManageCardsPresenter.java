package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.ManageCardsView;
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
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B-\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\b\u0001\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u000e\u001a\u00020\rJ\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\nJ\b\u0010\u0011\u001a\u00020\rH\u0014J\u0016\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/ManageCardsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/ManageCardsView;", "router", "Lru/terrakok/cicerone/Router;", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "cardsSubject", "Lio/reactivex/subjects/BehaviorSubject;", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;Lio/reactivex/subjects/BehaviorSubject;)V", "fetchCards", "", "onBackPressed", "onDeleteCardClicked", "card", "onFirstViewAttach", "onUpdateCardStatus", "isDefault", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: ManageCardsPresenter.kt */
public final class ManageCardsPresenter extends BasePresenter<ManageCardsView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DELETE_RESULT = "delete_result";
    private static final String UPDATE_RESULT = "update_result";
    /* access modifiers changed from: private */
    public final BehaviorSubject<List<Card>> cardsSubject;
    /* access modifiers changed from: private */
    public final PaymentGateway paymentGateway;
    private final Router router;

    @Inject
    public ManageCardsPresenter(Router router2, PaymentGateway paymentGateway2, @Cards BehaviorSubject<List<Card>> behaviorSubject) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        Intrinsics.checkParameterIsNotNull(behaviorSubject, "cardsSubject");
        this.router = router2;
        this.paymentGateway = paymentGateway2;
        this.cardsSubject = behaviorSubject;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        this.paymentGateway.definePaymentGatewayType();
        fetchCards();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.cardsSubject.subscribe(new ManageCardsPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "cardsSubject.subscribe {…wState.displayCards(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    private final void fetchCards() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.getCards().subscribe(new ManageCardsPresenter$fetchCards$1(this), ManageCardsPresenter$fetchCards$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.getCards(…)\n                }, { })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onDeleteCardClicked(Card card) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        ((ManageCardsView) getViewState()).showProgress();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.paymentGateway.deleteCard(card.getToken())).doOnEvent(new ManageCardsPresenter$onDeleteCardClicked$1(this)).subscribe(new ManageCardsPresenter$onDeleteCardClicked$2(this, card), ManageCardsPresenter$onDeleteCardClicked$3.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.deleteCar… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onUpdateCardStatus(Card card, boolean z) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        ((ManageCardsView) getViewState()).showProgress();
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.paymentGateway.updateCardStatus(card.getToken(), z).flatMap(new ManageCardsPresenter$onUpdateCardStatus$1(this)).doOnEvent(new ManageCardsPresenter$onUpdateCardStatus$2(this)).subscribe(new ManageCardsPresenter$onUpdateCardStatus$3(this), ManageCardsPresenter$onUpdateCardStatus$4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "paymentGateway.updateCar….onNext(it.body!!) }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onBackPressed() {
        this.router.exit();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/ManageCardsPresenter$Companion;", "", "()V", "DELETE_RESULT", "", "UPDATE_RESULT", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ManageCardsPresenter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
