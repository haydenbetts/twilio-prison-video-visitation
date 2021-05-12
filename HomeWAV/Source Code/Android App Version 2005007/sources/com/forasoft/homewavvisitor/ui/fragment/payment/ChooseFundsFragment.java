package com.forasoft.homewavvisitor.ui.fragment.payment;

import air.HomeWAV.R;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.PaymentMethod;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.data.payment.PaymentState;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.payment.ChooseFundsPresenter;
import com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.payment.ConfirmGeneralFundsDialog;
import com.forasoft.homewavvisitor.ui.views.ProgressButton;
import com.forasoft.homewavvisitor.ui.views.StepperView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.sdk15.listeners.Sdk15ListenersListenersKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0007\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0011H\u0016J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J%\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001f2\u000e\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0#H\u0016¢\u0006\u0002\u0010$J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0014H\u0016J\u0012\u0010+\u001a\u00020\u00142\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0018\u0010.\u001a\u00020\u00142\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J&\u00103\u001a\u0004\u0018\u00010'2\u0006\u00101\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u00107\u001a\u00020\u0014H\u0016J\u0010\u00108\u001a\u00020)2\u0006\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020\u0014H\u0016J\u0010\u0010<\u001a\u00020\u00142\u0006\u0010=\u001a\u00020-H\u0016J\u001a\u0010>\u001a\u00020\u00142\u0006\u0010&\u001a\u00020'2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010?\u001a\u00020\u000bH\u0007J\u0010\u0010@\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u001fH\u0016J\b\u0010B\u001a\u00020\u0014H\u0016J\u0010\u0010C\u001a\u00020\u00142\u0006\u0010D\u001a\u00020\u001fH\u0016J\u0010\u0010E\u001a\u00020\u00142\u0006\u0010!\u001a\u00020\u001fH\u0016J\b\u0010F\u001a\u00020\u0014H\u0016J\b\u0010G\u001a\u00020\u0014H\u0016J\u0010\u0010H\u001a\u00020\u00142\u0006\u0010I\u001a\u00020\u001cH\u0002J\b\u0010J\u001a\u00020\u0014H\u0016J\u0010\u0010K\u001a\u00020\u00142\u0006\u0010L\u001a\u00020MH\u0016J\u0010\u0010N\u001a\u00020\u00142\u0006\u0010O\u001a\u00020)H\u0016J\b\u0010P\u001a\u00020\u0014H\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u000b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X.¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/payment/ChooseFundsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/payment/ChooseFundsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog$OnConfirmListener;", "()V", "braintreeFragment", "Lcom/braintreepayments/api/BraintreeFragment;", "paymentScopeChangeListener", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/payment/ChooseFundsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/payment/ChooseFundsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/payment/ChooseFundsPresenter;)V", "toggles", "", "Landroid/widget/ToggleButton;", "displayCards", "", "cards", "Lcom/forasoft/homewavvisitor/model/data/Card;", "enterAmountTextWatcher", "text", "Landroid/text/Editable;", "executePayment", "getSelectedAmount", "", "initPayment", "token", "", "initPaymentScope", "scope", "allowedScopes", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "onAmountClicked", "view", "Landroid/view/View;", "onBackPressed", "", "onConfirmed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onSaveInstanceState", "outState", "onViewCreated", "providePresenter", "setBalance", "balance", "setFraudState", "setName", "name", "setPaymentScope", "showBraintreeError", "showErrorMessage", "showFaqDialog", "messageId", "showGeneralFundsConfirmation", "showHandling", "handling", "", "showProgress", "show", "showSuccessMessage", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ChooseFundsFragment.kt */
public final class ChooseFundsFragment extends BaseFragment implements ChooseFundsView, OnBackButtonPressedListener, ConfirmGeneralFundsDialog.OnConfirmListener {
    private HashMap _$_findViewCache;
    private BraintreeFragment braintreeFragment;
    private final CompoundButton.OnCheckedChangeListener paymentScopeChangeListener = new ChooseFundsFragment$paymentScopeChangeListener$1(this);
    @InjectPresenter
    public ChooseFundsPresenter presenter;
    /* access modifiers changed from: private */
    public List<? extends ToggleButton> toggles;

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

