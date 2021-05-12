package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
final /* synthetic */ class ConversationFragment$setupList$1$4 extends FunctionReference implements Function0<Unit> {
    ConversationFragment$setupList$1$4(ConversationPresenter conversationPresenter) {
        super(0, conversationPresenter);
    }

    public final String getName() {
        return "onAddFundsClicked";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ConversationPresenter.class);
    }

    public final String getSignature() {
        return "onAddFundsClicked()V";
    }

    public final void invoke() {
        ((ConversationPresenter) this.receiver).onAddFundsClicked();
    }
}
