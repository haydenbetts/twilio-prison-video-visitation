package com.forasoft.homewavvisitor.ui.fragment.home;

import air.HomeWAV.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.tutorials.TutorialsAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.home.TutorialsPresenter;
import com.forasoft.homewavvisitor.presentation.view.home.TutorialsView;
import com.forasoft.homewavvisitor.ui.MarginItemDecoration;
import com.forasoft.homewavvisitor.ui.activity.MainActivity;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import org.jetbrains.anko.DimensionsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u001d\u001a\u00020\u0006H\u0007J\b\u0010\u001e\u001a\u00020\u000eH\u0016J\u0016\u0010\u001f\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016J\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006$"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/home/TutorialsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/home/TutorialsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/home/TutorialsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/home/TutorialsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/home/TutorialsPresenter;)V", "onBackPressed", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "showError", "showTutorials", "tutorials", "", "", "sortTutorials", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TutorialsFragment.kt */
public final class TutorialsFragment extends BaseFragment implements TutorialsView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public TutorialsPresenter presenter;

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

    public final TutorialsPresenter getPresenter() {
        TutorialsPresenter tutorialsPresenter = this.presenter;
        if (tutorialsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return tutorialsPresenter;
    }

    public final void setPresenter(TutorialsPresenter tutorialsPresenter) {
        Intrinsics.checkParameterIsNotNull(tutorialsPresenter, "<set-?>");
        this.presenter = tutorialsPresenter;
    }

    @ProvidePresenter
    public final TutorialsPresenter providePresenter() {
        Object instance = getScope().getInstance(TutorialsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(TutorialsPresenter::class.java)");
        return (TutorialsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_tutorials, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tutorials_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "tutorials_list");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tutorials_list)).addItemDecoration(new MarginItemDecoration(DimensionsKt.dimen((Context) requireActivity, (int) R.dimen.base_offset)));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tutorials_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "tutorials_list");
        TutorialsPresenter tutorialsPresenter = this.presenter;
        if (tutorialsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView2.setAdapter(new TutorialsAdapter(new TutorialsFragment$onViewCreated$1(tutorialsPresenter)));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setTitle((int) R.string.label_tutorials);
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                CommonKt.hide((BottomNavigationViewEx) ((MainActivity) activity2)._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onDestroyView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            CommonKt.show((BottomNavigationViewEx) ((MainActivity) activity)._$_findCachedViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
            super.onDestroyView();
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.ui.activity.MainActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        TutorialsPresenter tutorialsPresenter = this.presenter;
        if (tutorialsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        tutorialsPresenter.onBackPressed();
        return true;
    }

    public void showTutorials(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "tutorials");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tutorials_list);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "tutorials_list");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((TutorialsAdapter) adapter).submitList(sortTutorials(list));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.tutorials.TutorialsAdapter");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> sortTutorials(java.util.List<java.lang.String> r9) {
        /*
            r8 = this;
            r0 = r9
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r1 = r0.iterator()
        L_0x0007:
            boolean r2 = r1.hasNext()
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0023
            java.lang.Object r2 = r1.next()
            r5 = r2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.String r6 = "account"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r5 = kotlin.text.StringsKt.contains((java.lang.CharSequence) r5, (java.lang.CharSequence) r6, (boolean) r4)
            if (r5 == 0) goto L_0x0007
            goto L_0x0024
        L_0x0023:
            r2 = r3
        L_0x0024:
            java.lang.String r2 = (java.lang.String) r2
            java.util.Iterator r1 = r0.iterator()
        L_0x002a:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x0045
            java.lang.Object r5 = r1.next()
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r7 = "visit"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r6 = kotlin.text.StringsKt.contains((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, (boolean) r4)
            if (r6 == 0) goto L_0x002a
            goto L_0x0046
        L_0x0045:
            r5 = r3
        L_0x0046:
            java.lang.String r5 = (java.lang.String) r5
            java.util.Iterator r0 = r0.iterator()
        L_0x004c:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0066
            java.lang.Object r1 = r0.next()
            r6 = r1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r7 = "call"
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            boolean r6 = kotlin.text.StringsKt.contains((java.lang.CharSequence) r6, (java.lang.CharSequence) r7, (boolean) r4)
            if (r6 == 0) goto L_0x004c
            r3 = r1
        L_0x0066:
            java.lang.String r3 = (java.lang.String) r3
            if (r2 == 0) goto L_0x007e
            if (r5 == 0) goto L_0x007e
            if (r3 != 0) goto L_0x006f
            goto L_0x007e
        L_0x006f:
            r9 = 3
            java.lang.String[] r9 = new java.lang.String[r9]
            r0 = 0
            r9[r0] = r2
            r9[r4] = r5
            r0 = 2
            r9[r0] = r3
            java.util.List r9 = kotlin.collections.CollectionsKt.listOf(r9)
        L_0x007e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.ui.fragment.home.TutorialsFragment.sortTutorials(java.util.List):java.util.List");
    }

    public void showError() {
        ContextKt.showSnackbar((Fragment) this, getString(R.string.something_error));
    }

    public boolean onBackPressed() {
        TutorialsPresenter tutorialsPresenter = this.presenter;
        if (tutorialsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        tutorialsPresenter.onBackPressed();
        return true;
    }
}
