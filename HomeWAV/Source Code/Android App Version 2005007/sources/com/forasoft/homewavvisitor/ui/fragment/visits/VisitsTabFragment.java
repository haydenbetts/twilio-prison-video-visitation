package com.forasoft.homewavvisitor.ui.fragment.visits;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.View;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.ui.activity.CallListener;
import com.forasoft.homewavvisitor.ui.fragment.BaseTabFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u00012\u00020\u0002:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\tH\u0014¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitsTabFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseTabFragment;", "Lcom/forasoft/homewavvisitor/ui/activity/CallListener;", "()V", "createNavigator", "Lru/terrakok/cicerone/Navigator;", "getScreenKey", "", "onLiveswitchCallAccepted", "", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "onTwilioCallAccepted", "setRootFragment", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitsTabFragment.kt */
public final class VisitsTabFragment extends BaseTabFragment implements CallListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String SCHEDULE_NEW_VISIT = "schedule new visit";
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitsTabFragment$Companion;", "", "()V", "SCHEDULE_NEW_VISIT", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitsTabFragment;", "scheduleNewVisit", "", "(Ljava/lang/Boolean;)Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitsTabFragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: VisitsTabFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ VisitsTabFragment newInstance$default(Companion companion, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = null;
            }
            return companion.newInstance(bool);
        }

        public final VisitsTabFragment newInstance(Boolean bool) {
            VisitsTabFragment visitsTabFragment = new VisitsTabFragment();
            if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(VisitsTabFragment.SCHEDULE_NEW_VISIT, bool.booleanValue());
                visitsTabFragment.setArguments(bundle);
            }
            return visitsTabFragment;
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
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.getBoolean(SCHEDULE_NEW_VISIT)) {
            getRouter().newRootScreen(Screens.VisitsScreen.INSTANCE);
            return;
        }
        getRouter().newRootChain(Screens.VisitsScreen.INSTANCE, Screens.InmateChooseVisitsScreen.INSTANCE);
    }

    /* access modifiers changed from: protected */
    public String getScreenKey() {
        String screenKey = new Screens.VisitsTabScreen((Boolean) null, 1, (DefaultConstructorMarker) null).getScreenKey();
        Intrinsics.checkExpressionValueIsNotNull(screenKey, "Screens.VisitsTabScreen().screenKey");
        return screenKey;
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
