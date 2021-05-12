package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.server.apis.SignUpApi;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.AccountView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.rxkotlin.Flowables;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016*\u0001\u0016\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BQ\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020\u001dH\u0002J\u0006\u0010!\u001a\u00020\u001dJ\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00190#2\u0006\u0010$\u001a\u00020%H\u0002J\u0006\u0010&\u001a\u00020\u001dJ\u0006\u0010'\u001a\u00020\u0019J\u0006\u0010(\u001a\u00020\u001dJ\u000e\u0010)\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0019J\b\u0010*\u001a\u00020\u001dH\u0014J\u0006\u0010+\u001a\u00020\u001dJ\u0006\u0010,\u001a\u00020\u001dJ\u0006\u0010-\u001a\u00020\u001dJ\u0006\u0010.\u001a\u00020\u001dJ\u0006\u0010/\u001a\u00020\u001dJ\u0006\u00100\u001a\u00020\u001dJ\u0006\u00101\u001a\u00020\u001dJ\u0006\u00102\u001a\u00020\u001dJ\u0006\u00103\u001a\u00020\u001dJ\u0006\u00104\u001a\u00020\u001dJ\u0006\u00105\u001a\u00020\u001dJ\u0006\u00106\u001a\u00020\u001dJ\u0006\u00107\u001a\u00020\u001dJ\u0006\u00108\u001a\u00020\u001dJ\u0006\u00109\u001a\u00020\u001dJ\u0006\u0010:\u001a\u00020\u001dR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/AccountPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/AccountView;", "globalRouter", "Lru/terrakok/cicerone/Router;", "router", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "authInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "accountInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "signUpApi", "Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;", "notifier", "Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "(Lru/terrakok/cicerone/Router;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/server/apis/SignUpApi;Lcom/forasoft/homewavvisitor/navigation/BusNotifier;)V", "busListener", "com/forasoft/homewavvisitor/presentation/presenter/account/AccountPresenter$busListener$1", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/AccountPresenter$busListener$1;", "isLogoutClicked", "", "photoLockDisposable", "Lio/reactivex/disposables/Disposable;", "beforeDestroy", "", "changeEmailSubscriptionStatus", "isEnabled", "finish", "getNotificationsCount", "isEditPhotoEnabled", "Lio/reactivex/Flowable;", "visitorId", "", "onAvatarClicked", "onBackPressed", "onEditClicked", "onEmailSubscriptionChanged", "onFirstViewAttach", "onHelpClicked", "onHistoryClicked", "onLogout", "onManageFundsClicked", "onNotificationSettingsClicked", "onNotificationsClicked", "onPaymentMethodsClicked", "onRefundClicked", "onReportBugClicked", "onResume", "onSubscriptionConfirmed", "onTabSelected", "onTermsClicked", "onTestVideoClicked", "onUnsubscribeConfirmed", "onUnsubscribeRejected", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: AccountPresenter.kt */
public final class AccountPresenter extends BasePresenter<AccountView> {
    private final AccountInteractor accountInteractor;
    private final AuthHolder authHolder;
    private final AuthInteractor authInteractor;
    private final AccountPresenter$busListener$1 busListener = new AccountPresenter$busListener$1(this);
    /* access modifiers changed from: private */
    public final Router globalRouter;
    private final InmateDao inmateDao;
    private boolean isLogoutClicked;
    private final NotificationDao notificationDao;
    private final BusNotifier notifier;
    private Disposable photoLockDisposable;
    private final Router router;
    private final SignUpApi signUpApi;

