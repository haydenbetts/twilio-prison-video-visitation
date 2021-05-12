package com.forasoft.homewavvisitor.presentation.adapter.account;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.model.data.account.HistoryItem;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.StringExtensionsKt;
import com.forasoft.homewavvisitor.toothpick.DI;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u001f B\u000f\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001aH\u0016R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter$ViewHolder;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "getAuthHolder", "()Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "setAuthHolder", "(Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "getInmateDao", "()Lcom/forasoft/homewavvisitor/dao/InmateDao;", "setInmateDao", "(Lcom/forasoft/homewavvisitor/dao/InmateDao;)V", "inmates", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "HISTORY_ITEM_COMPARATOR", "ViewHolder", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: AccountHistoryAdapter.kt */
public final class AccountHistoryAdapter extends ListAdapter<HistoryItem, ViewHolder> {
    @Inject
    public AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    @Inject
    public InmateDao inmateDao;
    /* access modifiers changed from: private */
    public List<Inmate> inmates;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Inject
    public AccountHistoryAdapter(HeartbeatRepository heartbeatRepository2) {
        super(HISTORY_ITEM_COMPARATOR.INSTANCE);
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.heartbeatRepository = heartbeatRepository2;
        Toothpick.inject(this, Toothpick.openScope(DI.SERVER_SCOPE));
        InmateDao inmateDao2 = this.inmateDao;
        if (inmateDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inmateDao");
        }
        AuthHolder authHolder2 = this.authHolder;
        if (authHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authHolder");
        }
        User user = authHolder2.getUser();
        String visitor_id = user != null ? user.getVisitor_id() : null;
        if (visitor_id == null) {
            Intrinsics.throwNpe();
        }
        CommonKt.applyAsync(inmateDao2.getApprovedInmates(visitor_id)).subscribe(new Consumer<List<? extends Inmate>>(this) {
            final /* synthetic */ AccountHistoryAdapter this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(List<Inmate> list) {
                List<Inmate> list2 = list;
                State value = this.this$0.heartbeatRepository.getHeartbeatState().getValue();
                Map<String, String> status = value != null ? value.getStatus() : null;
                AccountHistoryAdapter accountHistoryAdapter = this.this$0;
                Intrinsics.checkExpressionValueIsNotNull(list2, "it");
                Iterable<Inmate> iterable = list2;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (Inmate inmate : iterable) {
                    String str = status != null ? status.get(inmate.getId()) : null;
                    if (str != null) {
                        inmate = Inmate.copy$default(inmate, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, str, (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -131073, -1, 63, (Object) null);
                    }
                    arrayList.add(inmate);
                }
                accountHistoryAdapter.inmates = (List) arrayList;
            }
        });
    }

    public final InmateDao getInmateDao() {
        InmateDao inmateDao2 = this.inmateDao;
        if (inmateDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inmateDao");
        }
        return inmateDao2;
    }

    public final void setInmateDao(InmateDao inmateDao2) {
        Intrinsics.checkParameterIsNotNull(inmateDao2, "<set-?>");
        this.inmateDao = inmateDao2;
    }

    public final AuthHolder getAuthHolder() {
        AuthHolder authHolder2 = this.authHolder;
        if (authHolder2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authHolder");
        }
        return authHolder2;
    }

    public final void setAuthHolder(AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(authHolder2, "<set-?>");
        this.authHolder = authHolder2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_account_history, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return new ViewHolder(inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        HistoryItem historyItem = (HistoryItem) getItem(i);
        View view = viewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.textDate);
        Intrinsics.checkExpressionValueIsNotNull(textView, "itemView.textDate");
        textView.setText(historyItem.getDate());
        TextView textView2 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.textAmount);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "itemView.textAmount");
        textView2.setText(historyItem.getValue());
        TextView textView3 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.textAction);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "itemView.textAction");
        textView3.setText(historyItem.getNotes());
        CharSequence fullName = historyItem.getFullName();
        int i2 = 0;
        if (!(fullName == null || fullName.length() == 0)) {
            CommonKt.show((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle));
            CommonKt.show(view.findViewById(com.forasoft.homewavvisitor.R.id.viewStatus));
            try {
                ImageView imageView = (ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle);
                View view2 = viewHolder.itemView;
                Intrinsics.checkExpressionValueIsNotNull(view2, "holder.itemView");
                Context context = view2.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "holder.itemView.context");
                String fullName2 = historyItem.getFullName();
                if (fullName2 == null) {
                    Intrinsics.throwNpe();
                }
                imageView.setImageDrawable(ContextExtensionsKt.createTextDrawable$default(context, StringExtensionsKt.getAsInitials(fullName2), 0, 0, 0, 14, (Object) null));
                List<Inmate> list = this.inmates;
                if (list != null) {
                    if (list == null) {
                        Intrinsics.throwNpe();
                    }
                    for (Object next : list) {
                        Inmate inmate = (Inmate) next;
                        if (Intrinsics.areEqual((Object) inmate.getFirst_name() + ' ' + inmate.getLast_name(), (Object) historyItem.getFullName())) {
                            String status = ((Inmate) next).getStatus();
                            if (status != null) {
                                int hashCode = status.hashCode();
                                if (hashCode != 112785) {
                                    if (hashCode == 98619139) {
                                        if (status.equals("green")) {
                                            i2 = 2;
                                        }
                                    }
                                } else if (status.equals("red")) {
                                    i2 = 1;
                                }
                            }
                            ImageView imageView2 = (ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_status);
                            Intrinsics.checkExpressionValueIsNotNull(imageView2, "itemView.iv_status");
                            Drawable background = imageView2.getBackground();
                            if (background != null) {
                                ((LevelListDrawable) background).setLevel(i2);
                                return;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable");
                        }
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
            } catch (Exception unused) {
            }
        } else {
            ImageView imageView3 = (ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.imageAvatarCircle);
            Intrinsics.checkExpressionValueIsNotNull(imageView3, "itemView.imageAvatarCircle");
            imageView3.setVisibility(4);
            View findViewById = view.findViewById(com.forasoft.homewavvisitor.R.id.viewStatus);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "itemView.viewStatus");
            findViewById.setVisibility(4);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AccountHistoryAdapter.kt */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "view");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/account/AccountHistoryAdapter$HISTORY_ITEM_COMPARATOR;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/account/HistoryItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: AccountHistoryAdapter.kt */
    public static final class HISTORY_ITEM_COMPARATOR extends DiffUtil.ItemCallback<HistoryItem> {
        public static final HISTORY_ITEM_COMPARATOR INSTANCE = new HISTORY_ITEM_COMPARATOR();

        private HISTORY_ITEM_COMPARATOR() {
        }

        public boolean areItemsTheSame(HistoryItem historyItem, HistoryItem historyItem2) {
            Intrinsics.checkParameterIsNotNull(historyItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(historyItem2, "newItem");
            return historyItem.getTimestamp() == historyItem2.getTimestamp() && Intrinsics.areEqual((Object) historyItem.getNotes(), (Object) historyItem2.getNotes());
        }

        public boolean areContentsTheSame(HistoryItem historyItem, HistoryItem historyItem2) {
            Intrinsics.checkParameterIsNotNull(historyItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(historyItem2, "newItem");
            return Intrinsics.areEqual((Object) historyItem, (Object) historyItem2);
        }
    }
}
