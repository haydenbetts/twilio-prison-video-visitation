package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.account.CardsAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.account.PaymentMethodsPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.PaymentMethodsView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u00162\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010!\u001a\u00020\u0006H\u0007R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\""}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/PaymentMethodsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/PaymentMethodsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/PaymentMethodsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/PaymentMethodsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/PaymentMethodsPresenter;)V", "displayCards", "", "cards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentMethodsFragment.kt */
public final class PaymentMethodsFragment extends BaseFragment implements PaymentMethodsView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public PaymentMethodsPresenter presenter;

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

    public final PaymentMethodsPresenter getPresenter() {
        PaymentMethodsPresenter paymentMethodsPresenter = this.presenter;
        if (paymentMethodsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return paymentMethodsPresenter;
    }

    public final void setPresenter(PaymentMethodsPresenter paymentMethodsPresenter) {
        Intrinsics.checkParameterIsNotNull(paymentMethodsPresenter, "<set-?>");
        this.presenter = paymentMethodsPresenter;
    }

    @ProvidePresenter
    public final PaymentMethodsPresenter providePresenter() {
        Object instance = getScope().getInstance(PaymentMethodsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Paymen…odsPresenter::class.java)");
        return (PaymentMethodsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_payment_methods, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…ethods, container, false)");
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
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_payment_methods);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(true);
            }
            PaymentMethodsPresenter paymentMethodsPresenter = this.presenter;
            if (paymentMethodsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            paymentMethodsPresenter.getNotificationsCount();
            Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_manage_cards);
            Intrinsics.checkExpressionValueIsNotNull(button, "btn_manage_cards");
            button.setEnabled(false);
            Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_manage_cards);
            Intrinsics.checkExpressionValueIsNotNull(button2, "btn_manage_cards");
            button2.setOnClickListener(new PaymentMethodsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new PaymentMethodsFragment$onViewCreated$2(this)));
            Button button3 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_add_card);
            Intrinsics.checkExpressionValueIsNotNull(button3, "btn_add_card");
            button3.setOnClickListener(new PaymentMethodsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new PaymentMethodsFragment$onViewCreated$3(this)));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            PaymentMethodsPresenter paymentMethodsPresenter = this.presenter;
            if (paymentMethodsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            paymentMethodsPresenter.onBackPressed();
            return true;
        } else if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            PaymentMethodsPresenter paymentMethodsPresenter2 = this.presenter;
            if (paymentMethodsPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            paymentMethodsPresenter2.onNotificationsClicked();
            return true;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        CommonKt.show((BottomNavigationViewEx) requireActivity.findViewById(com.forasoft.homewavvisitor.R.id.bnv_main));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(false);
            }
            _$_clearFindViewByIdCache();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void displayCards(List<Card> list) {
        Intrinsics.checkParameterIsNotNull(list, "cards");
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_manage_cards);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_manage_cards");
        button.setEnabled(!list.isEmpty());
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recyclerView");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "recyclerView");
        CardsAdapter cardsAdapter = new CardsAdapter();
        cardsAdapter.submitList(list);
        recyclerView2.setAdapter(cardsAdapter);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "recyclerView");
        CommonKt.clearDecorations(recyclerView3);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), 1);
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        dividerItemDecoration.setDrawable(ContextExtensionsKt.getDrawableResource(requireContext, R.drawable.item_decoration));
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recyclerView)).addItemDecoration(dividerItemDecoration);
    }

    public boolean onBackPressed() {
        PaymentMethodsPresenter paymentMethodsPresenter = this.presenter;
        if (paymentMethodsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        paymentMethodsPresenter.onBackPressed();
        return true;
    }
}
