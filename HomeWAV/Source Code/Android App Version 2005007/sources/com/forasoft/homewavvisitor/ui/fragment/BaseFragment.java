package com.forasoft.homewavvisitor.ui.fragment;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.NotificationProvider;
import com.forasoft.homewavvisitor.ui.activity.BaseActivity;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.MvpAppCompatFragment;
import toothpick.Scope;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000 52\u00020\u00012\u00020\u0002:\u00015B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0017H\u0014J\b\u0010\u001d\u001a\u00020\tH\u0002J\b\u0010\u001e\u001a\u00020\tH\u0002J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020\u001cH\u0016J\b\u0010(\u001a\u00020\u001cH\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020!H\u0016J\u001a\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0010H\u0016J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u0007H\u0016J\b\u00102\u001a\u00020\u001cH\u0016J\u0010\u00103\u001a\u00020\u001c2\u0006\u00104\u001a\u00020\u0010H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00078TX\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0017@BX.¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u00066"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lmoxy/MvpAppCompatFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "()V", "actionProvider", "Lcom/forasoft/homewavvisitor/ui/NotificationProvider;", "fragmentScopeName", "", "hasNotificationMenu", "", "getHasNotificationMenu", "()Z", "setHasNotificationMenu", "(Z)V", "instanceStateSaved", "notifications", "", "parentScopeName", "getParentScopeName", "()Ljava/lang/String;", "parentScopeName$delegate", "Lkotlin/Lazy;", "<set-?>", "Ltoothpick/Scope;", "scope", "getScope", "()Ltoothpick/Scope;", "installModules", "", "isRealRemoving", "needCloseScope", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onDestroy", "onPause", "onResume", "onSaveInstanceState", "outState", "onViewCreated", "view", "Landroid/view/View;", "showMessage", "resId", "message", "showServerError", "updateNotificationMenu", "count", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BaseFragment.kt */
public class BaseFragment extends MvpAppCompatFragment implements BaseView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String STATE_SCOPE_NAME = "state_scope_name";
    private HashMap _$_findViewCache;
    private NotificationProvider actionProvider;
    /* access modifiers changed from: private */
    public String fragmentScopeName;
    private boolean hasNotificationMenu;
    private boolean instanceStateSaved;
    private int notifications;
    private final Lazy parentScopeName$delegate = LazyKt.lazy(new BaseFragment$parentScopeName$2(this));
    private Scope scope;

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public String getParentScopeName() {
        return (String) this.parentScopeName$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope2) {
        Intrinsics.checkParameterIsNotNull(scope2, "scope");
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment$Companion;", "", "()V", "STATE_SCOPE_NAME", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BaseFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final /* synthetic */ String access$getFragmentScopeName$p(BaseFragment baseFragment) {
        String str = baseFragment.fragmentScopeName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentScopeName");
        }
        return str;
    }

    public void showProgress(boolean z) {
        BaseView.DefaultImpls.showProgress(this, z);
    }

    /* access modifiers changed from: protected */
    public final boolean getHasNotificationMenu() {
        return this.hasNotificationMenu;
    }

    /* access modifiers changed from: protected */
    public final void setHasNotificationMenu(boolean z) {
        this.hasNotificationMenu = z;
    }

    /* access modifiers changed from: protected */
    public final Scope getScope() {
        Scope scope2 = this.scope;
        if (scope2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scope");
        }
        return scope2;
    }

    public void onCreate(Bundle bundle) {
        String str;
        if (bundle == null || (str = bundle.getString(STATE_SCOPE_NAME)) == null) {
            str = CommonKt.objectScopeName(this);
        }
        this.fragmentScopeName = str;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentScopeName");
        }
        if (Toothpick.isScopeOpen(str)) {
            String str2 = this.fragmentScopeName;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentScopeName");
            }
            Scope openScope = Toothpick.openScope(str2);
            Intrinsics.checkExpressionValueIsNotNull(openScope, "Toothpick.openScope(fragmentScopeName)");
            this.scope = openScope;
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = getParentScopeName();
            String str3 = this.fragmentScopeName;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fragmentScopeName");
            }
            objArr[1] = str3;
            Scope openScopes = Toothpick.openScopes(objArr);
            Intrinsics.checkExpressionValueIsNotNull(openScopes, "Toothpick.openScopes(par…eName, fragmentScopeName)");
            this.scope = openScopes;
            if (openScopes == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scope");
            }
            installModules(openScopes);
        }
        super.onCreate(bundle);
        if (this.hasNotificationMenu) {
            setHasOptionsMenu(true);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        requireActivity().invalidateOptionsMenu();
    }

    public void onResume() {
        super.onResume();
        this.instanceStateSaved = false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        this.instanceStateSaved = true;
        String str = this.fragmentScopeName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentScopeName");
        }
        bundle.putString(STATE_SCOPE_NAME, str);
    }

    public void onDestroy() {
        super.onDestroy();
        if (needCloseScope()) {
            Scope scope2 = this.scope;
            if (scope2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scope");
            }
            Toothpick.closeScope(scope2.getName());
        }
    }

    private final boolean needCloseScope() {
        FragmentActivity activity = getActivity();
        if (activity != null && activity.isChangingConfigurations()) {
            return false;
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 == null || !activity2.isFinishing()) {
            return isRealRemoving();
        }
        return true;
    }

    private final boolean isRealRemoving() {
        if (!isRemoving() || this.instanceStateSaved) {
            Fragment parentFragment = getParentFragment();
            if (!(parentFragment instanceof BaseFragment)) {
                parentFragment = null;
            }
            BaseFragment baseFragment = (BaseFragment) parentFragment;
            return baseFragment != null ? baseFragment.isRealRemoving() : false;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        if (this.hasNotificationMenu) {
            menuInflater.inflate(R.menu.toolbar_menu, menu);
            MenuItem findItem = menu.findItem(R.id.action_notifications);
            ActionProvider actionProvider2 = MenuItemCompat.getActionProvider(findItem);
            if (actionProvider2 != null) {
                NotificationProvider notificationProvider = (NotificationProvider) actionProvider2;
                this.actionProvider = notificationProvider;
                if (notificationProvider != null) {
                    notificationProvider.setOnClickListener(new BaseFragment$onCreateOptionsMenu$1(this, findItem));
                }
                NotificationProvider notificationProvider2 = this.actionProvider;
                if (notificationProvider2 != null) {
                    notificationProvider2.updateSize(this.notifications);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.NotificationProvider");
        }
    }

    public void showMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        ContextKt.showSnackbar((Fragment) this, str);
    }

    public void showMessage(int i) {
        String string = getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(resId)");
        showMessage(string);
    }

    public void updateNotificationMenu(int i) {
        if (this.hasNotificationMenu) {
            this.notifications = i;
            NotificationProvider notificationProvider = this.actionProvider;
            if (notificationProvider != null) {
                notificationProvider.updateSize(i);
            }
        }
    }

    public void showServerError() {
        FragmentActivity activity = getActivity();
        if (!(activity instanceof BaseActivity)) {
            activity = null;
        }
        BaseActivity baseActivity = (BaseActivity) activity;
        if (baseActivity != null) {
            baseActivity.showServerError();
        }
    }

    public void onPause() {
        super.onPause();
        FragmentActivity activity = getActivity();
        if (!(activity instanceof BaseActivity)) {
            activity = null;
        }
        BaseActivity baseActivity = (BaseActivity) activity;
        if (baseActivity != null) {
            baseActivity.hideServerError();
        }
    }
}
