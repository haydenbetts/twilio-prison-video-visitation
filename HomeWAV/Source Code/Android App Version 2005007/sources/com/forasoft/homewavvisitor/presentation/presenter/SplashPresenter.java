package com.forasoft.homewavvisitor.presentation.presenter;

import android.content.Context;
import com.forasoft.homewavvisitor.BuildConfig;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.VersionError;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.interactor.auth.AuthInteractor;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.view.SplashView;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.google.gson.stream.MalformedJsonException;
import com.urbanairship.channel.ChannelRegistrationPayload;
import fm.liveswitch.IAction1;
import fm.liveswitch.openh264.Utility;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.net.SocketTimeoutException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.Screen;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0014J\u0012\u0010\u001a\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u00112\b\u0010\u001b\u001a\u0004\u0018\u00010\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u0011R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/SplashPresenter;", "Lmoxy/MvpPresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/SplashView;", "router", "Lru/terrakok/cicerone/Router;", "context", "Landroid/content/Context;", "authInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;", "homewavApi", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "(Lru/terrakok/cicerone/Router;Landroid/content/Context;Lcom/forasoft/homewavvisitor/model/interactor/auth/AuthInteractor;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/model/Analytics;)V", "subscription", "Lio/reactivex/disposables/Disposable;", "cancel", "", "checkAuthAndDispatch", "screen", "Lru/terrakok/cicerone/Screen;", "isNeedAuth", "", "loadH264", "onDestroy", "onFirstViewAttach", "onLoggedIn", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onNotLoggedIn", "", "onSplash", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: SplashPresenter.kt */
public final class SplashPresenter extends MvpPresenter<SplashView> {
    private final Analytics analytics;
    /* access modifiers changed from: private */
    public final AuthInteractor authInteractor;
    /* access modifiers changed from: private */
    public final Context context;
    private final HomewavApi homewavApi;
    /* access modifiers changed from: private */
    public final Router router;
    private Disposable subscription;

    @Inject
    public SplashPresenter(@Global Router router2, Context context2, AuthInteractor authInteractor2, HomewavApi homewavApi2, Analytics analytics2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(authInteractor2, "authInteractor");
        Intrinsics.checkParameterIsNotNull(homewavApi2, "homewavApi");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        this.router = router2;
        this.context = context2;
        this.authInteractor = authInteractor2;
        this.homewavApi = homewavApi2;
        this.analytics = analytics2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        ((SplashView) getViewState()).onLoadedLibrary();
    }

    private final void loadH264() {
        if (Utility.isSupported()) {
            File filesDir = this.context.getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
            Utility.downloadOpenH264(filesDir.getPath()).then(new SplashPresenter$loadH264$1(this)).fail((IAction1<Exception>) new SplashPresenter$loadH264$2(this));
            return;
        }
        ((SplashView) getViewState()).onLoadedLibrary();
    }

    public final void onSplash() {
        this.analytics.onOpen();
        Single<R> flatMap = this.homewavApi.checkVersion(BuildConfig.VERSION_NAME, ChannelRegistrationPayload.ANDROID_DEVICE_TYPE).flatMap(new SplashPresenter$onSplash$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "homewavApi.checkVersion(…      }\n                }");
        SplashPresenter splashPresenter = this;
        this.subscription = CommonKt.applyAsync(flatMap).subscribe(new SplashPresenter$sam$io_reactivex_functions_Consumer$0(new SplashPresenter$onSplash$2(splashPresenter)), new SplashPresenter$sam$io_reactivex_functions_Consumer$0(new SplashPresenter$onSplash$3(splashPresenter)));
    }

    /* access modifiers changed from: private */
    public final void onNotLoggedIn(Throwable th) {
        if (th instanceof VersionError) {
            ((SplashView) getViewState()).showUpdateVersionDialog();
        } else if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((SplashView) getViewState()).showServerError();
            onSplash();
        } else {
            this.router.newRootScreen(new Screens.LoginScreen(false, 1, (DefaultConstructorMarker) null));
            Disposable disposable = this.subscription;
            if (disposable != null) {
                disposable.dispose();
            }
        }
    }

    /* access modifiers changed from: private */
    public final void onLoggedIn(User user) {
        this.authInteractor.subscribeForUserUpdates().subscribe();
        Timber.d("getUserFlowable: %s", user);
        if (user == null || !user.getVerified()) {
            this.router.newRootScreen(new Screens.LoginScreen(false, 1, (DefaultConstructorMarker) null));
        } else {
            this.router.newRootScreen(new Screens.MainScreen((String) null, 1, (DefaultConstructorMarker) null));
        }
        Disposable disposable = this.subscription;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public static /* synthetic */ void checkAuthAndDispatch$default(SplashPresenter splashPresenter, Screen screen, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        splashPresenter.checkAuthAndDispatch(screen, z);
    }

    public final void checkAuthAndDispatch(Screen screen, boolean z) {
        Intrinsics.checkParameterIsNotNull(screen, MainActivity.SCREEN_KEY);
        this.analytics.onOpen();
        Single<R> flatMap = this.homewavApi.checkVersion(BuildConfig.VERSION_NAME, ChannelRegistrationPayload.ANDROID_DEVICE_TYPE).flatMap(new SplashPresenter$checkAuthAndDispatch$subscription$1(this));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "homewavApi.checkVersion(…      }\n                }");
        CommonKt.applyAsync(flatMap).subscribe(new SplashPresenter$checkAuthAndDispatch$subscription$2(this, z, screen), new SplashPresenter$checkAuthAndDispatch$subscription$3(this, z, screen));
    }

    public void onDestroy() {
        super.onDestroy();
        cancel();
    }

    public final void cancel() {
        Disposable disposable;
        Disposable disposable2 = this.subscription;
        if (disposable2 != null) {
            Boolean valueOf = disposable2 != null ? Boolean.valueOf(disposable2.isDisposed()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (!valueOf.booleanValue() && (disposable = this.subscription) != null) {
                disposable.dispose();
            }
        }
    }
}
