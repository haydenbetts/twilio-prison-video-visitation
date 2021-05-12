package com.forasoft.homewavvisitor.ui.activity.auth;

import air.HomeWAV.R;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.forasoft.homewavvisitor.presentation.presenter.auth.LoginPresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.LoginView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.BaseActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\b\u0010\u0018\u001a\u00020\u0007H\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/auth/LoginActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/LoginView;", "()V", "logoutDialog", "Landroid/app/AlertDialog;", "mLoginPresenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/auth/LoginPresenter;", "getMLoginPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/auth/LoginPresenter;", "setMLoginPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/auth/LoginPresenter;)V", "<set-?>", "Lru/terrakok/cicerone/Navigator;", "navigator", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "setNavigator", "(Lru/terrakok/cicerone/Navigator;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "providePresenter", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LoginActivity.kt */
public final class LoginActivity extends BaseActivity implements LoginView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String SHOW_LOGOUT_DIALOG = "show logout dialog";
    private HashMap _$_findViewCache;
    private AlertDialog logoutDialog;
    @InjectPresenter
    public LoginPresenter mLoginPresenter;
    private Navigator navigator = new SupportAppNavigator(this, R.id.container);

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/auth/LoginActivity$Companion;", "", "()V", "SHOW_LOGOUT_DIALOG", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: LoginActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final LoginPresenter getMLoginPresenter() {
        LoginPresenter loginPresenter = this.mLoginPresenter;
        if (loginPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mLoginPresenter");
        }
        return loginPresenter;
    }

    public final void setMLoginPresenter(LoginPresenter loginPresenter) {
        Intrinsics.checkParameterIsNotNull(loginPresenter, "<set-?>");
        this.mLoginPresenter = loginPresenter;
    }

    @ProvidePresenter
    public final LoginPresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(LoginPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…ginPresenter::class.java)");
        return (LoginPresenter) instance;
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }

    public void setNavigator(Navigator navigator2) {
        Intrinsics.checkParameterIsNotNull(navigator2, "<set-?>");
        this.navigator = navigator2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE));
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_login);
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null ? extras.getBoolean(SHOW_LOGOUT_DIALOG) : false) {
            this.logoutDialog = new AlertDialog.Builder(this).setTitle(R.string.error).setMessage(R.string.logout_message).setPositiveButton(R.string.label_ok, LoginActivity$onCreate$1.INSTANCE).setCancelable(false).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AlertDialog alertDialog = this.logoutDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }
}
