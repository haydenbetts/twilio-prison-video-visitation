package com.forasoft.homewavvisitor.ui.fragment;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.navigation.CiceroneHolder;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.toothpick.DI;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u001fH\u0002J\b\u0010 \u001a\u00020\u000bH$J\b\u0010!\u001a\u00020\"H$J\b\u0010#\u001a\u00020$H\u0016J\u0012\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J$\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010/\u001a\u00020&H\u0016J\b\u00100\u001a\u00020&H\u0016J\b\u00101\u001a\u00020&H$R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0010\u001a\u00020\u00118\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u00062"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/BaseTabFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "ciceroneHolder", "Lcom/forasoft/homewavvisitor/navigation/CiceroneHolder;", "getCiceroneHolder", "()Lcom/forasoft/homewavvisitor/navigation/CiceroneHolder;", "setCiceroneHolder", "(Lcom/forasoft/homewavvisitor/navigation/CiceroneHolder;)V", "navigator", "Lru/terrakok/cicerone/Navigator;", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "navigator$delegate", "Lkotlin/Lazy;", "navigatorHolder", "Lru/terrakok/cicerone/NavigatorHolder;", "getNavigatorHolder", "()Lru/terrakok/cicerone/NavigatorHolder;", "setNavigatorHolder", "(Lru/terrakok/cicerone/NavigatorHolder;)V", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "getRouter", "()Lcom/forasoft/homewavvisitor/HomewavRouter;", "setRouter", "(Lcom/forasoft/homewavvisitor/HomewavRouter;)V", "createModule", "Ltoothpick/config/Module;", "cicerone", "Lru/terrakok/cicerone/Cicerone;", "createNavigator", "getScreenKey", "", "onBackPressed", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPause", "onResume", "setRootFragment", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BaseTabFragment.kt */
public abstract class BaseTabFragment extends Fragment implements OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @Inject
    public CiceroneHolder ciceroneHolder;
    private final Lazy navigator$delegate = LazyKt.lazy(new BaseTabFragment$navigator$2(this));
    @Inject
    public NavigatorHolder navigatorHolder;
    @Inject
    public HomewavRouter router;

    private final Navigator getNavigator() {
        return (Navigator) this.navigator$delegate.getValue();
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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public abstract Navigator createNavigator();

    /* access modifiers changed from: protected */
    public abstract String getScreenKey();

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* access modifiers changed from: protected */
    public abstract void setRootFragment();

    public final CiceroneHolder getCiceroneHolder() {
        CiceroneHolder ciceroneHolder2 = this.ciceroneHolder;
        if (ciceroneHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ciceroneHolder");
        }
        return ciceroneHolder2;
    }

    public final void setCiceroneHolder(CiceroneHolder ciceroneHolder2) {
        Intrinsics.checkParameterIsNotNull(ciceroneHolder2, "<set-?>");
        this.ciceroneHolder = ciceroneHolder2;
    }

    public final HomewavRouter getRouter() {
        HomewavRouter homewavRouter = this.router;
        if (homewavRouter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        return homewavRouter;
    }

    public final void setRouter(HomewavRouter homewavRouter) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "<set-?>");
        this.router = homewavRouter;
    }

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

    public void onCreate(Bundle bundle) {
        Scope openScopes = Toothpick.openScopes(DI.SERVER_SCOPE);
        Toothpick.inject(this, openScopes);
        CiceroneHolder ciceroneHolder2 = this.ciceroneHolder;
        if (ciceroneHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ciceroneHolder");
        }
        openScopes.installModules(createModule(ciceroneHolder2.getCicerone(getScreenKey())));
        Toothpick.inject(this, openScopes);
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        if (navigatorHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigatorHolder");
        }
        navigatorHolder2.setNavigator(getNavigator());
        super.onCreate(bundle);
        if (bundle == null) {
            setRootFragment();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_tab, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…nt_tab, container, false)");
        return inflate;
    }

    public void onResume() {
        super.onResume();
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        if (navigatorHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigatorHolder");
        }
        navigatorHolder2.setNavigator(getNavigator());
    }

    public void onPause() {
        NavigatorHolder navigatorHolder2 = this.navigatorHolder;
        if (navigatorHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigatorHolder");
        }
        navigatorHolder2.removeNavigator();
        super.onPause();
    }

    public boolean onBackPressed() {
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.container);
        if (!(findFragmentById instanceof OnBackButtonPressedListener)) {
            findFragmentById = null;
        }
        OnBackButtonPressedListener onBackButtonPressedListener = (OnBackButtonPressedListener) findFragmentById;
        if (onBackButtonPressedListener != null) {
            return onBackButtonPressedListener.onBackPressed();
        }
        return false;
    }

    private final Module createModule(Cicerone<HomewavRouter> cicerone) {
        return new BaseTabFragment$createModule$1(cicerone);
    }
}
