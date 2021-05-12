package com.forasoft.homewavvisitor.presentation.adapter.messages;

import air.HomeWAV.R;
import android.view.ViewGroup;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageType;
import com.forasoft.homewavvisitor.model.data.Sender;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014J&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\fH\u0014J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/VisitorTextMessageDelegate;", "Lcom/hannesdorfmann/adapterdelegates4/AbsListItemAdapterDelegate;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "Lcom/forasoft/homewavvisitor/presentation/adapter/messages/VisitorTextMessageVH;", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "(Lcom/forasoft/homewavvisitor/model/data/auth/User;)V", "isForViewType", "", "item", "items", "", "position", "", "onBindViewHolder", "", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitorTextMessageDelegate.kt */
public final class VisitorTextMessageDelegate extends AbsListItemAdapterDelegate<Message, ChatItem, VisitorTextMessageVH> {
    private final User user;

    public VisitorTextMessageDelegate(User user2) {
        Intrinsics.checkParameterIsNotNull(user2, "user");
        this.user = user2;
    }

    /* access modifiers changed from: protected */
    public VisitorTextMessageVH onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new VisitorTextMessageVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_visitor_text_message, false, 2, (Object) null), this.user);
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(Message message, VisitorTextMessageVH visitorTextMessageVH, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(message, "item");
        Intrinsics.checkParameterIsNotNull(visitorTextMessageVH, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        visitorTextMessageVH.bind(message);
    }

    /* access modifiers changed from: protected */
    public boolean isForViewType(ChatItem chatItem, List<ChatItem> list, int i) {
        MessageType type;
        Intrinsics.checkParameterIsNotNull(chatItem, "item");
        Intrinsics.checkParameterIsNotNull(list, "items");
        boolean z = chatItem instanceof Message;
        Sender sender = null;
        Message message = (Message) (!z ? null : chatItem);
        if (!(message == null || (type = message.getType()) == null || type.ordinal() != 0)) {
            if (!z) {
                chatItem = null;
            }
            Message message2 = (Message) chatItem;
            if (message2 != null) {
                sender = message2.getSender();
            }
            if (sender == Sender.VISITOR) {
                return true;
            }
        }
        return false;
    }
}
