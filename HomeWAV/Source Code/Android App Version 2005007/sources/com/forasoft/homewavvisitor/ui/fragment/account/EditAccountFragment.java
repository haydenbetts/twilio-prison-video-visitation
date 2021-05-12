package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.braintreepayments.api.models.PostalAddressParser;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.account.EditAccountPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.EditAccountView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.util.Attributes;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\b\u0010#\u001a\u00020\u0012H\u0016J\u001a\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010&\u001a\u00020\u0006H\u0007J\u0010\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020)H\u0016J\u0010\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020)H\u0016J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020)H\u0016J\u0010\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020)H\u0016J\u0010\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020)H\u0016J\u0010\u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020)H\u0016J\u0010\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u00020)H\u0016J\u0010\u00108\u001a\u00020\u00122\u0006\u00109\u001a\u00020)H\u0016J\u0010\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020)H\u0016J\u0010\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020)H\u0016J\u0010\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020)H\u0016J\u0012\u0010@\u001a\u00020\u00122\b\u0010A\u001a\u0004\u0018\u00010)H\u0016J \u0010B\u001a\u00020\u00122\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020DH\u0016J\u0018\u0010G\u001a\u00020\u00122\u0006\u0010(\u001a\u00020)2\u0006\u0010H\u001a\u00020)H\u0016J\b\u0010I\u001a\u00020\u0012H\u0016J\u0016\u0010J\u001a\u00020\u00122\f\u0010K\u001a\b\u0012\u0004\u0012\u00020)0LH\u0016J\b\u0010M\u001a\u00020\u0012H\u0016J\u0010\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020)H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006P"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/EditAccountFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/EditAccountView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditAccountPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditAccountPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/EditAccountPresenter;)V", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "clearErrors", "", "hideProgress", "modifyView", "onBackPressed", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onViewCreated", "view", "providePresenter", "scrollToError", "field", "", "setBirthDate", "birthDate", "setCity", "city", "setEmail", "email", "setFirstName", "firstName", "setLastName", "lastName", "setPhone", "phone", "setPin", "pin", "setState", "state", "setStreet", "street", "setUsername", "username", "setZip", "zip", "showDate", "formatedDate", "showDatePickDialog", "day", "", "month", "year", "showFieldError", "message", "showPasswordField", "showPickStateDialog", "statesList", "", "showProgress", "showSateAbbreviation", "abbreviation", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EditAccountFragment.kt */
public final class EditAccountFragment extends BaseFragment implements EditAccountView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public EditAccountPresenter presenter;
    private ProgressDialog progressDialog;

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

    public final EditAccountPresenter getPresenter() {
        EditAccountPresenter editAccountPresenter = this.presenter;
        if (editAccountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return editAccountPresenter;
    }

    public final void setPresenter(EditAccountPresenter editAccountPresenter) {
        Intrinsics.checkParameterIsNotNull(editAccountPresenter, "<set-?>");
        this.presenter = editAccountPresenter;
    }

    @ProvidePresenter
    public final EditAccountPresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(EditAccountPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …untPresenter::class.java)");
        return (EditAccountPresenter) instance;
    }

    public final ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public final void setProgressDialog(ProgressDialog progressDialog2) {
        this.progressDialog = progressDialog2;
    }

    public void showProgress() {
        this.progressDialog = ProgressDialog.show(getContext(), "Please, wait", "Updating profile", true, false);
    }

    public void hideProgress() {
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.cancel();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        Timber.d("onCreateView", new Object[0]);
        return layoutInflater.inflate(R.layout.fragment_sign_up2, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Timber.d("onViewCreated", new Object[0]);
        super.onViewCreated(view, bundle);
        modifyView();
    }

    private final void modifyView() {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepHeader);
        Intrinsics.checkExpressionValueIsNotNull(textView, "stepHeader");
        textView.setText(getString(R.string.header_edit_account));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textAccountInfoExplanations);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "textAccountInfoExplanations");
        textView2.setText(getString(R.string.text_edit_account_explanation));
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setText(getString(R.string.label_submit_for_approval));
        View _$_findCachedViewById = _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.divider);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "divider");
        ViewGroup.LayoutParams layoutParams = _$_findCachedViewById.getLayoutParams();
        if (layoutParams != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.topToBottom = R.id.linkChangePassword;
            View _$_findCachedViewById2 = _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.divider);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "divider");
            _$_findCachedViewById2.setLayoutParams(layoutParams2);
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword));
            CommonKt.hide((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkChangePassword));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelUsername));
            CommonKt.hide((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editUsername));
            CommonKt.hide((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editFirstName));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textFirstNameVerification));
            CommonKt.hide((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editLastName));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textLastNameVerification));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelFirstName));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelLastName));
            CommonKt.hide((AppCompatCheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkEmailSubscription));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailSubscription));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textExplanationTermConditions));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkTermConditions));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void onResume() {
        Timber.d("onResume", new Object[0]);
        super.onResume();
        ContextKt.showActionBack(this);
        ContextKt.setTitle((Fragment) this, "Edit Account");
        ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).requestFocus();
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setOnClickListener(new EditAccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditAccountFragment$onResume$1(this)));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editState);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editState");
        textView.setOnClickListener(new EditAccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditAccountFragment$onResume$2(this)));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkTermConditions);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "linkTermConditions");
        textView2.setOnClickListener(new EditAccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditAccountFragment$onResume$3(this)));
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "editDateOfBirth");
        textView3.setOnClickListener(new EditAccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditAccountFragment$onResume$4(this)));
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editPhone");
        editText.setInputType(2);
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editPhone");
        editText2.setKeyListener(DigitsKeyListener.getInstance("1234567890+-() "));
        EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editPhone");
        MaskedTextChangedListener maskedTextChangedListener = new MaskedTextChangedListener("+1 ([000]) [000]-[0000]", editText3);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone)).addTextChangedListener(maskedTextChangedListener);
        EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "editPhone");
        editText4.setOnFocusChangeListener(maskedTextChangedListener);
        TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkChangePassword);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "linkChangePassword");
        textView4.setOnClickListener(new EditAccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditAccountFragment$onResume$5(this)));
        TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkTermConditions);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "linkTermConditions");
        textView5.setOnClickListener(new EditAccountFragment$inlined$sam$i$android_view_View_OnClickListener$0(new EditAccountFragment$onResume$6(this)));
    }

    public void showDatePickDialog(int i, int i2, int i3) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), 3, new EditAccountFragment$showDatePickDialog$1(this), i3, i2, i);
        DatePicker datePicker = datePickerDialog.getDatePicker();
        Intrinsics.checkExpressionValueIsNotNull(datePicker, "datePicker");
        datePicker.setMaxDate(new Date().getTime());
        datePickerDialog.show();
    }

    public void showDate(String str) {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        textView.setText(str);
    }

    public void onPause() {
        super.onPause();
        ContextKt.hideActionBack(this);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        EditAccountPresenter editAccountPresenter = this.presenter;
        if (editAccountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return editAccountPresenter.onBackPressed();
    }

    public void showPasswordField() {
        View _$_findCachedViewById = _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.divider);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "divider");
        ViewGroup.LayoutParams layoutParams = _$_findCachedViewById.getLayoutParams();
        if (layoutParams != null) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.topToBottom = R.id.editNewPasswordWrapper;
            View _$_findCachedViewById2 = _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.divider);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "divider");
            _$_findCachedViewById2.setLayoutParams(layoutParams2);
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword);
            Intrinsics.checkExpressionValueIsNotNull(textView, "labelPassword");
            textView.setText(getResources().getString(R.string.label_current_password));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkChangePassword));
            CommonKt.show((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelNewPassword));
            CommonKt.show((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editNewPasswordWrapper));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textNewPasswordVerification));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
    }

    public void showPickStateDialog(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "statesList");
        AlertDialog.Builder title = new AlertDialog.Builder(requireContext()).setTitle((int) R.string.title_choose_state);
        Object[] array = list.toArray(new String[0]);
        if (array != null) {
            title.setItems((CharSequence[]) array, (DialogInterface.OnClickListener) new EditAccountFragment$showPickStateDialog$1(this)).create().show();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public void showSateAbbreviation(String str) {
        Intrinsics.checkParameterIsNotNull(str, "abbreviation");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editState);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editState");
        textView.setText(str);
    }

    public void setEmail(String str) {
        Intrinsics.checkParameterIsNotNull(str, "email");
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editEmail");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setUsername(String str) {
        Intrinsics.checkParameterIsNotNull(str, Attributes.USERNAME);
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editUsername);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editUsername");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setFirstName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "firstName");
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editFirstName);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editFirstName");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setLastName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "lastName");
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editLastName);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editLastName");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setBirthDate(String str) {
        Intrinsics.checkParameterIsNotNull(str, "birthDate");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editDateOfBirth");
        textView.setText(CommonKt.toEditable(str));
    }

    public void setPhone(String str) {
        Intrinsics.checkParameterIsNotNull(str, ShippingInfoWidget.PHONE_FIELD);
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editPhone");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setPin(String str) {
        Intrinsics.checkParameterIsNotNull(str, "pin");
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPin));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPin);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textPin");
        textView.setText(getResources().getString(R.string.pin_message, new Object[]{str}));
    }

    public void setStreet(String str) {
        Intrinsics.checkParameterIsNotNull(str, "street");
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editStreet");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setCity(String str) {
        Intrinsics.checkParameterIsNotNull(str, "city");
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editCity");
        editText.setText(CommonKt.toEditable(str));
    }

    public void setState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "state");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editState);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editState");
        textView.setText(str);
    }

    public void setZip(String str) {
        Intrinsics.checkParameterIsNotNull(str, "zip");
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editZip");
        editText.setText(CommonKt.toEditable(str));
    }

    public void showFieldError(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "field");
        Intrinsics.checkParameterIsNotNull(str2, "message");
        int color = ContextCompat.getColor(requireContext(), R.color.errorRed);
        switch (str.hashCode()) {
            case -1881886578:
                if (str.equals(PostalAddressParser.STREET_ADDRESS_KEY)) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelStreet)).setTextColor(color);
                    EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet);
                    Intrinsics.checkExpressionValueIsNotNull(editText, "editStreet");
                    Drawable background = editText.getBackground();
                    if (background != null) {
                        ((NinePatchDrawable) background).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textStreetVerification)).setTextColor(color);
                        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textStreetVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "textStreetVerification");
                        textView.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case -281146226:
                if (str.equals("zipcode")) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelZip)).setTextColor(color);
                    EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
                    Intrinsics.checkExpressionValueIsNotNull(editText2, "editZip");
                    Drawable background2 = editText2.getBackground();
                    if (background2 != null) {
                        ((NinePatchDrawable) background2).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textZipVerification)).setTextColor(color);
                        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textZipVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView2, "textZipVerification");
                        textView2.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 3053931:
                if (str.equals("city")) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCity)).setTextColor(color);
                    EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity);
                    Intrinsics.checkExpressionValueIsNotNull(editText3, "editCity");
                    Drawable background3 = editText3.getBackground();
                    if (background3 != null) {
                        ((NinePatchDrawable) background3).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification)).setTextColor(color);
                        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView3, "textCityVerification");
                        textView3.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 96619420:
                if (str.equals("email")) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail)).setTextColor(color);
                    EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
                    Intrinsics.checkExpressionValueIsNotNull(editText4, "editEmail");
                    Drawable background4 = editText4.getBackground();
                    if (background4 != null) {
                        ((NinePatchDrawable) background4).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification)).setTextColor(color);
                        TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView4, "textEmailVerification");
                        textView4.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 106642798:
                if (str.equals(ShippingInfoWidget.PHONE_FIELD)) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPhone)).setTextColor(color);
                    EditText editText5 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
                    Intrinsics.checkExpressionValueIsNotNull(editText5, "editPhone");
                    Drawable background5 = editText5.getBackground();
                    if (background5 != null) {
                        ((NinePatchDrawable) background5).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification)).setTextColor(color);
                        TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView5, "textPhoneVerification");
                        textView5.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 1216985755:
                if (str.equals("password")) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword)).setTextColor(color);
                    TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper);
                    Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "editPasswordWrapper");
                    Drawable background6 = textInputLayout.getBackground();
                    if (background6 != null) {
                        ((NinePatchDrawable) background6).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper)).setPadding(16, 0, 16, 0);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification)).setTextColor(color);
                        TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView6, "textPasswordVerification");
                        textView6.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            default:
                return;
        }
    }

    public void scrollToError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "field");
        switch (str.hashCode()) {
            case -1881886578:
                if (str.equals(PostalAddressParser.STREET_ADDRESS_KEY)) {
                    TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelStreet);
                    Intrinsics.checkExpressionValueIsNotNull(textView, "labelStreet");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet)).requestFocus();
                    break;
                }
                break;
            case -281146226:
                if (str.equals("zipcode")) {
                    TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelZip);
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "labelZip");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView2.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip)).requestFocus();
                    break;
                }
                break;
            case 3053931:
                if (str.equals("city")) {
                    TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCity);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "labelCity");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView3.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity)).requestFocus();
                    break;
                }
                break;
            case 96619420:
                if (str.equals("email")) {
                    TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail);
                    Intrinsics.checkExpressionValueIsNotNull(textView4, "labelEmail");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView4.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail)).requestFocus();
                    break;
                }
                break;
            case 106642798:
                if (str.equals(ShippingInfoWidget.PHONE_FIELD)) {
                    TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPhone);
                    Intrinsics.checkExpressionValueIsNotNull(textView5, "labelPhone");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView5.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone)).requestFocus();
                    break;
                }
                break;
            case 1216985755:
                if (str.equals("password")) {
                    TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword);
                    Intrinsics.checkExpressionValueIsNotNull(textView6, "labelPassword");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView6.getY());
                    ((TextInputEditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPassword)).requestFocus();
                    break;
                }
                break;
        }
        ContextExtensionsKt.showKeyboard(this);
    }

    public void clearErrors() {
        int color = ContextCompat.getColor(requireContext(), R.color.textGray);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail)).setTextColor(color);
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editEmail");
        Drawable drawable = null;
        editText.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification)).setTextColor(color);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textEmailVerification");
        textView.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword)).setTextColor(color);
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "editPasswordWrapper");
        textInputLayout.setBackground(drawable);
        TextInputLayout textInputLayout2 = (TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout2, "editPasswordWrapper");
        textInputLayout2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.background_edit_normal));
        ((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper)).setPadding(16, 0, 16, 0);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification)).setTextColor(color);
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "textPasswordVerification");
        textView2.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelStreet)).setTextColor(color);
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editStreet");
        editText2.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textStreetVerification)).setTextColor(color);
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textStreetVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "textStreetVerification");
        textView3.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCity)).setTextColor(color);
        EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editCity");
        editText3.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification)).setTextColor(color);
        TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "textCityVerification");
        textView4.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPhone)).setTextColor(color);
        EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "editPhone");
        editText4.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification)).setTextColor(color);
        TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "textPhoneVerification");
        textView5.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelZip)).setTextColor(color);
        EditText editText5 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
        Intrinsics.checkExpressionValueIsNotNull(editText5, "editZip");
        editText5.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textZipVerification)).setTextColor(color);
        TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textZipVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView6, "textZipVerification");
        textView6.setText("");
    }

    public boolean onBackPressed() {
        EditAccountPresenter editAccountPresenter = this.presenter;
        if (editAccountPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return editAccountPresenter.onBackPressed();
    }
}
