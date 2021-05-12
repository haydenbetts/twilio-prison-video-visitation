package com.forasoft.homewavvisitor.presentation.adapter.notifications;

import air.HomeWAV.R;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0016B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\rH\u0016J\u0018\u0010\u0014\u001a\u00020\u00072\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\nH\u0016R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/notifications/NotificationsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "Lcom/forasoft/homewavvisitor/presentation/adapter/notifications/NotificationVH;", "onAddFundsClickListener", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "items", "", "getItemAt", "position", "", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationsAdapter.kt */
public final class NotificationsAdapter extends ListAdapter<NotificationWithInmateStatus, NotificationVH> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final DiffUtil.ItemCallback<NotificationWithInmateStatus> NOTIFICATION_COMPARATOR = new NotificationsAdapter$Companion$NOTIFICATION_COMPARATOR$1();
    private final List<NotificationWithInmateStatus> items = new ArrayList();
    private final Function1<String, Unit> onAddFundsClickListener;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationsAdapter(Function1<? super String, Unit> function1) {
        super(NOTIFICATION_COMPARATOR);
        Intrinsics.checkParameterIsNotNull(function1, "onAddFundsClickListener");
        this.onAddFundsClickListener = function1;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/notifications/NotificationsAdapter$Companion;", "", "()V", "NOTIFICATION_COMPARATOR", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "getNOTIFICATION_COMPARATOR", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: NotificationsAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DiffUtil.ItemCallback<NotificationWithInmateStatus> getNOTIFICATION_COMPARATOR() {
            return NotificationsAdapter.NOTIFICATION_COMPARATOR;
        }
    }

    public NotificationVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new NotificationVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_notification, false, 2, (Object) null), this.onAddFundsClickListener);
    }

    public void onBindViewHolder(NotificationVH notificationVH, int i) {
        Intrinsics.checkParameterIsNotNull(notificationVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        notificationVH.bindNotification((NotificationWithInmateStatus) item);
    }

    public void submitList(List<NotificationWithInmateStatus> list) {
        this.items.clear();
        this.items.addAll(list != null ? list : CollectionsKt.emptyList());
        super.submitList(list);
    }

    public final NotificationWithInmateStatus getItemAt(int i) {
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        return (NotificationWithInmateStatus) item;
    }
}
