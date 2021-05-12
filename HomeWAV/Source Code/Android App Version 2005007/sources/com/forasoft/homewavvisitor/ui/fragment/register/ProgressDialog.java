package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u001a\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ProgressDialog.kt */
public final class ProgressDialog extends DialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EXTRA_TITLE = "extraTitle";
    public static final String TAG = "ProgressDialog";
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog$Companion;", "", "()V", "EXTRA_TITLE", "", "TAG", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ProgressDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.dialog_progress, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        String str;
        Window window;
        Intrinsics.checkParameterIsNotNull(view, "view");
        Dialog dialog = getDialog();
        if (!(dialog == null || (window = dialog.getWindow()) == null)) {
            window.requestFeature(1);
        }
        super.onViewCreated(view, bundle);
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setCanceledOnTouchOutside(false);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_title);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_title");
        Bundle arguments = getArguments();
        if (arguments == null || (str = arguments.getString(EXTRA_TITLE)) == null) {
            str = "";
        }
        textView.setText(str);
    }

    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        Point point = new Point();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        WindowManager windowManager = window.getWindowManager();
        Intrinsics.checkExpressionValueIsNotNull(windowManager, "window!!.windowManager");
        windowManager.getDefaultDisplay().getSize(point);
        window.setLayout((int) (((float) point.x) * 0.9f), -2);
        window.setGravity(17);
    }
}
