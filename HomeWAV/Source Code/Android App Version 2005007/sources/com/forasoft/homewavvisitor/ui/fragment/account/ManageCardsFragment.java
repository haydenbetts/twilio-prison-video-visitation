package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.MaterialDialog;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.account.EditCardsAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.account.ManageCardsPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.ManageCardsView;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010 \u001a\u00020\fH\u0016J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0016J\u001a\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u001c2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010&\u001a\u00020\u0006H\u0007J\u0010\u0010'\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u000fH\u0002J\b\u0010)\u001a\u00020\fH\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006*"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/ManageCardsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/ManageCardsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/ManageCardsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/ManageCardsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/ManageCardsPresenter;)V", "displayCards", "", "cards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "hideProgress", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "showConfirmDialog", "card", "showProgress", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ManageCardsFragment.kt */
public final class ManageCardsFragment extends BaseFragment implements ManageCardsView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public ManageCardsPresenter presenter;

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

    public final ManageCardsPresenter getPresenter() {
        ManageCardsPresenter manageCardsPresenter = this.presenter;
        if (manageCardsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return manageCardsPresenter;
    }

    public final void setPresenter(ManageCardsPresenter manageCardsPresenter) {
        Intrinsics.checkParameterIsNotNull(manageCardsPresenter, "<set-?>");
        this.presenter = manageCardsPresenter;
    }

    @ProvidePresenter
    public final ManageCardsPresenter providePresenter() {
        Object instance = getScope().getInstance(ManageCardsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Manage…rdsPresenter::class.java)");
        return (ManageCardsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_manage_cards, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…_cards, container, false)");
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.hide((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_manage_cards);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
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

    public void onDestroyView() {
        super.onDestroyView();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        _$_clearFindViewByIdCache();
    }

    public void displayCards(List<Card> list) {
        Intrinsics.checkParameterIsNotNull(list, "cards");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "recyclerView");
        Function1 manageCardsFragment$displayCards$1 = new ManageCardsFragment$displayCards$1(this);
        ManageCardsPresenter manageCardsPresenter = this.presenter;
        if (manageCardsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        EditCardsAdapter editCardsAdapter = new EditCardsAdapter(manageCardsFragment$displayCards$1, new ManageCardsFragment$displayCards$2(manageCardsPresenter));
        editCardsAdapter.submitList(list);
        recyclerView2.setAdapter(editCardsAdapter);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "recyclerView");
        CommonKt.clearDecorations(recyclerView3);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.item_decoration);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        dividerItemDecoration.setDrawable(drawable);
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView)).addItemDecoration(dividerItemDecoration);
    }

    /* access modifiers changed from: private */
    public final void showConfirmDialog(Card card) {
        new MaterialDialog.Builder(requireContext()).title((int) R.string.label_delete_card).content((int) R.string.label_delete_card_body).positiveText((int) R.string.label_delete_card).negativeText((int) R.string.label_keep_card).cancelable(false).onPositive(new ManageCardsFragment$showConfirmDialog$1(this, card)).build().show();
    }

    public void showProgress() {
        String string = getResources().getString(R.string.title_updating_cards);
        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.string.title_updating_cards)");
        ProgressDialog progressDialog = new ProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ProgressDialog.EXTRA_TITLE, string);
        progressDialog.setArguments(bundle);
        progressDialog.show(getChildFragmentManager(), ProgressDialog.TAG);
    }

    public void hideProgress() {
        Fragment findFragmentByTag = getChildFragmentManager().findFragmentByTag(ProgressDialog.TAG);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        if (findFragmentByTag == null) {
            Intrinsics.throwNpe();
        }
        beginTransaction.remove(findFragmentByTag).commit();
    }

    public boolean onBackPressed() {
        ManageCardsPresenter manageCardsPresenter = this.presenter;
        if (manageCardsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        manageCardsPresenter.onBackPressed();
        return true;
    }
}
