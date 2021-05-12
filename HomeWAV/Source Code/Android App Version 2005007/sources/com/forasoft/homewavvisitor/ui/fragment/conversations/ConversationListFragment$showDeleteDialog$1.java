package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.content.DialogInterface;
import com.forasoft.homewavvisitor.model.data.Inmate;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationListFragment.kt */
final class ConversationListFragment$showDeleteDialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ Inmate $inmate;
    final /* synthetic */ ConversationListFragment this$0;

    ConversationListFragment$showDeleteDialog$1(ConversationListFragment conversationListFragment, Inmate inmate) {
        this.this$0 = conversationListFragment;
        this.$inmate = inmate;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.getPresenter().onDeleteChatClicked(this.$inmate.getId());
        dialogInterface.dismiss();
    }
}
