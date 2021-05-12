package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.TermConditionsPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.TermConditionsView;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.activity.register.CreateAccountActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.StepperView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.Sdk27PropertiesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 !2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001!B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0007J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\""}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/TermConditionsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/TermConditionsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/TermConditionsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/TermConditionsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/TermConditionsPresenter;)V", "onBackPressed", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "showTerms", "termsText", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TermConditionsFragment.kt */
public final class TermConditionsFragment extends BaseFragment implements TermConditionsView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int RESULT_SHOW_TERMS_DIALOG = 1830;
    public static final String SHOW_TERMS_DIALOG = "show terms dialog";
    private HashMap _$_findViewCache;
    @InjectPresenter
    public TermConditionsPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/TermConditionsFragment$Companion;", "", "()V", "RESULT_SHOW_TERMS_DIALOG", "", "SHOW_TERMS_DIALOG", "", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/account/TermConditionsFragment;", "isFromTermsDialog", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TermConditionsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TermConditionsFragment newInstance(boolean z) {
            TermConditionsFragment termConditionsFragment = new TermConditionsFragment();
            if (z) {
                Bundle bundle = new Bundle();
                bundle.putBoolean(TermConditionsFragment.SHOW_TERMS_DIALOG, z);
                termConditionsFragment.setArguments(bundle);
            }
            return termConditionsFragment;
        }
    }

    public final TermConditionsPresenter getPresenter() {
        TermConditionsPresenter termConditionsPresenter = this.presenter;
        if (termConditionsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return termConditionsPresenter;
    }

    public final void setPresenter(TermConditionsPresenter termConditionsPresenter) {
        Intrinsics.checkParameterIsNotNull(termConditionsPresenter, "<set-?>");
        this.presenter = termConditionsPresenter;
    }

    @ProvidePresenter
    public final TermConditionsPresenter providePresenter() {
        Object instance = getScope().getInstance(TermConditionsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(TermCo…onsPresenter::class.java)");
        return (TermConditionsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_term_conditions, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…itions, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        BottomNavigationViewEx bottomNavigationViewEx;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.getBoolean(SHOW_TERMS_DIALOG)) {
            TermConditionsPresenter termConditionsPresenter = this.presenter;
            if (termConditionsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            termConditionsPresenter.setShowTermsDialog(true);
        }
        WebView webView = (WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.terms);
        Intrinsics.checkExpressionValueIsNotNull(webView, "terms");
        View view2 = webView;
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Sdk27PropertiesKt.setBackgroundColor(view2, ContextCompat.getColor(context, R.color.slightlyGray));
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
        if (activity2 != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity2;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) appCompatActivity.getString(R.string.title_terms_and_conditions));
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            FragmentActivity activity3 = getActivity();
            if (activity3 instanceof CreateAccountActivity) {
                createAccountActivity = activity3;
            }
            CreateAccountActivity createAccountActivity2 = createAccountActivity;
            if (createAccountActivity2 != null) {
                CommonKt.hide((StepperView) createAccountActivity2._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper));
                TermConditionsPresenter termConditionsPresenter2 = this.presenter;
                if (termConditionsPresenter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("presenter");
                }
                termConditionsPresenter2.setGlobalRouter();
            }
            TermConditionsPresenter termConditionsPresenter3 = this.presenter;
            if (termConditionsPresenter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            termConditionsPresenter3.getTerms();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onDestroyView() {
        BottomNavigationViewEx bottomNavigationViewEx;
        super.onDestroyView();
        FragmentActivity activity = getActivity();
        CreateAccountActivity createAccountActivity = null;
        if (!(activity instanceof MainActivity)) {
            activity = null;
        }
        MainActivity mainActivity = (MainActivity) activity;
        if (!(mainActivity == null || (bottomNavigationViewEx = (BottomNavigationViewEx) mainActivity._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main)) == null)) {
            CommonKt.show(bottomNavigationViewEx);
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 instanceof CreateAccountActivity) {
            createAccountActivity = activity2;
        }
        CreateAccountActivity createAccountActivity2 = createAccountActivity;
        if (createAccountActivity2 != null) {
            ActionBar supportActionBar = createAccountActivity2.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) createAccountActivity2.getString(R.string.title_create_account));
            }
            ActionBar supportActionBar2 = createAccountActivity2.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
            }
            CommonKt.show((StepperView) createAccountActivity2._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.stepper));
        }
        _$_clearFindViewByIdCache();
    }

    public void showTerms(String str) {
        Intrinsics.checkParameterIsNotNull(str, "termsText");
        ((WebView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.terms)).loadDataWithBaseURL("file:///android_asset/", getString(R.string.terms_style_start) + str + getString(R.string.terms_style_end), "text/html", "utf-8", (String) null);
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
        CommonKt.show((ScrollView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.scrollRoot));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        TermConditionsPresenter termConditionsPresenter = this.presenter;
        if (termConditionsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        termConditionsPresenter.onBackPressed();
        return true;
    }

    public boolean onBackPressed() {
        TermConditionsPresenter termConditionsPresenter = this.presenter;
        if (termConditionsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return termConditionsPresenter.onBackPressed();
    }
}
