package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.DividerItemDecoration;
import com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.account.InmateChooserPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.InmateChooserView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Scope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 %2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001%B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J&\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001e\u001a\u00020\fH\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0016J\u001a\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010$\u001a\u00020\u0006H\u0007R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/InmateChooserAccountFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/InmateChooserView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/InmateChooserPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/InmateChooserPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/InmateChooserPresenter;)V", "displayInmates", "", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "installModules", "scope", "Ltoothpick/Scope;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateChooserAccountFragment.kt */
public final class InmateChooserAccountFragment extends BaseFragment implements InmateChooserView, OnBackButtonPressedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String EXTRA_TITLE = "argumentTitle";
    private static final String INMATE = "inmate";
    private HashMap _$_findViewCache;
    @InjectPresenter
    public InmateChooserPresenter presenter;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/InmateChooserAccountFragment$Companion;", "", "()V", "EXTRA_TITLE", "", "INMATE", "newInstance", "Lcom/forasoft/homewavvisitor/ui/fragment/account/InmateChooserAccountFragment;", "title", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: InmateChooserAccountFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InmateChooserAccountFragment newInstance(String str, Inmate inmate) {
            Intrinsics.checkParameterIsNotNull(str, "title");
            InmateChooserAccountFragment inmateChooserAccountFragment = new InmateChooserAccountFragment();
            Bundle bundle = new Bundle();
            bundle.putString(InmateChooserAccountFragment.EXTRA_TITLE, str);
            bundle.putParcelable(InmateChooserAccountFragment.INMATE, inmate);
            inmateChooserAccountFragment.setArguments(bundle);
            return inmateChooserAccountFragment;
        }
    }

    /* access modifiers changed from: protected */
    public void installModules(Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        scope.installModules(new InmateChooserAccountFragment$installModules$1(this));
    }

    public final InmateChooserPresenter getPresenter() {
        InmateChooserPresenter inmateChooserPresenter = this.presenter;
        if (inmateChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return inmateChooserPresenter;
    }

    public final void setPresenter(InmateChooserPresenter inmateChooserPresenter) {
        Intrinsics.checkParameterIsNotNull(inmateChooserPresenter, "<set-?>");
        this.presenter = inmateChooserPresenter;
    }

    @ProvidePresenter
    public final InmateChooserPresenter providePresenter() {
        Object instance = getScope().getInstance(InmateChooserPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Inmate…serPresenter::class.java)");
        return (InmateChooserPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_inmate_chooser, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        String str;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_list);
        InmateChooserPresenter inmateChooserPresenter = this.presenter;
        if (inmateChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView.setAdapter(new InmatesWithBalanceAdapter(new InmateChooserAccountFragment$onViewCreated$1$1(inmateChooserPresenter)));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                Bundle arguments = getArguments();
                if (arguments == null || (str = arguments.getString(EXTRA_TITLE)) == null) {
                    str = "";
                }
                supportActionBar.setTitle((CharSequence) str);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayShowHomeEnabled(true);
            }
            ActionBar supportActionBar3 = appCompatActivity.getSupportActionBar();
            if (supportActionBar3 != null) {
                supportActionBar3.setDisplayHomeAsUpEnabled(true);
            }
            CommonKt.hide((BottomNavigationViewEx) appCompatActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onDestroyView() {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayShowHomeEnabled(false);
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                ActionBar supportActionBar2 = ((AppCompatActivity) activity2).getSupportActionBar();
                if (supportActionBar2 != null) {
                    supportActionBar2.setDisplayHomeAsUpEnabled(false);
                }
                super.onDestroyView();
                _$_clearFindViewByIdCache();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        return onBackPressed();
    }

    public void displayInmates(List<Inmate> list) {
        Intrinsics.checkParameterIsNotNull(list, "inmates");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_list");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((InmatesWithBalanceAdapter) adapter).submitList(list);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.account.InmatesWithBalanceAdapter");
    }

    public boolean onBackPressed() {
        InmateChooserPresenter inmateChooserPresenter = this.presenter;
        if (inmateChooserPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        inmateChooserPresenter.onBackPressed();
        return true;
    }
}
