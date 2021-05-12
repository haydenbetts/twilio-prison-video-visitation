package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.account.AccountHistoryAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.account.HistoryPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.HistoryView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.ui.ScrollToTopOnDataChangeObserver;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import com.forasoft.homewavvisitor.ui.views.TernaryView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Typography;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010\u0018\u001a\u00020\u00152\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cH\u0016J \u0010\u001d\u001a\u00020\u00152\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cH\u0016J \u0010\u001f\u001a\u00020\u00152\u0016\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001cH\u0016J\b\u0010!\u001a\u00020\u0015H\u0002J\b\u0010\"\u001a\u00020#H\u0016J\u0012\u0010$\u001a\u00020\u00152\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J,\u0010'\u001a\n )*\u0004\u0018\u00010(0(2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010.\u001a\u00020\u0015H\u0016J\u0010\u0010/\u001a\u00020#2\u0006\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u00020\u0015H\u0016J\b\u00103\u001a\u00020\u0015H\u0016J\u001a\u00104\u001a\u00020\u00152\u0006\u00105\u001a\u00020(2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u00106\u001a\u00020\fH\u0007J\b\u00107\u001a\u00020\u0015H\u0016J\b\u00108\u001a\u00020\u0015H\u0016J\b\u00109\u001a\u00020\u0015H\u0016J\b\u0010:\u001a\u00020\u0015H\u0016J\u0010\u0010;\u001a\u00020\u00152\u0006\u0010<\u001a\u00020\u0017H\u0016J\u0010\u0010=\u001a\u00020\u00152\u0006\u0010<\u001a\u00020\u0017H\u0016J\u0010\u0010>\u001a\u00020\u00152\u0006\u0010<\u001a\u00020\u0017H\u0016J\u0010\u0010?\u001a\u00020\u00152\u0006\u0010@\u001a\u00020AH\u0016J\u0016\u0010B\u001a\u00020\u00152\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u0012H\u0016J\u0010\u0010E\u001a\u00020\u00152\u0006\u0010F\u001a\u00020\u001bH\u0016R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X.¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/HistoryFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/HistoryView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "accountHistoryAdapter", "Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter;", "getAccountHistoryAdapter", "()Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter;", "accountHistoryAdapter$delegate", "Lkotlin/Lazy;", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/HistoryPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/HistoryPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/HistoryPresenter;)V", "wrappers", "", "Lcom/forasoft/homewavvisitor/ui/views/TernaryView;", "highlightTernary", "", "index", "", "initInmatesSpinner", "inmatesList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "initOrderSpinner", "historyOrderList", "initPeriodSpinner", "historyPeriodList", "initWrappers", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "kotlin.jvm.PlatformType", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPause", "onResume", "onViewCreated", "view", "providePresenter", "selectActivities", "selectCalls", "selectMessages", "selectMoney", "setActivitiesCount", "count", "setCallsCount", "setMessagesCount", "setMoneyAmount", "amount", "", "showData", "body", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "showPeriod", "text", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HistoryFragment.kt */
public final class HistoryFragment extends BaseFragment implements HistoryView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    private final Lazy accountHistoryAdapter$delegate = LazyKt.lazy(HistoryFragment$accountHistoryAdapter$2.INSTANCE);
    @InjectPresenter
    public HistoryPresenter presenter;
    private List<TernaryView> wrappers;

    private final AccountHistoryAdapter getAccountHistoryAdapter() {
        return (AccountHistoryAdapter) this.accountHistoryAdapter$delegate.getValue();
    }

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

    public final HistoryPresenter getPresenter() {
        HistoryPresenter historyPresenter = this.presenter;
        if (historyPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return historyPresenter;
    }

    public final void setPresenter(HistoryPresenter historyPresenter) {
        Intrinsics.checkParameterIsNotNull(historyPresenter, "<set-?>");
        this.presenter = historyPresenter;
    }

    @ProvidePresenter
    public final HistoryPresenter providePresenter() {
        Object instance = Toothpick.openScope(DI.SERVER_SCOPE).getInstance(HistoryPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "Toothpick\n              …oryPresenter::class.java)");
        return (HistoryPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_history, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recycler);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "recycler");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recycler);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "recycler");
        recyclerView2.setAdapter(getAccountHistoryAdapter());
        ((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recycler)).addItemDecoration(new DividerItemDecoration(getContext(), 1));
        HistoryPresenter historyPresenter = this.presenter;
        if (historyPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        historyPresenter.getNotificationsCount();
        ContextKt.hideBottomNavigation(this);
        initWrappers();
        AccountHistoryAdapter accountHistoryAdapter = getAccountHistoryAdapter();
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.recycler);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "recycler");
        accountHistoryAdapter.registerAdapterDataObserver(new ScrollToTopOnDataChangeObserver(recyclerView3, getAccountHistoryAdapter()));
    }

    private final void initWrappers() {
        this.wrappers = CollectionsKt.arrayListOf((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryActivities), (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMessages), (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryCalls), (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMoneyAdded));
        TernaryView ternaryView = (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryActivities);
        Intrinsics.checkExpressionValueIsNotNull(ternaryView, "ternaryActivities");
        ternaryView.setOnClickListener(new HistoryFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HistoryFragment$initWrappers$1(this)));
        TernaryView ternaryView2 = (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMessages);
        Intrinsics.checkExpressionValueIsNotNull(ternaryView2, "ternaryMessages");
        ternaryView2.setOnClickListener(new HistoryFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HistoryFragment$initWrappers$2(this)));
        TernaryView ternaryView3 = (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryCalls);
        Intrinsics.checkExpressionValueIsNotNull(ternaryView3, "ternaryCalls");
        ternaryView3.setOnClickListener(new HistoryFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HistoryFragment$initWrappers$3(this)));
        TernaryView ternaryView4 = (TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMoneyAdded);
        Intrinsics.checkExpressionValueIsNotNull(ternaryView4, "ternaryMoneyAdded");
        ternaryView4.setOnClickListener(new HistoryFragment$inlined$sam$i$android_view_View_OnClickListener$0(new HistoryFragment$initWrappers$4(this)));
    }

    public void onResume() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_account_history);
            }
            ContextKt.showActionBack(this);
            super.onResume();
            Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinnerPeriod);
            Intrinsics.checkExpressionValueIsNotNull(spinner, "spinnerPeriod");
            CommonKt.onItemSelected(spinner, new HistoryFragment$onResume$1(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onPause() {
        super.onPause();
        ContextKt.hideActionBack(this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        ContextKt.showBottomNavigation(this);
        _$_clearFindViewByIdCache();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() == R.id.action_notifications) {
            HistoryPresenter historyPresenter = this.presenter;
            if (historyPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            return historyPresenter.onNotificationsClicked();
        } else if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            HistoryPresenter historyPresenter2 = this.presenter;
            if (historyPresenter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            return historyPresenter2.onBackPressed();
        }
    }

    public void showData(List<HistoryItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "body");
        getAccountHistoryAdapter().submitList(list);
    }

    public void initPeriodSpinner(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "historyPeriodList");
        Spinner spinner = (Spinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinnerPeriod);
        Intrinsics.checkExpressionValueIsNotNull(spinner, "spinnerPeriod");
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.item_spinner_big, arrayList);
        arrayAdapter.setDropDownViewResource(R.layout.item_spinner_big);
        spinner.setAdapter(arrayAdapter);
    }

    public void initOrderSpinner(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "historyOrderList");
        AppCompatSpinner appCompatSpinner = (AppCompatSpinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinnerOrder);
        Intrinsics.checkExpressionValueIsNotNull(appCompatSpinner, "spinnerOrder");
        appCompatSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(requireContext(), R.layout.item_spinner, arrayList));
        AppCompatSpinner appCompatSpinner2 = (AppCompatSpinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinnerOrder);
        Intrinsics.checkExpressionValueIsNotNull(appCompatSpinner2, "spinnerOrder");
        CommonKt.onItemSelected(appCompatSpinner2, new HistoryFragment$initOrderSpinner$1(this));
    }

    public void initInmatesSpinner(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "inmatesList");
        AppCompatSpinner appCompatSpinner = (AppCompatSpinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinnerInmate);
        Intrinsics.checkExpressionValueIsNotNull(appCompatSpinner, "spinnerInmate");
        appCompatSpinner.setAdapter((SpinnerAdapter) new ArrayAdapter(requireContext(), R.layout.item_spinner, arrayList));
        AppCompatSpinner appCompatSpinner2 = (AppCompatSpinner) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.spinnerInmate);
        Intrinsics.checkExpressionValueIsNotNull(appCompatSpinner2, "spinnerInmate");
        CommonKt.onItemSelected(appCompatSpinner2, new HistoryFragment$initInmatesSpinner$1(this));
    }

    public void selectActivities() {
        highlightTernary(0);
    }

    public void selectMessages() {
        highlightTernary(1);
    }

    public void selectCalls() {
        highlightTernary(2);
    }

    public void selectMoney() {
        highlightTernary(3);
    }

    private final void highlightTernary(int i) {
        List<TernaryView> list = this.wrappers;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wrappers");
        }
        for (TernaryView highlighted : list) {
            highlighted.setHighlighted(false);
        }
        List<TernaryView> list2 = this.wrappers;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("wrappers");
        }
        list2.get(i).setHighlighted(true);
    }

    public void setActivitiesCount(int i) {
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryActivities)).setCaption(String.valueOf(i));
    }

    public void setMessagesCount(int i) {
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMessages)).setCaption(String.valueOf(i));
    }

    public void setCallsCount(int i) {
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryCalls)).setCaption(String.valueOf(i));
    }

    public void setMoneyAmount(float f) {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.0f", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        sb.append(format);
        ((TernaryView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.ternaryMoneyAdded)).setCaption(sb.toString());
    }

    public void showPeriod(String str) {
        Intrinsics.checkParameterIsNotNull(str, "text");
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.textPeriod);
        Intrinsics.checkExpressionValueIsNotNull(textView, "textPeriod");
        textView.setText(str);
    }

    public boolean onBackPressed() {
        HistoryPresenter historyPresenter = this.presenter;
        if (historyPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return historyPresenter.onBackPressed();
    }
}
