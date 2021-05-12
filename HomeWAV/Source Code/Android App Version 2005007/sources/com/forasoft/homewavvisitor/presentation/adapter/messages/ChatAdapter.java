package com.forasoft.homewavvisitor.presentation.adapter.messages;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.ChatNotification;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 '2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001'B_\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\b\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\n0\b\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\u000f¢\u0006\u0002\u0010\u0010J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00190\u001cJ(\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\t2\u000e\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010#0\"H\u0016J\u0016\u0010$\u001a\u00020\n2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020\u001cH\u0016J\u0006\u0010&\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0012@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0018j\b\u0012\u0004\u0012\u00020\u0019`\u001aX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/ChatAdapter;", "Lcom/hannesdorfmann/adapterdelegates4/AsyncListDifferDelegationAdapter;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "context", "Landroid/content/Context;", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onMessageLongClickListener", "Lkotlin/Function1;", "", "", "onVideoMessageClickListener", "Lcom/forasoft/homewavvisitor/model/data/Message;", "onImageMessageClickListener", "onAddFundsClickListener", "Lkotlin/Function0;", "(Landroid/content/Context;Lcom/forasoft/homewavvisitor/model/data/auth/User;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "value", "", "isTextMessagesEnabled", "()Z", "setTextMessagesEnabled", "(Z)V", "selectedItems", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getSelectedMessages", "", "onBindViewHolder", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "position", "payloads", "", "", "setItems", "items", "unselectMessages", "ChatItemCallback", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ChatAdapter.kt */
public final class ChatAdapter extends AsyncListDifferDelegationAdapter<ChatItem> {
    public static final ChatItemCallback ChatItemCallback = new ChatItemCallback((DefaultConstructorMarker) null);
    private final Context context;
    private boolean isTextMessagesEnabled = true;
    /* access modifiers changed from: private */
    public final Function1<Integer, Unit> onMessageLongClickListener;
    /* access modifiers changed from: private */
    public final HashSet<String> selectedItems = new HashSet<>();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ChatAdapter(Context context2, User user, Function1<? super Integer, Unit> function1, Function1<? super Message, Unit> function12, Function1<? super Message, Unit> function13, Function0<Unit> function0) {
        super(ChatItemCallback);
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(user, "user");
        Intrinsics.checkParameterIsNotNull(function1, "onMessageLongClickListener");
        Intrinsics.checkParameterIsNotNull(function12, "onVideoMessageClickListener");
        Intrinsics.checkParameterIsNotNull(function13, "onImageMessageClickListener");
        Intrinsics.checkParameterIsNotNull(function0, "onAddFundsClickListener");
        this.context = context2;
        this.onMessageLongClickListener = function1;
        this.delegatesManager.addDelegate(new InmateTextMessageDelegate()).addDelegate(new ChatNotificationDelegate()).addDelegate(new InmateVideoMessageDelegate(function12)).addDelegate(new InmateImageMessageDelegate(function13)).addDelegate(new InmateGifMessageDelegate(function13)).addDelegate(new VisitorTextMessageDelegate(user)).addDelegate(new VisitorVideoMessageDelegate(user, function12)).addDelegate(new VisitorImageMessageDelegate(user, function13)).addDelegate(new VisitorGifMessageDelegate(user, function13)).addDelegate(new CallStartedDelegate(user)).addDelegate(new CallEndedDelegate(user)).addDelegate(new LowBalanceDelegate(function0));
    }

    public final boolean isTextMessagesEnabled() {
        return this.isTextMessagesEnabled;
    }

    public final void setTextMessagesEnabled(boolean z) {
        this.isTextMessagesEnabled = z;
        setItems(getItems());
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        Drawable drawable;
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        super.onBindViewHolder(viewHolder, i, list);
        View view = viewHolder.itemView;
        ChatItem chatItem = (ChatItem) getItems().get(i);
        if (chatItem instanceof Message) {
            String id = ((Message) chatItem).getId();
            if (this.selectedItems.contains(id)) {
                Context context2 = view.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                drawable = ContextExtensionsKt.getDrawableResource(context2, R.drawable.bg_light_green);
            } else {
                Context context3 = view.getContext();
                Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                drawable = ContextExtensionsKt.getDrawableResource(context3, R.drawable.bg_white);
            }
            view.setBackground(drawable);
            view.setOnLongClickListener(new ChatAdapter$inlined$sam$i$android_view_View_OnLongClickListener$0(new ChatAdapter$onBindViewHolder$$inlined$with$lambda$1(view, id, this, i)));
        }
    }

    public final List<String> getSelectedMessages() {
        return CollectionsKt.toList(this.selectedItems);
    }

    public final void unselectMessages() {
        List items = getItems();
        Intrinsics.checkExpressionValueIsNotNull(items, "items");
        int i = 0;
        for (Object next : items) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ChatItem chatItem = (ChatItem) next;
            if ((chatItem instanceof Message) && this.selectedItems.contains(((Message) chatItem).getId())) {
                notifyItemChanged(i);
            }
            i = i2;
        }
        this.selectedItems.clear();
    }

    public void setItems(List<? extends ChatItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "items");
        if (!this.isTextMessagesEnabled) {
            String string = this.context.getString(R.string.text_messages_disabled);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.text_messages_disabled)");
            super.setItems(CollectionsKt.plus(CollectionsKt.listOf(new ChatNotification(string)), list));
            return;
        }
        super.setItems(list);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/ChatAdapter$ChatItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ChatAdapter.kt */
    public static final class ChatItemCallback extends DiffUtil.ItemCallback<ChatItem> {
        private ChatItemCallback() {
        }

        public /* synthetic */ ChatItemCallback(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public boolean areContentsTheSame(ChatItem chatItem, ChatItem chatItem2) {
            Intrinsics.checkParameterIsNotNull(chatItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(chatItem2, "newItem");
            return Intrinsics.areEqual((Object) chatItem, (Object) chatItem2);
        }

        public boolean areItemsTheSame(ChatItem chatItem, ChatItem chatItem2) {
            Intrinsics.checkParameterIsNotNull(chatItem, "oldItem");
            Intrinsics.checkParameterIsNotNull(chatItem2, "newItem");
            if ((chatItem instanceof Message) && (chatItem2 instanceof Message)) {
                return Intrinsics.areEqual((Object) ((Message) chatItem).getId(), (Object) ((Message) chatItem2).getId());
            }
            if (!(chatItem instanceof Notification) || !(chatItem2 instanceof Notification) || ((Notification) chatItem).getId() != ((Notification) chatItem2).getId()) {
                return false;
            }
            return true;
        }
    }
}
