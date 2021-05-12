package com.forasoft.homewavvisitor.ui.activity.register;

import air.HomeWAV.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.register.CreateAccountPresenter;
import com.forasoft.homewavvisitor.presentation.view.register.CreateAccountView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.BaseActivity;
import com.forasoft.homewavvisitor.ui.views.StepperView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u000fH\u0014J\b\u0010\u0014\u001a\u00020\tH\u0007J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001d"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/register/CreateAccountActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lcom/forasoft/homewavvisitor/presentation/view/register/CreateAccountView;", "()V", "navigator", "Lru/terrakok/cicerone/Navigator;", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/register/CreateAccountPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/register/CreateAccountPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/register/CreateAccountPresenter;)V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "providePresenter", "setStepperStep", "step", "", "showMessage", "resId", "message", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CreateAccountActivity.kt */
public final class CreateAccountActivity extends BaseActivity implements CreateAccountView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "CreateAccountActivity";
    private HashMap _$_findViewCache;
    private final Navigator navigator = new SupportAppNavigator(this, R.id.container);
    @InjectPresenter
    public CreateAccountPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/register/CreateAccountActivity$Companion;", "", "()V", "TAG", "", "getIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CreateAccountActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent getIntent(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new Intent(context, CreateAccountActivity.class);
        }
    }

    public void showProgress(boolean z) {
        CreateAccountView.DefaultImpls.showProgress(this, z);
    }

    public void updateNotificationMenu(int i) {
        CreateAccountView.DefaultImpls.updateNotificationMenu(this, i);
    }

    public final CreateAccountPresenter getPresenter() {
        CreateAccountPresenter createAccountPresenter = this.presenter;
        if (createAccountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return createAccountPresenter;
    }

    public final void setPresenter(CreateAccountPresenter createAccountPresenter) {
        Intrinsics.checkParameterIsNotNull(createAccountPresenter, "<set-?>");
        this.presenter = createAccountPresenter;
    }

    @ProvidePresenter
    public final CreateAccountPresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(CreateAccountPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …untPresenter::class.java)");
        return (CreateAccountPresenter) instance;
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Toothpick.openScopes(DI.SERVER_SCOPE, DI.ADD_CONNECTION_SCOPE).installModules(new CreateAccountActivity$onCreate$1(this));
        Toothpick.inject(this, Toothpick.openScope(DI.SERVER_SCOPE));
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_create_account);
        setSupportActionBar((Toolbar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) getString(R.string.title_create_account));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ((StepperView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper)).setOnStepperClickListener(new CreateAccountActivity$onResume$1(this));
    }

    public void setStepperStep(int i) {
        ((StepperView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper)).setStep(i);
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(getContainerId());
        if (findFragmentById == null || !(findFragmentById instanceof OnBackButtonPressedListener) || !((OnBackButtonPressedListener) findFragmentById).onBackPressed()) {
            CreateAccountPresenter createAccountPresenter = this.presenter;
            if (createAccountPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            createAccountPresenter.onBackPressed();
        }
    }

    public void showMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        ContextKt.showSnackbar((Activity) this, str);
    }

    public void showMessage(int i) {
        String string = getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(resId)");
        showMessage(string);
    }
}
