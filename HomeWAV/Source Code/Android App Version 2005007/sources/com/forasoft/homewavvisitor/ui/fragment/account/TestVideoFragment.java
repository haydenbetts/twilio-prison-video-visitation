package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.TestVideoPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.TestVideoView;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.CameraView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.bytedeco.ffmpeg.global.avcodec;
import org.jetbrains.anko.Sdk27ServicesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\nH\u0016J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\r\u0010#\u001a\u00020\u001aH\u0001¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u001aH\u0001¢\u0006\u0002\b&J\b\u0010'\u001a\u00020\u001aH\u0016J\r\u0010(\u001a\u00020\u001aH\u0001¢\u0006\u0002\b)J\r\u0010*\u001a\u00020\u001aH\u0001¢\u0006\u0002\b+J\u0010\u0010,\u001a\u00020\n2\u0006\u0010-\u001a\u00020.H\u0016J\b\u0010/\u001a\u00020\u001aH\u0016J-\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u0002022\u000e\u00103\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u0017042\u0006\u00105\u001a\u000206H\u0016¢\u0006\u0002\u00107J\b\u00108\u001a\u00020\u001aH\u0016J\u001a\u00109\u001a\u00020\u001a2\u0006\u0010:\u001a\u00020\u001e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010;\u001a\u00020\u001aH\u0002J\r\u0010<\u001a\u00020\u001aH\u0001¢\u0006\u0002\b=J\b\u0010>\u001a\u00020\u0010H\u0007J\b\u0010?\u001a\u00020\u001aH\u0002J\r\u0010@\u001a\u00020\u001aH\u0001¢\u0006\u0002\bAJ\b\u0010B\u001a\u00020\u001aH\u0002J\b\u0010C\u001a\u00020\u001aH\u0002J\b\u0010D\u001a\u00020\u001aH\u0016J\u0010\u0010E\u001a\u00020\u001a2\u0006\u0010F\u001a\u000202H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/TestVideoFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "Lcom/forasoft/homewavvisitor/presentation/view/account/TestVideoView;", "()V", "camera", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/CameraView;", "dialog", "Landroidx/appcompat/app/AlertDialog;", "dialogShowed", "", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "micLevelRecorder", "Landroid/media/MediaRecorder;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/TestVideoPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/TestVideoPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/TestVideoPresenter;)V", "stopTryStarted", "testFilePath", "", "onBackPressed", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDeniedPermissionsToCameraAndMicrophone", "onDeniedPermissionsToCameraAndMicrophone$app_release", "onDeniedPermissionsToMicrophone", "onDeniedPermissionsToMicrophone$app_release", "onDestroyView", "onNeverAskCameraAndMicrophone", "onNeverAskCameraAndMicrophone$app_release", "onNeverAskMicrophone", "onNeverAskMicrophone$app_release", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onViewCreated", "view", "openSpeedtest", "prepareMicRecorder", "prepareMicRecorder$app_release", "providePresenter", "releaseMicRecorder", "setupCamera", "setupCamera$app_release", "showDeniedPermissionsDialog", "startMicLevelShowing", "stopRecording", "updateRemainingTime", "remaining", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TestVideoFragment.kt */
public final class TestVideoFragment extends BaseFragment implements OnBackButtonPressedListener, TestVideoView {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public CameraView camera;
    private AlertDialog dialog;
    private boolean dialogShowed;
    private CompositeDisposable disposables = new CompositeDisposable();
    /* access modifiers changed from: private */
    public MediaRecorder micLevelRecorder;
    @InjectPresenter
    public TestVideoPresenter presenter;
    private boolean stopTryStarted;
    private String testFilePath;

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

