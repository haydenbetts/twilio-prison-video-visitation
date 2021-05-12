package com.forasoft.homewavvisitor.ui.fragment.home;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import com.forasoft.homewavvisitor.ui.fragment.BaseTabFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u00012\u00020\u0002:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0014¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/HomeTabFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseTabFragment;", "Lcom/forasoft/homewavvisitor/ui/activity/CallListener;", "()V", "createNavigator", "Lru/terrakok/cicerone/Navigator;", "getScreenKey", "", "onBackPressed", "", "onLiveswitchCallAccepted", "", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "onTwilioCallAccepted", "setRootFragment", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HomeTabFragment.kt */
public final class HomeTabFragment extends BaseTabFragment implements CallListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String SCREEN_KEY = "screen key";
    private HashMap _$_findViewCache;

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

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/HomeTabFragment$Companion;", "", "()V", "SCREEN_KEY", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/home/HomeTabFragment;", "screenKey", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: HomeTabFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ HomeTabFragment newInstance$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return companion.newInstance(str);
        }

        public final HomeTabFragment newInstance(String str) {
            HomeTabFragment homeTabFragment = new HomeTabFragment();
            if (str != null) {
                Bundle bundle = new Bundle();
                bundle.putString(HomeTabFragment.SCREEN_KEY, str);
                homeTabFragment.setArguments(bundle);
            }
            return homeTabFragment;
        }
    }

    public void onCallCancelled() {
        CallListener.DefaultImpls.onCallCancelled(this);
    }

    public void onCallDeclined() {
        CallListener.DefaultImpls.onCallDeclined(this);
    }

    public void onDialogButtonClicked() {
        CallListener.DefaultImpls.onDialogButtonClicked(this);
    }

    /* access modifiers changed from: protected */
    public void setRootFragment() {
        SupportAppScreen supportAppScreen;
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString(SCREEN_KEY) : null;
        if (string != null) {
            if (Intrinsics.areEqual((Object) string, (Object) Screens.AddConnectionScreen.INSTANCE.getScreenKey())) {
                supportAppScreen = Screens.AddConnectionScreen.INSTANCE;
            } else if (Intrinsics.areEqual((Object) string, (Object) Screens.TutorialsScreen.INSTANCE.getScreenKey())) {
                supportAppScreen = Screens.TutorialsScreen.INSTANCE;
            } else if (Intrinsics.areEqual((Object) string, (Object) Screens.ChooseFundsScreen.INSTANCE.getScreenKey())) {
                supportAppScreen = new Screens.SignUp4Screen((Boolean) null, 1, (DefaultConstructorMarker) null);
            } else if (Intrinsics.areEqual((Object) string, (Object) Screens.NotificationsScreen.INSTANCE.getScreenKey())) {
                supportAppScreen = Screens.NotificationsScreen.INSTANCE;
            } else if (Intrinsics.areEqual((Object) string, (Object) Screens.HelpAndContactScreen.INSTANCE.getScreenKey())) {
                supportAppScreen = Screens.HelpAndContactScreen.INSTANCE;
            } else {
                return;
            }
            getRouter().newRootChain(Screens.HomeScreen.INSTANCE, supportAppScreen);
            return;
        }
        getRouter().newRootScreen(Screens.HomeScreen.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public String getScreenKey() {
        String screenKey = new Screens.HomeTabScreen((String) null, 1, (DefaultConstructorMarker) null).getScreenKey();
        Intrinsics.checkExpressionValueIsNotNull(screenKey, "Screens.HomeTabScreen().screenKey");
        return screenKey;
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

    /* access modifiers changed from: protected */
    public Navigator createNavigator() {
        return new SupportAppNavigator(requireActivity(), getChildFragmentManager(), R.id.container);
    }

    public void onTwilioCallAccepted(CallWrapper callWrapper) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        getRouter().navigateTo(new Screens.TwilioCallScreen(callWrapper));
    }

    public void onLiveswitchCallAccepted(CallWrapper callWrapper, LiveswitchCallDataResponse liveswitchCallDataResponse) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
        getRouter().navigateTo(new Screens.LiveswitchCallScreen(callWrapper, liveswitchCallDataResponse));
    }
}
