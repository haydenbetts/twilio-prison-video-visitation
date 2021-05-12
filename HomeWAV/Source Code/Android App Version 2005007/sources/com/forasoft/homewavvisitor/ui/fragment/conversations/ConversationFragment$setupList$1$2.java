package com.forasoft.homewavvisitor.ui.fragment.conversations;

import com.forasoft.homewavvisitor.model.data.Message;
import com.forasoft.homewavvisitor.presentation.presenter.conversations.ConversationPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/data/Message;", "Lkotlin/ParameterName;", "name", "message", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ConversationFragment.kt */
final /* synthetic */ class ConversationFragment$setupList$1$2 extends FunctionReference implements Function1<Message, Unit> {
    ConversationFragment$setupList$1$2(ConversationPresenter conversationPresenter) {
        super(1, conversationPresenter);
    }

    public final String getName() {
        return "onVideoPreviewClicked";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ConversationPresenter.class);
    }

    public final String getSignature() {
        return "onVideoPreviewClicked(Lcom/forasoft/homewavvisitor/model/data/Message;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Message) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Message message) {
        Intrinsics.checkParameterIsNotNull(message, "p1");
        ((ConversationPresenter) this.receiver).onVideoPreviewClicked(message);
    }
}
