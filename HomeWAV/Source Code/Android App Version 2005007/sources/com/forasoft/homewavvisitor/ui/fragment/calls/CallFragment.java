package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.presentation.extensions.DimensionsKt;
import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\tH\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016J\u001a\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0019\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u001b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0015J\u0018\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0016J\u0010\u0010!\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\t2\u0006\u0010\"\u001a\u00020#H\u0016J\u001a\u0010%\u001a\u00020\t2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\t0'J\b\u0010(\u001a\u00020\tH\u0016J\b\u0010)\u001a\u00020\tH&J\b\u0010*\u001a\u00020\tH&R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/CallFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "()V", "audioManagerHolder", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/AudioManagerHolder;", "reportBugDialog", "Landroidx/appcompat/app/AlertDialog;", "checkCamera", "", "hideWarningMessage", "isCameraUnavailable", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "refreshBackgroundView", "refreshBottomView", "refreshCallMessageViews", "refreshVisitorVideoView", "visitorVideoView", "setRecordingWarningText", "englishResId", "", "spanishResId", "showAdminMessage", "message", "", "showMessage", "showReportBugDialog", "onReportBugClickListener", "Lkotlin/Function1;", "showWarningMessage", "startCall", "stopCall", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallFragment.kt */
public abstract class CallFragment extends BaseFragment implements CallView {
    private HashMap _$_findViewCache;
    private AudioManagerHolder audioManagerHolder;
    /* access modifiers changed from: private */
    public AlertDialog reportBugDialog;

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

    public abstract void startCall();

