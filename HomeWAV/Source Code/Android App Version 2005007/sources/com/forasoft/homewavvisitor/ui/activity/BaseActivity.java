package com.forasoft.homewavvisitor.ui.activity;

import air.HomeWAV.R;
import android.app.Dialog;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import com.forasoft.homewavvisitor.ui.fragment.ServerErrorFragment;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.CustomEvent;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.MvpAppCompatActivity;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0014J\b\u0010\u001a\u001a\u00020\u0017H\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\rX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lmoxy/MvpAppCompatActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "()V", "containerId", "", "getContainerId", "()I", "setContainerId", "(I)V", "currentDialogFragment", "Lcom/forasoft/homewavvisitor/ui/fragment/ServerErrorFragment;", "navigator", "Lru/terrakok/cicerone/Navigator;", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "navigatorHolder", "Lru/terrakok/cicerone/NavigatorHolder;", "getNavigatorHolder", "()Lru/terrakok/cicerone/NavigatorHolder;", "setNavigatorHolder", "(Lru/terrakok/cicerone/NavigatorHolder;)V", "hideServerError", "", "onBackPressed", "onPause", "onResumeFragments", "showServerError", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BaseActivity.kt */
public abstract class BaseActivity extends MvpAppCompatActivity implements ServerErrorDisplay {
    private HashMap _$_findViewCache;
    private int containerId = R.id.container;
    private ServerErrorFragment currentDialogFragment;
    @Inject
    @Global
    public NavigatorHolder navigatorHolder;

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

    /* access modifiers changed from: protected */
    public abstract Navigator getNavigator();

    public final NavigatorHolder getNavigatorHolder() {
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        if (navigatorHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigatorHolder");
        }
        return navigatorHolder2;
    }

    public final void setNavigatorHolder(NavigatorHolder navigatorHolder2) {
        Intrinsics.checkParameterIsNotNull(navigatorHolder2, "<set-?>");
        this.navigatorHolder = navigatorHolder2;
    }

    /* access modifiers changed from: protected */
    public final int getContainerId() {
        return this.containerId;
    }

    /* access modifiers changed from: protected */
    public final void setContainerId(int i) {
        this.containerId = i;
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        if (navigatorHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigatorHolder");
        }
        navigatorHolder2.setNavigator(getNavigator());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        if (navigatorHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigatorHolder");
        }
        navigatorHolder2.removeNavigator();
        CustomEvent build = new CustomEvent.Builder("visitor_close_app").build();
        Intrinsics.checkExpressionValueIsNotNull(build, "CustomEvent.Builder(\"visitor_close_app\").build()");
        UAirship shared = UAirship.shared();
        Intrinsics.checkExpressionValueIsNotNull(shared, "UAirship.shared()");
        shared.getAnalytics().addEvent(build);
        hideServerError();
        super.onPause();
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(this.containerId);
        if (findFragmentById == null || !(findFragmentById instanceof OnBackButtonPressedListener) || !((OnBackButtonPressedListener) findFragmentById).onBackPressed()) {
            super.onBackPressed();
        }
    }

    public final void hideServerError() {
        Dialog dialog;
        ServerErrorFragment serverErrorFragment;
        ServerErrorFragment serverErrorFragment2 = this.currentDialogFragment;
        if (serverErrorFragment2 != null && serverErrorFragment2 != null && (dialog = serverErrorFragment2.getDialog()) != null && dialog.isShowing() && (serverErrorFragment = this.currentDialogFragment) != null) {
            serverErrorFragment.dismiss();
        }
    }

    public void showServerError() {
        Dialog dialog;
        Dialog dialog2;
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag("ServerErrorFragment");
        if (!(findFragmentByTag instanceof ServerErrorFragment)) {
            findFragmentByTag = null;
        }
        ServerErrorFragment serverErrorFragment = (ServerErrorFragment) findFragmentByTag;
        this.currentDialogFragment = serverErrorFragment;
        if (serverErrorFragment == null || serverErrorFragment == null || (dialog2 = serverErrorFragment.getDialog()) == null || !dialog2.isShowing()) {
            ServerErrorFragment serverErrorFragment2 = this.currentDialogFragment;
            if (serverErrorFragment2 == null) {
                ServerErrorFragment serverErrorFragment3 = new ServerErrorFragment();
                this.currentDialogFragment = serverErrorFragment3;
                if (serverErrorFragment3 != null) {
                    serverErrorFragment3.show(getSupportFragmentManager(), "ServerErrorFragment");
                }
            } else if (serverErrorFragment2 != null && (dialog = serverErrorFragment2.getDialog()) != null) {
                dialog.show();
            }
        }
    }
}
