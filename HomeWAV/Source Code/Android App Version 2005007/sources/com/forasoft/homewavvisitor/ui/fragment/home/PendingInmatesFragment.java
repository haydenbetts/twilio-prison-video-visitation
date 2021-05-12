package com.forasoft.homewavvisitor.ui.fragment.home;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.PendingInmatesAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.home.PendingInmatesPresenter;
import com.forasoft.homewavvisitor.presentation.view.home.PendingInmatesView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.fragment.register.ProgressDialog;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0016J\u001a\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010#\u001a\u00020\u0006H\u0007J\b\u0010$\u001a\u00020\u000eH\u0002J\b\u0010%\u001a\u00020\u000eH\u0002J\u0010\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u0013H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/PendingInmatesFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/home/PendingInmatesView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/home/PendingInmatesPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/home/PendingInmatesPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/home/PendingInmatesPresenter;)V", "progressDialog", "Lcom/forasoft/homewavvisitor/ui/fragment/register/ProgressDialog;", "displayInmates", "", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "setupList", "setupToolbar", "showProgress", "show", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PendingInmatesFragment.kt */
public final class PendingInmatesFragment extends BaseFragment implements PendingInmatesView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public PendingInmatesPresenter presenter;
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

    public final PendingInmatesPresenter getPresenter() {
        PendingInmatesPresenter pendingInmatesPresenter = this.presenter;
        if (pendingInmatesPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return pendingInmatesPresenter;
    }

    public final void setPresenter(PendingInmatesPresenter pendingInmatesPresenter) {
        Intrinsics.checkParameterIsNotNull(pendingInmatesPresenter, "<set-?>");
        this.presenter = pendingInmatesPresenter;
    }

    @ProvidePresenter
    public final PendingInmatesPresenter providePresenter() {
        Object instance = getScope().getInstance(PendingInmatesPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Pendin…tesPresenter::class.java)");
        return (PendingInmatesPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_pending_inmates, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        setupList();
        setupToolbar();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
    }

    public void onDestroyView() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        PendingInmatesPresenter pendingInmatesPresenter = this.presenter;
        if (pendingInmatesPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        pendingInmatesPresenter.onBackPressed();
        return true;
    }

    public void displayInmates(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_inmates);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_inmates");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((PendingInmatesAdapter) adapter).submitList(list);
            if (list.isEmpty()) {
                CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_info));
            } else {
                CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_info));
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.PendingInmatesAdapter");
        }
    }

    public void showProgress(boolean z) {
        if (z) {
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
            return;
        }
        ProgressDialog progressDialog3 = this.progressDialog;
        if (progressDialog3 != null) {
            progressDialog3.dismiss();
        }
    }

    public boolean onBackPressed() {
        PendingInmatesPresenter pendingInmatesPresenter = this.presenter;
        if (pendingInmatesPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return pendingInmatesPresenter.onBackPressed();
    }

    private final void setupList() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_inmates);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        PendingInmatesPresenter pendingInmatesPresenter = this.presenter;
        if (pendingInmatesPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        Function1 pendingInmatesFragment$setupList$1$1 = new PendingInmatesFragment$setupList$1$1(pendingInmatesPresenter);
        PendingInmatesPresenter pendingInmatesPresenter2 = this.presenter;
        if (pendingInmatesPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView.setAdapter(new PendingInmatesAdapter(pendingInmatesFragment$setupList$1$1, new PendingInmatesFragment$setupList$1$2(pendingInmatesPresenter2)));
    }

    private final void setupToolbar() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) appCompatActivity.getString(R.string.label_pending_connection));
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }
}
