package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.presenter.account.RefundsPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.RefundsView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u0019\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016J&\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u001f\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010&\u001a\u00020\u000eH\u0016J0\u0010'\u001a\u00020\u000e2\f\u0010(\u001a\b\u0012\u0002\b\u0003\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010\"2\u0006\u0010+\u001a\u00020\u00142\u0006\u0010,\u001a\u00020-H\u0016J\u0016\u0010.\u001a\u00020\u000e2\f\u0010(\u001a\b\u0012\u0002\b\u0003\u0018\u00010)H\u0016J\u0010\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u000201H\u0016J(\u00102\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u001a\u00104\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\"2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u00105\u001a\u00020\bH\u0007J\u0016\u00106\u001a\u00020\u000e2\f\u00107\u001a\b\u0012\u0004\u0012\u00020908H\u0016J\u0010\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u000209H\u0016J\u0010\u0010<\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020>H\u0016R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006?"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/RefundsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/RefundsView;", "Landroid/text/TextWatcher;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/RefundsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/RefundsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/RefundsPresenter;)V", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onItemSelected", "parent", "Landroid/widget/AdapterView;", "view", "position", "id", "", "onNothingSelected", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onTextChanged", "before", "onViewCreated", "providePresenter", "showInmates", "inmates", "", "", "showMessage", "message", "updateTotalAmount", "amount", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RefundsFragment.kt */
public final class RefundsFragment extends BaseFragment implements RefundsView, TextWatcher, AdapterView.OnItemSelectedListener, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public RefundsPresenter presenter;

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

    public void afterTextChanged(Editable editable) {
        Intrinsics.checkParameterIsNotNull(editable, "s");
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "s");
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public final RefundsPresenter getPresenter() {
        RefundsPresenter refundsPresenter = this.presenter;
        if (refundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return refundsPresenter;
    }

    public final void setPresenter(RefundsPresenter refundsPresenter) {
        Intrinsics.checkParameterIsNotNull(refundsPresenter, "<set-?>");
        this.presenter = refundsPresenter;
    }

    @ProvidePresenter
    public final RefundsPresenter providePresenter() {
        Object instance = getScope().getInstance(RefundsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(RefundsPresenter::class.java)");
        return (RefundsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_refunds, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity2, "requireActivity()");
        ((Toolbar) requireActivity2.findViewById(com.forasoft.homewavvisitor.R.id.toolbar)).setTitle((int) R.string.title_refunds);
        ((EditText) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.et_amount)).addTextChangedListener(this);
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_request);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_request");
        button.setOnClickListener(new RefundsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new RefundsFragment$onViewCreated$1(this)));
        Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinner_inmates);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "spinner_inmates");
        spinner.setOnItemSelectedListener(this);
    }

    public void onDestroyView() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        Intrinsics.checkParameterIsNotNull(menuInflater, "inflater");
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_close, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        RefundsPresenter refundsPresenter = this.presenter;
        if (refundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        refundsPresenter.onBackPressed();
        return true;
    }

    public void showInmates(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinner_inmates);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "spinner_inmates");
        spinner.setAdapter(new ArrayAdapter(requireContext(), R.layout.item_simple_list, list));
    }

    public void updateTotalAmount(float f) {
        String str = f < ((float) 0) ? "-" : "";
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_total_amount);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_total_amount");
        textView.setText(str + getResources().getString(R.string.label_total_amount, new Object[]{Float.valueOf(Math.abs(f))}));
    }

    public void showMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        ContextKt.showSnackbar((Fragment) this, str);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(charSequence, "s");
        RefundsPresenter refundsPresenter = this.presenter;
        if (refundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        refundsPresenter.onAmountChanged(charSequence.toString());
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        RefundsPresenter refundsPresenter = this.presenter;
        if (refundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        refundsPresenter.onInmateSelected(i);
    }

    public boolean onBackPressed() {
        RefundsPresenter refundsPresenter = this.presenter;
        if (refundsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        refundsPresenter.onBackPressed();
        return true;
    }
}
