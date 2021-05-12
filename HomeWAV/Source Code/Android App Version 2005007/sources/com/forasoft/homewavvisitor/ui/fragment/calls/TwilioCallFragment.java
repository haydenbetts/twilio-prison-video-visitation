package com.forasoft.homewavvisitor.ui.fragment.calls;

import air.HomeWAV.R;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.calls.TwilioCallPresenter;
import com.forasoft.homewavvisitor.presentation.view.calls.TwilioCallView;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.RemoteVideoTrack;
import com.twilio.video.VideoTextureView;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.threeten.bp.Duration;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 ,2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001,B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020 H\u0016J\u001a\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010%\u001a\u00020\u0006H\u0007J\b\u0010&\u001a\u00020\fH\u0016J\b\u0010'\u001a\u00020\fH\u0016J\b\u0010(\u001a\u00020\fH\u0016J\u0010\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020+H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006-"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/TwilioCallFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/CallFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/TwilioCallView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/calls/TwilioCallPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/calls/TwilioCallPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/calls/TwilioCallPresenter;)V", "displayInmateVideo", "", "videoTrack", "Lcom/twilio/video/RemoteVideoTrack;", "displayVisitorVideo", "Lcom/twilio/video/LocalVideoTrack;", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onViewCreated", "view", "providePresenter", "showReportBugButton", "startCall", "stopCall", "updateRemainingTime", "remainingTime", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TwilioCallFragment.kt */
public final class TwilioCallFragment extends CallFragment implements TwilioCallView, OnBackButtonPressedListener {
    private static final String CALL_WRAPPER = "call wrapper";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    @InjectPresenter
    public TwilioCallPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/TwilioCallFragment$Companion;", "", "()V", "CALL_WRAPPER", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/TwilioCallFragment;", "callWrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TwilioCallFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TwilioCallFragment newInstance(CallWrapper callWrapper) {
            Intrinsics.checkParameterIsNotNull(callWrapper, "callWrapper");
            TwilioCallFragment twilioCallFragment = new TwilioCallFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(TwilioCallFragment.CALL_WRAPPER, callWrapper);
            twilioCallFragment.setArguments(bundle);
            return twilioCallFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new TwilioCallFragment$installModules$1(this));
    }

    public final TwilioCallPresenter getPresenter() {
        TwilioCallPresenter twilioCallPresenter = this.presenter;
        if (twilioCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return twilioCallPresenter;
    }

    public final void setPresenter(TwilioCallPresenter twilioCallPresenter) {
        Intrinsics.checkParameterIsNotNull(twilioCallPresenter, "<set-?>");
        this.presenter = twilioCallPresenter;
    }

    @ProvidePresenter
    public final TwilioCallPresenter providePresenter() {
        Object instance = getScope().getInstance(TwilioCallPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(TwilioCallPresenter::class.java)");
        return (TwilioCallPresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_twilio_call, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…o_call, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_end_call);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "btn_end_call");
        imageView.setOnClickListener(new TwilioCallFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TwilioCallFragment$onViewCreated$1(this)));
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        VideoTextureView videoTextureView = (VideoTextureView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.visitor_video_view);
        Intrinsics.checkExpressionValueIsNotNull(videoTextureView, "visitor_video_view");
        refreshVisitorVideoView(configuration, videoTextureView);
    }

    public void startCall() {
        TwilioCallPresenter twilioCallPresenter = this.presenter;
        if (twilioCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        twilioCallPresenter.startCall();
    }

    public void stopCall() {
        TwilioCallPresenter twilioCallPresenter = this.presenter;
        if (twilioCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        twilioCallPresenter.onEndCallClicked();
    }

    public boolean onBackPressed() {
        TwilioCallPresenter twilioCallPresenter = this.presenter;
        if (twilioCallPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        twilioCallPresenter.onEndCallClicked();
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("CALL_IN_PROGRESS", true);
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

    public void displayInmateVideo(RemoteVideoTrack remoteVideoTrack) {
        if (remoteVideoTrack != null) {
            remoteVideoTrack.addRenderer((VideoTextureView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.inmate_video_view));
        }
    }

    public void displayVisitorVideo(LocalVideoTrack localVideoTrack) {
        if (localVideoTrack != null) {
            localVideoTrack.addRenderer((VideoTextureView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.visitor_video_view));
        }
    }

    public void showReportBugButton() {
        CommonKt.show((ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_bug_report));
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_bug_report);
        Intrinsics.checkExpressionValueIsNotNull(imageButton, "iv_bug_report");
        imageButton.setOnClickListener(new TwilioCallFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TwilioCallFragment$showReportBugButton$1(this)));
    }
}
