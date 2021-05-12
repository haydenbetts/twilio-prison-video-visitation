package com.forasoft.homewavvisitor.ui.fragment.account;

import air.HomeWAV.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.extension.ContextKt;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.forasoft.homewavvisitor.navigation.OnBackButtonPressedListener;
import com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationsAdapter;
import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus;
import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationsPresenter;
import com.forasoft.homewavvisitor.presentation.view.account.NotificationsView;
import com.forasoft.homewavvisitor.ui.fragment.BaseFragment;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0016\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010 \u001a\u00020\rH\u0016J\u0010\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H\u0016J\u001a\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u001c2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010)\u001a\u00020\u0007H\u0007J\b\u0010*\u001a\u00020\rH\u0002J\b\u0010+\u001a\u00020\rH\u0002J\u0010\u0010,\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020#H\u0016R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006-"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/NotificationListFragment;", "Lcom/forasoft/homewavvisitor/ui/fragment/BaseFragment;", "Lcom/forasoft/homewavvisitor/presentation/view/account/NotificationsView;", "Lcom/forasoft/homewavvisitor/ui/fragment/account/ItemDismissListener;", "Lcom/forasoft/homewavvisitor/navigation/OnBackButtonPressedListener;", "()V", "presenter", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationsPresenter;", "getPresenter", "()Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationsPresenter;", "setPresenter", "(Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationsPresenter;)V", "displayNotifications", "", "notifications", "", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onItemDismissed", "position", "", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "providePresenter", "setupList", "setupToolbar", "updateToolbar", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationListFragment.kt */
public final class NotificationListFragment extends BaseFragment implements NotificationsView, ItemDismissListener, OnBackButtonPressedListener {
    private HashMap _$_findViewCache;
    @InjectPresenter
    public NotificationsPresenter presenter;

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

    public final NotificationsPresenter getPresenter() {
        NotificationsPresenter notificationsPresenter = this.presenter;
        if (notificationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        return notificationsPresenter;
    }

    public final void setPresenter(NotificationsPresenter notificationsPresenter) {
        Intrinsics.checkParameterIsNotNull(notificationsPresenter, "<set-?>");
        this.presenter = notificationsPresenter;
    }

    @ProvidePresenter
    public final NotificationsPresenter providePresenter() {
        Object instance = getScope().getInstance(NotificationsPresenter.class);
        Intrinsics.checkExpressionValueIsNotNull(instance, "scope.getInstance(Notifi…onsPresenter::class.java)");
        return (NotificationsPresenter) instance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        return layoutInflater.inflate(R.layout.fragment_notifications, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        setupList();
        setupToolbar();
        ContextKt.hideBottomNavigation(this);
        ((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.tv_dismiss_all)).setOnClickListener(new NotificationListFragment$onViewCreated$1(this));
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
        return onBackPressed();
    }

    public void onDestroyView() {
        ContextKt.showBottomNavigation(this);
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void displayNotifications(List<NotificationWithInmateStatus> list) {
        Intrinsics.checkParameterIsNotNull(list, "notifications");
        if (list.isEmpty()) {
            CommonKt.show((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
        } else {
            CommonKt.hide((FrameLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.fl_empty));
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_notifications);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_notifications");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            ((NotificationsAdapter) adapter).submitList(CollectionsKt.toMutableList(list));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationsAdapter");
    }

    public void updateToolbar(int i) {
        if (i == 0) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                ActionBar supportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
                if (supportActionBar != null) {
                    supportActionBar.setTitle((CharSequence) requireContext().getString(R.string.label_notifications));
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            ActionBar supportActionBar2 = ((AppCompatActivity) activity2).getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setTitle((CharSequence) requireContext().getString(R.string.label_notifications_unread, new Object[]{Integer.valueOf(i)}));
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }

    public void onItemDismissed(int i) {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_notifications);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_notifications");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            Notification notification = ((NotificationsAdapter) adapter).getItemAt(i).getNotification();
            NotificationsPresenter notificationsPresenter = this.presenter;
            if (notificationsPresenter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("presenter");
            }
            notificationsPresenter.onNotificationDismissed(notification);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.presentation.adapter.notifications.NotificationsAdapter");
    }

    public boolean onBackPressed() {
        NotificationsPresenter notificationsPresenter = this.presenter;
        if (notificationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        notificationsPresenter.onBackPressed();
        return true;
    }

    private final void setupList() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.rv_notifications);
        NotificationsPresenter notificationsPresenter = this.presenter;
        if (notificationsPresenter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("presenter");
        }
        recyclerView.setAdapter(new NotificationsAdapter(new NotificationListFragment$setupList$1$1(notificationsPresenter)));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), 1));
        new ItemTouchHelper(new DismissCallback(this)).attachToRecyclerView(recyclerView);
    }

    private final void setupToolbar() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
            ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle((CharSequence) requireContext().getString(R.string.label_notifications));
            }
            ActionBar supportActionBar2 = appCompatActivity.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.appcompat.app.AppCompatActivity");
    }
}