    public final TestVideoPresenter getPresenter() {
        TestVideoPresenter testVideoPresenter = this.presenter;
        if (testVideoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return testVideoPresenter;
    }

    public final void setPresenter(TestVideoPresenter testVideoPresenter) {
        Intrinsics.checkParameterIsNotNull(testVideoPresenter, "<set-?>");
        this.presenter = testVideoPresenter;
    }

    @ProvidePresenter
    public final TestVideoPresenter providePresenter() {
        Object instance = getScope().getInstance(TestVideoPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(TestVideoPresenter::class.java)");
        return (TestVideoPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasOptionsMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_test_video, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.test_connection_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "test_connection_button");
        button.setOnClickListener(new TestVideoFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TestVideoFragment$onViewCreated$1(this)));
        Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.test_video_button);
        Intrinsics.checkExpressionValueIsNotNull(button2, "test_video_button");
        button2.setOnClickListener(new TestVideoFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TestVideoFragment$onViewCreated$2(this)));
        Button button3 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stop_video_button);
        Intrinsics.checkExpressionValueIsNotNull(button3, "stop_video_button");
        button3.setOnClickListener(new TestVideoFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TestVideoFragment$onViewCreated$3(this)));
        Button button4 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.see_result_button);
        Intrinsics.checkExpressionValueIsNotNull(button4, "see_result_button");
        button4.setOnClickListener(new TestVideoFragment$inlined$sam$i$android_view_View_OnClickListener$0(new TestVideoFragment$onViewCreated$4(this)));
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.mic_level);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "mic_level");
        progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            MainActivity mainActivity = (MainActivity) activity;
            ActionBar supportActionBar = mainActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_test_connection);
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setDisplayShowHomeEnabled(true);
            }
            CommonKt.hide((BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public void onDestroyView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            CommonKt.show((BottomNavigationViewEx) ((MainActivity) activity)._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
            super.onDestroyView();
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public final void setupCamera$app_release() {
        CameraView cameraView;
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        CameraView cameraView2 = this.camera;
        if (cameraView2 != null) {
            if (!(cameraView2 == null || !cameraView2.recording || (cameraView = this.camera) == null)) {
                cameraView.stopPreview();
            }
            ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.camera_preview)).removeAllViews();
        }
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        this.testFilePath = new File(requireContext.getFilesDir(), "test.mp4").getAbsolutePath();
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        Display defaultDisplay = Sdk27ServicesKt.getWindowManager(requireContext2).getDefaultDisplay();
        Intrinsics.checkExpressionValueIsNotNull(defaultDisplay, "requireContext().windowManager.defaultDisplay");
        CameraView create = new CameraView.Builder(getContext()).filename(this.testFilePath).format("mp4").rotation(defaultDisplay.getRotation()).facing(1).imageWidth(640).imageHeight(480).fps(30).videoCodec(27).audioCodec(avcodec.AV_CODEC_ID_AAC).videoBitrate(2000000).create();
        this.camera = create;
        if (create != null) {
            create.setZOrderMediaOverlay(true);
        }
        ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.camera_preview)).removeAllViews();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams.dimensionRatio = "W, 3:4";
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        layoutParams.startToStart = 0;
        layoutParams.endToEnd = 0;
        ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.camera_preview)).addView(this.camera, layoutParams);
        CameraView cameraView3 = this.camera;
        if (cameraView3 != null) {
            cameraView3.updateImageDims();
        }
    }

    public final void prepareMicRecorder$app_release() {
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        if (this.micLevelRecorder == null) {
            StringBuilder sb = new StringBuilder();
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            File externalCacheDir = requireContext.getExternalCacheDir();
            if (externalCacheDir == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(externalCacheDir, "requireContext().externalCacheDir!!");
            sb.append(externalCacheDir.getAbsolutePath());
            sb.append("/test.3gp");
            String sb2 = sb.toString();
            MediaRecorder mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(1);
            mediaRecorder.setOutputFormat(1);
            mediaRecorder.setAudioEncoder(1);
            mediaRecorder.setOutputFile(sb2);
            mediaRecorder.prepare();
            mediaRecorder.start();
            this.micLevelRecorder = mediaRecorder;
        }
    }

    private final void startMicLevelShowing() {
        this.disposables.clear();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        this.disposables = compositeDisposable;
        Disposable subscribe = Observable.interval(0, 100, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TestVideoFragment$startMicLevelShowing$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0, 1…plitude\n                }");
        DisposableKt.plusAssign(compositeDisposable, subscribe);
    }

    /* access modifiers changed from: private */
    public final void releaseMicRecorder() {
        MediaRecorder mediaRecorder = this.micLevelRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            this.micLevelRecorder = null;
        }
    }

    public void onResume() {
        super.onResume();
        if (!this.stopTryStarted) {
            TestVideoFragmentPermissionsDispatcher.setupCameraWithPermissionCheck(this);
            TestVideoFragmentPermissionsDispatcher.prepareMicRecorderWithPermissionCheck(this);
            CameraView cameraView = this.camera;
            if (cameraView != null) {
                cameraView.startPreview();
            }
            startMicLevelShowing();
        }
    }

    public void onPause() {
        CameraView cameraView;
        CameraView cameraView2;
        super.onPause();
        try {
            CameraView cameraView3 = this.camera;
            if (!(cameraView3 == null || !cameraView3.isPreviewOn || (cameraView2 = this.camera) == null)) {
                cameraView2.stopPreview();
            }
            CameraView cameraView4 = this.camera;
            if (!(cameraView4 == null || !cameraView4.recording || (cameraView = this.camera) == null)) {
                cameraView.stopRecording();
            }
            releaseMicRecorder();
            this.disposables.clear();
        } catch (Exception unused) {
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "permissions");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        super.onRequestPermissionsResult(i, strArr, iArr);
        TestVideoFragmentPermissionsDispatcher.onRequestPermissionsResult(this, i, iArr);
    }

    /* access modifiers changed from: private */
    public final void openSpeedtest() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + "org.zwanoo.android.speedtest"));
            intent.setPackage("com.android.vending");
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + "org.zwanoo.android.speedtest")));
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            return onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onBackPressed() {
        TestVideoPresenter testVideoPresenter = this.presenter;
        if (testVideoPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        testVideoPresenter.onBackPressed();
        return true;
    }

    public void updateRemainingTime(int i) {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_remaining);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_remaining");
        textView.setText(getResources().getString(R.string.label_remaining_time, new Object[]{Integer.valueOf(i)}));
    }

    public void stopRecording() {
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stop_video_button);
        Intrinsics.checkExpressionValueIsNotNull(button, "stop_video_button");
        button.setVisibility(8);
        Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.see_result_button);
        Intrinsics.checkExpressionValueIsNotNull(button2, "see_result_button");
        button2.setVisibility(0);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_remaining);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_remaining");
        textView.setVisibility(8);
        CameraView cameraView = this.camera;
        if (cameraView != null) {
            cameraView.stopRecording();
        }
        CameraView cameraView2 = this.camera;
        if (cameraView2 != null) {
            cameraView2.stopPreview();
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.camera_preview);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "camera_preview");
        constraintLayout.setVisibility(8);
        ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.camera_preview)).removeAllViews();
        ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_holder);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout2, "video_holder");
        constraintLayout2.setVisibility(0);
        ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.result_video)).setVideoURI(Uri.parse(this.testFilePath));
        ((VideoView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.result_video)).seekTo(200);
        TestVideoFragmentPermissionsDispatcher.prepareMicRecorderWithPermissionCheck(this);
        startMicLevelShowing();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.mic_level_layout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "mic_level_layout");
        linearLayout.setVisibility(0);
    }

    public final void onDeniedPermissionsToCameraAndMicrophone$app_release() {
        this.stopTryStarted = true;
        showDeniedPermissionsDialog();
    }

    public final void onNeverAskCameraAndMicrophone$app_release() {
        this.stopTryStarted = true;
        showDeniedPermissionsDialog();
    }

    public final void onDeniedPermissionsToMicrophone$app_release() {
        this.stopTryStarted = true;
        showDeniedPermissionsDialog();
    }

    public final void onNeverAskMicrophone$app_release() {
        this.stopTryStarted = true;
        showDeniedPermissionsDialog();
    }

    private final void showDeniedPermissionsDialog() {
        if (!this.dialogShowed) {
            this.dialogShowed = true;
            this.dialog = new AlertDialog.Builder(requireContext()).setMessage((int) R.string.allow_camera_and_microphone).setPositiveButton((CharSequence) "Ok", (DialogInterface.OnClickListener) new TestVideoFragment$showDeniedPermissionsDialog$1(this)).setCancelable(false).show();
        }
    }
}
