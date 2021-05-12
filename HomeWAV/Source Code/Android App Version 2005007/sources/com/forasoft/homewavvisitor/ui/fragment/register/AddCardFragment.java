package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.models.CardBuilder;
import com.forasoft.homewavvisitor.BuildConfig;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.payment.PaymentState;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.AddCardPresenter;
import com.forasoft.homewavvisitor.presentation.view.AddCardView;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.payment.ConfirmGeneralFundsDialog;
import com.forasoft.homewavvisitor.ui.views.ProgressButton;
import com.forasoft.homewavvisitor.ui.views.StepperView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.redmadrobot.inputmask.MaskedTextChangedListener;
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy;
import com.stripe.android.Stripe;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\f\u0018\u0000 F2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001FB\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\u0010\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0015H\u0016J\b\u0010\"\u001a\u00020\u0019H\u0016J\b\u0010#\u001a\u00020\u0019H\u0002J\u0012\u0010$\u001a\u00020\u00192\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0018\u0010'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J$\u0010,\u001a\u00020-2\u0006\u0010*\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u00101\u001a\u00020\u0019H\u0016J\u0010\u00102\u001a\u00020\u00152\u0006\u00103\u001a\u000204H\u0016J\b\u00105\u001a\u00020\u0019H\u0016J\u0010\u00106\u001a\u00020\u00192\u0006\u00107\u001a\u00020&H\u0016J\u001a\u00108\u001a\u00020\u00192\u0006\u00109\u001a\u00020-2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010:\u001a\u00020\u000fH\u0007J\b\u0010;\u001a\u00020\u0019H\u0016J\b\u0010<\u001a\u00020\u0019H\u0002J\u0010\u0010=\u001a\u00020\u00192\u0006\u0010>\u001a\u00020\u0015H\u0016J\b\u0010?\u001a\u00020\u0019H\u0002J\u0010\u0010@\u001a\u00020\u00192\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020\u00192\u0006\u0010D\u001a\u00020EH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158BX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006G"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/AddCardView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "Lcom/forasoft/homewavvisitor/ui/fragment/payment/ConfirmGeneralFundsDialog$OnConfirmListener;", "()V", "braintreeFragment", "Lcom/braintreepayments/api/BraintreeFragment;", "cardSystems", "", "Landroid/widget/ImageView;", "expiresTextWatcher", "com/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment$expiresTextWatcher$1", "Lcom/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment$expiresTextWatcher$1;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/AddCardPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/AddCardPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/AddCardPresenter;)V", "shouldInitializeBraintree", "", "getShouldInitializeBraintree", "()Z", "addCardSystemWithIndex", "", "index", "", "clearCardSystems", "hideZipField", "initPayment", "token", "", "onBackPressed", "onConfirmed", "onContinueClicked", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onSaveInstanceState", "outState", "onViewCreated", "view", "providePresenter", "showCardTypes", "showGeneralFundsConfirmation", "showProgress", "show", "showZipField", "tokenize", "cardBuilder", "Lcom/braintreepayments/api/models/CardBuilder;", "tokenizeStripe", "card", "Lcom/stripe/android/model/Card;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AddCardFragment.kt */
public final class AddCardFragment extends BaseFragment implements AddCardView, OnBackButtonPressedListener, ConfirmGeneralFundsDialog.OnConfirmListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String PAYMENT_SCOPE = "payment scope";
    private HashMap _$_findViewCache;
    private BraintreeFragment braintreeFragment;
    private List<? extends ImageView> cardSystems;
    private final AddCardFragment$expiresTextWatcher$1 expiresTextWatcher = new AddCardFragment$expiresTextWatcher$1(this);
    @InjectPresenter
    public AddCardPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment$Companion;", "", "()V", "PAYMENT_SCOPE", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/register/AddCardFragment;", "paymentScope", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AddCardFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ AddCardFragment newInstance$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = null;
            }
            return companion.newInstance(str);
        }

        public final AddCardFragment newInstance(String str) {
            AddCardFragment addCardFragment = new AddCardFragment();
            if (str != null) {
                Bundle bundle = new Bundle();
                bundle.putString(AddCardFragment.PAYMENT_SCOPE, str);
                addCardFragment.setArguments(bundle);
            }
            return addCardFragment;
        }
    }

    public final AddCardPresenter getPresenter() {
        AddCardPresenter addCardPresenter = this.presenter;
        if (addCardPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return addCardPresenter;
    }

    public final void setPresenter(AddCardPresenter addCardPresenter) {
        Intrinsics.checkParameterIsNotNull(addCardPresenter, "<set-?>");
        this.presenter = addCardPresenter;
    }

    @ProvidePresenter
    public final AddCardPresenter providePresenter() {
        Object instance = getScope().getInstance(AddCardPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(AddCardPresenter::class.java)");
        return (AddCardPresenter) instance;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean getShouldInitializeBraintree() {
        /*
            r4 = this;
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            r1 = 0
            if (r0 == 0) goto L_0x0035
            androidx.fragment.app.FragmentManager r0 = r0.getSupportFragmentManager()
            if (r0 == 0) goto L_0x0035
            java.util.List r0 = r0.getFragments()
            if (r0 == 0) goto L_0x0035
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0019:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x002b
            java.lang.Object r2 = r0.next()
            r3 = r2
            androidx.fragment.app.Fragment r3 = (androidx.fragment.app.Fragment) r3
            boolean r3 = r3 instanceof com.forasoft.homewavvisitor.ui.fragment.BaseTabFragment
            if (r3 == 0) goto L_0x0019
            goto L_0x002c
        L_0x002b:
            r2 = r1
        L_0x002c:
            androidx.fragment.app.Fragment r2 = (androidx.fragment.app.Fragment) r2
            if (r2 == 0) goto L_0x0035
            androidx.fragment.app.FragmentManager r0 = r2.getChildFragmentManager()
            goto L_0x0036
        L_0x0035:
            r0 = r1
        L_0x0036:
            r2 = 0
            if (r0 == 0) goto L_0x0040
            int r3 = r0.getBackStackEntryCount()
            if (r3 != 0) goto L_0x0040
            goto L_0x004c
        L_0x0040:
            if (r0 == 0) goto L_0x004c
            androidx.fragment.app.FragmentManager$BackStackEntry r0 = r0.getBackStackEntryAt(r2)
            if (r0 == 0) goto L_0x004c
            java.lang.String r1 = r0.getName()
        L_0x004c:
            androidx.fragment.app.FragmentActivity r0 = r4.getActivity()
            boolean r0 = r0 instanceof com.forasoft.homewavvisitor.ui.activity.MainActivity
            if (r0 == 0) goto L_0x005e
            com.forasoft.homewavvisitor.navigation.Screens$PaymentMethodsScreen r0 = com.forasoft.homewavvisitor.navigation.Screens.PaymentMethodsScreen.INSTANCE
            java.lang.String r0 = r0.getScreenKey()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
        L_0x005e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.register.AddCardFragment.getShouldInitializeBraintree():boolean");
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
        if (bundle != null) {
            AddCardPresenter addCardPresenter = this.presenter;
            if (addCardPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            Parcelable parcelable = bundle.getParcelable(PaymentGateway.PAYMENT_STATE);
            if (parcelable == null) {
                Intrinsics.throwNpe();
            }
            addCardPresenter.setPaymentState((PaymentState) parcelable);
        }
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

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_add_card, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…d_card, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        BottomNavigationViewEx bottomNavigationViewEx;
        StepperView stepperView;
        Intrinsics.checkParameterIsNotNull(view, "view");
        List<? extends ImageView> arrayListOf = CollectionsKt.arrayListOf((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageMastercard), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageMaestro), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageVisa), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageAmerican), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageDci), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageDiscover), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.imageJcb));
        this.cardSystems = arrayListOf;
        if (arrayListOf == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardSystems");
        }
        for (ImageView hide : arrayListOf) {
            CommonKt.hide(hide);
        }
        FragmentActivity activity = getActivity();
        if (!(activity == null || (stepperView = (StepperView) activity.findViewById(R.id.stepper)) == null)) {
            CommonKt.hide(stepperView);
        }
        FragmentActivity activity2 = getActivity();
        if (!(activity2 == null || (bottomNavigationViewEx = (BottomNavigationViewEx) activity2.findViewById(R.id.bnv_main)) == null)) {
            CommonKt.hide(bottomNavigationViewEx);
        }
        Bundle arguments = getArguments();
        CreateAccountActivity createAccountActivity = null;
        String string = arguments != null ? arguments.getString(PAYMENT_SCOPE) : null;
        if (string != null) {
            AddCardPresenter addCardPresenter = this.presenter;
            if (addCardPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            addCardPresenter.setPaymentScope(string);
        }
        AddCardPresenter addCardPresenter2 = this.presenter;
        if (addCardPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        addCardPresenter2.initializePayments();
        if (getShouldInitializeBraintree()) {
            CommonKt.hide((CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkSaveCard));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelSaveCard));
        } else {
            String string2 = getResources().getString(R.string.label_add_funds);
            Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.string.label_add_funds)");
            ((ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonContinue)).setText(string2);
            CommonKt.hide((CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkMakeDefault));
            CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelMakeDefault));
        }
        FragmentActivity activity3 = getActivity();
        if (activity3 instanceof CreateAccountActivity) {
            createAccountActivity = activity3;
        }
        if (createAccountActivity != null) {
            AddCardPresenter addCardPresenter3 = this.presenter;
            if (addCardPresenter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            addCardPresenter3.setGlobalRouter();
        }
        FragmentActivity activity4 = getActivity();
        if (activity4 != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity4).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) getString(R.string.label_add_card));
            }
            CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkSaveCard);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkSaveCard");
            checkBox.setOnCheckedChangeListener(new AddCardFragment$inlined$sam$i$android_widget_CompoundButton_OnCheckedChangeListener$0(new AddCardFragment$onViewCreated$3(this)));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onResume() {
        super.onResume();
        MaskedTextChangedListener.Companion companion = MaskedTextChangedListener.Companion;
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCardNumber);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editCardNumber");
        companion.installOn(editText, "", CollectionsKt.listOf("35[00] [0000] [0000] [0000] [999]", "3[000] [000000] [00009]", "[0000] [0000] [0000] [0000] [999]"), AffinityCalculationStrategy.PREFIX, new AddCardFragment$onResume$1(this));
        ProgressButton progressButton = (ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonContinue);
        Intrinsics.checkExpressionValueIsNotNull(progressButton, "buttonContinue");
        progressButton.setOnClickListener(new AddCardFragment$inlined$sam$i$android_view_View_OnClickListener$0(new AddCardFragment$onResume$2(this)));
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editExpires)).addTextChangedListener(this.expiresTextWatcher);
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCvv);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editCvv");
        editText2.setOnEditorActionListener(new AddCardFragment$inlined$sam$i$android_widget_TextView_OnEditorActionListener$0(new AddCardFragment$onResume$3(this)));
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "outState");
        super.onSaveInstanceState(bundle);
        AddCardPresenter addCardPresenter = this.presenter;
        if (addCardPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        bundle.putParcelable(PaymentGateway.PAYMENT_STATE, addCardPresenter.getPaymentState());
    }

    public void onDestroyView() {
        ActionBar supportActionBar;
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        if (!(activity instanceof AppCompatActivity)) {
            activity = null;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        if (!(appCompatActivity == null || (supportActionBar = appCompatActivity.getSupportActionBar()) == null)) {
            supportActionBar.setTitle((CharSequence) getString(R.string.title_create_account));
        }
        _$_clearFindViewByIdCache();
    }

    /* access modifiers changed from: private */
    public final void showGeneralFundsConfirmation() {
        ConfirmGeneralFundsDialog newInstance = ConfirmGeneralFundsDialog.Companion.newInstance();
        newInstance.setConfirmListener(this);
        newInstance.show(getChildFragmentManager(), (String) null);
    }

    public void onConfirmed() {
        onContinueClicked();
    }

    /* access modifiers changed from: private */
    public final void onContinueClicked() {
        String str;
        boolean z;
        AddCardPresenter addCardPresenter = this.presenter;
        if (addCardPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCardNumber);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editCardNumber");
        String obj = editText.getText().toString();
        if (obj != null) {
            String obj2 = StringsKt.trim((CharSequence) obj).toString();
            EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCvv);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "editCvv");
            String obj3 = editText2.getText().toString();
            EditText editText3 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editExpires);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "editExpires");
            String obj4 = editText3.getText().toString();
            EditText editText4 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
            Intrinsics.checkExpressionValueIsNotNull(editText4, "editZip");
            if (CommonKt.isVisible(editText4)) {
                EditText editText5 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
                Intrinsics.checkExpressionValueIsNotNull(editText5, "editZip");
                str = editText5.getText().toString();
            } else {
                str = null;
            }
            CheckBox checkBox = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkSaveCard);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "checkSaveCard");
            if (CommonKt.isVisible(checkBox)) {
                CheckBox checkBox2 = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkSaveCard);
                Intrinsics.checkExpressionValueIsNotNull(checkBox2, "checkSaveCard");
                z = checkBox2.isChecked();
            } else {
                z = true;
            }
            CheckBox checkBox3 = (CheckBox) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.checkMakeDefault);
            Intrinsics.checkExpressionValueIsNotNull(checkBox3, "checkMakeDefault");
            addCardPresenter.onContinueClicked(obj2, obj3, obj4, str, z, checkBox3.isChecked());
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    public void showCardTypes() {
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelCardType));
        CommonKt.show((Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cardTypesSpinner));
        showZipField();
        String[] strArr = {getString(R.string.credit_card), getString(R.string.debit_card), getString(R.string.prepaid_card)};
        Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cardTypesSpinner);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "cardTypesSpinner");
        spinner.setAdapter(new ArrayAdapter(requireContext(), R.layout.item_spinner_big, strArr));
        Spinner spinner2 = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.cardTypesSpinner);
        Intrinsics.checkExpressionValueIsNotNull(spinner2, "cardTypesSpinner");
        spinner2.setOnItemSelectedListener(new AddCardFragment$showCardTypes$1(this));
    }

    /* access modifiers changed from: private */
    public final void showZipField() {
        CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelZip));
        CommonKt.show((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip));
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCvv);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editCvv");
        editText.setImeOptions(5);
    }

    /* access modifiers changed from: private */
    public final void hideZipField() {
        CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.labelZip));
        CommonKt.hide((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip));
        EditText editText = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editZip);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editZip");
        editText.getText().clear();
        EditText editText2 = (EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.editCvv);
        Intrinsics.checkExpressionValueIsNotNull(editText2, "editCvv");
        editText2.setImeOptions(6);
    }

    public void addCardSystemWithIndex(int i) {
        List<? extends ImageView> list = this.cardSystems;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardSystems");
        }
        CommonKt.show((View) list.get(i));
    }

    public void clearCardSystems() {
        List<? extends ImageView> list = this.cardSystems;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("cardSystems");
        }
        for (ImageView hide : list) {
            CommonKt.hide(hide);
        }
    }

    public void tokenize(CardBuilder cardBuilder) {
        Intrinsics.checkParameterIsNotNull(cardBuilder, "cardBuilder");
        Card.tokenize(this.braintreeFragment, cardBuilder);
    }

    public void tokenizeStripe(com.stripe.android.model.Card card) {
        Intrinsics.checkParameterIsNotNull(card, "card");
        new Stripe(requireContext(), BuildConfig.STRIPE_PUBLISHABLE_KEY).createToken(card, new AddCardFragment$tokenizeStripe$1(this));
    }

    public void initPayment(String str) {
        Intrinsics.checkParameterIsNotNull(str, "token");
        BraintreeFragment newInstance = BraintreeFragment.newInstance(getActivity(), str);
        newInstance.addListener(new AddCardFragment$initPayment$$inlined$apply$lambda$1(this));
        newInstance.addListener(new AddCardFragment$initPayment$$inlined$apply$lambda$2(this));
        this.braintreeFragment = newInstance;
    }

    public void showProgress(boolean z) {
        ((ProgressButton) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonContinue)).setProgressVisible(z);
    }

    public boolean onBackPressed() {
        BraintreeFragment braintreeFragment2;
        if (getShouldInitializeBraintree() && (braintreeFragment2 = this.braintreeFragment) != null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            requireActivity.getSupportFragmentManager().beginTransaction().remove(braintreeFragment2).commit();
        }
        AddCardPresenter addCardPresenter = this.presenter;
        if (addCardPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        addCardPresenter.onBackPressed();
        return true;
    }
}
