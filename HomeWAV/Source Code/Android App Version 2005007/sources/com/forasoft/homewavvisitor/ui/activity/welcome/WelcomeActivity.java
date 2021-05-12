package com.forasoft.homewavvisitor.ui.activity.welcome;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0006\u0010\u0012\u001a\u00020\u000fJ\b\u0010\u0013\u001a\u00020\tH\u0007R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeView;", "()V", "navigator", "Lru/terrakok/cicerone/Navigator;", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "presenter", "Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomePresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomePresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomePresenter;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onWelcomeFinish", "providePresenter", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WelcomeActivity.kt */
public final class WelcomeActivity extends BaseActivity implements WelcomeView {
    private HashMap _$_findViewCache;
    private final Navigator navigator = new SupportAppNavigator(this, R.id.container);
    @InjectPresenter
    public WelcomePresenter presenter;

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

    public final WelcomePresenter getPresenter() {
        WelcomePresenter welcomePresenter = this.presenter;
        if (welcomePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return welcomePresenter;
    }

    public final void setPresenter(WelcomePresenter welcomePresenter) {
        Intrinsics.checkParameterIsNotNull(welcomePresenter, "<set-?>");
        this.presenter = welcomePresenter;
    }

    @ProvidePresenter
    public final WelcomePresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(WelcomePresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …omePresenter::class.java)");
        return (WelcomePresenter) instance;
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_welcome);
        Toothpick.inject(this, Toothpick.openScope(DI.APP_SCOPE));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.pager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "pager");
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        viewPager.setAdapter(new WelcomeSlidePagerAdapter(supportFragmentManager));
    }

    public final void onWelcomeFinish() {
        WelcomePresenter welcomePresenter = this.presenter;
        if (welcomePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        welcomePresenter.finish();
    }
}
