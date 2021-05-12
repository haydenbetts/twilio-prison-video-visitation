package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.ResultListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.MainView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.account.TermConditionsFragment;
import com.google.firebase.iid.FirebaseInstanceId;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Screen;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000_\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0012\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B9\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0006\u0010\u0017\u001a\u00020\u0015J\u0006\u0010\u0018\u001a\u00020\u0015J\u000e\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u0015H\u0014J\u0006\u0010\u001d\u001a\u00020\u0015J\u0012\u0010\u001e\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0006\u0010!\u001a\u00020\u0015J\u0006\u0010\"\u001a\u00020\u0015J\u000e\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020\u0015H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/MainPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/MainView;", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "authInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "homewavApi", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "pusherListener", "com/forasoft/homewavvisitor/presentation/presenter/MainPresenter$pusherListener$1", "Lcom/forasoft/homewavvisitor/presentation/presenter/MainPresenter$pusherListener$1;", "acceptTerms", "", "checkTerms", "connectToPusher", "disconnectFromPusher", "onDeepLink", "screenKey", "", "onFirstViewAttach", "onLogout", "onResult", "resultData", "", "onScheduleVisit", "onTermsClicked", "toScreen", "screen", "Lru/terrakok/cicerone/Screen;", "updateFirebaseToken", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: MainPresenter.kt */
public final class MainPresenter extends BasePresenter<MainView> implements ResultListener {
    /* access modifiers changed from: private */
    public final AuthHolder authHolder;
    private final AuthInteractor authInteractor;
    private final HeartbeatRepository heartbeatRepository;
    /* access modifiers changed from: private */
    public final HomewavApi homewavApi;
    private final PusherHolder pusherHolder;
    private final MainPresenter$pusherListener$1 pusherListener = new MainPresenter$pusherListener$1(this);
    /* access modifiers changed from: private */
    public final HomewavRouter router;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PusherEvent.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PusherEvent.Type.visitor_login.ordinal()] = 1;
            iArr[PusherEvent.Type.new_call.ordinal()] = 2;
        }
    }

    @Inject
    public MainPresenter(AuthHolder authHolder2, AuthInteractor authInteractor2, HeartbeatRepository heartbeatRepository2, @Global HomewavRouter homewavRouter, PusherHolder pusherHolder2, HomewavApi homewavApi2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(authInteractor2, "authInteractor");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(pusherHolder2, "pusherHolder");
        Intrinsics.checkParameterIsNotNull(homewavApi2, "homewavApi");
        this.authHolder = authHolder2;
        this.authInteractor = authInteractor2;
        this.heartbeatRepository = heartbeatRepository2;
        this.router = homewavRouter;
        this.pusherHolder = pusherHolder2;
        this.homewavApi = homewavApi2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.authInteractor.subscribeForUserUpdates().subscribe();
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "authInteractor.subscribe…             .subscribe()");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = CommonKt.applyAsync(this.heartbeatRepository.getHeartbeatState()).subscribe(new MainPresenter$onFirstViewAttach$1(this), MainPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "heartbeatRepository.hear…))\n                }, {})");
        DisposableKt.plusAssign(disposables2, subscribe2);
        CompositeDisposable disposables3 = getDisposables();
        Disposable subscribe3 = this.heartbeatRepository.getHeartbeatState().subscribe(new MainPresenter$onFirstViewAttach$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe3, "heartbeatRepository.hear…      }\n                }");
        DisposableKt.plusAssign(disposables3, subscribe3);
        User user = this.authHolder.getUser();
        if (!(user == null || user.getVisitor_id() == null)) {
            this.pusherHolder.listenEvents(this.pusherListener);
        }
        updateFirebaseToken();
        checkTerms();
    }

    private final void updateFirebaseToken() {
        FirebaseInstanceId instance = FirebaseInstanceId.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "FirebaseInstanceId.getInstance()");
        instance.getInstanceId().addOnSuccessListener(new MainPresenter$updateFirebaseToken$1(this));
    }

    private final void checkTerms() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.homewavApi.getTerms()).subscribe(new MainPresenter$checkTerms$1(this), MainPresenter$checkTerms$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "homewavApi.getTerms()\n  … }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void acceptTerms() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.homewavApi.setTermsAgreed()).subscribe(MainPresenter$acceptTerms$1.INSTANCE, MainPresenter$acceptTerms$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "homewavApi.setTermsAgree…age}\")\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void connectToPusher() {
        String visitor_id;
        User user = this.authHolder.getUser();
        if (user != null && (visitor_id = user.getVisitor_id()) != null) {
            PusherHolder.subscribe$default(this.pusherHolder, visitor_id, false, 2, (Object) null);
        }
    }

    public final void disconnectFromPusher() {
        PusherHolder.release$default(this.pusherHolder, false, 1, (Object) null);
    }

    public final void onTermsClicked() {
        this.router.setResultListener(TermConditionsFragment.RESULT_SHOW_TERMS_DIALOG, this);
        this.router.navigateTo(new Screens.TermsAndConditionsScreen(true));
    }

    public void onResult(Object obj) {
        this.router.removeResultListener(Integer.valueOf(TermConditionsFragment.RESULT_SHOW_TERMS_DIALOG));
        ((MainView) getViewState()).showTermsConditionsDialog();
    }

    public final void onScheduleVisit() {
        this.router.replaceScreen(new Screens.VisitsTabScreen(true));
    }

    public final void onDeepLink(String str) {
        Intrinsics.checkParameterIsNotNull(str, "screenKey");
        this.router.replaceScreen(new Screens.HomeTabScreen(str));
    }

    public final void toScreen(Screen screen) {
        Intrinsics.checkParameterIsNotNull(screen, MainActivity.SCREEN_KEY);
        this.router.replaceScreen(screen);
    }

    public final void onLogout() {
        this.authInteractor.logout(new MainPresenter$onLogout$1(this));
    }
}
