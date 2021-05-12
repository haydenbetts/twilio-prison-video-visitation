package com.forasoft.homewavvisitor.ui.fragment.calls;

import air.HomeWAV.R;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.liveswitch.CameraLocalMedia;
import com.forasoft.homewavvisitor.model.liveswitch.RemoteMedia;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.calls.LiveswitchCallPresenter;
import com.forasoft.homewavvisitor.presentation.view.calls.LiveswitchCallView;
import fm.liveswitch.android.LayoutManager;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.threeten.bp.Duration;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 52\u00020\u00012\u00020\u00022\u00020\u0003:\u00015B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001a\u0010%\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010'\u001a\u00020\bH\u0007J\u0010\u0010(\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u000f2\u0006\u0010)\u001a\u00020*H\u0016J\u0010\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u000fH\u0016J\b\u00100\u001a\u00020\u000fH\u0016J\b\u00101\u001a\u00020\u000fH\u0016J\u0010\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u000204H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/LiveswitchCallFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/CallFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/LiveswitchCallView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "localLayoutManager", "Lfm/liveswitch/android/LayoutManager;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/calls/LiveswitchCallPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/calls/LiveswitchCallPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/calls/LiveswitchCallPresenter;)V", "remoteLayoutManager", "displayInmateVideo", "", "remoteMedia", "Lcom/forasoft/homewavvisitor/model/liveswitch/RemoteMedia;", "displayVisitorVideo", "localMedia", "Lcom/forasoft/homewavvisitor/model/liveswitch/CameraLocalMedia;", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "providePresenter", "removeInmateVideo", "mediaId", "", "removeVisitorVideo", "showConnectionState", "resId", "", "showReportBugButton", "startCall", "stopCall", "updateRemainingTime", "remainingTime", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: LiveswitchCallFragment.kt */
public final class LiveswitchCallFragment extends CallFragment implements LiveswitchCallView, OnBackButtonPressedListener {
    private static final String CALL_DATA = "call data";
    private static final String CALL_WRAPPER = "call wrapper";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public LayoutManager localLayoutManager;
    @InjectPresenter
    public LiveswitchCallPresenter presenter;
    /* access modifiers changed from: private */
    public LayoutManager remoteLayoutManager;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/LiveswitchCallFragment$Companion;", "", "()V", "CALL_DATA", "", "CALL_WRAPPER", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/LiveswitchCallFragment;", "callWrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "callData", "Lcom/forasoft/homewavvisitor/model/server/response/LiveswitchCallDataResponse;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: LiveswitchCallFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LiveswitchCallFragment newInstance(CallWrapper callWrapper, LiveswitchCallDataResponse liveswitchCallDataResponse) {
            Intrinsics.checkParameterIsNotNull(callWrapper, "callWrapper");
            Intrinsics.checkParameterIsNotNull(liveswitchCallDataResponse, "callData");
            LiveswitchCallFragment liveswitchCallFragment = new LiveswitchCallFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(LiveswitchCallFragment.CALL_WRAPPER, callWrapper);
            bundle.putParcelable(LiveswitchCallFragment.CALL_DATA, liveswitchCallDataResponse);
            liveswitchCallFragment.setArguments(bundle);
            return liveswitchCallFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new LiveswitchCallFragment$installModules$1(this));
    }

    public final LiveswitchCallPresenter getPresenter() {
        LiveswitchCallPresenter liveswitchCallPresenter = this.presenter;
        if (liveswitchCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return liveswitchCallPresenter;
    }

    public final void setPresenter(LiveswitchCallPresenter liveswitchCallPresenter) {
        Intrinsics.checkParameterIsNotNull(liveswitchCallPresenter, "<set-?>");
        this.presenter = liveswitchCallPresenter;
    }

    @ProvidePresenter
    public final LiveswitchCallPresenter providePresenter() {
        Object instance = getScope().getInstance(LiveswitchCallPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Livesw…allPresenter::class.java)");
        return (LiveswitchCallPresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_liveswitch_call, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…h_call, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_network_connection));
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_network_connection));
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_end_call);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "btn_end_call");
        imageView.setOnClickListener(new LiveswitchCallFragment$inlined$sam$i$android_view_View_OnClickListener$0(new LiveswitchCallFragment$onViewCreated$1(this)));
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.visitor_video_view);
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "visitor_video_view");
        refreshVisitorVideoView(configuration, frameLayout);
    }

    public void startCall() {
        LiveswitchCallPresenter liveswitchCallPresenter = this.presenter;
        if (liveswitchCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        liveswitchCallPresenter.startCall();
    }

    public void stopCall() {
        LiveswitchCallPresenter liveswitchCallPresenter = this.presenter;
        if (liveswitchCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        liveswitchCallPresenter.onEndCallClicked();
    }

    public void displayInmateVideo(RemoteMedia remoteMedia) {
        Intrinsics.checkParameterIsNotNull(remoteMedia, "remoteMedia");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new LiveswitchCallFragment$displayInmateVideo$1(this, remoteMedia));
        }
    }

    public void removeInmateVideo(String str) {
        Intrinsics.checkParameterIsNotNull(str, "mediaId");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new LiveswitchCallFragment$removeInmateVideo$1(this, str));
        }
    }

    public void displayVisitorVideo(CameraLocalMedia cameraLocalMedia) {
        Intrinsics.checkParameterIsNotNull(cameraLocalMedia, "localMedia");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new LiveswitchCallFragment$displayVisitorVideo$1(this, cameraLocalMedia));
        }
    }

    public void removeVisitorVideo(String str) {
        Intrinsics.checkParameterIsNotNull(str, "mediaId");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new LiveswitchCallFragment$removeVisitorVideo$1(this));
        }
    }

    public void updateRemainingTime(long j) {
        Duration ofSeconds = Duration.ofSeconds(j);
        long hours = ofSeconds.toHours();
        long minutes = ofSeconds.minusHours(hours).toMinutes();
        Duration minusMinutes = ofSeconds.minusHours(hours).minusMinutes(minutes);
        Intrinsics.checkExpressionValueIsNotNull(minusMinutes, "duration.minusHours(hours).minusMinutes(minutes)");
        long seconds = minusMinutes.getSeconds();
        if (((int) hours) != 0) {
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_remaining_time);
            Intrinsics.checkExpressionValueIsNotNull(textView, "tv_remaining_time");
            String format = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)}, 3));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
            textView.setText(format);
            return;
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_remaining_time);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_remaining_time");
        String format2 = String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}, 2));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(this, *args)");
        textView2.setText(format2);
    }

    public void showConnectionState(int i) {
        ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_network_connection)).setImageResource(i);
    }

    public void showReportBugButton() {
        CommonKt.show((ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_bug_report));
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_bug_report);
        Intrinsics.checkExpressionValueIsNotNull(imageButton, "iv_bug_report");
        imageButton.setOnClickListener(new LiveswitchCallFragment$inlined$sam$i$android_view_View_OnClickListener$0(new LiveswitchCallFragment$showReportBugButton$1(this)));
    }

    public boolean onBackPressed() {
        LiveswitchCallPresenter liveswitchCallPresenter = this.presenter;
        if (liveswitchCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        liveswitchCallPresenter.onEndCallClicked();
        return true;
    }
}
