package com.forasoft.homewavvisitor.ui.activity;

import air.HomeWAV.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.navigation.BusNotifier;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.presenter.MainPresenter;
import com.forasoft.homewavvisitor.presentation.view.MainView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import com.forasoft.homewavvisitor.ui.fragment.BaseTabFragment;
import com.forasoft.homewavvisitor.ui.fragment.calls.IncomingCallDialogFragment;
import com.forasoft.homewavvisitor.utils.XiaomiUtilities;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b*\u0001\u0014\u0018\u0000 S2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001SB\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\u001b\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0002¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u001fH\u0002J\b\u0010)\u001a\u00020\u001fH\u0002J\b\u0010*\u001a\u00020\u001fH\u0002J\b\u0010+\u001a\u00020\u001fH\u0002J\b\u0010,\u001a\u00020\u001fH\u0016J\b\u0010-\u001a\u00020\u001fH\u0016J\u0012\u0010.\u001a\u00020\u001f2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\u0010\u00101\u001a\u00020\u001f2\u0006\u00102\u001a\u00020&H\u0002J\b\u00103\u001a\u00020\u001fH\u0014J\b\u00104\u001a\u00020\u001fH\u0016J\u0018\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0016J\u0010\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020<H\u0014J\b\u0010=\u001a\u00020\u001fH\u0014J\b\u0010>\u001a\u00020\u001fH\u0014J\u0010\u0010?\u001a\u00020\u001f2\u0006\u0010@\u001a\u000200H\u0014J\b\u0010A\u001a\u00020\u001fH\u0002J\b\u0010B\u001a\u00020\u001fH\u0014J\b\u0010C\u001a\u00020\u001fH\u0014J\b\u0010D\u001a\u00020\u001fH\u0002J\u0010\u0010E\u001a\u00020\u001f2\u0006\u00106\u001a\u000207H\u0016J\u0010\u0010F\u001a\u00020\u001f2\u0006\u00102\u001a\u00020&H\u0002J\b\u0010G\u001a\u00020\nH\u0007J\b\u0010H\u001a\u00020\u001fH\u0002J\b\u0010I\u001a\u00020\u001fH\u0002J\u0010\u0010J\u001a\u00020\u001f2\u0006\u0010K\u001a\u00020LH\u0016J\b\u0010M\u001a\u00020\u001fH\u0016J\u0010\u0010N\u001a\u00020#2\u0006\u0010O\u001a\u00020\u0007H\u0002J\b\u0010P\u001a\u00020\u001fH\u0016J\u0010\u0010Q\u001a\u00020\u001f2\u0006\u0010R\u001a\u00020\u0007H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000¨\u0006T"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/MainActivity;", "Lcom/forasoft/homewavvisitor/ui/activity/BaseActivity;", "Lcom/forasoft/homewavvisitor/presentation/view/MainView;", "Lcom/forasoft/homewavvisitor/ui/activity/CallListener;", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "()V", "actionLayoutVisibility", "", "currentSelectedAction", "mainPresenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/MainPresenter;", "getMainPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/MainPresenter;", "setMainPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/MainPresenter;)V", "navigator", "Lru/terrakok/cicerone/Navigator;", "getNavigator", "()Lru/terrakok/cicerone/Navigator;", "networkChangeReceiver", "com/forasoft/homewavvisitor/ui/activity/MainActivity$networkChangeReceiver$1", "Lcom/forasoft/homewavvisitor/ui/activity/MainActivity$networkChangeReceiver$1;", "notifier", "Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "getNotifier", "()Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "setNotifier", "(Lcom/forasoft/homewavvisitor/navigation/BusNotifier;)V", "termsDialog", "Landroidx/appcompat/app/AlertDialog;", "dismissCallFragment", "", "getLocalRouter", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "hasAllPermissions", "", "permissions", "", "", "([Ljava/lang/String;)Z", "initBottomNavigation", "onAddFunds", "onAddInmate", "onAddInmateDeepLink", "onBackPressed", "onCallDeclined", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDeepLink", "screenKey", "onDestroy", "onGlobalLayout", "onLiveswitchCallAccepted", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "onNewIntent", "intent", "Landroid/content/Intent;", "onPause", "onResume", "onSaveInstanceState", "outState", "onScheduleVisit", "onStart", "onStop", "onTermsClicked", "onTwilioCallAccepted", "openScreen", "providePresenter", "requestPermissionsForApp", "setActionLayoutOnVisibilityChangeListener", "showCallDialog", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "showDisapprovedDialog", "showTab", "itemId", "showTermsConditionsDialog", "updateMessagesBadge", "count", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
public final class MainActivity extends BaseActivity implements MainView, CallListener, ViewTreeObserver.OnGlobalLayoutListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String SCREEN_KEY = "screen";
    public static final String SELECTED_TAB = "selected tab";
    public static final String TAG = "MainActivity";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public int actionLayoutVisibility = 8;
    /* access modifiers changed from: private */
    public int currentSelectedAction = R.id.action_home;
    @InjectPresenter
    public MainPresenter mainPresenter;
    private final Navigator navigator = new SupportAppNavigator(this, R.id.container);
    private final MainActivity$networkChangeReceiver$1 networkChangeReceiver = new MainActivity$networkChangeReceiver$1(this);
    @Inject
    public BusNotifier notifier;
    private AlertDialog termsDialog;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/MainActivity$Companion;", "", "()V", "SCREEN_KEY", "", "SELECTED_TAB", "TAG", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MainActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public void onCallCancelled() {
        CallListener.DefaultImpls.onCallCancelled(this);
    }

    public void onDialogButtonClicked() {
        CallListener.DefaultImpls.onDialogButtonClicked(this);
    }

    public final MainPresenter getMainPresenter() {
        MainPresenter mainPresenter2 = this.mainPresenter;
        if (mainPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
        }
        return mainPresenter2;
    }

    public final void setMainPresenter(MainPresenter mainPresenter2) {
        Intrinsics.checkParameterIsNotNull(mainPresenter2, "<set-?>");
        this.mainPresenter = mainPresenter2;
    }

    @ProvidePresenter
    public final MainPresenter providePresenter() {
        Object instance = Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE).getInstance(MainPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick.openScopes(DI.…ainPresenter::class.java)");
        return (MainPresenter) instance;
    }

    public final BusNotifier getNotifier() {
        BusNotifier busNotifier = this.notifier;
        if (busNotifier == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notifier");
        }
        return busNotifier;
    }

    public final void setNotifier(BusNotifier busNotifier) {
        Intrinsics.checkParameterIsNotNull(busNotifier, "<set-?>");
        this.notifier = busNotifier;
    }

    /* access modifiers changed from: protected */
    public Navigator getNavigator() {
        return this.navigator;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Toothpick.inject(this, Toothpick.openScopes(DI.APP_SCOPE, DI.SERVER_SCOPE));
        super.onCreate(bundle);
        requestPermissionsForApp();
        setContentView((int) R.layout.activity_main);
        setSupportActionBar((Toolbar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toolbar));
        if (bundle == null) {
            showTab(R.id.action_home);
        } else {
            this.currentSelectedAction = bundle.getInt(SELECTED_TAB);
            BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
            Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx, "bnv_main");
            bottomNavigationViewEx.setSelectedItemId(this.currentSelectedAction);
        }
        initBottomNavigation();
        BottomNavigationViewEx bottomNavigationViewEx2 = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
        Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx2, "bnv_main");
        bottomNavigationViewEx2.getViewTreeObserver().addOnGlobalLayoutListener(this);
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "layout_action");
        constraintLayout.setOnClickListener(new MainActivity$inlined$sam$i$android_view_View_OnClickListener$0(new MainActivity$onCreate$1(this)));
        ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout2, "layout_action");
        ImageView imageView = (ImageView) constraintLayout2.findViewById(com.forasoft.homewavvisitor.R.id.action_add_funds);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "layout_action.action_add_funds");
        imageView.setOnClickListener(new MainActivity$inlined$sam$i$android_view_View_OnClickListener$0(new MainActivity$onCreate$2(this)));
        ConstraintLayout constraintLayout3 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout3, "layout_action");
        ImageView imageView2 = (ImageView) constraintLayout3.findViewById(com.forasoft.homewavvisitor.R.id.action_add_inmates);
        Intrinsics.checkExpressionValueIsNotNull(imageView2, "layout_action.action_add_inmates");
        imageView2.setOnClickListener(new MainActivity$inlined$sam$i$android_view_View_OnClickListener$0(new MainActivity$onCreate$3(this)));
        ConstraintLayout constraintLayout4 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout4, "layout_action");
        ImageView imageView3 = (ImageView) constraintLayout4.findViewById(com.forasoft.homewavvisitor.R.id.action_schedule_visit);
        Intrinsics.checkExpressionValueIsNotNull(imageView3, "layout_action.action_schedule_visit");
        imageView3.setOnClickListener(new MainActivity$inlined$sam$i$android_view_View_OnClickListener$0(new MainActivity$onCreate$4(this)));
        ConstraintLayout constraintLayout5 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout5, "layout_action");
        ImageView imageView4 = (ImageView) constraintLayout5.findViewById(com.forasoft.homewavvisitor.R.id.action_new_message);
        Intrinsics.checkExpressionValueIsNotNull(imageView4, "layout_action.action_new_message");
        imageView4.setOnClickListener(new MainActivity$inlined$sam$i$android_view_View_OnClickListener$0(new MainActivity$onCreate$5(this)));
        ViewCompat.setTranslationZ((BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main), 16.0f);
        ((BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)).setSmallTextSize(14.0f);
        ((BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)).setLargeTextSize(14.0f);
        String stringExtra = getIntent().getStringExtra(SCREEN_KEY);
        if (stringExtra != null) {
            openScreen(stringExtra);
        }
        setActionLayoutOnVisibilityChangeListener();
    }

    private final void requestPermissionsForApp() {
        String[] strArr = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_NETWORK_STATE"};
        if (!hasAllPermissions(strArr)) {
            ActivityCompat.requestPermissions(this, strArr, 2);
        }
        if (XiaomiUtilities.INSTANCE.isMIUI()) {
            XiaomiUtilities.INSTANCE.checkLockedScreenPermission(this);
        }
    }

    private final boolean hasAllPermissions(String[] strArr) {
        for (String checkSelfPermission : strArr) {
            if (ActivityCompat.checkSelfPermission(this, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final boolean showTab(int i) {
        switch (i) {
            case R.id.action_account /*2131296301*/:
                MainPresenter mainPresenter2 = this.mainPresenter;
                if (mainPresenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
                }
                mainPresenter2.toScreen(Screens.AccountTabScreen.INSTANCE);
                break;
            case R.id.action_conversations /*2131296315*/:
                MainPresenter mainPresenter3 = this.mainPresenter;
                if (mainPresenter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
                }
                mainPresenter3.toScreen(Screens.ConversationsTabScreen.INSTANCE);
                break;
            case R.id.action_home /*2131296319*/:
                MainPresenter mainPresenter4 = this.mainPresenter;
                if (mainPresenter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
                }
                mainPresenter4.toScreen(new Screens.HomeTabScreen((String) null, 1, (DefaultConstructorMarker) null));
                break;
            case R.id.action_visits /*2131296332*/:
                MainPresenter mainPresenter5 = this.mainPresenter;
                if (mainPresenter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
                }
                mainPresenter5.toScreen(new Screens.VisitsTabScreen((Boolean) null, 1, (DefaultConstructorMarker) null));
                break;
            default:
                CommonKt.hide((BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
                CommonKt.show((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action));
                break;
        }
        if (i != R.id.action_add) {
            this.currentSelectedAction = i;
        }
        return true;
    }

    private final void initBottomNavigation() {
        ColorStateList colorStateList = ContextCompat.getColorStateList(this, R.color.selector_item_add);
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.setIconSizeAt(2, 45.0f, 45.0f);
        bottomNavigationViewEx.setIconTintList(2, colorStateList);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new MainActivity$initBottomNavigation$$inlined$apply$lambda$1(this, colorStateList));
    }

    /* access modifiers changed from: private */
    public final void onAddFunds() {
        CommonKt.hide((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action));
        getLocalRouter().navigateTo(new Screens.SignUp4Screen((Boolean) null, 1, (DefaultConstructorMarker) null));
    }

    /* access modifiers changed from: private */
    public final void onAddInmate() {
        CommonKt.hide((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action));
        Toothpick.openScopes(DI.SERVER_SCOPE, DI.ADD_CONNECTION_SCOPE).installModules(new MainActivity$onAddInmate$1(this));
        getLocalRouter().navigateTo(Screens.AddConnectionScreen.INSTANCE);
    }

    private final HomewavRouter getLocalRouter() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (findFragmentById != null) {
            return ((BaseTabFragment) findFragmentById).getRouter();
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.fragment.BaseTabFragment");
    }

    /* access modifiers changed from: private */
    public final void onScheduleVisit() {
        CommonKt.hide((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action));
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
        Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx, "bnv_main");
        bottomNavigationViewEx.setSelectedItemId(R.id.action_visits);
        MainPresenter mainPresenter2 = this.mainPresenter;
        if (mainPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
        }
        mainPresenter2.onScheduleVisit();
    }

    private final void openScreen(String str) {
        if (Intrinsics.areEqual((Object) str, (Object) Screens.AddConnectionScreen.INSTANCE.getScreenKey())) {
            onAddInmateDeepLink();
        } else if (Intrinsics.areEqual((Object) str, (Object) Screens.InmateChooseVisitsScreen.INSTANCE.getScreenKey())) {
            onScheduleVisit();
        } else if (Intrinsics.areEqual((Object) str, (Object) Screens.ChooseFundsScreen.INSTANCE.getScreenKey())) {
            onDeepLink(str);
        } else if (Intrinsics.areEqual((Object) str, (Object) Screens.NotificationsScreen.INSTANCE.getScreenKey())) {
            onDeepLink(str);
        } else if (Intrinsics.areEqual((Object) str, (Object) Screens.HelpAndContactScreen.INSTANCE.getScreenKey())) {
            onDeepLink(str);
        } else if (Intrinsics.areEqual((Object) str, (Object) Screens.TutorialsScreen.INSTANCE.getScreenKey())) {
            onDeepLink(str);
        }
    }

    private final void onAddInmateDeepLink() {
        Toothpick.openScopes(DI.SERVER_SCOPE, DI.ADD_CONNECTION_SCOPE).installModules(new MainActivity$onAddInmateDeepLink$1(this));
        String screenKey = Screens.AddConnectionScreen.INSTANCE.getScreenKey();
        Intrinsics.checkExpressionValueIsNotNull(screenKey, "Screens.AddConnectionScreen.screenKey");
        onDeepLink(screenKey);
    }

    private final void onDeepLink(String str) {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
        Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx, "bnv_main");
        bottomNavigationViewEx.setSelectedItemId(R.id.action_home);
        MainPresenter mainPresenter2 = this.mainPresenter;
        if (mainPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
        }
        mainPresenter2.onDeepLink(str);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        super.onNewIntent(intent);
        String stringExtra = intent.getStringExtra(SCREEN_KEY);
        if (stringExtra != null) {
            openScreen(stringExtra);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        MainPresenter mainPresenter2 = this.mainPresenter;
        if (mainPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
        }
        mainPresenter2.connectToPusher();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.layout_action)).performClick();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.networkChangeReceiver, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.networkChangeReceiver);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        MainPresenter mainPresenter2 = this.mainPresenter;
        if (mainPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
        }
        mainPresenter2.disconnectFromPusher();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putInt(SELECTED_TAB, this.currentSelectedAction);
    }

    public void onBackPressed() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(findFragmentById instanceof OnBackButtonPressedListener)) {
            findFragmentById = null;
        }
        OnBackButtonPressedListener onBackButtonPressedListener = (OnBackButtonPressedListener) findFragmentById;
        if (onBackButtonPressedListener != null) {
            onBackButtonPressedListener.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
        Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx, "bnv_main");
        bottomNavigationViewEx.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        AlertDialog alertDialog = this.termsDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        super.onDestroy();
    }

    public void updateMessagesBadge(int i) {
        CharSequence charSequence;
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_badge");
        if (100 <= i && Integer.MAX_VALUE >= i) {
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge));
        } else if (i == 0) {
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge));
        } else {
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge));
            charSequence = String.valueOf(i);
        }
        textView.setText(charSequence);
    }

    public void showTermsConditionsDialog() {
        Context context = this;
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_terms, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "contentView");
        TextView textView = (TextView) inflate.findViewById(com.forasoft.homewavvisitor.R.id.linkTermConditions);
        Intrinsics.checkExpressionValueIsNotNull(textView, "contentView.linkTermConditions");
        textView.setOnClickListener(new MainActivity$inlined$sam$i$android_view_View_OnClickListener$0(new MainActivity$showTermsConditionsDialog$1(this)));
        this.termsDialog = new AlertDialog.Builder(context).setView(inflate).setTitle((int) R.string.title_terms_and_conditions).setPositiveButton((CharSequence) getString(R.string.button_accept), (DialogInterface.OnClickListener) new MainActivity$showTermsConditionsDialog$2(this)).setNegativeButton((CharSequence) getString(R.string.button_no_close_app), (DialogInterface.OnClickListener) new MainActivity$showTermsConditionsDialog$3(this)).setCancelable(false).show();
    }

    /* access modifiers changed from: private */
    public final void onTermsClicked() {
        MainPresenter mainPresenter2 = this.mainPresenter;
        if (mainPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainPresenter");
        }
        mainPresenter2.onTermsClicked();
        AlertDialog alertDialog = this.termsDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    public void showDisapprovedDialog() {
        new AlertDialog.Builder(this).setTitle((int) R.string.error).setMessage((int) R.string.disapproved_alert).setPositiveButton((int) R.string.label_ok, (DialogInterface.OnClickListener) MainActivity$showDisapprovedDialog$1.INSTANCE).setCancelable(false).show();
    }

    public void showCallDialog(Call call) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(TAG);
        if (!(findFragmentByTag instanceof DialogFragment)) {
            findFragmentByTag = null;
        }
        if (((DialogFragment) findFragmentByTag) == null) {
            IncomingCallDialogFragment.Companion.newInstance(call).show(getSupportFragmentManager(), TAG);
        }
    }

    public void onTwilioCallAccepted(CallWrapper callWrapper) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(findFragmentById instanceof CallListener)) {
            findFragmentById = null;
        }
        CallListener callListener = (CallListener) findFragmentById;
        if (callListener != null) {
            callListener.onTwilioCallAccepted(callWrapper);
        }
    }

    public void onLiveswitchCallAccepted(CallWrapper callWrapper, LiveswitchCallDataResponse liveswitchCallDataResponse) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(findFragmentById instanceof CallListener)) {
            findFragmentById = null;
        }
        CallListener callListener = (CallListener) findFragmentById;
        if (callListener != null) {
            callListener.onLiveswitchCallAccepted(callWrapper, liveswitchCallDataResponse);
        }
    }

    public void onCallDeclined() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.container);
        if (!(findFragmentById instanceof CallListener)) {
            findFragmentById = null;
        }
        CallListener callListener = (CallListener) findFragmentById;
        if (callListener != null) {
            callListener.onCallDeclined();
        }
    }

    public final void dismissCallFragment() {
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(TAG);
        if (!(findFragmentByTag instanceof DialogFragment)) {
            findFragmentByTag = null;
        }
        DialogFragment dialogFragment = (DialogFragment) findFragmentByTag;
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    public void onGlobalLayout() {
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main);
        Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx, "bnv_main");
        if (bottomNavigationViewEx.getVisibility() == 0) {
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_badge");
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_badge");
            textView.setVisibility(Intrinsics.areEqual((Object) textView2.getText(), (Object) "") ? 8 : 0);
            return;
        }
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_badge));
    }

    private final void setActionLayoutOnVisibilityChangeListener() {
        View findViewById = findViewById(16908290);
        View view = null;
        if (!(findViewById instanceof ViewGroup)) {
            findViewById = null;
        }
        ViewGroup viewGroup = (ViewGroup) findViewById;
        if (viewGroup != null) {
            view = viewGroup.getChildAt(0);
        }
        if (view == null) {
            Intrinsics.throwNpe();
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver == null) {
            Intrinsics.throwNpe();
        }
        viewTreeObserver.addOnGlobalLayoutListener(new MainActivity$setActionLayoutOnVisibilityChangeListener$1(this));
    }
}