    @Inject
    public AccountPresenter(@Global Router router2, Router router3, AuthHolder authHolder2, AuthInteractor authInteractor2, AccountInteractor accountInteractor2, NotificationDao notificationDao2, InmateDao inmateDao2, SignUpApi signUpApi2, BusNotifier busNotifier) {
        Intrinsics.checkParameterIsNotNull(router2, "globalRouter");
        Intrinsics.checkParameterIsNotNull(router3, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(authInteractor2, "authInteractor");
        Intrinsics.checkParameterIsNotNull(accountInteractor2, "accountInteractor");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(signUpApi2, "signUpApi");
        Intrinsics.checkParameterIsNotNull(busNotifier, "notifier");
        this.globalRouter = router2;
        this.router = router3;
        this.authHolder = authHolder2;
        this.authInteractor = authInteractor2;
        this.accountInteractor = accountInteractor2;
        this.notificationDao = notificationDao2;
        this.inmateDao = inmateDao2;
        this.signUpApi = signUpApi2;
        this.notifier = busNotifier;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        ((AccountView) getViewState()).setName(user.getFullName());
        ((AccountView) getViewState()).setPhone(user.getPhone());
        ((AccountView) getViewState()).setEmail(user.getEmail());
        String pin = user.getPin();
        if (pin != null) {
            ((AccountView) getViewState()).setPin(pin);
        }
        String photoProfileUrl = user.getPhotoProfileUrl();
        if (photoProfileUrl != null) {
            ((AccountView) getViewState()).setAvatarUrl(photoProfileUrl);
        }
        ((AccountView) getViewState()).setEmailSubscription(user.isNotificationSubscriptionEnabled());
        this.notifier.add(this.busListener);
        ((AccountView) getViewState()).setLocation(user.getStreet() + ", " + user.getCity() + ' ' + user.getState() + ' ' + user.getZip() + ' ');
    }

    public void beforeDestroy() {
        this.notifier.remove(this.busListener);
        Disposable disposable = this.photoLockDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new AccountPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…ateNotificationMenu(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final boolean onBackPressed() {
        finish();
        return true;
    }

    private final void finish() {
        this.router.exit();
        getDisposables().dispose();
    }

    public final void onLogout() {
        if (!this.isLogoutClicked) {
            this.isLogoutClicked = true;
            this.authInteractor.logout(new AccountPresenter$onLogout$1(this));
        }
    }

    public final void onAvatarClicked() {
        this.router.navigateTo(Screens.EditPhotosScreen.INSTANCE);
    }

    public final void onResume() {
        User user = this.authHolder.getUser();
        if (user != null) {
            ((AccountView) getViewState()).setName(user.getFirstName() + ' ' + user.getLastName());
            String phone = user.getPhone();
            AccountView accountView = (AccountView) getViewState();
            StringBuilder sb = new StringBuilder();
            sb.append('+');
            sb.append(StringsKt.substring(phone, new IntRange(0, 0)));
            sb.append(" (");
            sb.append(StringsKt.substring(phone, new IntRange(1, 3)));
            sb.append(") ");
            sb.append(StringsKt.substring(phone, new IntRange(4, 6)));
            sb.append('-');
            if (phone != null) {
                String substring = phone.substring(7);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                sb.append(substring);
                accountView.setPhone(sb.toString());
                ((AccountView) getViewState()).setEmail(user.getEmail());
                String photoProfileUrl = user.getPhotoProfileUrl();
                if (photoProfileUrl != null) {
                    ((AccountView) getViewState()).setAvatarUrl(photoProfileUrl);
                } else {
                    ((AccountView) getViewState()).createAvatarFromName(user.getFullName());
                }
                ((AccountView) getViewState()).setLocation(user.getStreet() + ", " + user.getCity() + ' ' + user.getState() + ' ' + user.getZip() + ' ');
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    public final void onEditClicked() {
        this.router.navigateTo(Screens.EditAccountScreen.INSTANCE);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final void onHistoryClicked() {
        this.router.navigateTo(Screens.AccountHistoryScreen.INSTANCE);
    }

    public final void onTermsClicked() {
        this.router.navigateTo(new Screens.TermsAndConditionsScreen(false, 1, (DefaultConstructorMarker) null));
    }

    public final void onHelpClicked() {
        this.router.navigateTo(Screens.HelpAndContactScreen.INSTANCE);
    }

    public final void onReportBugClicked() {
        this.router.navigateTo(Screens.ReportBugScreen.INSTANCE);
    }

    public final void onManageFundsClicked() {
        this.router.navigateTo(new Screens.SignUp4Screen(true));
    }

    public final void onNotificationSettingsClicked() {
        this.router.navigateTo(Screens.NotificationSettingsScreen.INSTANCE);
    }

    public final void onRefundClicked() {
        this.router.navigateTo(Screens.RefundsScreen.INSTANCE);
    }

    public final void onPaymentMethodsClicked() {
        this.router.navigateTo(Screens.PaymentMethodsScreen.INSTANCE);
    }

    public final void onTestVideoClicked() {
        this.router.navigateTo(Screens.TestVideoScreen.INSTANCE);
    }

    public final void onTabSelected() {
        String visitor_id;
        User user = this.authHolder.getUser();
        if (user != null && (visitor_id = user.getVisitor_id()) != null) {
            Disposable disposable = this.photoLockDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            this.photoLockDisposable = CommonKt.applyAsync(isEditPhotoEnabled(visitor_id)).subscribe(new AccountPresenter$sam$io_reactivex_functions_Consumer$0(new AccountPresenter$onTabSelected$1((AccountView) getViewState())), AccountPresenter$onTabSelected$2.INSTANCE);
        }
    }

    private final Flowable<Boolean> isEditPhotoEnabled(String str) {
        Flowables flowables = Flowables.INSTANCE;
        Flowable<R> flowable = this.signUpApi.loadFacilities().map(AccountPresenter$isEditPhotoEnabled$1.INSTANCE).toFlowable();
        Intrinsics.checkExpressionValueIsNotNull(flowable, "signUpApi.loadFacilities…            .toFlowable()");
        Flowable<R> map = flowables.combineLatest(flowable, this.inmateDao.getInmates(str)).map(AccountPresenter$isEditPhotoEnabled$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "Flowables.combineLatest(…al == \"1\" }\n            }");
        return map;
    }

    public final void onEmailSubscriptionChanged(boolean z) {
        if (z) {
            ((AccountView) getViewState()).showSubscribeDialog();
        } else {
            ((AccountView) getViewState()).showUnsubscribeDialog();
        }
    }

    public final void onSubscriptionConfirmed() {
        changeEmailSubscriptionStatus(true);
    }

    public final void onUnsubscribeConfirmed() {
        changeEmailSubscriptionStatus(false);
    }

    public final void onUnsubscribeRejected() {
        ((AccountView) getViewState()).setEmailSubscription(true);
    }

    private final void changeEmailSubscriptionStatus(boolean z) {
        User user = this.authHolder.getUser();
        if (user != null && user.isNotificationSubscriptionEnabled() != z) {
            User user2 = this.authHolder.getUser();
            if (user2 != null) {
                user2.setNotificationSubscriptionEnabled(z);
            }
            Single editAccount$default = AccountInteractor.editAccount$default(this.accountInteractor, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, Boolean.valueOf(z), 511, (Object) null);
            Intrinsics.checkExpressionValueIsNotNull(editAccount$default, "accountInteractor.editAc…ptionEnabled = isEnabled)");
            CommonKt.applyAsync(editAccount$default).subscribe();
        }
    }
}
