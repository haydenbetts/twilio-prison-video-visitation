package com.forasoft.homewavvisitor.ui.fragment;

import air.HomeWAV.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.forasoft.homewavvisitor.model.data.account.Settings;
import com.forasoft.homewavvisitor.toothpick.DI;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0002\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/ConfirmDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "confirmDialogListener", "Lcom/forasoft/homewavvisitor/ui/fragment/ConfirmDialog$ConfirmDialogListener;", "onAttach", "", "context", "Landroid/content/Context;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "Companion", "ConfirmDialogListener", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConfirmDialog.kt */
public final class ConfirmDialog extends DialogFragment {
    private static final String ARG_MESSAGE = "arg_message";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public ConfirmDialogListener confirmDialogListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/ConfirmDialog$ConfirmDialogListener;", "", "onCanceled", "", "onConfirmed", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConfirmDialog.kt */
    public interface ConfirmDialogListener {
        void onCanceled();

        void onConfirmed();
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

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/ConfirmDialog$Companion;", "", "()V", "ARG_MESSAGE", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/ConfirmDialog;", "message", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConfirmDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConfirmDialog newInstance(String str) {
            Intrinsics.checkParameterIsNotNull(str, "message");
            ConfirmDialog confirmDialog = new ConfirmDialog();
            Bundle bundle = new Bundle();
            bundle.putString(ConfirmDialog.ARG_MESSAGE, str);
            confirmDialog.setArguments(bundle);
            return confirmDialog;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0018, code lost:
        r3 = r3.getChildFragmentManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttach(android.content.Context r3) {
        /*
            r2 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            super.onAttach(r3)
            com.forasoft.homewavvisitor.ui.activity.MainActivity r3 = (com.forasoft.homewavvisitor.ui.activity.MainActivity) r3
            androidx.fragment.app.FragmentManager r3 = r3.getSupportFragmentManager()
            r0 = 2131296444(0x7f0900bc, float:1.8210805E38)
            androidx.fragment.app.Fragment r3 = r3.findFragmentById(r0)
            r1 = 0
            if (r3 == 0) goto L_0x0023
            androidx.fragment.app.FragmentManager r3 = r3.getChildFragmentManager()
            if (r3 == 0) goto L_0x0023
            androidx.fragment.app.Fragment r3 = r3.findFragmentById(r0)
            goto L_0x0024
        L_0x0023:
            r3 = r1
        L_0x0024:
            boolean r0 = r3 instanceof com.forasoft.homewavvisitor.ui.fragment.ConfirmDialog.ConfirmDialogListener
            if (r0 != 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r1 = r3
        L_0x002a:
            com.forasoft.homewavvisitor.ui.fragment.ConfirmDialog$ConfirmDialogListener r1 = (com.forasoft.homewavvisitor.ui.fragment.ConfirmDialog.ConfirmDialogListener) r1
            r2.confirmDialogListener = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.ConfirmDialog.onAttach(android.content.Context):void");
    }

    public void onDetach() {
        super.onDetach();
        this.confirmDialogListener = null;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String string = requireArguments().getString(ARG_MESSAGE);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        TextView textView = (TextView) inflate.findViewById(com.forasoft.homewavvisitor.R.id.tv_message);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.tv_message");
        textView.setText(string);
        ((CheckBox) inflate.findViewById(com.forasoft.homewavvisitor.R.id.checkBox)).setOnCheckedChangeListener(new ConfirmDialog$onCreateDialog$1((Settings) Toothpick.openScope(DI.SERVER_SCOPE).getInstance(Settings.class)));
        setCancelable(false);
        AlertDialog create = new AlertDialog.Builder(requireContext()).setTitle((int) R.string.dialog_confirm_title).setView(inflate).setPositiveButton((int) R.string.label_send, (DialogInterface.OnClickListener) new ConfirmDialog$onCreateDialog$2(this)).setNegativeButton((int) R.string.label_cancel, (DialogInterface.OnClickListener) new ConfirmDialog$onCreateDialog$3(this)).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(requ…                .create()");
        return create;
    }
}
