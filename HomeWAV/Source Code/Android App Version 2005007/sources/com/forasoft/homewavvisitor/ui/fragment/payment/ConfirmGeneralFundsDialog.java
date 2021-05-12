package com.forasoft.homewavvisitor.ui.fragment.payment;

import air.HomeWAV.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onConfirmListener", "Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog$OnConfirmListener;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onDetach", "", "setConfirmListener", "listener", "Companion", "OnConfirmListener", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConfirmGeneralFundsDialog.kt */
public final class ConfirmGeneralFundsDialog extends DialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public OnConfirmListener onConfirmListener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog$OnConfirmListener;", "", "onConfirmed", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConfirmGeneralFundsDialog.kt */
    public interface OnConfirmListener {
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog$Companion;", "", "()V", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ConfirmGeneralFundsDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ConfirmGeneralFundsDialog newInstance() {
            return new ConfirmGeneralFundsDialog();
        }
    }

    public void onDetach() {
        super.onDetach();
        this.onConfirmListener = null;
    }

    public final void setConfirmListener(OnConfirmListener onConfirmListener2) {
        Intrinsics.checkParameterIsNotNull(onConfirmListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.onConfirmListener = onConfirmListener2;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_confirm_general_funds, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        TextView textView = (TextView) inflate.findViewById(com.forasoft.homewavvisitor.R.id.tv_message);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.tv_message");
        textView.setText(getString(R.string.confirm_general_funds));
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        AlertDialog create = new AlertDialog.Builder(context).setTitle((int) R.string.confirm_general_funds_title).setView(inflate).setCancelable(false).setPositiveButton((int) R.string.label_agree, (DialogInterface.OnClickListener) new ConfirmGeneralFundsDialog$onCreateDialog$1(this)).setNegativeButton((int) R.string.label_cancel, (DialogInterface.OnClickListener) ConfirmGeneralFundsDialog$onCreateDialog$2.INSTANCE).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(cont…                .create()");
        return create;
    }
}
