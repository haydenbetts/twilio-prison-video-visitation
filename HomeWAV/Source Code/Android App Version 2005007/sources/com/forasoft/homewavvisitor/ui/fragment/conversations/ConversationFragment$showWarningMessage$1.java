package com.forasoft.homewavvisitor.ui.fragment.conversations;

import androidx.cardview.widget.CardView;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
final class ConversationFragment$showWarningMessage$1 implements Runnable {
    final /* synthetic */ ConversationFragment this$0;

    ConversationFragment$showWarningMessage$1(ConversationFragment conversationFragment) {
        this.this$0 = conversationFragment;
    }

    public final void run() {
        CommonKt.show((CardView) this.this$0._$_findCachedViewById(R.id.cv_warning));
        CommonKt.startBlinkingAnimation((CardView) this.this$0._$_findCachedViewById(R.id.cv_warning));
    }
}
