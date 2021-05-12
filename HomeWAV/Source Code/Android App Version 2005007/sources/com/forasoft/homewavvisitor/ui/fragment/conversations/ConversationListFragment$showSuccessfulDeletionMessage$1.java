package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationListFragment.kt */
final class ConversationListFragment$showSuccessfulDeletionMessage$1 implements View.OnClickListener {
    final /* synthetic */ ConversationListFragment this$0;

    ConversationListFragment$showSuccessfulDeletionMessage$1(ConversationListFragment conversationListFragment) {
        this.this$0 = conversationListFragment;
    }

    public final void onClick(View view) {
        this.this$0.getPresenter().onUndoClicked();
    }
}
