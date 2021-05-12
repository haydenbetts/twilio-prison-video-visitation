package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.google.android.material.snackbar.Snackbar;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/ConversationListFragment$showSuccessfulDeletionMessage$2", "Lcom/google/android/material/snackbar/Snackbar$Callback;", "onDismissed", "", "transientBottomBar", "Lcom/google/android/material/snackbar/Snackbar;", "event", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationListFragment.kt */
public final class ConversationListFragment$showSuccessfulDeletionMessage$2 extends Snackbar.Callback {
    final /* synthetic */ ConversationListFragment this$0;

    ConversationListFragment$showSuccessfulDeletionMessage$2(ConversationListFragment conversationListFragment) {
        this.this$0 = conversationListFragment;
    }

    public void onDismissed(Snackbar snackbar, int i) {
        this.this$0.getPresenter().onSnackbarDismissed();
    }
}
