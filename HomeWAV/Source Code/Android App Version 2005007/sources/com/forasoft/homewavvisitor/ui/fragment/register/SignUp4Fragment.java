package com.forasoft.homewavvisitor.ui.fragment.register;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.HeartbeatService;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.ContactsAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.register.SignUp4Presenter;
import com.forasoft.homewavvisitor.presentation.view.register.SignUp4View;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.account.AccountTabFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.Sdk27PropertiesKt;
import org.jetbrains.anko.internals.AnkoInternals;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u0000 /2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001/B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u0012\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u0012H\u0016J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0012H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\u001a\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0017J\b\u0010%\u001a\u00020\bH\u0007J\u0016\u0010&\u001a\u00020\u00122\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0016J\b\u0010*\u001a\u00020\u0012H\u0016J\b\u0010+\u001a\u00020\u0012H\u0016J\u0010\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020.H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp4Fragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/register/SignUp4View;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "contactsAdapter", "Lcom/forasoft/homewavvisitor/presentation/adapter/ContactsAdapter;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp4Presenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp4Presenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/register/SignUp4Presenter;)V", "progressDialog", "Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;", "showTransfer", "", "hideProgress", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onViewCreated", "view", "providePresenter", "showConnections", "connections", "", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "showProgress", "showSuccessMessage", "showTotalBalance", "balance", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SignUp4Fragment.kt */
public final class SignUp4Fragment extends BaseFragment implements SignUp4View, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EXTRA_SHOW_TRANSFER = "ExtraShowTransfer";
    private HashMap _$_findViewCache;
    private ContactsAdapter contactsAdapter;
    @InjectPresenter
    public SignUp4Presenter presenter;
    private ProgressDialog progressDialog;
    private boolean showTransfer;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp4Fragment$Companion;", "", "()V", "EXTRA_SHOW_TRANSFER", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp4Fragment;", "showExtraTransfer", "", "(Ljava/lang/Boolean;)Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp4Fragment;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SignUp4Fragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SignUp4Fragment newInstance(Boolean bool) {
            SignUp4Fragment signUp4Fragment = new SignUp4Fragment();
            if (bool != null) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(SignUp4Fragment.EXTRA_SHOW_TRANSFER, bool.booleanValue());
                signUp4Fragment.setArguments(bundle);
            }
            return signUp4Fragment;
        }
    }

    public final SignUp4Presenter getPresenter() {
        SignUp4Presenter signUp4Presenter = this.presenter;
        if (signUp4Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return signUp4Presenter;
    }

    public final void setPresenter(SignUp4Presenter signUp4Presenter) {
        Intrinsics.checkParameterIsNotNull(signUp4Presenter, "<set-?>");
        this.presenter = signUp4Presenter;
    }

    @ProvidePresenter
    public final SignUp4Presenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(SignUp4Presenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …Up4Presenter::class.java)");
        return (SignUp4Presenter) instance;
    }

    public void onCreate(Bundle bundle) {
        if (getActivity() instanceof MainActivity) {
            setHasNotificationMenu(true);
        }
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        boolean z = false;
        if (arguments != null) {
            z = arguments.getBoolean(EXTRA_SHOW_TRANSFER, false);
        }
        this.showTransfer = z;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_sign_up4, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        CharSequence charSequence;
        Intrinsics.checkParameterIsNotNull(view, "view");
        boolean z = getActivity() instanceof MainActivity;
        SignUp4Presenter signUp4Presenter = this.presenter;
        if (signUp4Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        this.contactsAdapter = new ContactsAdapter(new SignUp4Fragment$onViewCreated$1(signUp4Presenter), z);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "listContacts");
        ContactsAdapter contactsAdapter2 = this.contactsAdapter;
        if (contactsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsAdapter");
        }
        recyclerView.setAdapter(contactsAdapter2);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.listContacts);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "listContacts");
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        super.onViewCreated(view, bundle);
        if (z) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                MainActivity mainActivity = (MainActivity) activity;
                CommonKt.hide((BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
                ActionBar supportActionBar = mainActivity.getSupportActionBar();
                if (supportActionBar != null) {
                    if (getParentFragment() instanceof AccountTabFragment) {
                        String string = mainActivity.getResources().getString(R.string.label_funds);
                        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.label_funds)");
                        if (string != null) {
                            String lowerCase = string.toLowerCase();
                            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                            charSequence = StringsKt.capitalize(lowerCase);
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    } else {
                        charSequence = mainActivity.getResources().getString(R.string.label_add_funds);
                    }
                    supportActionBar.setTitle(charSequence);
                }
                if (this.showTransfer) {
                    CommonKt.show((ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.transfer_container));
                }
                CommonKt.hide((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip));
                CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepHeader));
                CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textApproval));
                TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPageExplanations);
                textView.setText(textView.getResources().getString(R.string.label_tap_to_add));
                Sdk27PropertiesKt.setBackgroundColor(textView, ContextCompat.getColor(requireContext(), R.color.slightlyGray));
                textView.setGravity(17);
                SignUp4Presenter signUp4Presenter2 = this.presenter;
                if (signUp4Presenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                signUp4Presenter2.getNotificationsCount();
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
            }
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.iv_more);
        Intrinsics.checkExpressionValueIsNotNull(imageView, "iv_more");
        imageView.setOnClickListener(new SignUp4Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp4Fragment$onViewCreated$4(this)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroyView() {
        /*
            r3 = this;
            super.onDestroyView()
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            boolean r1 = r0 instanceof com.forasoft.homewavvisitor.ui.activity.MainActivity
            r2 = 0
            if (r1 != 0) goto L_0x000d
            r0 = r2
        L_0x000d:
            com.forasoft.homewavvisitor.ui.activity.MainActivity r0 = (com.forasoft.homewavvisitor.ui.activity.MainActivity) r0
            if (r0 == 0) goto L_0x001a
            int r1 = com.forasoft.homewavvisitor.R.id.bnv_main
            android.view.View r0 = r0._$_findCachedViewById(r1)
            r2 = r0
            com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx r2 = (com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx) r2
        L_0x001a:
            android.view.View r2 = (android.view.View) r2
            com.forasoft.homewavvisitor.extension.CommonKt.show(r2)
            r3._$_clearFindViewByIdCache()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.register.SignUp4Fragment.onDestroyView():void");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            return onBackPressed();
        }
        if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        }
        SignUp4Presenter signUp4Presenter = this.presenter;
        if (signUp4Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        signUp4Presenter.onNotificationsClicked();
        return true;
    }

    public void onResume() {
        super.onResume();
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.buttonSkip);
        Intrinsics.checkExpressionValueIsNotNull(button, "buttonSkip");
        button.setOnClickListener(new SignUp4Fragment$inlined$sam$i$android_view_View_OnClickListener$0(new SignUp4Fragment$onResume$1(this)));
        if (getActivity() instanceof MainActivity) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                ActionBar supportActionBar = ((MainActivity) activity).getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.setDisplayHomeAsUpEnabled(true);
                    supportActionBar.setDisplayShowHomeEnabled(true);
                }
                SignUp4Presenter signUp4Presenter = this.presenter;
                if (signUp4Presenter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                signUp4Presenter.subscribe(true);
                SignUp4Presenter signUp4Presenter2 = this.presenter;
                if (signUp4Presenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                signUp4Presenter2.refresh();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        AnkoInternals.internalStartService(requireActivity, HeartbeatService.class, new Pair[0]);
        SignUp4Presenter signUp4Presenter3 = this.presenter;
        if (signUp4Presenter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        signUp4Presenter3.subscribe(false);
    }

    public void onPause() {
        super.onPause();
        if (getActivity() instanceof MainActivity) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                ActionBar supportActionBar = ((MainActivity) activity).getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.setDisplayHomeAsUpEnabled(false);
                    supportActionBar.setDisplayShowHomeEnabled(false);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        AnkoInternals.internalStopService(requireActivity, HeartbeatService.class, new Pair[0]);
    }

    public void showTotalBalance(double d) {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_money);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_money");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.ENGLISH");
        String format = String.format(locale, "%.2f", Arrays.copyOf(new Object[]{Double.valueOf(d)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        textView.setText(format);
    }

    public void showConnections(List<InmateByVisitor> list) {
        Intrinsics.checkParameterIsNotNull(list, "connections");
        ContactsAdapter contactsAdapter2 = this.contactsAdapter;
        if (contactsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsAdapter");
        }
        contactsAdapter2.submitList(list);
    }

    public void showSuccessMessage() {
        ContextKt.showSnackbar((Fragment) this, getResources().getString(R.string.funds_added));
    }

    public void showProgress() {
        String string = getResources().getString(R.string.title_checking_payment_options);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…checking_payment_options)");
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

    public boolean onBackPressed() {
        if (!(getActivity() instanceof MainActivity)) {
            return false;
        }
        SignUp4Presenter signUp4Presenter = this.presenter;
        if (signUp4Presenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        signUp4Presenter.onBackPressed();
        return true;
    }
}
