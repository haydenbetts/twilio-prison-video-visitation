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
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B!\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014J&\u0010\u0012\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0014J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0014R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/VisitorImageMessageDelegate;", "Lcom/hannesdorfmann/adapterdelegates4/AbsListItemAdapterDelegate;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "Lcom/forasoft/homewavvisitor/presentation/adapter/messages/VisitorImageMessageVH;", "user", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "onClickListener", "Lkotlin/Function1;", "", "(Lcom/forasoft/homewavvisitor/model/data/auth/User;Lkotlin/jvm/functions/Function1;)V", "isForViewType", "", "item", "items", "", "position", "", "onBindViewHolder", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitorImageMessageDelegate.kt */
public final class VisitorImageMessageDelegate extends AbsListItemAdapterDelegate<Message, ChatItem, VisitorImageMessageVH> {
    private final Function1<Message, Unit> onClickListener;
    private final User user;

    public VisitorImageMessageDelegate(User user2, Function1<? super Message, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(user2, "user");
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.user = user2;
        this.onClickListener = function1;
    }

    /* access modifiers changed from: protected */
    public VisitorImageMessageVH onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new VisitorImageMessageVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_visitor_image_message, false, 2, (Object) null), this.user, this.onClickListener);
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(Message message, VisitorImageMessageVH visitorImageMessageVH, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(message, "item");
        Intrinsics.checkParameterIsNotNull(visitorImageMessageVH, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        visitorImageMessageVH.bind(message);
    }

    /* access modifiers changed from: protected */
    public boolean isForViewType(ChatItem chatItem, List<ChatItem> list, int i) {
        MessageType type;
        Intrinsics.checkParameterIsNotNull(chatItem, "item");
        Intrinsics.checkParameterIsNotNull(list, "items");
        boolean z = chatItem instanceof Message;
        Sender sender = null;
        Message message = (Message) (!z ? null : chatItem);
        if (!(message == null || (type = message.getType()) == null || type.ordinal() != 2)) {
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
