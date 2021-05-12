package com.forasoft.homewavvisitor.ui.fragment;

import air.HomeWAV.R;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.DialogFragment;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/NetworkErrorFragment;", "Landroidx/fragment/app/DialogFragment;", "()V", "disposable", "Lio/reactivex/disposables/Disposable;", "createView", "Landroid/view/View;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onDestroy", "onResume", "onStop", "setWidth", "percent", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NetworkErrorFragment.kt */
public final class NetworkErrorFragment extends DialogFragment {
    private HashMap _$_findViewCache;
    private Disposable disposable;

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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.FullScreenDialogStyle);
        setCancelable(false);
        this.disposable = Observable.timer(10, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new NetworkErrorFragment$onCreate$1(this));
    }

    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog create = new AlertDialog.Builder(requireContext()).setCancelable(false).setView(createView()).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(requ…                .create()");
        return create;
    }

    public void onResume() {
        super.onResume();
        setWidth(0.9f);
    }

    public void onStop() {
        dismissAllowingStateLoss();
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
        Disposable disposable2 = this.disposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
    }

    private final void setWidth(float f) {
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        Point point = new Point();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager windowManager = window.getWindowManager();
        Intrinsics.checkExpressionValueIsNotNull(windowManager, "window!!.windowManager");
        windowManager.getDefaultDisplay().getSize(point);
        window.setLayout((int) (((float) point.x) * f), -2);
        window.setGravity(17);
    }

    private final View createView() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_network_error, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(com.forasoft.homewavvisitor.R.id.pb);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "view.pb");
        DrawableCompat.setTint(progressBar.getIndeterminateDrawable(), ContextCompat.getColor(requireContext(), R.color.colorAccent));
        return inflate;
    }
}
