package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.forasoft.homewavvisitor.model.data.Contacts;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001a\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\"\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/ContactUsDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "onDismissListener", "Lkotlin/Function0;", "", "getOnDismissListener", "()Lkotlin/jvm/functions/Function0;", "setOnDismissListener", "(Lkotlin/jvm/functions/Function0;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ContactUsDialog.kt */
public final class ContactUsDialog extends BottomSheetDialogFragment {
    private static final String ARG_REFUND_EMAIL = "arg_refund_email";
    private static final String ARG_SUPPORT_EMAIL = "arg_support_email";
    private static final String ARG_SUPPORT_PHONE = "arg_support_phone";
    private static final String ARG_WORK_HOURS = "arg_work_hours";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private Function0<Unit> onDismissListener;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/ContactUsDialog$Companion;", "", "()V", "ARG_REFUND_EMAIL", "", "ARG_SUPPORT_EMAIL", "ARG_SUPPORT_PHONE", "ARG_WORK_HOURS", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/account/ContactUsDialog;", "contacts", "Lcom/forasoft/homewavvisitor/model/data/Contacts;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ContactUsDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ContactUsDialog newInstance(Contacts contacts) {
            Intrinsics.checkParameterIsNotNull(contacts, "contacts");
            ContactUsDialog contactUsDialog = new ContactUsDialog();
            Bundle bundle = new Bundle();
            bundle.putString(ContactUsDialog.ARG_REFUND_EMAIL, contacts.getRefundEmail());
            bundle.putString(ContactUsDialog.ARG_SUPPORT_EMAIL, contacts.getSupportEmail());
            bundle.putString(ContactUsDialog.ARG_SUPPORT_PHONE, contacts.getSupportPhone());
            bundle.putString(ContactUsDialog.ARG_WORK_HOURS, contacts.getWorkHours());
            contactUsDialog.setArguments(bundle);
            return contactUsDialog;
        }
    }

    public final Function0<Unit> getOnDismissListener() {
        return this.onDismissListener;
    }

    public final void setOnDismissListener(Function0<Unit> function0) {
        this.onDismissListener = function0;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.dialog_contact_us, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        String string = requireArguments().getString(ARG_REFUND_EMAIL);
        if (string == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(string, "requireArguments().getString(ARG_REFUND_EMAIL)!!");
        String string2 = requireArguments().getString(ARG_SUPPORT_EMAIL);
        if (string2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(string2, "requireArguments().getString(ARG_SUPPORT_EMAIL)!!");
        String string3 = requireArguments().getString(ARG_SUPPORT_PHONE);
        if (string3 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(string3, "requireArguments().getString(ARG_SUPPORT_PHONE)!!");
        String string4 = requireArguments().getString(ARG_WORK_HOURS);
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_close);
        Intrinsics.checkExpressionValueIsNotNull(imageButton, "btn_close");
        imageButton.setOnClickListener(new ContactUsDialog$inlined$sam$i$android_view_View_OnClickListener$0(new ContactUsDialog$onViewCreated$1(this)));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView6);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textView6");
        textView.setText(getResources().getString(R.string.customer_support_hours, new Object[]{string4}));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView5);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "textView5");
        textView2.setOnClickListener(new ContactUsDialog$inlined$sam$i$android_view_View_OnClickListener$0(new ContactUsDialog$onViewCreated$2(this, string3)));
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView5);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "textView5");
        textView3.setText("Call " + string3);
        TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView3);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "textView3");
        textView4.setOnClickListener(new ContactUsDialog$inlined$sam$i$android_view_View_OnClickListener$0(new ContactUsDialog$onViewCreated$3(this, string2)));
        TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView3);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "textView3");
        textView5.setText("Email " + string2);
        TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView);
        Intrinsics.checkExpressionValueIsNotNull(textView6, "textView");
        textView6.setOnClickListener(new ContactUsDialog$inlined$sam$i$android_view_View_OnClickListener$0(new ContactUsDialog$onViewCreated$4(this, string)));
        TextView textView7 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textView);
        Intrinsics.checkExpressionValueIsNotNull(textView7, "textView");
        textView7.setText("Email " + string);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Intrinsics.checkParameterIsNotNull(dialogInterface, "dialog");
        super.onDismiss(dialogInterface);
        Function0<Unit> function0 = this.onDismissListener;
        if (function0 != null) {
            Unit invoke = function0.invoke();
        }
    }
}