    public abstract void stopCall();

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        checkCamera();
        FragmentActivity requireActivity = requireActivity();
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(R.id.bnv_main));
        CommonKt.hide((Toolbar) requireActivity.findViewById(R.id.toolbar));
        FrameLayout frameLayout = (FrameLayout) requireActivity().findViewById(air.HomeWAV.R.id.container);
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "container");
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            ((ConstraintLayout.LayoutParams) layoutParams).topMargin = 0;
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            this.audioManagerHolder = new AudioManagerHolder(requireContext);
            FragmentActivity activity = getActivity();
            if (!(activity instanceof MainActivity)) {
                activity = null;
            }
            MainActivity mainActivity = (MainActivity) activity;
            if (mainActivity != null) {
                mainActivity.dismissCallFragment();
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    private final void checkCamera() {
        if (isCameraUnavailable()) {
            new AlertDialog.Builder(requireContext()).setMessage((int) air.HomeWAV.R.string.dialog_unavailable_camera_message).setPositiveButton((int) air.HomeWAV.R.string.label_ok, (DialogInterface.OnClickListener) new CallFragment$checkCamera$1(this)).setCancelable(false).show();
        } else {
            startCall();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0010, code lost:
        return true;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean isCameraUnavailable() {
        /*
            r1 = this;
            r0 = 0
            android.hardware.Camera r0 = (android.hardware.Camera) r0
            android.hardware.Camera r0 = android.hardware.Camera.open()     // Catch:{ RuntimeException -> 0x0010, all -> 0x000e }
            if (r0 == 0) goto L_0x000c
            r0.release()
        L_0x000c:
            r0 = 0
            return r0
        L_0x000e:
            r0 = move-exception
            throw r0
        L_0x0010:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.calls.CallFragment.isCameraUnavailable():boolean");
    }

    public void onResume() {
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setRequestedOrientation(10);
        }
    }

    public void onPause() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setRequestedOrientation(1);
        }
        super.onPause();
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        refreshBackgroundView(configuration);
        refreshBottomView(configuration);
        refreshCallMessageViews(configuration);
    }

    public final void refreshVisitorVideoView(Configuration configuration, View view) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        Intrinsics.checkParameterIsNotNull(view, "visitorVideoView");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            if (configuration.orientation == 1) {
                layoutParams2.height = DimensionsKt.dpToPx(134);
                layoutParams2.width = DimensionsKt.dpToPx(100);
            } else if (configuration.orientation == 2) {
                layoutParams2.height = DimensionsKt.dpToPx(100);
                layoutParams2.width = DimensionsKt.dpToPx(134);
            }
            view.setLayoutParams(layoutParams2);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    private final void refreshBackgroundView(Configuration configuration) {
        View _$_findCachedViewById = _$_findCachedViewById(R.id.top_gradient);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "top_gradient");
        ViewGroup.LayoutParams layoutParams = _$_findCachedViewById.getLayoutParams();
        View _$_findCachedViewById2 = _$_findCachedViewById(R.id.bottom_gradient);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "bottom_gradient");
        ViewGroup.LayoutParams layoutParams2 = _$_findCachedViewById2.getLayoutParams();
        if (configuration.orientation == 1) {
            layoutParams.height = DimensionsKt.dpToPx(100);
            layoutParams2.height = DimensionsKt.dpToPx(170);
        } else if (configuration.orientation == 2) {
            layoutParams.height = DimensionsKt.dpToPx(70);
            layoutParams2.height = DimensionsKt.dpToPx(100);
        }
        View _$_findCachedViewById3 = _$_findCachedViewById(R.id.top_gradient);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById3, "top_gradient");
        _$_findCachedViewById3.setLayoutParams(layoutParams);
        View _$_findCachedViewById4 = _$_findCachedViewById(R.id.bottom_gradient);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById4, "bottom_gradient");
        _$_findCachedViewById4.setLayoutParams(layoutParams2);
    }

    private final void refreshBottomView(Configuration configuration) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tv_remaining_label);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_remaining_label");
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        if (layoutParams != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_remaining_time);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_remaining_time");
            ViewGroup.LayoutParams layoutParams3 = textView2.getLayoutParams();
            if (layoutParams3 != null) {
                ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
                ImageView imageView = (ImageView) _$_findCachedViewById(R.id.btn_end_call);
                Intrinsics.checkExpressionValueIsNotNull(imageView, "btn_end_call");
                ViewGroup.LayoutParams layoutParams5 = imageView.getLayoutParams();
                if (layoutParams5 != null) {
                    ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) layoutParams5;
                    if (configuration.orientation == 1) {
                        layoutParams2.horizontalBias = 0.5f;
                        layoutParams2.bottomMargin = DimensionsKt.dpToPx(102);
                        layoutParams4.horizontalBias = 0.5f;
                        layoutParams6.bottomMargin = DimensionsKt.dpToPx(20);
                    } else if (configuration.orientation == 2) {
                        layoutParams2.horizontalBias = 0.0f;
                        layoutParams2.bottomMargin = DimensionsKt.dpToPx(20);
                        layoutParams4.horizontalBias = 0.0f;
                        layoutParams6.bottomMargin = DimensionsKt.dpToPx(10);
                    }
                    TextView textView3 = (TextView) _$_findCachedViewById(R.id.tv_remaining_time);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_remaining_time");
                    textView3.setLayoutParams(layoutParams2);
                    TextView textView4 = (TextView) _$_findCachedViewById(R.id.tv_remaining_time);
                    Intrinsics.checkExpressionValueIsNotNull(textView4, "tv_remaining_time");
                    textView4.setLayoutParams(layoutParams4);
                    ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.btn_end_call);
                    Intrinsics.checkExpressionValueIsNotNull(imageView2, "btn_end_call");
                    imageView2.setLayoutParams(layoutParams6);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    private final void refreshCallMessageViews(Configuration configuration) {
        float f;
        float f2 = 0.0f;
        if (configuration.orientation == 1) {
            f2 = 0.5f;
            f = 0.7f;
        } else if (configuration.orientation == 2) {
            f2 = 0.05f;
            f = 0.35f;
        } else {
            f = 0.0f;
        }
        CardView cardView = (CardView) _$_findCachedViewById(R.id.cv_admin);
        Intrinsics.checkExpressionValueIsNotNull(cardView, "cv_admin");
        ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
        if (layoutParams != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.horizontalBias = f2;
            layoutParams2.matchConstraintPercentWidth = f;
            CardView cardView2 = (CardView) _$_findCachedViewById(R.id.cv_admin);
            Intrinsics.checkExpressionValueIsNotNull(cardView2, "cv_admin");
            cardView2.setLayoutParams(layoutParams2);
            CardView cardView3 = (CardView) _$_findCachedViewById(R.id.cv_warning);
            Intrinsics.checkExpressionValueIsNotNull(cardView3, "cv_warning");
            ViewGroup.LayoutParams layoutParams3 = cardView3.getLayoutParams();
            if (layoutParams3 != null) {
                ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
                layoutParams4.horizontalBias = f2;
                layoutParams4.matchConstraintPercentWidth = f;
                CardView cardView4 = (CardView) _$_findCachedViewById(R.id.cv_warning);
                Intrinsics.checkExpressionValueIsNotNull(cardView4, "cv_warning");
                cardView4.setLayoutParams(layoutParams4);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void onDestroyView() {
        FrameLayout frameLayout = (FrameLayout) requireActivity().findViewById(air.HomeWAV.R.id.container);
        TypedValue typedValue = new TypedValue();
        FragmentActivity requireActivity = requireActivity();
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(R.id.bnv_main));
        CommonKt.show((Toolbar) requireActivity.findViewById(R.id.toolbar));
        requireActivity.getTheme().resolveAttribute(16843499, typedValue, true);
        int i = typedValue.data;
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        int complexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(i, resources.getDisplayMetrics());
        Intrinsics.checkExpressionValueIsNotNull(frameLayout, "container");
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            ((ConstraintLayout.LayoutParams) layoutParams).topMargin = complexToDimensionPixelSize;
            AlertDialog alertDialog = this.reportBugDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            AudioManagerHolder audioManagerHolder2 = this.audioManagerHolder;
            if (audioManagerHolder2 != null) {
                audioManagerHolder2.destroy();
            }
            super.onDestroyView();
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void setRecordingWarningText(int i, int i2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new CallFragment$setRecordingWarningText$1(this, i, i2));
        }
    }

    public void showAdminMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new CallFragment$showAdminMessage$1(this, str));
        }
    }

    public void showWarningMessage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new CallFragment$showWarningMessage$1(this));
        }
    }

    public void hideWarningMessage() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new CallFragment$hideWarningMessage$1(this));
        }
    }

    public void showMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        ContextKt.showSnackbar((Fragment) this, str);
    }

    public final void showReportBugDialog(Function1<? super String, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "onReportBugClickListener");
        View inflate = LayoutInflater.from(getContext()).inflate(air.HomeWAV.R.layout.bug_report_radio_group, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "contentView");
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.cb_mic);
        CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.cb_speaker);
        CheckBox checkBox3 = (CheckBox) inflate.findViewById(R.id.cb_connectivity);
        CheckBox checkBox4 = (CheckBox) inflate.findViewById(R.id.cb_video);
        CompoundButton.OnCheckedChangeListener callFragment$showReportBugDialog$onCheckedChangeListener$1 = new CallFragment$showReportBugDialog$onCheckedChangeListener$1(this, checkBox, checkBox2, checkBox3, checkBox4);
        checkBox.setOnCheckedChangeListener(callFragment$showReportBugDialog$onCheckedChangeListener$1);
        checkBox2.setOnCheckedChangeListener(callFragment$showReportBugDialog$onCheckedChangeListener$1);
        checkBox3.setOnCheckedChangeListener(callFragment$showReportBugDialog$onCheckedChangeListener$1);
        checkBox4.setOnCheckedChangeListener(callFragment$showReportBugDialog$onCheckedChangeListener$1);
        AlertDialog show = new AlertDialog.Builder(requireContext()).setView(inflate).setPositiveButton((CharSequence) getString(air.HomeWAV.R.string.button_report), (DialogInterface.OnClickListener) new CallFragment$showReportBugDialog$1(checkBox, checkBox2, checkBox3, checkBox4, function1)).show();
        this.reportBugDialog = show;
        if (show == null) {
            Intrinsics.throwNpe();
        }
        Button button = show.getButton(-1);
        Intrinsics.checkExpressionValueIsNotNull(button, "reportBugDialog!!.getBut…rtDialog.BUTTON_POSITIVE)");
        button.setEnabled(false);
    }
}
