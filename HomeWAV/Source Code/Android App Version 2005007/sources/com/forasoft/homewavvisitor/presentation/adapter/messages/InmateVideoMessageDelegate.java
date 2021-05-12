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
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\u0019\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J&\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0014J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016H\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/messages/InmateVideoMessageDelegate;", "Lcom/hannesdorfmann/adapterdelegates4/AbsListItemAdapterDelegate;", "Lcom/forasoft/homewavvisitor/model/data/Message;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "Lcom/forasoft/homewavvisitor/presentation/adapter/messages/InmateVideoMessageVH;", "onClickListener", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "isForViewType", "", "item", "items", "", "position", "", "onBindViewHolder", "holder", "payloads", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateVideoMessageDelegate.kt */
public final class InmateVideoMessageDelegate extends AbsListItemAdapterDelegate<Message, ChatItem, InmateVideoMessageVH> {
    private final Function1<Message, Unit> onClickListener;

    public InmateVideoMessageDelegate(Function1<? super Message, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.onClickListener = function1;
    }

    /* access modifiers changed from: protected */
    public InmateVideoMessageVH onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new InmateVideoMessageVH(ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_inmate_video_message, false, 2, (Object) null), this.onClickListener);
    }

    /* access modifiers changed from: protected */
    public void onBindViewHolder(Message message, InmateVideoMessageVH inmateVideoMessageVH, List<Object> list) {
        Intrinsics.checkParameterIsNotNull(message, "item");
        Intrinsics.checkParameterIsNotNull(inmateVideoMessageVH, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        inmateVideoMessageVH.bind(message);
    }

    /* access modifiers changed from: protected */
    public boolean isForViewType(ChatItem chatItem, List<ChatItem> list, int i) {
        MessageType type;
        Intrinsics.checkParameterIsNotNull(chatItem, "item");
        Intrinsics.checkParameterIsNotNull(list, "items");
        boolean z = chatItem instanceof Message;
        Sender sender = null;
        Message message = (Message) (!z ? null : chatItem);
        if (!(message == null || (type = message.getType()) == null || type.ordinal() != 1)) {
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