    public static final /* synthetic */ List access$getToggles$p(ChooseFundsFragment chooseFundsFragment) {
        List<? extends ToggleButton> list = chooseFundsFragment.toggles;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toggles");
        }
        return list;
    }

    public final ChooseFundsPresenter getPresenter() {
        ChooseFundsPresenter chooseFundsPresenter = this.presenter;
        if (chooseFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return chooseFundsPresenter;
    }

    public final void setPresenter(ChooseFundsPresenter chooseFundsPresenter) {
        Intrinsics.checkParameterIsNotNull(chooseFundsPresenter, "<set-?>");
        this.presenter = chooseFundsPresenter;
    }

    @ProvidePresenter
    public final ChooseFundsPresenter providePresenter() {
        Object instance = getScope().getInstance(ChooseFundsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Choose…ndsPresenter::class.java)");
        return (ChooseFundsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
        if (bundle != null) {
            ChooseFundsPresenter chooseFundsPresenter = this.presenter;
            if (chooseFundsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            Parcelable parcelable = bundle.getParcelable(PaymentGateway.PAYMENT_STATE);
            if (parcelable == null) {
                Intrinsics.throwNpe();
            }
            chooseFundsPresenter.setPaymentState((PaymentState) parcelable);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_choose_funds, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        BottomNavigationViewEx bottomNavigationViewEx;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        this.toggles = CollectionsKt.arrayListOf((ToggleButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toggleFive), (ToggleButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toggleTen), (ToggleButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toggleTwenty), (ToggleButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.toggleOther));
        FragmentActivity activity = getActivity();
        CreateAccountActivity createAccountActivity = null;
        if (!(activity instanceof MainActivity)) {
            activity = null;
        }
        MainActivity mainActivity = (MainActivity) activity;
        if (!(mainActivity == null || (bottomNavigationViewEx = (BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)) == null)) {
            CommonKt.hide(bottomNavigationViewEx);
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 instanceof CreateAccountActivity) {
            createAccountActivity = activity2;
        }
        CreateAccountActivity createAccountActivity2 = createAccountActivity;
        if (createAccountActivity2 != null) {
            CommonKt.hide((StepperView) createAccountActivity2._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper));
            ChooseFundsPresenter chooseFundsPresenter = this.presenter;
            if (chooseFundsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            chooseFundsPresenter.setGlobalRouter();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        ChooseFundsPresenter chooseFundsPresenter = this.presenter;
        if (chooseFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        bundle.putParcelable(PaymentGateway.PAYMENT_STATE, chooseFundsPresenter.getPaymentState());
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

    public void onResume() {
        super.onResume();
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCustomAmount);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editCustomAmount");
        Sdk15ListenersListenersKt.textChangedListener(editText, new ChooseFundsFragment$onResume$1(this));
        List<? extends ToggleButton> list = this.toggles;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toggles");
        }
        for (ToggleButton onClickListener : list) {
            onClickListener.setOnClickListener(new ChooseFundsFragment$onResume$$inlined$forEach$lambda$1(this));
        }
        List<? extends ToggleButton> list2 = this.toggles;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toggles");
        }
        if (((ToggleButton) list2.get(3)).isChecked()) {
            CommonKt.show((Group) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.groupCustomAmount));
        }
        ProgressButton progressButton = (ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonAddFunds);
        Intrinsics.checkExpressionValueIsNotNull(progressButton, "buttonAddFunds");
        progressButton.setOnClickListener(new ChooseFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ChooseFundsFragment$onResume$3(this)));
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.pay_near_me_link);
        Intrinsics.checkExpressionValueIsNotNull(textView, "pay_near_me_link");
        textView.setOnClickListener(new ChooseFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ChooseFundsFragment$onResume$4(this)));
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.faq_talk_to_me);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "faq_talk_to_me");
        imageView.setOnClickListener(new ChooseFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ChooseFundsFragment$onResume$5(this)));
        ImageView imageView2 = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.faq_general_funds);
        Intrinsics.checkExpressionValueIsNotNull(imageView2, "faq_general_funds");
        imageView2.setOnClickListener(new ChooseFundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new ChooseFundsFragment$onResume$6(this)));
        ChooseFundsPresenter chooseFundsPresenter = this.presenter;
        if (chooseFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        chooseFundsPresenter.resetPaymentScope();
        if (getActivity() instanceof MainActivity) {
            ChooseFundsPresenter chooseFundsPresenter2 = this.presenter;
            if (chooseFundsPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            chooseFundsPresenter2.refresh();
        }
    }

    /* access modifiers changed from: private */
    public final void enterAmountTextWatcher(Editable editable) {
        if (editable != null) {
            if (editable.length() == 0) {
                EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCustomAmount);
                Intrinsics.checkExpressionValueIsNotNull(editText, "editCustomAmount");
                editText.setText(CommonKt.toEditable("$"));
                ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCustomAmount)).setSelection(1);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void onAmountClicked(View view) {
        if (view != null) {
            ToggleButton toggleButton = (ToggleButton) view;
            if (!toggleButton.isChecked()) {
                toggleButton.setChecked(true);
            }
            List<? extends ToggleButton> list = this.toggles;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toggles");
            }
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                if (!Intrinsics.areEqual((Object) (ToggleButton) next, (Object) view)) {
                    arrayList.add(next);
                }
            }
            for (ToggleButton checked : (List) arrayList) {
                checked.setChecked(false);
            }
            List<? extends ToggleButton> list2 = this.toggles;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toggles");
            }
            if (((ToggleButton) list2.get(3)).isChecked()) {
                CommonKt.show((Group) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.groupCustomAmount));
            } else {
                CommonKt.hide((Group) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.groupCustomAmount));
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ToggleButton");
        }
    }

    /* access modifiers changed from: private */
    public final int getSelectedAmount() {
        List<? extends ToggleButton> list = this.toggles;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toggles");
        }
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (((ToggleButton) next).isChecked()) {
                arrayList.add(next);
            }
        }
        Iterable<ToggleButton> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ToggleButton toggleButton : iterable) {
            List<? extends ToggleButton> list2 = this.toggles;
            if (list2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toggles");
            }
            arrayList2.add(Integer.valueOf(list2.indexOf(toggleButton)));
        }
        return ((Number) CollectionsKt.first((List) arrayList2)).intValue();
    }

    /* access modifiers changed from: private */
    public final void showFaqDialog(int i) {
        new AlertDialog.Builder(requireContext()).setMessage(i).setPositiveButton((int) R.string.got_it_label, (DialogInterface.OnClickListener) ChooseFundsFragment$showFaqDialog$1.INSTANCE).show();
    }

    public boolean onBackPressed() {
        BraintreeFragment braintreeFragment2 = this.braintreeFragment;
        if (braintreeFragment2 != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            requireActivity.getSupportFragmentManager().beginTransaction().remove(braintreeFragment2).commit();
        }
        ChooseFundsPresenter chooseFundsPresenter = this.presenter;
        if (chooseFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return chooseFundsPresenter.onBackPressed();
    }

    public void onDestroyView() {
        StepperView stepperView;
        BottomNavigationViewEx bottomNavigationViewEx;
        ActionBar supportActionBar;
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        CreateAccountActivity createAccountActivity = null;
        if (!(activity instanceof AppCompatActivity)) {
            activity = null;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        if (!(appCompatActivity == null || (supportActionBar = appCompatActivity.getSupportActionBar()) == null)) {
            supportActionBar.setTitle((CharSequence) getString(R.string.title_create_account));
        }
        FragmentActivity activity2 = getActivity();
        if (!(activity2 instanceof MainActivity)) {
            activity2 = null;
        }
        MainActivity mainActivity = (MainActivity) activity2;
        if (!(mainActivity == null || (bottomNavigationViewEx = (BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)) == null)) {
            CommonKt.hide(bottomNavigationViewEx);
        }
        FragmentActivity activity3 = getActivity();
        if (activity3 instanceof CreateAccountActivity) {
            createAccountActivity = activity3;
        }
        CreateAccountActivity createAccountActivity2 = createAccountActivity;
        if (!(createAccountActivity2 == null || (stepperView = (StepperView) createAccountActivity2._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper)) == null)) {
            CommonKt.show(stepperView);
        }
        _$_clearFindViewByIdCache();
    }

    public void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textName");
        textView.setText(CommonKt.toEditable(str));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) str);
            }
            Context requireContext = requireContext();
            Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle)).setImageDrawable(ContextExtensionsKt.createTextDrawable$default(requireContext, StringExtensionsKt.getAsInitials(str), 0, 0, 0, 14, (Object) null));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void setBalance(String str) {
        Intrinsics.checkParameterIsNotNull(str, "balance");
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null)) {
            str = str + ".00";
        }
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textMoney);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textMoney");
        textView.setText(str);
    }

    public void initPaymentScope(String str, String[] strArr) {
        Intrinsics.checkParameterIsNotNull(str, "scope");
        Intrinsics.checkParameterIsNotNull(strArr, "allowedScopes");
        if (ArraysKt.indexOf((T[]) strArr, "inmate") != -1) {
            CommonKt.show((RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_talk_to_me));
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.faq_talk_to_me));
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.pay_near_me_link));
        }
        if (ArraysKt.indexOf((T[]) strArr, "occupant") != -1) {
            CommonKt.show((RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_general_funds));
            CommonKt.show((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.faq_general_funds));
        }
        int hashCode = str.hashCode();
        if (hashCode != -1183974998) {
            if (hashCode == 792741309 && str.equals("occupant")) {
                RadioButton radioButton = (RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_general_funds);
                Intrinsics.checkExpressionValueIsNotNull(radioButton, "btn_general_funds");
                radioButton.setChecked(true);
            }
        } else if (str.equals("inmate")) {
            RadioButton radioButton2 = (RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_talk_to_me);
            Intrinsics.checkExpressionValueIsNotNull(radioButton2, "btn_talk_to_me");
            radioButton2.setChecked(true);
        }
        ((RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_talk_to_me)).setOnCheckedChangeListener(this.paymentScopeChangeListener);
        ((RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_general_funds)).setOnCheckedChangeListener(this.paymentScopeChangeListener);
    }

    public void setPaymentScope(String str) {
        Intrinsics.checkParameterIsNotNull(str, "scope");
        if (Intrinsics.areEqual((Object) str, (Object) "inmate")) {
            RadioButton radioButton = (RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_talk_to_me);
            Intrinsics.checkExpressionValueIsNotNull(radioButton, "btn_talk_to_me");
            radioButton.setChecked(false);
            RadioButton radioButton2 = (RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_talk_to_me);
            Intrinsics.checkExpressionValueIsNotNull(radioButton2, "btn_talk_to_me");
            radioButton2.setChecked(true);
        }
        if (Intrinsics.areEqual((Object) str, (Object) "occupant")) {
            RadioButton radioButton3 = (RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_general_funds);
            Intrinsics.checkExpressionValueIsNotNull(radioButton3, "btn_general_funds");
            radioButton3.setChecked(false);
            RadioButton radioButton4 = (RadioButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_general_funds);
            Intrinsics.checkExpressionValueIsNotNull(radioButton4, "btn_general_funds");
            radioButton4.setChecked(true);
        }
    }

    public void showGeneralFundsConfirmation() {
        ConfirmGeneralFundsDialog newInstance = ConfirmGeneralFundsDialog.Companion.newInstance();
        newInstance.setConfirmListener(this);
        newInstance.show(getChildFragmentManager(), (String) null);
    }

    public void onConfirmed() {
        executePayment();
    }

    public void setFraudState() {
        CommonKt.hide((Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.paymentMethodsSpinner));
        CommonKt.hide((ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonAddFunds));
        CommonKt.hide((Group) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.groupPaymentMethods));
    }

    public void showHandling(float f) {
        String string = getResources().getString(R.string.text_handling_fee, new Object[]{Float.valueOf(f)});
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…t_handling_fee, handling)");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textHandlingFee);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textHandlingFee");
        textView.setText(string);
    }

    public void displayCards(List<Card> list) {
        Intrinsics.checkParameterIsNotNull(list, "cards");
        Iterable<Card> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Card card : iterable) {
            arrayList.add(card.getCardType() + " ending in " + StringsKt.takeLast(card.getLast4(), 2));
        }
        List plus = CollectionsKt.plus((List) arrayList, getResources().getString(R.string.use_another_card));
        Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.paymentMethodsSpinner);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "paymentMethodsSpinner");
        spinner.setAdapter(new ArrayAdapter(requireContext(), R.layout.item_spinner_big, plus));
    }

    public void initPayment(String str) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        BraintreeFragment braintreeFragment2 = this.braintreeFragment;
        if (braintreeFragment2 != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            FragmentManager supportFragmentManager = requireActivity.getSupportFragmentManager();
            supportFragmentManager.beginTransaction().remove(braintreeFragment2).commit();
            supportFragmentManager.executePendingTransactions();
        }
        BraintreeFragment newInstance = BraintreeFragment.newInstance(getActivity(), str);
        newInstance.addListener(new ChooseFundsFragment$initPayment$$inlined$apply$lambda$1(this));
        newInstance.addListener(new ChooseFundsFragment$initPayment$$inlined$apply$lambda$2(this));
        newInstance.addListener(new ChooseFundsFragment$initPayment$$inlined$apply$lambda$3(this));
        PaymentMethod.getPaymentMethodNonces(newInstance);
        this.braintreeFragment = newInstance;
    }

    public void executePayment() {
        ChooseFundsPresenter chooseFundsPresenter = this.presenter;
        if (chooseFundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        int selectedAmount = getSelectedAmount();
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCustomAmount);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editCustomAmount");
        String obj = editText.getText().toString();
        Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.paymentMethodsSpinner);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "paymentMethodsSpinner");
        chooseFundsPresenter.onNextClicked(selectedAmount, obj, spinner.getSelectedItemPosition());
    }

    public void showSuccessMessage() {
        ContextKt.showSnackbar((Fragment) this, getResources().getString(R.string.funds_added));
    }

    public void showErrorMessage() {
        ContextKt.showSnackbar((Fragment) this, getResources().getString(R.string.funds_not_added));
        showProgress(false);
    }

    public void showBraintreeError() {
        String string = getResources().getString(R.string.card_error);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.card_error)");
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        View findViewById = requireActivity.findViewById(16908290);
        View view = null;
        if (!(findViewById instanceof ViewGroup)) {
            findViewById = null;
        }
        ViewGroup viewGroup = (ViewGroup) findViewById;
        if (viewGroup != null) {
            view = viewGroup.getChildAt(0);
        }
        if (view == null) {
            Intrinsics.throwNpe();
        }
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        ContextKt.showGreenSnackbar(string, view, resources);
        showProgress(false);
    }

    public void showProgress(boolean z) {
        ((ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonAddFunds)).setProgressVisible(z);
    }
}
