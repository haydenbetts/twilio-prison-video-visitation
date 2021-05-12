package com.forasoft.homewavvisitor.ui.fragment.auth;

import air.HomeWAV.R;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.auth.VerifyMethodPresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyMethodView;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.Mask;
import com.redmadrobot.inputmask.model.CaretString;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001c\u001a\u00020\tH\u0007J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\u0010\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/auth/VerifyMethodFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/VerifyMethodView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "email", "", "phone", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyMethodPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyMethodPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/auth/VerifyMethodPresenter;)V", "onBackPressed", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onViewCreated", "view", "providePresenter", "showChangeEmailDialog", "showChangePhoneDialog", "showEmail", "showPhone", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VerifyMethodFragment.kt */
public final class VerifyMethodFragment extends BaseFragment implements VerifyMethodView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    private String email;
    private String phone;
    @InjectPresenter
    public VerifyMethodPresenter presenter;

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

    public final VerifyMethodPresenter getPresenter() {
        VerifyMethodPresenter verifyMethodPresenter = this.presenter;
        if (verifyMethodPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return verifyMethodPresenter;
    }

    public final void setPresenter(VerifyMethodPresenter verifyMethodPresenter) {
        Intrinsics.checkParameterIsNotNull(verifyMethodPresenter, "<set-?>");
        this.presenter = verifyMethodPresenter;
    }

    @ProvidePresenter
    public final VerifyMethodPresenter providePresenter() {
        Object instance = getScope().getInstance(VerifyMethodPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Verify…hodPresenter::class.java)");
        return (VerifyMethodPresenter) instance;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_verify_method, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toolbar);
        toolbar.setTitle((CharSequence) "Verify Account");
        MenuItem add = toolbar.getMenu().add(R.string.label_close);
        add.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_close));
        add.setShowAsAction(2);
        toolbar.setOnMenuItemClickListener(new VerifyMethodFragment$onViewCreated$$inlined$apply$lambda$1(this));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_edit_phone);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_edit_phone");
        textView.setOnClickListener(new VerifyMethodFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VerifyMethodFragment$onViewCreated$2(this)));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_edit_email);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_edit_email");
        textView2.setOnClickListener(new VerifyMethodFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VerifyMethodFragment$onViewCreated$3(this)));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_send);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_send");
        button.setOnClickListener(new VerifyMethodFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VerifyMethodFragment$onViewCreated$4(this)));
        if (getActivity() instanceof CreateAccountActivity) {
            CommonKt.hide(requireActivity().findViewById(R.id.stepper));
            CommonKt.hide(requireActivity().findViewById(R.id.toolbar));
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (getActivity() instanceof CreateAccountActivity) {
            CommonKt.show(requireActivity().findViewById(R.id.stepper));
            CommonKt.show(requireActivity().findViewById(R.id.toolbar));
        }
        _$_clearFindViewByIdCache();
    }

    public void showPhone(String str) {
        Intrinsics.checkParameterIsNotNull(str, ShippingInfoWidget.PHONE_FIELD);
        this.phone = str;
        Mask.Result apply = new Mask("+1 ([000])-[000]-[0000" + StringsKt.repeat("9", str.length() - 10) + ']').apply(new CaretString(str, str.length(), (CaretString.CaretGravity) null, 4, (DefaultConstructorMarker) null), true);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_phone);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_phone");
        textView.setText(apply.getFormattedText().getString());
    }

    public void showEmail(String str) {
        Intrinsics.checkParameterIsNotNull(str, "email");
        this.email = str;
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_email);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_email");
        textView.setText(str);
    }

    public boolean onBackPressed() {
        VerifyMethodPresenter verifyMethodPresenter = this.presenter;
        if (verifyMethodPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        verifyMethodPresenter.onBackPressed();
        return true;
    }

    /* access modifiers changed from: private */
    public final void showChangeEmailDialog() {
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_input, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "contentView");
        TextInputEditText textInputEditText = (TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText, "contentView.et_input");
        String str = this.email;
        if (str == null) {
            str = "";
        }
        textInputEditText.setText(new SpannableStringBuilder(str));
        AlertDialog create = new AlertDialog.Builder(requireContext()).setView(inflate).setPositiveButton((int) R.string.label_ok, (DialogInterface.OnClickListener) new VerifyMethodFragment$showChangeEmailDialog$dialog$1(this, inflate)).setNegativeButton((int) R.string.label_cancel, (DialogInterface.OnClickListener) VerifyMethodFragment$showChangeEmailDialog$dialog$2.INSTANCE).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(requ…                .create()");
        TextInputEditText textInputEditText2 = (TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText2, "et_input");
        textInputEditText2.setInputType(32);
        TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(com.forasoft.homewavvisitor.R.id.input_wrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "input_wrapper");
        textInputLayout.setHint(inflate.getResources().getString(R.string.hint_email));
        create.show();
    }

    /* access modifiers changed from: private */
    public final void showChangePhoneDialog() {
        View inflate = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_input, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "contentView");
        TextInputEditText textInputEditText = (TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText, "contentView.et_input");
        String str = this.phone;
        if (str == null) {
            str = "";
        }
        textInputEditText.setText(new SpannableStringBuilder(str));
        AlertDialog create = new AlertDialog.Builder(requireContext()).setView(inflate).setPositiveButton((int) R.string.label_ok, (DialogInterface.OnClickListener) new VerifyMethodFragment$showChangePhoneDialog$dialog$1(this, inflate)).setNegativeButton((int) R.string.label_cancel, (DialogInterface.OnClickListener) VerifyMethodFragment$showChangePhoneDialog$dialog$2.INSTANCE).create();
        Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(requ…                .create()");
        TextInputEditText textInputEditText2 = (TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText2, "et_input");
        MaskedTextChangedListener maskedTextChangedListener = new MaskedTextChangedListener("+1 ([000]) [000]-[0000]", textInputEditText2);
        ((TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input)).addTextChangedListener(maskedTextChangedListener);
        TextInputEditText textInputEditText3 = (TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText3, "et_input");
        textInputEditText3.setOnFocusChangeListener(maskedTextChangedListener);
        TextInputEditText textInputEditText4 = (TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input);
        Intrinsics.checkExpressionValueIsNotNull(textInputEditText4, "et_input");
        textInputEditText4.setInputType(3);
        ((TextInputEditText) inflate.findViewById(com.forasoft.homewavvisitor.R.id.et_input)).requestFocus();
        TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(com.forasoft.homewavvisitor.R.id.input_wrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "input_wrapper");
        textInputLayout.setHint(inflate.getResources().getString(R.string.hint_phone_number));
        create.show();
    }
}
