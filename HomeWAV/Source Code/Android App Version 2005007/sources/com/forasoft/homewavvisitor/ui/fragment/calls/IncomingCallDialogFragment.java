package com.forasoft.homewavvisitor.ui.fragment.calls;

import air.HomeWAV.R;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.AirshipAnalytics;
import com.forasoft.homewavvisitor.model.IncomingCallActionsReceiver;
import com.forasoft.homewavvisitor.model.IncomingCallService;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.Protocol;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.model.server.response.LiveswitchCallDataResponse;
import com.forasoft.homewavvisitor.model.system.RingtoneManager;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import jp.wasabeef.blurry.Blurry;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000b\b\u0007\u0018\u0000 I2\u00020\u00012\u00020\u0002:\u0001IB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020,H\u0016J\u0012\u0010-\u001a\u00020)2\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J&\u00100\u001a\u0004\u0018\u0001012\u0006\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u0001052\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\b\u00106\u001a\u00020)H\u0007J\b\u00107\u001a\u00020)H\u0016J\b\u00108\u001a\u00020)H\u0016J\b\u00109\u001a\u00020)H\u0016J-\u0010:\u001a\u00020)2\u0006\u0010;\u001a\u00020,2\u000e\u0010<\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110=2\u0006\u0010>\u001a\u00020?H\u0016¢\u0006\u0002\u0010@J\b\u0010A\u001a\u00020)H\u0016J\u001a\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u0002012\b\u0010.\u001a\u0004\u0018\u00010/H\u0016J\u0010\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u00020,H\u0002J\u0018\u0010F\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u0011H\u0007J\u0018\u0010H\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\u0011H\u0007R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u001d8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020#8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006J"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", "()V", "analytics", "Lcom/forasoft/homewavvisitor/model/AirshipAnalytics;", "getAnalytics", "()Lcom/forasoft/homewavvisitor/model/AirshipAnalytics;", "setAnalytics", "(Lcom/forasoft/homewavvisitor/model/AirshipAnalytics;)V", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "getApi", "()Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "setApi", "(Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "callId", "", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "getInmateDao", "()Lcom/forasoft/homewavvisitor/dao/InmateDao;", "setInmateDao", "(Lcom/forasoft/homewavvisitor/dao/InmateDao;)V", "isAnswered", "", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "getNotificationDao", "()Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "setNotificationDao", "(Lcom/forasoft/homewavvisitor/dao/NotificationDao;)V", "ringtone", "Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;", "getRingtone", "()Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;", "setRingtone", "(Lcom/forasoft/homewavvisitor/model/system/RingtoneManager;)V", "cancelCall", "", "onAudioFocusChange", "focusChange", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDeniedCallPermissions", "onDestroy", "onDestroyView", "onPause", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onViewCreated", "view", "showCallStatus", "statusTextResId", "startLiveswitchCall", "pubid", "startTwilioCall", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragment.kt */
public final class IncomingCallDialogFragment extends DialogFragment implements AudioManager.OnAudioFocusChangeListener {
    private static final String CALL = "call";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    @Inject
    public AirshipAnalytics analytics;
    @Inject
    public HomewavApi api;
    /* access modifiers changed from: private */
    public String callId;
    private CompositeDisposable disposable = new CompositeDisposable();
    @Inject
    public InmateDao inmateDao;
    /* access modifiers changed from: private */
    public boolean isAnswered;
    @Inject
    public NotificationDao notificationDao;
    @Inject
    public RingtoneManager ringtone;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Protocol.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Protocol.WEBRTC.ordinal()] = 1;
        }
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

    public void onAudioFocusChange(int i) {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragment$Companion;", "", "()V", "CALL", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragment;", "call", "Lcom/forasoft/homewavvisitor/model/data/Call;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: IncomingCallDialogFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final IncomingCallDialogFragment newInstance(Call call) {
            Intrinsics.checkParameterIsNotNull(call, "call");
            IncomingCallDialogFragment incomingCallDialogFragment = new IncomingCallDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("call", call);
            incomingCallDialogFragment.setArguments(bundle);
            return incomingCallDialogFragment;
        }
    }

    public final HomewavApi getApi() {
        HomewavApi homewavApi = this.api;
        if (homewavApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("api");
        }
        return homewavApi;
    }

    public final void setApi(HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(homewavApi, "<set-?>");
        this.api = homewavApi;
    }

    public final InmateDao getInmateDao() {
        InmateDao inmateDao2 = this.inmateDao;
        if (inmateDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inmateDao");
        }
        return inmateDao2;
    }

    public final void setInmateDao(InmateDao inmateDao2) {
        Intrinsics.checkParameterIsNotNull(inmateDao2, "<set-?>");
        this.inmateDao = inmateDao2;
    }

    public final NotificationDao getNotificationDao() {
        NotificationDao notificationDao2 = this.notificationDao;
        if (notificationDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationDao");
        }
        return notificationDao2;
    }

    public final void setNotificationDao(NotificationDao notificationDao2) {
        Intrinsics.checkParameterIsNotNull(notificationDao2, "<set-?>");
        this.notificationDao = notificationDao2;
    }

    public final AirshipAnalytics getAnalytics() {
        AirshipAnalytics airshipAnalytics = this.analytics;
        if (airshipAnalytics == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Modules.ANALYTICS_MODULE);
        }
        return airshipAnalytics;
    }

    public final void setAnalytics(AirshipAnalytics airshipAnalytics) {
        Intrinsics.checkParameterIsNotNull(airshipAnalytics, "<set-?>");
        this.analytics = airshipAnalytics;
    }

    public final RingtoneManager getRingtone() {
        RingtoneManager ringtoneManager = this.ringtone;
        if (ringtoneManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ringtone");
        }
        return ringtoneManager;
    }

    public final void setRingtone(RingtoneManager ringtoneManager) {
        Intrinsics.checkParameterIsNotNull(ringtoneManager, "<set-?>");
        this.ringtone = ringtoneManager;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.FullScreenDialogStyle);
        Toothpick.inject(this, Toothpick.openScopes(DI.SERVER_SCOPE, this));
        setCancelable(false);
        Object systemService = requireContext().getSystemService("audio");
        if (systemService != null) {
            ((AudioManager) systemService).requestAudioFocus(this, 3, 1);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
    }

    public void onDestroy() {
        super.onDestroy();
        Object systemService = requireContext().getSystemService("audio");
        if (systemService != null) {
            ((AudioManager) systemService).abandonAudioFocus(this);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.media.AudioManager");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_incoming_call, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intent intent;
        Intent intent2;
        Intrinsics.checkParameterIsNotNull(view, "view");
        Blurry.with(getContext()).radius(50).from(BitmapFactory.decodeResource(getResources(), R.drawable.inmate)).into((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_inmate));
        Bundle arguments = getArguments();
        String str = null;
        Call call = arguments != null ? (Call) arguments.getParcelable("call") : null;
        TextView textView = (TextView) view.findViewById(R.id.tv_remaining);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_avatar);
        if (call != null) {
            this.callId = call.getId();
            String inmateCredits = call.getInmateCredits();
            if (inmateCredits == null) {
                inmateCredits = "";
            }
            String str2 = inmateCredits;
            CompositeDisposable compositeDisposable = this.disposable;
            NotificationDao notificationDao2 = this.notificationDao;
            if (notificationDao2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("notificationDao");
            }
            String str3 = this.callId;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            Disposable subscribe = CommonKt.applyAsync(notificationDao2.getCallNotification(str3)).subscribe(new IncomingCallDialogFragment$onViewCreated$1(this), IncomingCallDialogFragment$onViewCreated$2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.getCallN…                   }, {})");
            DisposableKt.plusAssign(compositeDisposable, subscribe);
            CompositeDisposable compositeDisposable2 = this.disposable;
            Observable<R> filter = Observable.interval(0, 5, TimeUnit.SECONDS).flatMap(new IncomingCallDialogFragment$onViewCreated$3(this)).filter(IncomingCallDialogFragment$onViewCreated$4.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(filter, "Observable.interval(0L, …SED\n                    }");
            Disposable subscribe2 = CommonKt.applyAsync(filter).subscribe(new IncomingCallDialogFragment$onViewCreated$5(this), new IncomingCallDialogFragment$onViewCreated$6(this));
            Intrinsics.checkExpressionValueIsNotNull(subscribe2, "Observable.interval(0L, …()\n                    })");
            DisposableKt.plusAssign(compositeDisposable2, subscribe2);
            CompositeDisposable compositeDisposable3 = this.disposable;
            InmateDao inmateDao2 = this.inmateDao;
            if (inmateDao2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("inmateDao");
            }
            Disposable subscribe3 = CommonKt.applyAsync(inmateDao2.getInmate(call.getInmateId())).subscribe(new IncomingCallDialogFragment$onViewCreated$7(this, textView, str2, textView2, imageView, call), IncomingCallDialogFragment$onViewCreated$8.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe3, "inmateDao.getInmate(call…                   }, {})");
            DisposableKt.plusAssign(compositeDisposable3, subscribe3);
            ImageView imageView2 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_accept);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, "iv_accept");
            Sdk27CoroutinesListenersWithCoroutinesKt.onClick$default(imageView2, (CoroutineContext) null, new IncomingCallDialogFragment$onViewCreated$9(this, call, (Continuation) null), 1, (Object) null);
            ImageView imageView3 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_decline);
            Intrinsics.checkExpressionValueIsNotNull(imageView3, "iv_decline");
            Sdk27CoroutinesListenersWithCoroutinesKt.onClick$default(imageView3, (CoroutineContext) null, new IncomingCallDialogFragment$onViewCreated$10(this, (Continuation) null), 1, (Object) null);
        } else {
            dismiss();
        }
        Intent intent3 = new Intent(getContext(), IncomingCallActionsReceiver.class);
        StringBuilder sb = new StringBuilder();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            str = activity.getPackageName();
        }
        sb.append(str);
        sb.append(".HIDE_NOTIFICATION");
        intent3.setAction(sb.toString());
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            activity2.sendBroadcast(intent3);
        }
        FragmentActivity activity3 = getActivity();
        boolean z = false;
        if (!(activity3 == null || (intent2 = activity3.getIntent()) == null)) {
            z = intent2.getBooleanExtra(IncomingCallService.SKIP_CALL_DIALOG, false);
        }
        if (z) {
            FragmentActivity activity4 = getActivity();
            if (!(activity4 == null || (intent = activity4.getIntent()) == null)) {
                intent.removeExtra(IncomingCallService.SKIP_CALL_DIALOG);
            }
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_accept)).performClick();
        }
    }

    /* access modifiers changed from: private */
    public final void showCallStatus(int i) {
        CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_accept));
        CommonKt.hide((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_decline));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_call_status);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_call_status");
        textView.setText(getString(i));
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_call_status));
    }

    public void onResume() {
        Window window;
        super.onResume();
        Dialog dialog = getDialog();
        if (!(dialog == null || (window = dialog.getWindow()) == null)) {
            window.setLayout(-1, -1);
        }
        if (getActivity() instanceof MainActivity) {
            RingtoneManager ringtoneManager = this.ringtone;
            if (ringtoneManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ringtone");
            }
            ringtoneManager.play();
        }
    }

    public void onPause() {
        super.onPause();
        if (getActivity() instanceof MainActivity) {
            RingtoneManager ringtoneManager = this.ringtone;
            if (ringtoneManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ringtone");
            }
            ringtoneManager.stop();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        RingtoneManager ringtoneManager = this.ringtone;
        if (ringtoneManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ringtone");
        }
        ringtoneManager.stop();
        this.disposable.dispose();
        _$_clearFindViewByIdCache();
    }

    public final void startTwilioCall(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "callId");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_PUB_ID);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (CallWrapper) null;
        CompositeDisposable compositeDisposable = this.disposable;
        HomewavApi homewavApi = this.api;
        if (homewavApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("api");
        }
        Single doOnSuccess = HomewavApi.DefaultImpls.getTwilioToken$default(homewavApi, str2, (String) null, 2, (Object) null).flatMap(new IncomingCallDialogFragment$startTwilioCall$1(this, str2)).flatMap(new IncomingCallDialogFragment$startTwilioCall$2(this, str)).doOnSuccess(new IncomingCallDialogFragment$startTwilioCall$3(objectRef));
        Intrinsics.checkExpressionValueIsNotNull(doOnSuccess, "api.getTwilioToken(pubid…s { wrapper = it.body!! }");
        Disposable subscribe = CommonKt.applyAsync(doOnSuccess).subscribe(new IncomingCallDialogFragment$startTwilioCall$4(this, objectRef), new IncomingCallDialogFragment$startTwilioCall$5(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getTwilioToken(pubid…        }, { dismiss() })");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    public final void startLiveswitchCall(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "callId");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_PUB_ID);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (CallWrapper) null;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = (LiveswitchCallDataResponse) null;
        CompositeDisposable compositeDisposable = this.disposable;
        HomewavApi homewavApi = this.api;
        if (homewavApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("api");
        }
        Single doOnSuccess = HomewavApi.DefaultImpls.getLiveswitchCallData$default(homewavApi, str2, (String) null, 2, (Object) null).flatMap(new IncomingCallDialogFragment$startLiveswitchCall$1(this, objectRef2, str2)).flatMap(new IncomingCallDialogFragment$startLiveswitchCall$2(this, str)).doOnSuccess(new IncomingCallDialogFragment$startLiveswitchCall$3(objectRef));
        Intrinsics.checkExpressionValueIsNotNull(doOnSuccess, "api.getLiveswitchCallDat…s { wrapper = it.body!! }");
        Disposable subscribe = CommonKt.applyAsync(doOnSuccess).subscribe(new IncomingCallDialogFragment$startLiveswitchCall$4(this, objectRef, objectRef2), new IncomingCallDialogFragment$startLiveswitchCall$5(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getLiveswitchCallDat…        }, { dismiss() })");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    public final void onDeniedCallPermissions() {
        String str = this.callId;
        if (str != null) {
            cancelCall(str);
        }
    }

    /* access modifiers changed from: private */
    public final void cancelCall(String str) {
        CompositeDisposable compositeDisposable = this.disposable;
        HomewavApi homewavApi = this.api;
        if (homewavApi == null) {
            Intrinsics.throwUninitializedPropertyAccessException("api");
        }
        Disposable subscribe = CommonKt.applyAsync(homewavApi.cancelCall(str)).subscribe(new IncomingCallDialogFragment$cancelCall$1(this), new IncomingCallDialogFragment$cancelCall$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.cancelCall(callId)\n …Loss()\n                })");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        IncomingCallDialogFragmentPermissionsDispatcher.onRequestPermissionsResult(this, i, iArr);
    }
}
