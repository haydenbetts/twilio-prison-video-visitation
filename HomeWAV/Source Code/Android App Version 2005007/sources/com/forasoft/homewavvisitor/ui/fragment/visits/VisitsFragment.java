package com.forasoft.homewavvisitor.ui.fragment.visits;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.visits.VisitsAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.visits.VisitsPresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitsView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0016\u0010\u0010\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J,\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\fH\u0016J\u001a\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010$\u001a\u00020\u0006H\u0007J\b\u0010%\u001a\u00020\fH\u0002R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/visits/VisitsFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/VisitsView;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitsPresenter;)V", "displayPendingVisits", "", "visits", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "displayScheduledVisits", "initLists", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "kotlin.jvm.PlatformType", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "onViewCreated", "view", "providePresenter", "showEmptyView", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitsFragment.kt */
public final class VisitsFragment extends BaseFragment implements VisitsView, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public VisitsPresenter presenter;

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

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final VisitsPresenter getPresenter() {
        VisitsPresenter visitsPresenter = this.presenter;
        if (visitsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return visitsPresenter;
    }

    public final void setPresenter(VisitsPresenter visitsPresenter) {
        Intrinsics.checkParameterIsNotNull(visitsPresenter, "<set-?>");
        this.presenter = visitsPresenter;
    }

    @ProvidePresenter
    public final VisitsPresenter providePresenter() {
        Object instance = getScope().getInstance(VisitsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(VisitsPresenter::class.java)");
        return (VisitsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        setHasNotificationMenu(true);
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_visits, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        initLists();
        VisitsPresenter visitsPresenter = this.presenter;
        if (visitsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        visitsPresenter.getNotificationsCount();
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_schedule);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_schedule");
        button.setOnClickListener(new VisitsFragment$inlined$sam$i$android_view_View_OnClickListener$0(new VisitsFragment$onViewCreated$1(this)));
    }

    public void onResume() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((int) R.string.label_visits);
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
            }
            VisitsPresenter visitsPresenter = this.presenter;
            if (visitsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            visitsPresenter.getVisits();
            super.onResume();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != R.id.action_notifications) {
            return super.onOptionsItemSelected(menuItem);
        }
        VisitsPresenter visitsPresenter = this.presenter;
        if (visitsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        visitsPresenter.onNotificationsClicked();
        return true;
    }

    public boolean onBackPressed() {
        VisitsPresenter visitsPresenter = this.presenter;
        if (visitsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return visitsPresenter.onBackPressed();
    }

    public void displayScheduledVisits(List<ScheduledVisit> list) {
        Intrinsics.checkParameterIsNotNull(list, "visits");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_scheduled_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_scheduled_visits");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((VisitsAdapter) adapter).submitList(list);
            if (list.isEmpty()) {
                CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_scheduled));
                CommonKt.hide((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_scheduled_visits));
                showEmptyView();
                return;
            }
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_scheduled));
            CommonKt.show((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_scheduled_visits));
            CommonKt.hide((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.visits.VisitsAdapter");
    }

    public void displayPendingVisits(List<ScheduledVisit> list) {
        Intrinsics.checkParameterIsNotNull(list, "visits");
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_pending_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_pending_visits");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((VisitsAdapter) adapter).submitList(list);
            if (list.isEmpty()) {
                CommonKt.hide((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_pending));
                CommonKt.hide((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_pending_visits));
                showEmptyView();
                return;
            }
            CommonKt.show((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_pending));
            CommonKt.show((RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_pending_visits));
            CommonKt.hide((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.visits.VisitsAdapter");
    }

    private final void showEmptyView() {
        TextView textView = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_scheduled);
        Intrinsics.checkExpressionValueIsNotNull(textView, "tv_scheduled");
        if (!CommonKt.isVisible(textView)) {
            TextView textView2 = (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_pending);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_pending");
            if (!CommonKt.isVisible(textView2)) {
                CommonKt.show((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
            }
        }
    }

    private final void initLists() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_scheduled_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_scheduled_visits");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_scheduled_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rv_scheduled_visits");
        VisitsPresenter visitsPresenter = this.presenter;
        if (visitsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView2.setAdapter(new VisitsAdapter(new VisitsFragment$initLists$1(visitsPresenter)));
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_scheduled_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "rv_scheduled_visits");
        recyclerView3.setNestedScrollingEnabled(false);
        RecyclerView recyclerView4 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_pending_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView4, "rv_pending_visits");
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        RecyclerView recyclerView5 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_pending_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView5, "rv_pending_visits");
        VisitsPresenter visitsPresenter2 = this.presenter;
        if (visitsPresenter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView5.setAdapter(new VisitsAdapter(new VisitsFragment$initLists$2(visitsPresenter2)));
        RecyclerView recyclerView6 = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_pending_visits);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView6, "rv_pending_visits");
        recyclerView6.setNestedScrollingEnabled(false);
    }
}
