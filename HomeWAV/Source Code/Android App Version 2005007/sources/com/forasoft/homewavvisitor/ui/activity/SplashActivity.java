package com.forasoft.homewavvisitor.ui.activity;

import air.HomeWAV.R;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import com.forasoft.homewavvisitor.model.DeepLink;
import com.forasoft.homewavvisitor.model.DeepLinker;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.presenter.SplashPresenter;
import com.forasoft.homewavvisitor.presentation.view.SplashView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.urbanairship.messagecenter.MessageCenter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\u0010\u0010\u0018\u001a\n \u0019*\u0004\u0018\u00010\f0\fH\u0007J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0002R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/SplashActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lcom/forasoft/homewavvisitor/presentation/view/SplashView;", "()V", "<set-?>", "Lru/terrakok/cicerone/Navigator;", "navigator", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "setNavigator", "(Lru/terrakok/cicerone/Navigator;)V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/SplashPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/SplashPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/SplashPresenter;)V", "handleIntent", "", "onLoadedLibrary", "", "onNewIntent", "intent", "Landroid/content/Intent;", "providePresenter", "kotlin.jvm.PlatformType", "showUpdateVersionDialog", "startSplashOrProcessDeepLink", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SplashActivity.kt */
public final class SplashActivity extends BaseActivity implements SplashView {
    private HashMap _$_findViewCache;
    private Navigator navigator = new SupportAppNavigator(this, R.id.container);
    @InjectPresenter
    public SplashPresenter presenter;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeepLink.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DeepLink.SIGNUP.ordinal()] = 1;
            iArr[DeepLink.TUTORIALS.ordinal()] = 2;
            iArr[DeepLink.ADD_FUNDS.ordinal()] = 3;
            iArr[DeepLink.ADD_INMATES.ordinal()] = 4;
            iArr[DeepLink.NOTIFICATION_CENTER.ordinal()] = 5;
            iArr[DeepLink.MESSAGE_CENTER.ordinal()] = 6;
            iArr[DeepLink.SOCIAL.ordinal()] = 7;
            iArr[DeepLink.FAQ.ordinal()] = 8;
            iArr[DeepLink.SUPPORT.ordinal()] = 9;
            iArr[DeepLink.SCHEDULE_VISIT.ordinal()] = 10;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final SplashPresenter getPresenter() {
        SplashPresenter splashPresenter = this.presenter;
        if (splashPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return splashPresenter;
    }

    public final void setPresenter(SplashPresenter splashPresenter) {
        Intrinsics.checkParameterIsNotNull(splashPresenter, "<set-?>");
        this.presenter = splashPresenter;
    }

    @ProvidePresenter
    public final SplashPresenter providePresenter() {
        return (SplashPresenter) Toothpick.openScope(DI.SERVER_SCOPE).getInstance(SplashPresenter.class);
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }

    public void setNavigator(Navigator navigator2) {
        Intrinsics.checkParameterIsNotNull(navigator2, "<set-?>");
        this.navigator = navigator2;
    }

    public void onLoadedLibrary() {
        startSplashOrProcessDeepLink();
    }

    private final void startSplashOrProcessDeepLink() {
        if (!handleIntent()) {
            setContentView((int) R.layout.activity_splash);
            SplashPresenter splashPresenter = this.presenter;
            if (splashPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            splashPresenter.onSplash();
            Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE));
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        super.onNewIntent(intent);
        SplashPresenter splashPresenter = this.presenter;
        if (splashPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        splashPresenter.cancel();
        setIntent(intent);
        startSplashOrProcessDeepLink();
    }

    private final boolean handleIntent() {
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Uri data = intent.getData();
        if (data == null) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(data, "intent.data ?: return false");
        DeepLinker deepLinker = new DeepLinker();
        DeepLink linkFromBundle = deepLinker.getLinkFromBundle(deepLinker.buildBundle(data));
        if (linkFromBundle == null) {
            return false;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[linkFromBundle.ordinal()]) {
            case 1:
                SplashPresenter splashPresenter = this.presenter;
                if (splashPresenter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                splashPresenter.checkAuthAndDispatch(Screens.SignUpScreen.INSTANCE, false);
                break;
            case 2:
                SplashPresenter splashPresenter2 = this.presenter;
                if (splashPresenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter2, new Screens.MainScreen(Screens.TutorialsScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            case 3:
                SplashPresenter splashPresenter3 = this.presenter;
                if (splashPresenter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter3, new Screens.MainScreen(Screens.ChooseFundsScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            case 4:
                SplashPresenter splashPresenter4 = this.presenter;
                if (splashPresenter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter4, new Screens.MainScreen(Screens.AddConnectionScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            case 5:
                SplashPresenter splashPresenter5 = this.presenter;
                if (splashPresenter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter5, new Screens.MainScreen(Screens.NotificationsScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            case 6:
                MessageCenter.shared().showMessageCenter();
                finish();
                break;
            case 7:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.homewav_facebook))));
                finish();
                break;
            case 8:
                SplashPresenter splashPresenter6 = this.presenter;
                if (splashPresenter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter6, new Screens.MainScreen(Screens.HelpAndContactScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            case 9:
                SplashPresenter splashPresenter7 = this.presenter;
                if (splashPresenter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter7, new Screens.MainScreen(Screens.HelpAndContactScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            case 10:
                SplashPresenter splashPresenter8 = this.presenter;
                if (splashPresenter8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                SplashPresenter.checkAuthAndDispatch$default(splashPresenter8, new Screens.MainScreen(Screens.InmateChooseVisitsScreen.INSTANCE.getScreenKey()), false, 2, (Object) null);
                break;
            default:
                return false;
        }
        return true;
    }

    public void showUpdateVersionDialog() {
        new AlertDialog.Builder(this).setTitle((CharSequence) "New version available").setMessage((CharSequence) "There is a new version available. Please, download it from the store to be able to proceed").setPositiveButton((CharSequence) "Update", (DialogInterface.OnClickListener) new SplashActivity$showUpdateVersionDialog$1(this)).setCancelable(false).show();
    }
}
