package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.braintreepayments.api.models.PostalAddressParser;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp2Presenter;
import com.forasoft.homewavvisitor.presentation.view.register.SignUp2View;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\b\u0010\u001c\u001a\u00020\u0005H\u0007J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00112\b\u0010!\u001a\u0004\u0018\u00010\u001fH\u0016J \u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u001fH\u0016J\u0016\u0010)\u001a\u00020\u00112\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001f0+H\u0016J\b\u0010,\u001a\u00020\u0011H\u0016J\u0010\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020\u001fH\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006/"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp2Fragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp2View;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp2Presenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp2Presenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp2Presenter;)V", "progressDialog", "Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;", "getProgressDialog", "()Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;", "setProgressDialog", "(Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;)V", "clearErrors", "", "hideProgress", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "providePresenter", "scrollToError", "field", "", "showDate", "formatedDate", "showDatePickDialog", "day", "", "month", "year", "showFieldError", "message", "showPickStateDialog", "statesList", "", "showProgress", "showSateAbbreviation", "abbreviation", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUp2Fragment.kt */
public final class SignUp2Fragment extends BaseFragment implements SignUp2View {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public SignUp2Presenter presenter;
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

    public final SignUp2Presenter getPresenter() {
        SignUp2Presenter signUp2Presenter = this.presenter;
        if (signUp2Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return signUp2Presenter;
    }

    public final void setPresenter(SignUp2Presenter signUp2Presenter) {
        Intrinsics.checkParameterIsNotNull(signUp2Presenter, "<set-?>");
        this.presenter = signUp2Presenter;
    }

    @ProvidePresenter
    public final SignUp2Presenter providePresenter() {
        Object instance = getScope().getInstance(SignUp2Presenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(SignUp2Presenter::class.java)");
        return (SignUp2Presenter) instance;
    }

    public final ProgressDialog getProgressDialog() {
        return this.progressDialog;
    }

    public final void setProgressDialog(ProgressDialog progressDialog2) {
        this.progressDialog = progressDialog2;
    }

    public void showProgress() {
        String string = getResources().getString(R.string.title_creating_account);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…g.title_creating_account)");
        ProgressDialog progressDialog2 = new ProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ProgressDialog.EXTRA_TITLE, string);
        progressDialog2.setArguments(bundle);
        this.progressDialog = progressDialog2;
        if (progressDialog2 == null) {
            Intrinsics.throwNpe();
        }
        progressDialog2.show(getChildFragmentManager(), (String) null);
    }

