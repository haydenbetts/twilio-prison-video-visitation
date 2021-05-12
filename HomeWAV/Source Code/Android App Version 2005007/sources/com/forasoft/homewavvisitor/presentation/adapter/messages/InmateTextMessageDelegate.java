package com.forasoft.homewavvisitor.presentation.adapter.messages;

import air.HomeWAV.R;
import android.view.ViewGroup;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.model.data.MessageType;
import com.forasoft.homewavvisitor.model.data.Sender;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0005¢\u0006\u0002\u0010\u0005J&\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J&\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\nH\u0014J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0014¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/InmateTextMessageDelegate;", "Lcom/hannesdorfmann/adapterdelegates4/AbsListItemAdapterDelegate;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "Lcom/forasoft/homewavvisitor/presentation/adapter/messages/InmateTextMessageVH;", "()V", "isForViewType", "", "item", "items", "", "position", "", "onBindViewHolder", "", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateTextMessageDelegate.kt */
public final class InmateTextMessageDelegate extends AbsListItemAdapterDelegate<Message, ChatItem, InmateTextMessageVH> {
    /* access modifiers changed from: protected */
    public InmateTextMessageVH onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new InmateTextMessageVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_inmate_text_message, false, 2, (Object) null));
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(Message message, InmateTextMessageVH inmateTextMessageVH, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(message, "item");
        Intrinsics.checkParameterIsNotNull(inmateTextMessageVH, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        inmateTextMessageVH.bind(message);
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
            if (sender == Sender.INMATE) {
                return true;
            }
        }
        return false;
    }
}
