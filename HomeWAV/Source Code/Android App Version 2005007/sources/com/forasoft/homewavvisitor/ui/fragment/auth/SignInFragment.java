package com.forasoft.homewavvisitor.ui.fragment.auth;

import air.HomeWAV.R;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.presentation.presenter.auth.SignInPresenter;
import com.forasoft.homewavvisitor.presentation.view.auth.SignInView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.ProgressButton;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0002J&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000bH\u0002J\b\u0010\u0017\u001a\u00020\u000bH\u0016J\b\u0010\u0018\u001a\u00020\u000bH\u0016J\u0010\u0010\u0019\u001a\n \u001a*\u0004\u0018\u00010\u00050\u0005H\u0007J\b\u0010\u001b\u001a\u00020\u000bH\u0002J\b\u0010\u001c\u001a\u00020\u000bH\u0002J\b\u0010\u001d\u001a\u00020\u000bH\u0016J\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\rH\u0016R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006 "}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/auth/SignInFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/auth/SignInView;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/auth/SignInPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/auth/SignInPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/auth/SignInPresenter;)V", "hideError", "", "isInternetAvailable", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onLoginClicked", "onResume", "performLoginClick", "providePresenter", "kotlin.jvm.PlatformType", "showConnectionError", "showEmailOrPasswordInvalidError", "showError", "showLoading", "isLoading", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignInFragment.kt */
public final class SignInFragment extends BaseFragment implements SignInView {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public SignInPresenter presenter;

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

    public final SignInPresenter getPresenter() {
        SignInPresenter signInPresenter = this.presenter;
        if (signInPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return signInPresenter;
    }

    public final void setPresenter(SignInPresenter signInPresenter) {
        Intrinsics.checkParameterIsNotNull(signInPresenter, "<set-?>");
        this.presenter = signInPresenter;
    }

    @ProvidePresenter
    public final SignInPresenter providePresenter() {
        return (SignInPresenter) Toothpick.openScope(DI.SERVER_SCOPE).getInstance(SignInPresenter.class);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_login, viewGroup, false);
    }

    public void onResume() {
        super.onResume();
        ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.loginRoot)).requestFocus();
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkForgotPassword);
        Intrinsics.checkExpressionValueIsNotNull(textView, "linkForgotPassword");
        View view = textView;
        SignInPresenter signInPresenter = this.presenter;
        if (signInPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        view.setOnClickListener(new SignInFragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignInFragment$onResume$1(signInPresenter)));
        TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.linkCreateAccount);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "linkCreateAccount");
        View view2 = textView2;
        SignInPresenter signInPresenter2 = this.presenter;
        if (signInPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        view2.setOnClickListener(new SignInFragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignInFragment$onResume$2(signInPresenter2)));
        ProgressButton progressButton = (ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonLogIn);
        Intrinsics.checkExpressionValueIsNotNull(progressButton, "buttonLogIn");
        progressButton.setOnClickListener(new SignInFragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignInFragment$onResume$3(this)));
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
        SignInPresenter signInPresenter3 = this.presenter;
        if (signInPresenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        editText.setOnFocusChangeListener(new SignInFragment$sam$android_view_View_OnFocusChangeListener$0(new SignInFragment$onResume$4(signInPresenter3)));
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPassword);
        SignInPresenter signInPresenter4 = this.presenter;
        if (signInPresenter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        editText2.setOnFocusChangeListener(new SignInFragment$sam$android_view_View_OnFocusChangeListener$0(new SignInFragment$onResume$5(signInPresenter4)));
        EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPassword);
        Intrinsics.checkExpressionValueIsNotNull(editText3, "editPassword");
        TextView textView3 = editText3;
        SignInPresenter signInPresenter5 = this.presenter;
        if (signInPresenter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        textView3.setOnEditorActionListener(new SignInFragment$inlined$sam$i$android_widget_TextView_OnEditorActionListener$0(new SignInFragment$onResume$6(signInPresenter5)));
    }

    /* access modifiers changed from: private */
    public final void onLoginClicked() {
        ((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.loginRoot)).requestFocus();
        ContextKt.hideKeyboard((Activity) getActivity());
        SignInPresenter signInPresenter = this.presenter;
        if (signInPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editEmail");
        Editable text = editText.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "editEmail.text");
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editPassword);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editPassword");
        Editable text2 = editText2.getText();
        Intrinsics.checkExpressionValueIsNotNull(text2, "editPassword.text");
        signInPresenter.onLoginClicked(text, text2);
    }

    public void performLoginClick() {
        onLoginClicked();
    }

    public void showError() {
        if (isInternetAvailable()) {
            showEmailOrPasswordInvalidError();
        } else {
            showConnectionError();
        }
    }

    private final void showEmailOrPasswordInvalidError() {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.errorTextView);
        Intrinsics.checkExpressionValueIsNotNull(textView, "errorTextView");
        textView.setText(getResources().getString(R.string.error_email_password_incorrect));
        CardView cardView = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cardIncorrectEmailPassword);
        Intrinsics.checkExpressionValueIsNotNull(cardView, "cardIncorrectEmailPassword");
        cardView.setVisibility(0);
        CardView cardView2 = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.emailFieldCard);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        cardView2.setCardBackgroundColor(ContextCompat.getColor(context, R.color.errorRed));
        CardView cardView3 = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.passwordFieldCard);
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        cardView3.setCardBackgroundColor(ContextCompat.getColor(context2, R.color.errorRed));
    }

    private final void showConnectionError() {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.errorTextView);
        Intrinsics.checkExpressionValueIsNotNull(textView, "errorTextView");
        textView.setText(getResources().getString(R.string.error_connection));
        CardView cardView = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cardIncorrectEmailPassword);
        Intrinsics.checkExpressionValueIsNotNull(cardView, "cardIncorrectEmailPassword");
        cardView.setVisibility(0);
        CardView cardView2 = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.emailFieldCard);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        cardView2.setCardBackgroundColor(ContextCompat.getColor(context, R.color.errorRed));
        CardView cardView3 = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.passwordFieldCard);
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        cardView3.setCardBackgroundColor(ContextCompat.getColor(context2, R.color.errorRed));
    }

    public void hideError() {
        CardView cardView = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cardIncorrectEmailPassword);
        Intrinsics.checkExpressionValueIsNotNull(cardView, "cardIncorrectEmailPassword");
        cardView.setVisibility(8);
        CardView cardView2 = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.emailFieldCard);
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        cardView2.setCardBackgroundColor(ContextCompat.getColor(context, 17170443));
        CardView cardView3 = (CardView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.passwordFieldCard);
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        cardView3.setCardBackgroundColor(ContextCompat.getColor(context2, 17170443));
    }

    public void showLoading(boolean z) {
        ((ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonLogIn)).setProgressVisible(z);
    }

    private final boolean isInternetAvailable() {
        Context context = getContext();
        Object systemService = context != null ? context.getSystemService("connectivity") : null;
        if (systemService != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
    }
}
