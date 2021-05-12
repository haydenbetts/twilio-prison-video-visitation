package com.forasoft.homewavvisitor.ui.fragment.payment;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.toothpick.qualifier.PaynearmeOrderUrl;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.StepperView;
import java.net.URLDecoder;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Router;
import toothpick.Scope;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u00012\u00020\u0002:\u0001&B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0011H\u0016J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0016J\u001a\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010%\u001a\u00020\u0011H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006'"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeViewerFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "router", "Lru/terrakok/cicerone/Router;", "getRouter", "()Lru/terrakok/cicerone/Router;", "setRouter", "(Lru/terrakok/cicerone/Router;)V", "trackingUrl", "", "getTrackingUrl", "()Ljava/lang/String;", "setTrackingUrl", "(Ljava/lang/String;)V", "installModules", "", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "setGlobalRouter", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PayNearMeViewerFragment.kt */
public final class PayNearMeViewerFragment extends BaseFragment implements OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PAYNEARME_ORDER_URL = "paynearme order url";
    private HashMap _$_findViewCache;
    @Inject
    public Router router;
    @Inject
    @PaynearmeOrderUrl
    public String trackingUrl;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeViewerFragment$Companion;", "", "()V", "PAYNEARME_ORDER_URL", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeViewerFragment;", "inmateId", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: PayNearMeViewerFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PayNearMeViewerFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            PayNearMeViewerFragment payNearMeViewerFragment = new PayNearMeViewerFragment();
            Bundle bundle = new Bundle();
            bundle.putString(PayNearMeViewerFragment.PAYNEARME_ORDER_URL, str);
            payNearMeViewerFragment.setArguments(bundle);
            return payNearMeViewerFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new PayNearMeViewerFragment$installModules$1(this));
    }

    public final Router getRouter() {
        Router router2 = this.router;
        if (router2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        return router2;
    }

    public final void setRouter(Router router2) {
        Intrinsics.checkParameterIsNotNull(router2, "<set-?>");
        this.router = router2;
    }

    public final String getTrackingUrl() {
        String str = this.trackingUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingUrl");
        }
        return str;
    }

    public final void setTrackingUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.trackingUrl = str;
    }

    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        super.onCreate(bundle);
        Toothpick.inject(this, getScope());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_pay_near_me_viewer, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity activity = getActivity();
        CreateAccountActivity createAccountActivity = null;
        if (!(activity instanceof MainActivity)) {
            activity = null;
        }
        MainActivity mainActivity = (MainActivity) activity;
        if (mainActivity != null) {
            ActionBar supportActionBar = mainActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_paynearme_order);
            }
            ActionBar supportActionBar2 = mainActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            ActionBar supportActionBar3 = mainActivity.getSupportActionBar();
            if (supportActionBar3 != null) {
                supportActionBar3.setDisplayShowHomeEnabled(true);
            }
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 instanceof CreateAccountActivity) {
            createAccountActivity = activity2;
        }
        CreateAccountActivity createAccountActivity2 = createAccountActivity;
        if (createAccountActivity2 != null) {
            ActionBar supportActionBar4 = createAccountActivity2.getSupportActionBar();
            if (supportActionBar4 != null) {
                supportActionBar4.setTitle((int) R.string.label_paynearme_order);
            }
            ActionBar supportActionBar5 = createAccountActivity2.getSupportActionBar();
            if (supportActionBar5 != null) {
                supportActionBar5.setDisplayHomeAsUpEnabled(true);
            }
            ActionBar supportActionBar6 = createAccountActivity2.getSupportActionBar();
            if (supportActionBar6 != null) {
                supportActionBar6.setDisplayShowHomeEnabled(true);
            }
            CommonKt.hide((StepperView) createAccountActivity2._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper));
            setGlobalRouter();
        }
        String str = this.trackingUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingUrl");
        }
        String decode = URLDecoder.decode(str, "UTF-8");
        WebView webView = (WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.viewer);
        Intrinsics.checkExpressionValueIsNotNull(webView, "viewer");
        webView.setWebViewClient(new WebViewClient());
        WebView webView2 = (WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.viewer);
        Intrinsics.checkExpressionValueIsNotNull(webView2, "viewer");
        WebSettings settings = webView2.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "viewer.settings");
        settings.setJavaScriptEnabled(true);
        ((WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.viewer)).loadUrl(decode);
    }

    private final void setGlobalRouter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(Router.class, "com.forasoft.homewavvisitor.toothpick.qualifier.Global");
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScope(DI.S…thpick.qualifier.Global\")");
        this.router = (Router) instance;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            return onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onDestroyView() {
        StepperView stepperView;
        FragmentActivity activity = getActivity();
        if (!(activity instanceof CreateAccountActivity)) {
            activity = null;
        }
        CreateAccountActivity createAccountActivity = (CreateAccountActivity) activity;
        if (!(createAccountActivity == null || (stepperView = (StepperView) createAccountActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper)) == null)) {
            CommonKt.show(stepperView);
        }
        ContextKt.hideActionBack(this);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public boolean onBackPressed() {
        WebBackForwardList copyBackForwardList = ((WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.viewer)).copyBackForwardList();
        Intrinsics.checkExpressionValueIsNotNull(copyBackForwardList, "viewer.copyBackForwardList()");
        if (copyBackForwardList.getCurrentIndex() > 0) {
            ((WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.viewer)).goBack();
            return true;
        }
        Router router2 = this.router;
        if (router2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("router");
        }
        router2.exit();
        return true;
    }
}
