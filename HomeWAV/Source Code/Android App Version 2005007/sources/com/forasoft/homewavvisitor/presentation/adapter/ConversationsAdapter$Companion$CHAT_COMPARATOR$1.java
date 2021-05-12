package com.forasoft.homewavvisitor.presentation.adapter;

import androidx.recyclerview.widget.DiffUtil;
import com.forasoft.homewavvisitor.model.data.Chat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"com/forasoft/homewavvisitor/presentation/adapter/ConversationsAdapter$Companion$CHAT_COMPARATOR$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/Chat;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationsAdapter.kt */
public final class ConversationsAdapter$Companion$CHAT_COMPARATOR$1 extends DiffUtil.ItemCallback<Chat> {
    ConversationsAdapter$Companion$CHAT_COMPARATOR$1() {
    }

    public boolean areContentsTheSame(Chat chat, Chat chat2) {
        Intrinsics.checkParameterIsNotNull(chat, "oldItem");
        Intrinsics.checkParameterIsNotNull(chat2, "newItem");
        return Intrinsics.areEqual((Object) chat, (Object) chat2);
    }

    public boolean areItemsTheSame(Chat chat, Chat chat2) {
        Intrinsics.checkParameterIsNotNull(chat, "oldItem");
        Intrinsics.checkParameterIsNotNull(chat2, "newItem");
        return Intrinsics.areEqual((Object) chat.getInmate().getId(), (Object) chat2.getInmate().getId());
    }
}
