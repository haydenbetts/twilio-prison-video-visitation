package com.forasoft.homewavvisitor.ui.fragment.payment;

import air.HomeWAV.R;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.data.PaymentRequestData;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.payment.PayNearMePresenter;
import com.forasoft.homewavvisitor.presentation.view.payment.PayNearMeView;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.StepperView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 02\u00020\u00012\u00020\u00022\u00020\u0003:\u00010B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001b\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\"\u001a\u00020\fH\u0016J\u0010\u0010#\u001a\u00020\u00112\u0006\u0010$\u001a\u00020%H\u0016J\u001a\u0010&\u001a\u00020\f2\u0006\u0010'\u001a\u00020\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010(\u001a\u00020\u0006H\u0007J\u0012\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\fH\u0002J\u0012\u0010-\u001a\u00020\f2\b\u0010.\u001a\u0004\u0018\u00010+H\u0016J\b\u0010/\u001a\u00020\fH\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u00061"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/payment/PayNearMeView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/payment/PayNearMePresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/payment/PayNearMePresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/payment/PayNearMePresenter;)V", "clearEmailError", "", "installModules", "scope", "Ltoothpick/Scope;", "isValidEmail", "", "target", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "setInitialEmail", "email", "", "showEmailError", "showErrorMessage", "message", "showSuccessMessage", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PayNearMeFragment.kt */
public final class PayNearMeFragment extends BaseFragment implements PayNearMeView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String PAYMENT_REQUEST_DATA = "payment request data";
    private HashMap _$_findViewCache;
    @InjectPresenter
    public PayNearMePresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeFragment$Companion;", "", "()V", "PAYMENT_REQUEST_DATA", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeFragment;", "paymentRequestData", "Lcom/forasoft/homewavvisitor/model/data/PaymentRequestData;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: PayNearMeFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PayNearMeFragment newInstance(PaymentRequestData paymentRequestData) {
            Intrinsics.checkParameterIsNotNull(paymentRequestData, "paymentRequestData");
            PayNearMeFragment payNearMeFragment = new PayNearMeFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(PayNearMeFragment.PAYMENT_REQUEST_DATA, paymentRequestData);
            payNearMeFragment.setArguments(bundle);
            return payNearMeFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new PayNearMeFragment$installModules$1(this));
    }

    public final PayNearMePresenter getPresenter() {
        PayNearMePresenter payNearMePresenter = this.presenter;
        if (payNearMePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return payNearMePresenter;
    }

    public final void setPresenter(PayNearMePresenter payNearMePresenter) {
        Intrinsics.checkParameterIsNotNull(payNearMePresenter, "<set-?>");
        this.presenter = payNearMePresenter;
    }

    @ProvidePresenter
    public final PayNearMePresenter providePresenter() {
        Object instance = getScope().getInstance(PayNearMePresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(PayNearMePresenter::class.java)");
        return (PayNearMePresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_pay_near_me, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        ImageButton imageButton = (ImageButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.pay_button);
        Intrinsics.checkExpressionValueIsNotNull(imageButton, "pay_button");
        imageButton.setOnClickListener(new PayNearMeFragment$inlined$sam$i$android_view_View_OnClickListener$0(new PayNearMeFragment$onViewCreated$1(this)));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) getString(R.string.use_pay_near_me));
            }
            FragmentActivity activity2 = getActivity();
            if (!(activity2 instanceof CreateAccountActivity)) {
                activity2 = null;
            }
            CreateAccountActivity createAccountActivity = (CreateAccountActivity) activity2;
            if (createAccountActivity != null) {
                ContextKt.hideActionBack(this);
                CommonKt.hide((StepperView) createAccountActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper));
                PayNearMePresenter payNearMePresenter = this.presenter;
                if (payNearMePresenter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                payNearMePresenter.setGlobalRouter();
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    /* access modifiers changed from: private */
    public final boolean isValidEmail(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public void onDestroyView() {
        StepperView stepperView;
        FragmentActivity activity = getActivity();
        if (!(activity instanceof CreateAccountActivity)) {
            activity = null;
        }
        CreateAccountActivity createAccountActivity = (CreateAccountActivity) activity;
        if (!(createAccountActivity == null || (stepperView = (StepperView) createAccountActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper)) == null)) {
            CommonKt.show(stepperView);
        }
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    /* access modifiers changed from: private */
    public final void clearEmailError() {
        int color = ContextCompat.getColor(requireContext(), R.color.textGray);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail)).setTextColor(color);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail)).setBackgroundResource(R.drawable.background_edit_normal);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification)).setTextColor(color);
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textEmailVerification");
        textView.setText(getString(R.string.insert_email_for_pay_near_me));
    }

    /* access modifiers changed from: private */
    public final void showEmailError() {
        int color = ContextCompat.getColor(requireContext(), R.color.errorRed);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelEmail)).setTextColor(color);
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editEmail");
        Drawable background = editText.getBackground();
        if (background != null) {
            ((NinePatchDrawable) background).setColorFilter(ContextCompat.getColor(requireContext(), R.color.errorRed), PorterDuff.Mode.ADD);
            ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification)).setTextColor(color);
            TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textEmailVerification);
            Intrinsics.checkExpressionValueIsNotNull(textView, "textEmailVerification");
            textView.setText(getString(R.string.email_error));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.NinePatchDrawable");
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        MenuItem add = menu.add(R.string.label_close);
        add.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_close));
        add.setShowAsAction(2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        onBackPressed();
        return true;
    }

    public void showSuccessMessage() {
        ContextKt.showSnackbar((Fragment) this, getResources().getString(R.string.order_created));
    }

    public void showErrorMessage(String str) {
        if (str == null) {
            str = getResources().getString(R.string.order_not_created);
        }
        ContextKt.showSnackbar((Fragment) this, str);
    }

    public void setInitialEmail(String str) {
        if (str != null) {
            EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editEmail);
            Intrinsics.checkExpressionValueIsNotNull(editText, "editEmail");
            editText.setText(Editable.Factory.getInstance().newEditable(str));
        }
    }

    public boolean onBackPressed() {
        PayNearMePresenter payNearMePresenter = this.presenter;
        if (payNearMePresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return payNearMePresenter.onBackPressed();
    }
}