    public void hideProgress() {
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null) {
            progressDialog2.dismiss();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_sign_up2, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).requestFocus();
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonNext);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonNext");
        button.setOnClickListener(new SignUp2Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp2Fragment$onResume$1(this)));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editState);
        Intrinsics.checkExpressionValueIsNotNull(textView, "editState");
        textView.setOnClickListener(new SignUp2Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp2Fragment$onResume$2(this)));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkTermConditions);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "linkTermConditions");
        textView2.setOnClickListener(new SignUp2Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp2Fragment$onResume$3(this)));
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editDateOfBirth);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "editDateOfBirth");
        textView3.setOnClickListener(new SignUp2Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp2Fragment$onResume$4(this)));
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
    }

    public void showDatePickDialog(int i, int i2, int i3) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new SignUp2Fragment$showDatePickDialog$1(this), i3, i2, i);
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

    public void showPickStateDialog(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "statesList");
        AlertDialog.Builder title = new AlertDialog.Builder(requireContext()).setTitle((int) R.string.title_choose_state);
        Object[] array = list.toArray(new String[0]);
        if (array != null) {
            title.setItems((CharSequence[]) array, (DialogInterface.OnClickListener) new SignUp2Fragment$showPickStateDialog$1(this)).create().show();
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
            case -265713450:
                if (str.equals(Attributes.USERNAME)) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelUsername)).setTextColor(color);
                    EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editUsername);
                    Intrinsics.checkExpressionValueIsNotNull(editText3, "editUsername");
                    Drawable background3 = editText3.getBackground();
                    if (background3 != null) {
                        ((NinePatchDrawable) background3).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textUsernameVerification)).setTextColor(color);
                        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textUsernameVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView3, "textUsernameVerification");
                        textView3.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case -160985414:
                if (str.equals(Attributes.FIRST_NAME)) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelFirstName)).setTextColor(color);
                    EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editFirstName);
                    Intrinsics.checkExpressionValueIsNotNull(editText4, "editFirstName");
                    Drawable background4 = editText4.getBackground();
                    if (background4 != null) {
                        ((NinePatchDrawable) background4).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textFirstNameVerification)).setTextColor(color);
                        TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textFirstNameVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView4, "textFirstNameVerification");
                        textView4.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 3053931:
                if (str.equals("city")) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCity)).setTextColor(color);
                    EditText editText5 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity);
                    Intrinsics.checkExpressionValueIsNotNull(editText5, "editCity");
                    Drawable background5 = editText5.getBackground();
                    if (background5 != null) {
                        ((NinePatchDrawable) background5).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification)).setTextColor(color);
                        TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView5, "textCityVerification");
                        textView5.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 96619420:
                if (str.equals("email")) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail)).setTextColor(color);
                    EditText editText6 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
                    Intrinsics.checkExpressionValueIsNotNull(editText6, "editEmail");
                    Drawable background6 = editText6.getBackground();
                    if (background6 != null) {
                        ((NinePatchDrawable) background6).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification)).setTextColor(color);
                        TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView6, "textEmailVerification");
                        textView6.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 106642798:
                if (str.equals(ShippingInfoWidget.PHONE_FIELD)) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPhone)).setTextColor(color);
                    EditText editText7 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
                    Intrinsics.checkExpressionValueIsNotNull(editText7, "editPhone");
                    Drawable background7 = editText7.getBackground();
                    if (background7 != null) {
                        ((NinePatchDrawable) background7).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification)).setTextColor(color);
                        TextView textView7 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView7, "textPhoneVerification");
                        textView7.setText(str2);
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
                    Drawable background8 = textInputLayout.getBackground();
                    if (background8 != null) {
                        ((NinePatchDrawable) background8).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper)).setPadding(16, 0, 16, 0);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification)).setTextColor(color);
                        TextView textView8 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView8, "textPasswordVerification");
                        textView8.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            case 2013122196:
                if (str.equals(Attributes.LAST_NAME)) {
                    ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelLastName)).setTextColor(color);
                    EditText editText8 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editLastName);
                    Intrinsics.checkExpressionValueIsNotNull(editText8, "editLastName");
                    Drawable background9 = editText8.getBackground();
                    if (background9 != null) {
                        ((NinePatchDrawable) background9).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
                        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textLastNameVerification)).setTextColor(color);
                        TextView textView9 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textLastNameVerification);
                        Intrinsics.checkExpressionValueIsNotNull(textView9, "textLastNameVerification");
                        textView9.setText(str2);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
                }
                return;
            default:
                return;
        }
    }

    public void clearErrors() {
        int color = ContextCompat.getColor(requireContext(), R.color.textGray);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelUsername)).setTextColor(color);
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editUsername);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editUsername");
        Drawable drawable = null;
        editText.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editUsername)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textUsernameVerification)).setTextColor(color);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textUsernameVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textUsernameVerification");
        textView.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail)).setTextColor(color);
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editEmail");
        editText2.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification)).setTextColor(color);
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "textEmailVerification");
        textView2.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword)).setTextColor(color);
        TextInputLayout textInputLayout = (TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout, "editPasswordWrapper");
        textInputLayout.setBackground(drawable);
        TextInputLayout textInputLayout2 = (TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper);
        Intrinsics.checkExpressionValueIsNotNull(textInputLayout2, "editPasswordWrapper");
        textInputLayout2.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.background_edit_normal));
        ((TextInputLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPasswordWrapper)).setPadding(16, 0, 16, 0);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification)).setTextColor(color);
        TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPasswordVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "textPasswordVerification");
        textView3.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelStreet)).setTextColor(color);
        EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editStreet");
        editText3.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editStreet)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textStreetVerification)).setTextColor(color);
        TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textStreetVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView4, "textStreetVerification");
        textView4.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCity)).setTextColor(color);
        EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity);
        Intrinsics.checkExpressionValueIsNotNull(editText4, "editCity");
        editText4.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification)).setTextColor(color);
        TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textCityVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView5, "textCityVerification");
        textView5.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPhone)).setTextColor(color);
        EditText editText5 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone);
        Intrinsics.checkExpressionValueIsNotNull(editText5, "editPhone");
        editText5.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification)).setTextColor(color);
        TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPhoneVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView6, "textPhoneVerification");
        textView6.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelZip)).setTextColor(color);
        EditText editText6 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
        Intrinsics.checkExpressionValueIsNotNull(editText6, "editZip");
        editText6.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textZipVerification)).setTextColor(color);
        TextView textView7 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textZipVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView7, "textZipVerification");
        textView7.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelFirstName)).setTextColor(color);
        EditText editText7 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editFirstName);
        Intrinsics.checkExpressionValueIsNotNull(editText7, "editFirstName");
        editText7.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editFirstName)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textFirstNameVerification)).setTextColor(color);
        TextView textView8 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textFirstNameVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView8, "textFirstNameVerification");
        textView8.setText("");
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelLastName)).setTextColor(color);
        EditText editText8 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editLastName);
        Intrinsics.checkExpressionValueIsNotNull(editText8, "editLastName");
        editText8.setBackground(drawable);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editLastName)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textLastNameVerification)).setTextColor(color);
        TextView textView9 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textLastNameVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView9, "textLastNameVerification");
        textView9.setText("");
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
            case -265713450:
                if (str.equals(Attributes.USERNAME)) {
                    TextView textView3 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelUsername);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "labelUsername");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView3.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editUsername)).requestFocus();
                    break;
                }
                break;
            case -160985414:
                if (str.equals(Attributes.FIRST_NAME)) {
                    TextView textView4 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelFirstName);
                    Intrinsics.checkExpressionValueIsNotNull(textView4, "labelFirstName");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView4.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editFirstName)).requestFocus();
                    break;
                }
                break;
            case 3053931:
                if (str.equals("city")) {
                    TextView textView5 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCity);
                    Intrinsics.checkExpressionValueIsNotNull(textView5, "labelCity");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView5.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCity)).requestFocus();
                    break;
                }
                break;
            case 96619420:
                if (str.equals("email")) {
                    TextView textView6 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail);
                    Intrinsics.checkExpressionValueIsNotNull(textView6, "labelEmail");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView6.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail)).requestFocus();
                    break;
                }
                break;
            case 106642798:
                if (str.equals(ShippingInfoWidget.PHONE_FIELD)) {
                    TextView textView7 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPhone);
                    Intrinsics.checkExpressionValueIsNotNull(textView7, "labelPhone");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView7.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPhone)).requestFocus();
                    break;
                }
                break;
            case 1216985755:
                if (str.equals("password")) {
                    TextView textView8 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelPassword);
                    Intrinsics.checkExpressionValueIsNotNull(textView8, "labelPassword");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView8.getY());
                    ((TextInputEditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPassword)).requestFocus();
                    break;
                }
                break;
            case 2013122196:
                if (str.equals(Attributes.LAST_NAME)) {
                    TextView textView9 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelLastName);
                    Intrinsics.checkExpressionValueIsNotNull(textView9, "labelLastName");
                    ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root)).smoothScrollTo(0, (int) textView9.getY());
                    ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editLastName)).requestFocus();
                    break;
                }
                break;
        }
        ContextExtensionsKt.showKeyboard(this);
    }
}
