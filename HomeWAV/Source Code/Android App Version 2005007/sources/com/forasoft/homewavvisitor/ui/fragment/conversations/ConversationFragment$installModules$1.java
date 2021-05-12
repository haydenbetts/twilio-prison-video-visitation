package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.os.Bundle;
import com.forasoft.homewavvisitor.toothpick.qualifier.InmateId;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/conversations/ConversationFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
public final class ConversationFragment$installModules$1 extends Module {
    final /* synthetic */ ConversationFragment this$0;

    ConversationFragment$installModules$1(ConversationFragment conversationFragment) {
        this.this$0 = conversationFragment;
        Binding<T>.CanBeBound withName = bind(String.class).withName(InmateId.class);
        Bundle arguments = conversationFragment.getArguments();
        withName.toInstance(arguments != null ? arguments.getString("inmate id") : null);
    }
}
