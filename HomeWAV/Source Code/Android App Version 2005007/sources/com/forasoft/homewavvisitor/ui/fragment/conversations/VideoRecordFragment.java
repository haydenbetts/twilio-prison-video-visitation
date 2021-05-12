package com.forasoft.homewavvisitor.ui.fragment.conversations;

import air.HomeWAV.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.conversations.VideoRecordPresenter;
import com.forasoft.homewavvisitor.presentation.view.conversations.VideoRecordView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.conversations.CameraView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.io.File;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.bytedeco.ffmpeg.global.avcodec;
import org.jetbrains.anko.Sdk27ServicesKt;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 +2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001+B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\u001a\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010 \u001a\u00020\bH\u0007J \u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0012H\u0016J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020#H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006,"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/VideoRecordFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/conversations/VideoRecordView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "camera", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/CameraView;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/VideoRecordPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/VideoRecordPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/conversations/VideoRecordPresenter;)V", "installModules", "", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "providePresenter", "recordMp4Video", "facing", "", "canRecordVideo", "canTakePhoto", "showMessage", "message", "", "updateRemainingTime", "remaining", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VideoRecordFragment.kt */
public final class VideoRecordFragment extends BaseFragment implements VideoRecordView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String INMATE_ID = "inmate id";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public CameraView camera;
    @InjectPresenter
    public VideoRecordPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/VideoRecordFragment$Companion;", "", "()V", "INMATE_ID", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/VideoRecordFragment;", "inmateId", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: VideoRecordFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final VideoRecordFragment newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "inmateId");
            VideoRecordFragment videoRecordFragment = new VideoRecordFragment();
            Bundle bundle = new Bundle();
            bundle.putString(VideoRecordFragment.INMATE_ID, str);
            videoRecordFragment.setArguments(bundle);
            return videoRecordFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new VideoRecordFragment$installModules$1(this));
    }

    public final VideoRecordPresenter getPresenter() {
        VideoRecordPresenter videoRecordPresenter = this.presenter;
        if (videoRecordPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return videoRecordPresenter;
    }

    public final void setPresenter(VideoRecordPresenter videoRecordPresenter) {
        Intrinsics.checkParameterIsNotNull(videoRecordPresenter, "<set-?>");
        this.presenter = videoRecordPresenter;
    }

    @ProvidePresenter
    public final VideoRecordPresenter providePresenter() {
        Object instance = getScope().getInstance(VideoRecordPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(VideoR…ordPresenter::class.java)");
        return (VideoRecordPresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_video_record, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        CommonKt.hide((Toolbar) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.toolbar));
        FrameLayout frameLayout = (FrameLayout) requireActivity().findViewById(R.id.container);
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "container");
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            ((ConstraintLayout.LayoutParams) layoutParams).topMargin = 0;
            Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_stop);
            Intrinsics.checkExpressionValueIsNotNull(button, "btn_stop");
            button.setOnClickListener(new VideoRecordFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VideoRecordFragment$onViewCreated$2(this)));
            Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_record);
            Intrinsics.checkExpressionValueIsNotNull(button2, "btn_record");
            button2.setOnClickListener(new VideoRecordFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VideoRecordFragment$onViewCreated$3(this)));
            Button button3 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_take_photo);
            Intrinsics.checkExpressionValueIsNotNull(button3, "btn_take_photo");
            button3.setOnClickListener(new VideoRecordFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VideoRecordFragment$onViewCreated$4(this)));
            Button button4 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_rotate);
            Intrinsics.checkExpressionValueIsNotNull(button4, "btn_rotate");
            button4.setOnClickListener(new VideoRecordFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VideoRecordFragment$onViewCreated$5(this)));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void onResume() {
        super.onResume();
        CameraView cameraView = this.camera;
        if (cameraView != null) {
            cameraView.startPreview();
        }
    }

    public void onPause() {
        CameraView cameraView;
        super.onPause();
        try {
            CameraView cameraView2 = this.camera;
            if (cameraView2 != null && cameraView2.isPreviewOn && (cameraView = this.camera) != null) {
                cameraView.stopPreview();
            }
        } catch (Exception unused) {
        }
    }

    public void onDestroyView() {
        FrameLayout frameLayout = (FrameLayout) requireActivity().findViewById(R.id.container);
        TypedValue typedValue = new TypedValue();
        FragmentActivity requireActivity = requireActivity();
        CommonKt.show((Toolbar) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.toolbar));
        requireActivity.getTheme().resolveAttribute(16843499, typedValue, true);
        int i = typedValue.data;
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        int complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(i, resources.getDisplayMetrics());
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "container");
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            ((ConstraintLayout.LayoutParams) layoutParams).topMargin = complexToDimensionPixelSize;
            super.onDestroyView();
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void recordMp4Video(int i, boolean z, boolean z2) {
        CameraView cameraView;
        CameraView cameraView2 = this.camera;
        if (cameraView2 != null) {
            if (!(cameraView2 == null || !cameraView2.recording || (cameraView = this.camera) == null)) {
                cameraView.stopPreview();
            }
            ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_frame)).removeAllViews();
        }
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        String absolutePath = new File(requireContext.getFilesDir(), "message.mp4").getAbsolutePath();
        Context requireContext2 = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
        Display defaultDisplay = Sdk27ServicesKt.getWindowManager(requireContext2).getDefaultDisplay();
        Intrinsics.checkExpressionValueIsNotNull(defaultDisplay, "requireContext().windowManager.defaultDisplay");
        CameraView create = new CameraView.Builder(getContext()).filename(absolutePath).format("mp4").rotation(defaultDisplay.getRotation()).facing(i).imageWidth(640).imageHeight(480).fps(30).videoCodec(27).audioCodec(avcodec.AV_CODEC_ID_AAC).create();
        this.camera = create;
        if (create != null) {
            create.setZOrderMediaOverlay(true);
        }
        CameraView cameraView3 = this.camera;
        if (cameraView3 != null) {
            cameraView3.updateImageDims();
        }
        ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_frame)).removeAllViews();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(0, 0);
        layoutParams.dimensionRatio = "W, 3:4";
        layoutParams.topToTop = 0;
        layoutParams.bottomToBottom = 0;
        layoutParams.startToStart = 0;
        layoutParams.endToEnd = 0;
        ((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.video_frame)).addView(this.camera, layoutParams);
        if (z) {
            CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_record));
        }
        if (z2) {
            CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_take_photo));
        }
        CommonKt.show((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_rotate));
    }

    public void updateRemainingTime(int i) {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_remaining);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_remaining");
        textView.setText(getResources().getString(R.string.label_remaining_time, new Object[]{Integer.valueOf(i)}));
    }

    public void showMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        ContextKt.showSnackbar((Fragment) this, str);
    }

    public boolean onBackPressed() {
        CameraView cameraView = this.camera;
        if (cameraView != null && cameraView.recording) {
            CameraView cameraView2 = this.camera;
            if (cameraView2 != null) {
                cameraView2.stopPreview();
            }
            CameraView cameraView3 = this.camera;
            if (cameraView3 != null) {
                cameraView3.stopRecording();
            }
        }
        VideoRecordPresenter videoRecordPresenter = this.presenter;
        if (videoRecordPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        videoRecordPresenter.onBackPressed();
        return true;
    }
}
